(ns noir_video.views.login
  (:require [noir_video.views.common :as common]
            [noir_video.models.user :as user]
            [noir.session :as session]
            [noir.response :as resp])
  (:use [noir.core :only [defpage render]]
        [hiccup.form]))

(defpage "/login" {:as user}
         (common/layout
           (if (session/get :user)
            (resp/redirect "/videos")
            (form-to [:post "/login"]
                     (common/user-fields user)
                     (submit-button "Login")))))


(defpage [:post "/login"] {:as user}
  (if (user/login! user)
;    (resp/redirect "/videos")
    (resp/json noir_video.models.user/results)
    (render "/login" user)))
