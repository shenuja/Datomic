(ns noir_video.models.user  
  (:require [noir.session :as session]
            [datomic.api :as d]
            [noir_video.models :as n]))

(defn login! [{:keys [username] :as user}]
  (d/transact n/conn [["db/add" (d/tempid "db.part/user") "user/name" username]])  
  (def results (d/q '[:find ?u ?n :where [?u :user/name ?n]] (d/db n/conn)))
  (session/put! :user {:username username}))

(defn logged-in? []
  (session/get :user))

(defn logout! []
  (session/remove! :user))


