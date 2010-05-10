;;; show that golden ratio, phi, is a fixed point of the transformation x -> 1 + 1/x
;;; and use this fact to compute phi by means of the fixed point procedure.

(ns sicp.ex1_35
  (:use [sicp utils ch1_3]
	[clojure.contrib test-is]))

;;; the fixed point of the transformation x-> 1 + 1/x is the same as
;;; root of x - 1 - 1/x = 0 or x^2 - x - 1 = 0
;;
;;; using the half-interval-method, (and knowing that phi is 1.61...)
(comment
(half-interval-method (fn [x] (- (* x x) x 1)) 1.0 2.0)
;;;=>  1.61767578125
)

;;; using fixed point
(comment
(fixed-point (fn [x] (+ 1 (/ 1 x))) 1.0)
;;;=> 1.6180327868852458
)
