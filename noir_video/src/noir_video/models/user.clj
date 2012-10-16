(ns noir_video.models.user  
  (:require [noir.session :as session]
            ))

(defn login! [{:keys [username] :as user}]
  (session/put! :user {:username username}))

(defn logged-in? []
  (session/get :user))


