(ns ai.centripetal.alien-vault-otx-json.core
  (:require
    [camel-snake-kebab.core :as csk]
    [jsonista.core :as json]
    [malli.core :as m]))

(def mapper
  (json/object-mapper
   {:decode-key-fn csk/->kebab-case-keyword
    :encode-key-fn csk/->snake_case_string}))

;;; IOC: Indicator of Compromise

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
