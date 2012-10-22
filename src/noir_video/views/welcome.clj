(ns noir_video.views.welcome
  (:require [noir_video.views.common :as common]
            [noir_video.models.user :as users]
            [noir_video.models.video :as videos]
            [noir.content.getting-started]
            [noir.response :as resp]
            [noir.session :as session])
  (:use [noir.core :only [defpage]]))

(defpage "/welcome" []
         (common/layout
           [:p "Welcome to noir_video"]))

(defpage "/logout" []
  (users/logout!)
  (resp/redirect "/login"))

(defpage "/videos" []
        (if (users/logged-in?)
           (let [items videos/all-videos]
           (common/layout
             [:h1 "Video list!"
              [:p "logged in user - "
               [:span (:username (session/get :user))]]]
             (common/video-list items)))
         (resp/redirect "/login")
         ))

(defpage "/video/play/:id" {:keys [id]}
  (if-let [video (videos/get-item id)]
     (videos/play)
    (common/layout
     [:h3 "Post not found"])))


