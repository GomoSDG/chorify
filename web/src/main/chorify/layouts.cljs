(ns chorify.layouts)

(defn main-layout
  "layout for the website"
  [page]
  (fn []
    [:<>
     [:nav.navbar.is-dark {:role       "navigation"
                           :aria-label "main navigation"}
      [:div.navbar-menu
       [:div.navbar-start
        [:a.navbar-item
         "Home"]]]]
     [:section.section [page]]]))
