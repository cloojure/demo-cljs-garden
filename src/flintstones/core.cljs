(ns flintstones.core
  (:require
    [garden.core :as garden]
    [goog.style :as style]
    [oops.core :as oops]
    [reagent.core :as r]
))

(enable-console-print!)
(println
"This text is printed from src/flintstones/core.cljs.
Go ahead and edit it and see reloading in action. Again, or not.")
(println " Hello World! " )
(let [s1 (garden/css [:body {:font-size "16px"}])
      s2 (garden/css [:p.someClass {:font-size "25px" :color :RebeccaPurple}]) ; https://codepen.io/trezy/post/honoring-a-great-man
      btn-1 (garden/css [:.btn-1 {:background-color :mediumblue
                                  :color            :white
                                  :padding          "6px 12px"
                                  :border           "1px solid"
                                  :border-radius    :5px}
                     ])
     ]
  (println "Garden s1:  " (pr-str s1))
  (println "Garden s2:  " (pr-str s2))
  (println "Install Result: " (style/installStyles s2))
  (println "Garden btn-1:  "  btn-1)
  (style/installStyles btn-1)
)

(defn css-comp []
  [:p.someClass "I am a CSS hamster!"])

(defn simple-component []
  [:div
   [:p "I am a component!"]
   [:p.someclass
    "I have " [:strong "bold"]
    [:span {:style {:color "red"}} " and red"] " text."]
   [css-comp]
   [:button.btn-1 {:on-click #(println "Done it!")
                   :value "Just do it."}]
  ])

(defonce counter (atom 0))

(defn run []
  (r/render [simple-component] (js/document.getElementById "tgt-div")))

(defn figwheel-reload []
  ; optionally touch your app-state to force rerendering depending on your application
  ; (swap! app-state update-in [:__figwheel_counter] inc)
  (println "Reloading: " (swap! counter inc)))

(when (zero? @counter)
  (println "Initial load")
  (figwheel-reload))
(run)


