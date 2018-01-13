(ns chatter.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [hiccup.page :as page]))

(def chat-messages
  [{:name "blue" :message "hello world"}
  {:name "red" :message "red is my faborite color"}
  {:name "green" :message "green makes it go faster"}])

(defn generate-messages-view
  "this generates the HTML for displaying the messages"
  [messages]
  (page/html5
    [:head
      [:title "chatter"]]
    [:body
      [:h1 {:style "color:green"} "Our Chat App"]
      [:p messages]]))
(defroutes app-routes
  (GET "/" []
    (generate-messages-view chat-messages))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
