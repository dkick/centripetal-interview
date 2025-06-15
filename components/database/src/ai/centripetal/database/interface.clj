(ns ai.centripetal.database.interface
  (:require
    [ai.centripetal.database.core :as core]))

;;; Borrowed liberally from
;;; https://github.com/seancorfield/usermanager-example

(defn make-database
  "Given a database spec (hash map) and an initialization
  function (that accepts a database component and a string
  specifying the database type), return an initialized
  database component.

  The component can be invoked with no arguments to return
  the underlying data source (javax.sql.DataSource).

  The init-fn should be able to initialize a freshly-created
  database but also gracefully handle an existing database."
  [db-spec init-fn]
  (core/make-database db-spec init-fn))

