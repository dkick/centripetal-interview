(ns ai.centripetal.rest-api.handlers
  (:require
    [ai.centripetal.alien-vault-otx-json.interface :as otx]
    [jsonista.core :as json]
    [ring.util.response :as util]))

(defn json-resonse
  [body]
  (-> body
      (json/write-value-as-string otx/mapper)
      util/response
      (util/content-type "application/json; charset=utf-8")))

(defn get-indicators
  [request]
  (json-resonse
   (let [-type (get-in request [:params :type])]
     (if-not -type
       (otx/get-indicators)
       (otx/get-indicators--type -type)))))

(defn get-indicators--id
  [request]
  (json-resonse
   (let [id (get-in request [:params :id])]
     (otx/get-indicators--id id))))
