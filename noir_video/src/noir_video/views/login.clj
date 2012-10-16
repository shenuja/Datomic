(ns noir_video.views.login
  (:require [noir_video.views.common :as common]
            [noir_video.models.user :as users]
            [noir.session :as session]
            [noir.response :as resp])
  (:use [noir.core :only [defpage render]]
        [hiccup.form]))

(defpage "/login" {:as user}
         (common/layout
           (if (users/logged-in?)
            (render "/videos")
            (form-to [:post "/login"]
                     (common/user-fields user)
                     (submit-button "Login")))))


(defpage [:post "/login"] {:as user}
  (if (users/login! user)
    (resp/redirect "/videos")
    (render "/login" user)))
