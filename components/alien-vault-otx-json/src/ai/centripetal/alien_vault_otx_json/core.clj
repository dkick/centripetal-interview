(ns ai.centripetal.alien-vault-otx-json.core
  (:require
    [camel-snake-kebab.core :as csk]
    [clojure.java.io :as io]
    [jsonista.core :as json]
    [malli.core :as m]
    [meander.epsilon :as e]))

(def mapper
  (json/object-mapper
   {:decode-key-fn csk/->kebab-case-keyword
    :encode-key-fn csk/->snake_case_string}))

;;; IOC: Indicator Of Compromise

(def IocJsonMap
  (m/schema
   [:map
    ["industries" [:vector :string]]
    ["created" :string]
    ["tlp" :string]
    ["indicators"
     [:vector
      [:map
       ["created" :string]
       ["indicator" :string]
       ["id" :int]
       ["content" :string]
       ["title" :string]
       ["type" :string]
       ["description" :string]]]]
    ["extract_source" [:vector :any]]
    ["references" [:vector :string]]
    ["more_indicators" :boolean]
    ["tags" [:vector :string]]
    ["id" :string]
    ["revision" :int]
    ["name" :string]
    ["adversary" :string]
    ["modified" :string]
    ["public" :int]
    ["author_name" :string]
    ["description" :string]
    ["targeted_countries" [:vector :string]]]))

(def ioc-json-map?
  (m/validator IocJsonMap))

(def IocMap
  (m/schema
   [:map
    [:description :string]
    [:author-name :string]
    [:tags [:vector :string]]
    [:revision :int]
    [:name :string]
    [:public :int]
    [:indicators
     [:vector
      [:map
       [:description :string]
       [:content :string]
       [:type :string]
       [:created :string]
       [:title :string]
       [:id :int]
       [:indicator :string]]]]
    [:targeted-countries [:vector :string]]
    [:created :string]
    [:extract-source [:vector :any]]
    [:references [:vector :string]]
    [:modified :string]
    [:id :string]
    [:tlp :string]
    [:more-indicators :boolean]
    [:adversary :string]
    [:industries [:vector :string]]]))

(def ioc-map?
  (m/validator IocMap))

(defn unique-ioc-map-ids?
  [ioc-seq]
  (->> ioc-seq
       (map :id)
       frequencies
       vals
       (every? #(= % 1))))

(defn make-ioc-seq
  []
  (-> "alien-vault-otx-json/indicators.json"
      io/resource
      slurp
      (json/read-value mapper)))

(defn ensure-ioc-seq
  [ioc-seq]
  {:pre [(every? ioc-map? ioc-seq)
         (unique-ioc-map-ids? ioc-seq)]}
  ioc-seq)

(def ioc-seq
  (ensure-ioc-seq
   (make-ioc-seq)))

(defn get-ioc
  [ioc-id &
   {:keys [ioc-seq]
    :or   {ioc-seq ioc-seq}}]
  {:post [(ioc-map? %)]}
  ;; find ensures only one or none
  (e/find
   ioc-seq

   (e/scan
    {:id ~ioc-id
     :as ?ioc})
   ?ioc))

(defn ioc-indicator-id-frequencies
  [ioc-seq]
  (->> ioc-seq
       (mapcat :indicators)
       (map :id)
       frequencies))

(defn get-ioc-indicators
  [indicator-id &
   {:keys [ioc-seq]
    :or   {ioc-seq ioc-seq}}]
  (e/search
   ioc-seq

   (e/scan
    {:indicators (e/scan
                  {:id ~indicator-id
                   :as !indicator})
     :as         ?ioc})
   {:ioc        ?ioc
    :indicators !indicator}))

(defn get-indicators
  [indicator-id &
   {:keys [ioc-seq]
    :or   {ioc-seq ioc-seq}}]
  (->> (get-ioc-indicators indicator-id {:ioc-seq ioc-seq})
       (mapcat :indicators)))

(defn get-ioc-indicators-of-type
  [indicator-type &
   {:keys [ioc-seq]
    :or   {ioc-seq ioc-seq}}]
  (e/search
   ioc-seq

   (e/scan
    {:indicators (e/scan
                  {:type ~indicator-type
                   :as   !indicator})
     :as         ?ioc})
   {:ioc        ?ioc
    :indicators !indicator}))

(defn get-indicators-of-type
  [indicator-type &
   {:keys [ioc-seq]
    :or   {ioc-seq ioc-seq}}]
  (->> (get-ioc-indicators-of-type indicator-type {:ioc-seq ioc-seq})
       (mapcat :indicators)))
