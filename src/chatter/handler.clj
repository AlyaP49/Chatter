(ns chatter.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [hiccup.page :as page]))

(defn generate-messages-view
  "this generates the HTML for displaying the messages"
  []
  (page/html5
    [:head
      [:title "chatter"]]
    [:body
      [:h1 {:style "color:red"} "Our Chat App"]]))

(defroutes app-routes
  (GET "/" []
    (generate-messages-view))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
