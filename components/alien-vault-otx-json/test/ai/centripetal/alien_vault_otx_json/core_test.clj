(ns ai.centripetal.alien-vault-otx-json.core-test
  (:require
    [ai.centripetal.alien-vault-otx-json.core :as subject]
    [clojure.java.io :as io]
    [clojure.test :as test :refer [deftest is]]
    [jsonista.core :as json]
    [malli.provider :as mp]))

(deftest test-data-test
  (is (some? (io/resource "alien-vault-otx-json/indicators.json"))))

(deftest ioc-json-map-test
  (is (-> "alien-vault-otx-json/indicators.json"
          io/resource
          slurp
          json/read-value
          (as-> $ (every? subject/ioc-json-map? $)))))

(deftest ioc-map-test
  (is (-> "alien-vault-otx-json/indicators.json"
          io/resource
          slurp
          (json/read-value subject/mapper)
          (as-> $ (every? subject/ioc-map? $)))))

(comment
  (-> "alien-vault-otx-json/indicators.json"
      io/resource
      slurp
      json/read-value
      mp/provide)

  (-> "alien-vault-otx-json/indicators.json"
      io/resource
      slurp
      (json/read-value subject/mapper)
      mp/provide)
  #_|)
