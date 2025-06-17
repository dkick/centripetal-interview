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

(deftest get-ioc-indicators--id--test
  (is
   (=
    (->> (sut/get-ioc-indicators--id 2437645)
         (map (fn [{:keys [ioc indicators]}]
                {:ioc        (dissoc ioc :indicators)
                 :indicators indicators})))
    [{:ioc
      {:description        ""
       :author-name        "marcoramilli"
       :tags               ["cowrie" "suricata" "p0f" "dionaea" "glastopf"
                            "conpot"]
       :revision           1
       :name               "Public Feeds from Yoroi - past 6h"
       :public             1
       :targeted-countries []
       :created            "2018-07-09T06:02:57.653000"
       :extract-source     []
       :references         []
       :modified           "2018-07-09T06:02:57.653000"
       :id                 "5b42fa916a38fd592acf606e"
       :tlp                "green"
       :more-indicators    false
       :adversary          ""
       :industries         []}

      :indicators
      [{:description
        "On: 2018-07-09T02:42:18.332000OS detected: Windows 7 or 8"

        :content ""
        :type "IPv4"
        :created "2018-07-09T06:02:58"
        :title ""
        :id 2437645
        :indicator "103.5.148.2"}]}
     {:ioc
      {:description ""
       :author-name "marcoramilli"
       :tags
       ["cowrie"
        "suricata"
        "p0f"
        "dionaea"
        "conpot"
        "glastopf"
        "elastichoney"]

       :revision 1
       :name "Public Feeds from Yoroi - past 6h"
       :public 1
       :targeted-countries []
       :created "2018-07-06T06:05:02.877000"
       :extract-source []
       :references []
       :modified "2018-07-06T06:05:02.877000"
       :id "5b3f068eac187c2e28c55280"
       :tlp "green"
       :more-indicators false
       :adversary ""
       :industries []}

      :indicators
      [{:description
        "On: 2018-07-06T04:05:28.762000OS detected: Windows 7 or 8"

        :content ""
        :type "IPv4"
        :created "2018-07-06T06:05:04"
        :title ""
        :id 2437645
        :indicator "103.5.148.2"}]}])))

