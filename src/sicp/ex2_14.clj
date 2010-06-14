(ns sicp.ex2_14
  (:use [sicp ch2_1_extended ex2_12 ex2_7]))

(def r1 (make-center-percent 100 5))
(def r2 (make-center-percent 500 5))

(par1 r1 r2)
;;=> (71.62698412698413 96.71052631578948)
(par2 r1 r2)
;;=> (79.16666666666667 87.49999999999999)

(def r4 (make-center-percent 500 1))
(def r3 (make-center-percent 1000 1))

(div-interval r3 r4)
;;=> (1.9603960396039604 2.04040404040404)

(div-interval  r4 r4)
;;=> (0.9801980198019802 1.02020202020202)

(div-interval r4 r3)
;;=> (0.4900990099009901 0.51010101010101)

(percentage (div-interval r4 r3))
;;=> 1.999800019998008

(percentage (div-interval r3 r3))
;;=> 1.999800019998008
(center (div-interval r3 r3))
;;=> 1.000200020002