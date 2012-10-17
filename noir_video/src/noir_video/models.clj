(ns noir_video.models
  (:require [datomic.api :as d] 
            ))

(defn init []
;  (use '[datomic.api :only [q db] :as d])
;  (use 'clojure.pprint)

  (def uri "datomic:mem://noir_video")
  (datomic.api/create-database uri)
  (def conn (datomic.api/connect uri))
  (def schema-tx (read-string (slurp "src/noir_video/schema/noir-video-schema.dtm")))

  ;; display first statement
  (first schema-tx)
	
	;; submit schema transaction
	@(d/transact conn schema-tx)
	
	;; parse seed data dtm file
	(def data-tx (read-string (slurp "src/noir_video/schema/noir-video-data.dtm")))
	
	;; display first three statements in seed data transaction
	(first data-tx)
	(second data-tx)
	(nth data-tx 2)
	
	;; submit seed data transaction
	@(d/transact conn data-tx)
 
; (pprint schema-tx)
; (pprint data-tx)
  
)
