(ns ai.centripetal.web-server.interface
  (:require
   [ai.centripetal.web-server.core :as core]))

;;; Borrowed liberally from
;;; https://github.com/seancorfield/usermanager-example

(defn make-web-server
  "Return a WebServer component that depends on the application.

  The handler-fn is a function that accepts the app-state (Component)
  and returns a fully configured Ring handler (with middeware)."
  [handler-fn port]
  (core/make-web-server handler-fn port))

