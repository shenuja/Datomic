(ns noir_video.views.common
  (:use [noir.core :only [defpartial]]
        [hiccup.page :only [include-css include-js html5]]
        [hiccup.form]))

(defpartial layout [& content]
            (html5
              [:head
               [:title "noir_video"]
               (include-css "/css/reset.css")
               (include-js "//ajax.googleapis.com/ajax/libs/jquery/1.4.3/jquery.min.js")
               (include-js "/js/video.js")]
              [:body
               [:div#wrapper
                content]]))

(defpartial video-item [{:keys [id title path]}]
    [:li ;; maps define HTML attributes
        [:h3 title]
        [:video {:id id, :controls "true"}
         [:source {:src path
                   :type "video/mp4; codecs='avc1.42E01E, mp4a.40.2'"}]]]) ;; add a class

(defpartial video-list [items]
    [:ul#videoItems ;; set the id attribute
        (map video-item items)])

(video-list [{:id "Video1"
              :title "Sample Video"
              :path "lib/web.mp4"}])


(defpartial user-fields [{:keys [username]}]
  (label "username" "Username: ")
  (text-field "username" username))
