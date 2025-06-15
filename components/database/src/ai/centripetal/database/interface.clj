(ns ai.centripetal.database.interface
  (:require
    [com.stuartsierra.component :as component]))

;;; Borrowed liberally from
;;; https://github.com/seancorfield/usermanager-example

(defrecord Database [db-spec     ; configuration
                     init-fn     ; callback to initialize the database
                     datasource] ; state

  component/Lifecycle
    (start [this]
      (if datasource
        this
        (assoc this :datasource []))
      #_(if datasource
          this ; already initialized
          (let [this+ds (assoc this :datasource (jdbc/get-datasource db-spec))]
            ;; set up database if necessary
            (init-fn this+ds (:dbtype db-spec))
            this+ds)))
    (stop [this]
      (assoc this :datasource nil))

  ;; allow the Database component to be "called" with no arguments
  ;; to produce the underlying datasource object
  clojure.lang.IFn
    (invoke [_] datasource))
