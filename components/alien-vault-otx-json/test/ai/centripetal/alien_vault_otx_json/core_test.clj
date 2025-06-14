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
  (is (every? subject/ioc-map? (subject/make-ioc-seq))))

(deftest unique-ioc-ids-test
  (is (subject/unique-ioc-map-ids? (subject/make-ioc-seq))))

(deftest duplicate-ioc-indicator-ids-test
  (is (->> (subject/ioc-indicator-id-counts subject/ioc-seq)
           vals
           (some #(> % 1)))))

(comment
  (subject/get-ioc nil)

  (subject/ensure-ioc-seq
   (subject/make-ioc-seq))

  (subject/ioc-indicator-id-counts subject/ioc-seq)

  (first subject/ioc-seq)

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
