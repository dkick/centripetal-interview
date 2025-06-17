(ns ai.centripetal.alien-vault-otx-json.interface
  "The assignment mentions a document in three different places:
  
    3. Create a REST endpoint component that returns a document by its
       ID.
       a. GET /indicators/:id

    4. Add endpoints to return all documents or to find a document by
       its type.
       a. GET /indicators
       b. GET /indicators?type=IPv4
  
    5. There should be a component that does a lookup on a JSON
       file (array of documents).
  
       a. Treat the JSON file like a database with many records (array
          of documents). Attached is a JSON file you can use which
          contains 100 Indicators of Compromise (IOCs) from AlienVault
          OTX.

  Each IOC has an ID and a nested vector on Indicators, each with its
  own ID. The name of the 'indicators' REST enpoint seems to imply a
  lookup for only the nested Indicators but those IDs are not
  unique. Since the JSON file is an array of IOC documents, one might
  image use cases in which the IOC data associated with various
  Indicators might also be of interest.

  This component provides /get-indicators*/ interfaces meant for the
  REST 'indicators' endpoints, which only returns Indicators; but also
  /get-ioc-indicators*/ a map with an ':ioc' key, with the original
  IOC in which the Indicators were found, and ':indicators' with only
  the Indicators matching the criteria."
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
