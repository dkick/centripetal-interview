(ns ai.centripetal.alien-vault-otx-json.interface
  (:require
    [ai.centripetal.alien-vault-otx-json.core :as core]))

;;; IOC: Indicator Of Compromise

(defn get-indicators
  "Return all :indicators from all IOC values as a flattened sequence"
  [&
   {:keys [ioc-seq]
    :or   {ioc-seq core/ioc-seq}}]
  (core/get-indicators {:ioc-seq ioc-seq}))

(defn get-indicators--id
  "Return all :indicators for the indicator-id from all IOC values as a
  flattened sequence"
  [indicator-id &
   {:keys [ioc-seq]
    :or   {ioc-seq core/ioc-seq}}]
  (core/get-indicators--id indicator-id ioc-seq))

(defn get-indicators--type
  "Return all :indicators for the indicator-type from all IOC values as
  a flattened sequence"
  [indicator-type &
   {:keys [ioc-seq]
    :or   {ioc-seq core/ioc-seq}}]
  (core/get-indicators--type indicator-type {:ioc-seq ioc-seq}))

(defn get-ioc--id
  "Return all IOC values for the given ioc-id"
  [ioc-id &
   {:keys [ioc-seq]
    :or   {ioc-seq core/ioc-seq}}]
  (core/get-ioc--id ioc-id ioc-seq))

(defn get-ioc-indicators--id
  "Return all :indicators for the indicator-id from all IOC values as
  well as the IOC in which they were found"
  [indicator-id &
   {:keys [ioc-seq]
    :or   {ioc-seq core/ioc-seq}}]
  (core/get-ioc-indicators--id indicator-id ioc-seq))

(defn get-ioc-indicators--type
  "Return all :indicators for the indicator-type from all IOC values as
  well as the IOC in which they were found"
  [indicator-type &
   {:keys [ioc-seq]
    :or   {ioc-seq core/ioc-seq}}]
  (core/get-ioc-indicators--type indicator-type {:ioc-seq ioc-seq}))

(defn ioc-json-map?
  "Returns true if x is an IOC map with strings as keys (JSON
  format)"
  [x]
  (core/ioc-json-map? x))

(defn ioc-map?
  "Returns true if x is an IOC map with keywords as keys (Clojure
  format)"
  [x]
  (core/ioc-map? x))

(def mapper
  "A Jsonista object mapper (see https://github.com/metosin/jsonista)"
  core/mapper)
