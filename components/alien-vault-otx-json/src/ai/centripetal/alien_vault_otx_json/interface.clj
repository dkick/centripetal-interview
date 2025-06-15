(ns ai.centripetal.alien-vault-otx-json.interface
  (:require
    [ai.centripetal.alien-vault-otx-json.core :as core]))

;;; IOC: Indicator Of Compromise

(def get-indicators core/get-indicators)

(def get-indicators-by-type core/get-indicators-by-type)

(def get-ioc core/get-ioc)

(def get-ioc-indicators core/get-ioc-indicators)

(def get-ioc-indicators-by-type core/get-ioc-indicators-by-type)

(def ioc-json-map? core/ioc-json-map?)

(def ioc-map? core/ioc-map?)

(def mapper core/mapper)
