(ns flintstones.core
  (:require
    [garden.core :as garden]
    [garden.stylesheet :as gstyle]
    [goog.style :as style]
    [reagent.core :as r]
    [reagent.format :as fmt] ))

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

(defn css-comp []
  [:p.someClass "I am a CSS hamster!"])

;-----------------------------------------------------------------------------
(defn install-css
  "Compile a hiccup-format CSS spec and install in the browser"
  [css-def]
  (let [css-str (garden/css css-def)]
    (style/installStyles css-str)
    css-str))
;-----------------------------------------------------------------------------

(println "Installed via Garden:  \n"
  (install-css [".doit-class"           ; or :.doit-class
                {:background-color :#337ab7 ; or "#337ab7"
                 :color            :white
                 :text-align       "center" ; or :center
                 :display          :inline-block
                 :font-size        :16px
                 :padding          "10px 15px 10px 15px" ; or [[:10px :15px :10px :15px]]
                 :border           "1px solid"
                 :border-radius    :5px}]))

(def btn-doit-counter (r/atom 0))
(defn doit-btn []
  [:button.doit-class
   {:on-click (fn [& args]
                (swap! btn-doit-counter inc)
                (println (fmt/format "Done it %d times!" @btn-doit-counter)))}
   (str "Just do it (" @btn-doit-counter ")")])

;-----------------------------------------------------------------------------
(println "Installed via Garden:  \n"
   (install-css [:div [:p {:background-color :lightblue
                           :color            :blue
                           :font-size        :16px}]]))

(defn rand-para []
  [:div
   [:p {:style {:color (if (< 9 5) :red :purple)}}
    "This is some test just show that we can type anything we desire, and use an internal style definition independent
    of any outside influences. It could go on and on and on and on and on and on and on and on and on...."]])

;-----------------------------------------------------------------------------
; Examples for Tim Christiansen
(println   "#1"
  (install-css
    [:html                              ; .slm-structure-html
     {:font-size                :10px
      :-webkit-text-size-adjust :100%}]))

(println  "#2"
  (install-css [:* {:padding :0px}]))

(def all-in "100%")
(println  "#3"
  (install-css
    [:body
     {:width       all-in
      :min-width   (if (< 2 5)
                     :220px             ; <= this one wins
                     :333px)
      :font-size   :1.7rem
      :font-family "ntanffy02"
      "overflow-y" "scroll"
      :overflow-x :hidden  }
     ]))

(println "#4"
   (install-css
     (gstyle/at-media
       {:all               true
        :-ms-high-contrast :none} ; suppress IE responsive
       [:body {:width     :auto!important
               :min-width :1280px!important
               :overflow  :auto!important}
        ] )))

;-----------------------------------------------------------------------------
(defn demo-component []
  [:div
   [css-comp]
   [doit-btn]
   [rand-para]
  ])

(defn run []
  (r/render
    [demo-component]
    (js/document.getElementById "tgt-div")))

(run)



