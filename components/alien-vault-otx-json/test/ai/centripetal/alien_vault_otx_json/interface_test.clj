(ns ai.centripetal.alien-vault-otx-json.interface-test
  (:require
    [ai.centripetal.alien-vault-otx-json.core :as core]
    [ai.centripetal.alien-vault-otx-json.interface :as sut]
    [clojure.test :as test :refer [deftest is]]))

(deftest get-ioc--test
  (is (->> core/ioc-seq
           (map :id)
           (map sut/get-ioc--id)
           (every? sut/ioc-map?))))

(deftest get-indicators--id--test
  (is
   (= (sut/get-indicators--id 2437645)
      [{:description
        "On: 2018-07-09T02:42:18.332000OS detected: Windows 7 or 8"

        :content ""
        :type "IPv4"
        :created "2018-07-09T06:02:58"
        :title ""
        :id 2437645
        :indicator "103.5.148.2"}
       {:description
        "On: 2018-07-06T04:05:28.762000OS detected: Windows 7 or 8"

        :content ""
        :type "IPv4"
        :created "2018-07-06T06:05:04"
        :title ""
        :id 2437645
        :indicator "103.5.148.2"}])))
