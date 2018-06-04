(ns tst.flintstones.pebbles
  (:require-macros [flintstones.cljs.test :refer  [dotest is isnt is= isnt= testing use-fixtures]]))
(use-fixtures :once
  {:before (fn [] (println "Beginning pebbles tests..."))
   :after  (fn [] (println "Finished pebbles tests..."))})

(dotest
  (is= 2 (+ 1 1)) ; this works
  ; This must be commented-out, or it will throw an Error since `pebbles.js` is only
  ; included via the <script> tag in `index.html`. The Doo testing framework doesn't
  ; use `index.html`, so we cannot access items from `pebbles.js` in these tests.
  (comment
    (println "js/pebblesInfo:  " js/pebblesInfo))
  )
