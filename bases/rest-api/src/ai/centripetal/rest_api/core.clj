(ns ai.centripetal.rest-api.core
  (:require
    [ai.centripetal.alien-vault-otx-json.interface :as otx]
    [ai.centripetal.app-state.interface :refer [make-app-state]]
    [ai.centripetal.database.interface :refer [make-database]]
    [ai.centripetal.web-server.interface :refer [make-web-server]]
    [com.stuartsierra.component :as component]
    [compojure.coercions :refer [as-int]]
    [compojure.core :refer [GET let-routes]]
    [compojure.route :as route]
    [ring.logger.timbre :as logger]
    [ring.middleware.defaults :as ring-defaults])
  (:gen-class))

;;; Borrowed liberally from both:
;;;
;;; For a more complete, realworld example, with more use of Ring
;;; middleware, etc.:
;;; https://github.com/furkan3ayraktar/clojure-polylith-realworld-example-app
;;;
;;; For the use of Stuart Sierra's components:
;;; https://github.com/seancorfield/usermanager-example

;; Helper for building the middleware
(defn wrap-app-state
  "Middleware to add your app-state component into the request. Use
  the same qualified keyword in your controller to retrieve it."
  [handler app-state]
  (fn [request]
    (handler (assoc request :app-state/component app-state))))

;; This is Ring-specific, the specific stack of middleware you need
;; for your application. This example uses a fairly standard stack of
;; Ring middleware with some tweaks for convenience
(defn middleware-stack
  "Given the app-state component and middleware, return a standard stack of
  Ring middleware for a web application."
  [app-state]
  (fn [handler]
    (-> handler
        logger/wrap-with-logger
        (wrap-app-state app-state)
        (ring-defaults/wrap-defaults
         (-> ring-defaults/api-defaults
             ;; disable XSRF for now
             (assoc-in [:security :anti-forgery] false)
             ;; support load balancers
             (assoc-in [:proxy] true))))))

(defn get-indicators [request])

(defn get-indicators--id [request])

;; This is the main web handler, that builds routing middleware from
;; the app-state component. The handler is passed into the web-server
;; component.
;;
;; Note that Vars are used -- the #' notation -- instead of bare
;; symbols to make REPL-driven development easier. See the following
;; for details:
;;
;; https://clojure.org/guides/repl/
;; ...enhancing_your_repl_workflow#writing-repl-friendly-programs
;;
;; This is somewhere, I think, the use of Stuart Sierra's components
;; complicates things a bit (e.g. see doc string about let-routes vs
;; defroutes) and I wonder if it makes as much sense to do this in
;; Polylith.

;!zprint {:format :skip}
(defn app-handler
  "Given the app-state component, return middleware for routing.

  We use let-routes here rather than the more usual defroutes because
  Compojure assumes that if there's a match on the route, the entire
  request will be handled by the function specified for that route.

  Since we need to pass in the app-state component at start up, we
  need to define our route handlers so that they can be
  parameterized."
  [app-state]
  (let-routes [wrap (middleware-stack app-state)]
    (GET "/indicators"             [type]
      (wrap #'get-indicators))
    (GET "/indicators/:id{[0-9]+}" [id :<< as-int]
      (wrap #'get-indicators--id))
    (route/resources "/")
    (route/not-found "Not Found")))

;!zprint {:format :skip}
(defn make-system
  ([port] (let [repl true] (make-system port repl)))
  ([port repl]
   (component/system-map
    :app-state  (make-app-state {:repl repl})
    :database   (let [db-spec nil
                      init-fn nil]
                  (make-database db-spec init-fn))
    :web-server (make-web-server #'app-handler port))))

(defonce ^:private
  ^{:doc "This exists so that if you run a socket REPL when you start
  the application, you can get at the running system easily. See
  Corfield example from URL at the comment at the top of the file."}
  repl-system (atom nil))

(defn -main
  [& [port]]
  (let [-name "CENTRIPETAL_INTERVIEW_PORT"
        port (or port (get (System/getenv) -name 8080))
        port (cond-> port (string? port) Integer/parseInt)]
    (println "Starting up on port" port)
    ;; start the web server and application:
    (-> (component/start (make-system port false))
        ;; then put it into the atom so we can get at it from a REPL
        ;; connected to this application:
        (->> (reset! repl-system))
        ;; then wait "forever" on the promise created:
        :web-server :shutdown deref)))
