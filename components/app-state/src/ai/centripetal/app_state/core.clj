(ns ai.centripetal.app-state.core
  (:require
    [com.stuartsierra.component :as component :refer [using]]))

;;; Borrowed liberally from
;;; https://github.com/seancorfield/usermanager-example

;; Implement your application state's lifecycle here: Although the config is
;; not used in this simple case, it probably would be in the general
;; case -- and the state here is trivial but could be more complex.
(defrecord AppState [config             ; configuration (unused)
                     database           ; dependency
                     state]             ; behavior
  component/Lifecycle
    (start [this]
      ;; Component ensures that dependencies are fully initialized and
      ;; started before invoking this component.
      (if (= (:state this) :running)
        this
        (assoc this :state :running)))
    (stop [this]
      (assoc this :state :stopped)))

(defn make-app-state
  "Return your application state component, fully configured.

  In this simple case, we just pass the whole configuration into
  the application (a hash map containing a :repl flag).

  The application depends on the database (which is created in
  new-system below and automatically passed into Application by
  Component itself, before calling start)."
  [config]
  (using (map->AppState {:config config})
         [:database]))
