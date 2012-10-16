(ns noir_video.models.user  
  (:require [noir.session :as session]
            [datomic.api :as d]))

(defn login! [{:keys [username] :as user}]
  (datomic.api/transact noir_video.models/conn [["db/add" (datomic.api/tempid "db.part/user") "db/doc" "hello world"]])
  (def results (datomic.api/q '[:find ?entity :where [?entity :db/doc "hello world"]] (datomic.api/db noir_video.models/conn)))  
  (session/put! :user {:username username}))

(defn logged-in? []
  (session/get :user))


