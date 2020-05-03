(ns chorify.routes
  (:require [secretary.core :as secretary :refer-macros [defroute]]
            [reagent.core :as r]))

(defonce current-page (r/atom :home))

(defn go!
  "Goes to route if user is allowed"
  [path]
  (js/console.log (str "Tried visiting: " path))
  (secretary/dispatch! path))

(defn path-exists?
  "Checks if route exists"
  [path]
  true)


(defroute "/" {}
  (js/console.log "Home directory")
  (reset! current-page :home))
