(ns noir_video.models.video
  (:require [datomic.api :as d]
            [noir_video.models :as n]))

(defn all-videos 
  ([{
    :title "Horsetail Falls"
    :path "/lib/Horsetail Falls.mp4"
   },
   {
    :title "Yosemite Bears"
    :path "/lib/Yosemite Bears.mp4"
    },
    {
    :title "Yosemite Intro"
    :path "/lib/Yosemite Intro.mp4"
    }])
  )

(defn get-by-name [name]
  )

(defn get-item [id]
 )

(defn play []
  
;  (d/transact n/conn [["db/add" (d/tempid "db.part/user") "action/actor" userid "action/type" "action.type/play" "action/object" videoid]])
  )

(defn pause [id])

