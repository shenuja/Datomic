(ns noir_video.models.video
  (:require [datomic.api :as d]
            [noir_video.models :as n]
            [noir.session :as session]))

(defn all-videos []
  (defn foo[a] {:id (first a) :title (second a) :path (str "/lib/" (second a) ".m4v")})
  (def videos (d/q '[:find ?v ?t :where [?v :video/title ?t]] (d/db n/conn)))
  (map foo videos)
  )

(defn video-item [item]
  ({:id (get item :id)
     :title (get item :title)
     :path (str "/lib/" (get item :title) ".m4v" )
    }))

(defn get-by-name [name]
  )

(defn get-item [id]
 )

(defn play [id]
  (println "inside play")
  (def userid (:userid (session/get :user)))
  (def tx [{:db/id (d/tempid :db.part/user) :action/actor userid :action/type :action.type/play :action/object (Long/parseLong id)}])
  (println tx)
  @(d/transact n/conn tx)
  
;  (d/transact n/conn [["db/add" (d/tempid "db.part/user") "action/actor" userid "action/type" "action.type/play" "action/object" videoid]])
  )

(defn pause [id]
  (println "inside pause")
  (def userid (:userid (session/get :user)))
  (def tx [{:db/id (d/tempid :db.part/user) :action/actor userid :action/type :action.type/pause :action/object (Long/parseLong id)}])
  (println tx)
  @(d/transact n/conn tx))

