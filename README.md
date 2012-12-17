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


## Tryst with data modeling

- We need simple models for user, video and action so that we can record stuff like 'subhash login', 'subhash play lolcats' etc
- User and video are straightforward. Action is slightly tricky because it has to point to User and Video entities. Apparently, that is done by specifying ``:db/valueType :db.type/ref`` in the schema
- Initial fixtures would require us to create a new user and also an action that points to this user. This is done by creating the user object specifying a temporary id and using the same while pointing the action to this new user.

```
{:db/id #db/id[:db.part/user -10001], :user/name "subhash"}
{:db/id #db/id[:db.part/user], :action/actor #db/id[:db.part/user -10001],:action/type :action.type/login}
```



## False starts and hiccups

### Key bindings

Suppose we want to find all users who have logged in (in an application that records all user activities)

```
  :find ?u :where [?a :action/type :action.type/login] [?a :action/actor ?u]
```

Coming from the SQL world, we would expect that this query give us the entities directly. But what it does is to return the entity ids and you are expected to use a separate API, 'entity' which takes an entity id and returns a map of all entity values. I think the creators of Datomic instead expect you to query thus:

```
  :find ?n :where [?a :action/type :action.type/login] [?a :action/actor ?u] [?u :user/name ?n]
```

The implicit join frees you from bothering about inherent data models and instead focus on the real world relationship between users, their names and their actions. The key bindings are useful in pulling interesting information without having to query further

### Historical data

Datomic is a neat solution when you handle historical data because it does NOT update-in-place. Instead, it maintains time stamps with each transaction, so you can go back in the past or speculate the future at will. For example, you can record every login action of a user and when queried, to returns the latest as per the specified current time. Trouble strikes when you want all of history in a query. For example, we haven't yet figure out how to query all logins ever by a specific user

