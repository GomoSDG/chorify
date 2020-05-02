(ns chorify.core
  (:require [reagent.core :as r]
            [reagent.dom :as rdom]))

(declare main
         chore-input
         chore-item
         chore-panel)

(defn add-chore
  [ls tk]
  (js/console.log "Added chore")
  (conj ls {:name tk
            :id (str "gomosdg-" (rand-int 10000) "-" (rand-int 10000))
            :completed false}))

(defonce chores (r/atom []))

(defn ^:dev/after-load init
  []
  (js/console.log "Hello world!")
  (rdom/render
   [main]
   (.getElementById js/document "app")))

(defn main
  []
  [:<>
   [chore-panel]])

(defn chore-panel
  []
  [:nav.panel
   [:p.panel-heading
    "Chorify"]
   [:div.panel-block
    [:p.control [chore-input]]]
   (for [chore @chores]
     [:div.panel-block
      [chore-item chore]])])

(defn chore-item
  "List item that displays chores"
  [chore]
  (js/console.log (clj->js chore))
  [:label.checkbox
   [:input {:type "checkbox"
            :checked (:completed (:completed chore))}]
   (:name chore)])

(defn chore-input
  "Takes name of chore and adds it enter is pressed"
  []
  (let [chore-name (r/atom "")]
    (fn [{:keys [id class]}]
      [:input.input {:type        "text"
               :placeholder "What to do?"
               :value       @chore-name
               :on-change   #(do (reset! chore-name (-> % .-target .-value)))
               :on-key-down #(when (= "Enter" (.-key %))
                               (swap! chores add-chore @chore-name)
                               (reset! chore-name ""))}])))
