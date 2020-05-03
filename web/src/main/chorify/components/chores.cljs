(ns chorify.components.chores
  (:require [reagent.core :as r]))

(declare chore-item
         chore-input
         add-chore-button)

(defonce chores (r/atom {}))

(defn add-chore
  [mp tk]
  (js/console.log (clj->js @chores))
  (if (not (empty? tk))
    (let [id (str "gomosdg-" (.now js/Date))]
      (assoc mp id {:name      tk
                    :id        id
                    :completed? false}))
    mp))

(defn update-chore
  "Takes function to apply on a chore in order to update it"
  [f id]
  (js/console.log (clj->js @chores))
  (let [cs @chores
        chore (get @chores id)
        u-f #(update % id f)]
    (swap! chores u-f)))

(defn toggle-chore-completed
  "Completes are chore with the given id"
  [chore]
  (assoc chore :completed? (not (:completed? chore))))

(defn chore-panel
  []
  [:div.container.is-fluid
   [:nav.panel
   [:p.panel-heading
    "Chores"]
   [:div.panel-block 
    [chore-input]]
    (for [chore (vals @chores)]
     [:div.panel-block
      [chore-item chore]])]])

(defn chore-item
  "List item that displays chores"
  [chore]
  (js/console.log "Chore" (clj->js chore))
  (let []
    (fn []
      [:label.checkbox
       [:input {:type "checkbox"
                :checked (:completed? chore)
                :on-change (fn [e]
                             (js/console.log (clj->js (.. e -target)))
                             (update-chore toggle-chore-completed (:id chore))
                             (set! (.. e -target -checked) true))}]
       (:name chore)])))

(defn chore-input
  "Takes name of chore and adds it enter is pressed"
  []
  (let [chore-name (r/atom "")]
    (fn [{:keys [id class]}]
      [:div.field.has-addons
       [:div.control
        [:input.input {:type        "text"
                       :placeholder "What to do?"
                       :value       @chore-name
                       :on-change   #(do (reset! chore-name (-> % .-target .-value)))
                       :on-key-down #(when (= "Enter" (.-key %))
                                       (swap! chores add-chore @chore-name)
                                       (reset! chore-name ""))}]]
       [add-chore-button chore-name]])))

(defn add-chore-button
  [chore-name]
  [:div.control
   [:a.button.is-success {:on-click (fn []
                                      (swap! chores add-chore @chore-name)
                                      (reset! chore-name ""))}
    [:i.fas.fa-plus {:aria-hidden "true"}]]])
