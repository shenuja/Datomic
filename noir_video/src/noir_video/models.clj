(ns noir_video.models
  (:require [datomic.api :as d] ))

(defn init []
;  (use '[datomic.api :only [q db] :as d])
  (def uri "datomic:mem://noir_video")
  (datomic.api/create-database uri)
  (def conn (datomic.api/connect uri)) 
  )
