# noir_video

Though this project pretends to do video analytics using clojure/noir/datomic, the ulterior motive is to test drive datomic + noir, to understand the learning curve and document learnings

## Installation

1. Install latest eclipse
2. Install counterclockwise from update site http://ccw.cgrand.net/updatesite/
3. Import noir_video project into workspace
4. Right click -> Run Configurations -> Clojure -> Create new configuration -> Run
5. REPL console will open now & type 
      (noir_video.server/-main)
6. Now application will be accessible at http://localhost:8080


## Live example


## Logic flow

1. Startup: Under src/noir_video, server.clj starts up the noir server after initializing using models.clj. models.clj reads up schema and data from under src/noir_video/schema and issues datomic transactions 
2. Views: welcome.clj defines the two important pages - /videos (the video listing page) and /welcome (the analytics page). If no user is logged in, the app redirects to /login
2. Models: user/login! checks to see if the user exists and if not, the user is added to datomic. The login action is recorded in datomic
3. Ajax: Each time a video is played or paused, an ajax call is made in javascript (public/js/video.js). This action is recorded by the model 'video.clj'
4. Analytics: The view /welcome invokes user/data to query analytical data from datomic 


## False starts and hiccups

1. Datomic schema is a wee-bit tricky until one gets used to 

