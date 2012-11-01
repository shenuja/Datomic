(ns noir_video.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page :only [include-css include-js html5]]
        [hiccup.form]))

(defpartial layout [& content]
            (html5
              [:head
               [:title "noir_video"]
               (include-css "/css/reset.css")
               (include-css "/css/bootstrap.css")
               (include-css "/css/bootstrap-responsive.css")
               (include-js "//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js")
               (include-js "/js/bootstrap.min.js")
               (include-js "/js/video.js")]
               [:meta {:name "viewport", :content "width=device-width, initial-scale=1.0"}]
              [:body
               [:div#wrapper
                content]]))

;(defpartial video-item [{:keys [id title path]}]
;    [:li ;; maps define HTML attributes
;        [:h3 title]
;        [:video {:id id, :controls "true"}
;         [:source {:src path
;                   :type "video/mp4"}]]]) ;; add a class

(defpartial video-item [{:keys [id title path]}]
    [:li ;; maps define HTML attributes
        [:h3 title]
        [:img {:id id, :src path}]]) ;; add a class

(defpartial video-list [items]
    [:ul#videoItems ;; set the id attribute
        (map video-item items)])


(defpartial user-fields [{:keys [username]}]
  (label "username" "Username: ")
  (text-field "username" username))
