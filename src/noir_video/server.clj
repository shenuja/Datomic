(ns noir_video.server
  (:require [noir.server :as server]
            [noir_video.models :as models]))

(server/load-views-ns 'noir_video.views)

(defn -main [& m]
  (let [mode (keyword (or (first m) :dev))
        port (Integer. (get (System/getenv) "PORT" "8080"))]
    (models/init)
    (server/start port {:mode mode
                        :ns 'noir_video})))

