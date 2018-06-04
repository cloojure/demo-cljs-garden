(ns tst.flintstones.doorunner
  (:require
    [tst.flintstones.dino]
    [doo.runner :refer-macros [doo-tests]] ))
(enable-console-print!)
(println "doorunner - beginning")

(doo-tests
  'tst.flintstones.dino
)
(println "doorunner - end")
