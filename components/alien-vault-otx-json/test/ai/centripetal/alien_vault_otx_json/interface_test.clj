(ns ai.centripetal.alien-vault-otx-json.interface-test
  (:require
    [ai.centripetal.alien-vault-otx-json.core :as core]
    [ai.centripetal.alien-vault-otx-json.interface :as sut]
    [clojure.test :as test :refer [deftest is]]))

(deftest get-ioc-test
  (is (->> core/ioc-seq
           (map :id)
           (map sut/get-ioc)
           (every? sut/ioc-map?))))
