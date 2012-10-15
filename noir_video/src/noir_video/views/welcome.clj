(ns noir_video.views.welcome
  (:require [noir_video.views.common :as common]
            [noir.content.getting-started])
  (:use [noir.core :only [defpage]]))

(defpage "/welcome" []
         (common/layout
           [:p "Welcome to noir_video"]))


(defpage "/videos" []
         (let [items [{:id "Video1"
              :title "Sample Video"
              :path "/lib/mine.mp4"},
                 {:id "Video1"
              :title "Sample Video"
              :path "/lib/web.mp4"}]]
           (common/layout
             [:h1 "Video list!"]
             (common/video-list items))))

