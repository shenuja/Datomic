(ns noir_video.views.welcome
  (:require [noir_video.views.common :as common]
            [noir_video.models.user :as users]
            [noir_video.models.video :as videos]
            [noir.content.getting-started]
            [noir.response :as resp]
            [noir.session :as session])
  (:use [noir.core :only [defpage]]))

(defpage "/welcome" []
  (users/data)
  (def all_usernames (apply str (interpose ", " users/all_users)))
  (def all_videotitles (apply str (interpose ", " users/all_videos)))
  (def all_logins (apply str (interpose ", " users/all_logins)))
  (def all_plays (apply str (interpose ", " users/all_plays)))
  (def all_pauses (apply str (interpose ", " users/all_pauses)))
  (common/layout [:p (str "Users: " all_usernames)]
                 [:p (str "Videos: " all_videotitles)]
                 [:p (str "Logins by: " all_logins)]
                 [:p (str "Played by: " all_plays)]
                 [:p (str "Paused by: " all_pauses)]))


(defpage "/logout" []
  (users/logout!)
  (resp/redirect "/login"))

(defpage "/videos" []
        (if (users/logged-in?)
           (common/layout
             [:h1 "Video list!"
              [:p "logged in user - "
               [:span (:username (session/get :user))]]]
             (common/video-list (videos/all-videos)))
         (resp/redirect "/login")
         ))

(defpage "/video/play/:id" {:keys [id]}
  (println "video/play")
  (videos/play id)
  (resp/status 200 "ok"))

(defpage "/video/pause/:id" {:keys [id]}
  (println "video/pause")
  (videos/pause id)
  (resp/status 200 "ok"))

(defpage [:get "/GetFile"] {:keys [file]}
         (with-open [file (clojure.java.io/input-stream  (str "resources/public/lib/" file))]
           (resp/set-headers {"Content-Type" "text/plain"} file)))


