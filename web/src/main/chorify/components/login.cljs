(ns chorify.components.login)

(defn login
  "Login component"
  []
  (let []
    (fn []
      [:div.container.is-fluid
       [:div.field
        [:label "Username"]
        [:div.control.has-icons-left
         [:input.input {:type "text"
                        :placeholder "Enter Username"}]
         [:span.icon.is-left
          [:i.fas.fa-user]]]]
       [:div.field
        [:label "Password"]
        [:div.control.has-icons-left
         [:input.input {:type "password"
                        :placeholder "Enter Password"}]]]
       [:div.field
        [:div.control
         [:a.button.is-link
          "Login"]]]])))
