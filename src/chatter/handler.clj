(ns chatter.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [hiccup.page :as page]
            [hiccup.form :as form]
            [ring.middleware.params :refer [wrap-params]]))

(def chat-messages
  (atom ()))

(defn generate-message-row
  [message]
  [:tr [:td (:name message)] [:td (:message message)]])

(def message-form
  [:p
   (form/form-to
     [:post "/"]
     "Name:" (form/text-field "name")
     "Message:" (form/text-field "msg")
     (form/submit-button "Submit"))])

(defn generate-message-table
  [messages]
  [:p
    [:table
      (map generate-message-row messages)]])

(defn generate-messages-view
  "this generates the HTML for displaying the messages"
  [messages]
  (page/html5
    [:head
      [:title "chatter"]]
    [:body
      [:h1 {:style "color:green"} "Our Chat App"]
      message-form
      (generate-message-table messages)]))

(defn update-messages!
  [messages new-name new-message]
  (swap! messages conj
    {:name new-name
     :message new-message}))

(defroutes app-routes
  (GET "/" [] (generate-messages-view @chat-messages))
  (POST "/" [name msg]
    (generate-messages-view
     (update-messages! chat-messages name msg)))
  (route/not-found "Not Found"))

(def app (wrap-params app-routes))
