(ns ai.centripetal.alien-vault-otx-json.core-test
  (:require
    [ai.centripetal.alien-vault-otx-json.core :as sut]
    [clojure.java.io :as io]
    [clojure.test :as test :refer [deftest is]]
    [jsonista.core :as json]
    [malli.provider :as mp]))

(deftest test-data--test
  (is (some? (io/resource "alien-vault-otx-json/indicators.json"))))

(deftest ioc-json-map--test
  (is (-> "alien-vault-otx-json/indicators.json"
          io/resource
          slurp
          json/read-value
          (as-> $ (every? sut/ioc-json-map? $)))))

(deftest ioc-map--test
  (is (every? sut/ioc-map? (sut/make-ioc-seq))))

(deftest unique-ioc-ids--test
  (is (sut/unique-ioc-map-ids? (sut/make-ioc-seq))))

(def $ioc-indicator-id-frequencies
  (sut/ioc-indicator-id-frequencies sut/ioc-seq))

(deftest duplicate-ioc-indicator-ids--test
  (is (->> $ioc-indicator-id-frequencies
           vals
           (some #(> % 1))))
  (is (->> (map get
                (repeat $ioc-indicator-id-frequencies)
                [2437645 888830013])
           (every? #(> % 1)))))

(deftest get-indicators--id--test
  (doseq [id [700790756 2437645 888830013]]
    (is (= (-> id
               sut/get-indicators--id
               count)
           (get $ioc-indicator-id-frequencies id)))))

(comment
  (-> "alien-vault-otx-json/indicators.json"
      io/resource
      slurp
      json/read-value
      mp/provide)

  (-> "alien-vault-otx-json/indicators.json"
      io/resource
      slurp
      (json/read-value sut/mapper)
      mp/provide)
  #_|)
