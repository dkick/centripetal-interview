(ns ai.centripetal.alien-vault-otx-json.interface
  (:require
    [ai.centripetal.alien-vault-otx-json.core :as core]))

;;; IOC: Indicator Of Compromise

(def get-indicators core/get-indicators)

(def get-indicators--id core/get-indicators--id)

(def get-indicators--type core/get-indicators--type)

(def get-ioc core/get-ioc--id)

(def get-ioc-indicators--id core/get-ioc-indicators--id)

(def get-ioc-indicators--type core/get-ioc-indicators--type)

(def ioc-json-map? core/ioc-json-map?)

(def ioc-map? core/ioc-map?)

(def mapper core/mapper)
