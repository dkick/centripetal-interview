(ns ai.centripetal.app-state.interface
  (:require
   [ai.centripetal.app-state.core :as core]))

(defn make-app-state
  "Return your application state component, fully configured.

  In this simple case, we just pass the whole configuration into
  the application (a hash map containing a :repl flag).

  The application depends on the database (which is created in
  new-system below and automatically passed into Application by
  Component itself, before calling start)."
  [config]
  (core/make-app-state config))
