(ns flintstones.core
  (:require
    [garden.core :as garden]
    [goog.style :as style]
    [oops.core :as oops]
    [reagent.core :as r]
    [reagent.format :as fmt]
))

(enable-console-print!)
(println
"This text is printed from src/flintstones/core.cljs.
Go ahead and edit it and see reloading in action. Again, or not.")
(println " Hello World! " )

(let [s1 (garden/css [:body {:font-size "16px"}])
      s2 (garden/css [:p.someClass {:font-size "25px" :color :RebeccaPurple}]) ]
                                                      ; https://codepen.io/trezy/post/honoring-a-great-man
  (println "Garden s1:  " (pr-str s1))
  (println "Garden s2:  " (pr-str s2))
  (println "Install Result: " (style/installStyles s2)))

(defn install-css [css-def]
  (let [css-str (garden/css css-def)]
    (style/installStyles css-str)
    css-str))

(println "Garden btn-1:  \n"
  (install-css [".btn-1" {:background-color "#337ab7"
                         :color            :white
                         :text-align       :center
                         :display          :inline-block
                         :font-size        :16px
                         :padding          [:6px :12px]
                         :border           "1px solid"
                         :border-radius    :5px
                         }]))

(defn css-comp []
  [:p.someClass "I am a CSS hamster!"])

(def btn-doit-counter (r/atom 0))
(defn doit-btn []
  [:button.btn-1
   {:on-click (fn [& args]
                (swap! btn-doit-counter inc)
                (println (fmt/format "Done it %d times!" @btn-doit-counter)))}
   (str "Just do it (" @btn-doit-counter ")")])

(defn demo-component []
  [:div
   [css-comp]
   [doit-btn]
  ])

(defonce counter (atom 0))

(defn run []
  (r/render [demo-component] (js/document.getElementById "tgt-div")))

(defn figwheel-reload []
  ; optionally touch your app-state to force rerendering depending on your application
  ; (swap! app-state update-in [:__figwheel_counter] inc)
  (println "Reloading: " (swap! counter inc)))

(when (zero? @counter)
  (println "Initial load")
  (figwheel-reload))
(run)


