(ns chorify.core
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]
            [chorify.components.chores :refer [chore-panel]]
            [chorify.components.login :refer [login]]
            [chorify.layouts :refer [main-layout]]
            [accountant.core :as accountant]
            [chorify.routes :as routes]))

(accountant/configure-navigation!
 {:nav-handler routes/go!
  :path-exists? routes/path-exists?})

(declare main
         pages)

(defn ^:dev/after-load init
  []
  (js/console.log "Hello world!")
  (rdom/render
   [main]
   (.getElementById js/document "app")))

(defn main
  []
  [:<>
   [main-layout (@routes/current-page pages)]])

(def pages {:home chore-panel
            :login login})
