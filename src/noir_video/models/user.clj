(ns noir_video.models.user  
  (:require [noir.session :as session]
            [datomic.api :as d]
            [noir_video.models :as n]))

(defn login! [{:keys [username] :as user}]
  (def query (str "[:find ?u  :where [?u :user/name \"" username "\"]]"))
  (def results (d/q query (d/db n/conn)))
  (def userid (ffirst results))
  (println (str "existing " userid))
  (if (not userid)
    (let []
      (d/transact n/conn [["db/add" (d/tempid "db.part/user") "user/name" username]])
      (def results (d/q query (d/db n/conn)))
      (def userid (ffirst results))
      (println (str "new " userid))
    ))
;  (d/transact n/conn [["db/add" (d/tempid "db.part/user") "action/actor" userid "action/type" "action.type/login"]])
  @(d/transact n/conn [{:db/id (d/tempid :db.part/user) :action/actor userid :action/type :action.type/login}])

  (session/put! :user {:username username :userid userid}))

(defn logged-in? []
  (session/get :user))

(defn logout! []
  (session/remove! :user))

(defn data []
  (def all_users (d/q '[:find ?n :where [?u :user/name ?n]] (d/db n/conn)))
  (def all_videos (d/q '[:find ?t :where [?v :video/title ?t]] (d/db n/conn)))
  (def all_actions (d/q '[:find ?n  :where [?a :action/type :action.type/login] [?a :action/actor ?u] [?u :user/name ?n]] (d/db n/conn)))
  
  )
