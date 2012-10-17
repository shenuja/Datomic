(ns noir-video.models.video)

(defn get-by-name [name]
  (first
   (db/fetch-results
    ["SELECT id, username, password, essay, email
      FROM users
      WHERE username = ?"
     username])))

(defn get-item [id]
 )

(defn play []
  )

(defn pause [id])

