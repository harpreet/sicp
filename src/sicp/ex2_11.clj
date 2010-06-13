(ns sicp.ex2_11
  (:use [sicp utils ch2_1_extended ex2_7]
	[clojure.test]))

;; let [l1, u2] be lower and upper bound of range x
;; and [l2, u2] that of range y.

;; based on the signs of l1, u1, l2, u2, we have 16 combinations

;; l1 :u1 :l2 :u2
;; +   +   +   +
;; +   +   +   -
;; +   +   -   +
;; +   +   -   -
;; +   -   +   +
;; +   -   +   -
;; +   -   -   +
;; +   -   -   -
;; -   +   +   +
;; -   +   +   -
;; -   +   -   +
;; -   +   -   -
;; -   -   +   +
;; -   -   +   -
;; -   -   -   +
;; -   -   -   -

;; Now, if lower bound is +ve and upper is -ve, then it is invalid. So,
;; l1 :u1 :l2 :u2
;; +   +   +   +
;; +   +   +   -  => invalid
;; +   +   -   +
;; +   +   -   -
;; +   -   +   +  => invalid
;; +   -   +   -  => invalid
;; +   -   -   +  => invalid
;; +   -   -   -  => invalid
;; -   +   +   +
;; -   +   +   -  => invalid
;; -   +   -   +
;; -   +   -   -
;; -   -   +   +
;; -   -   +   -  => invalid
;; -   -   -   +
;; -   -   -   -

(defn new-mul-interval [x y]
  (let [l1 (lower-bound x)
	u1 (upper-bound x)
	l2 (lower-bound y)
	u2 (upper-bound y)
	sl1? (pos? l1)
	su1? (pos? u1)
	sl2? (pos? l2)
	su2? (pos? u2)]
    (cond (and sl1? su1? sl2? su2?)             (make-interval (* l1 l2) (* u1 u2))
	  (and sl1? su1? (not sl2?) su2?)       (make-interval (* u1 l2) (* u1 u2))
	  (and sl1? su1? (not sl2?) (not su2?)) (make-interval (* u1 l2) (* u2 l1))
	  (and (not sl1?) su1? sl2? su2?)       (make-interval (* l1 u2) (* u1 u2))
	  (and (not sl1?) su1? (not sl2?) (not su2?)) (make-interval (* u1 l2) (* l1 l2))
	  (and (not sl1?) (not su1?) sl2? su2?) (make-interval (* l1 u2) (* u1 l2))
	  (and (not sl1?) (not su1?) (not sl2?) su2?) (make-interval (* l1 u2) (* l1 l2))
	  (and (not sl1?) (not su1?) (not sl2?) (not su2?)) (make-interval (* u1 u2) (* l1 l2))
	  (and (not sl1?) su1? (not sl2?) su2?) (let [p1 (* l1 l2)
						      p2 (* l1 u2)
						      p3 (* u1 l2)
						      p4 (* u1 u2)]
						  (make-interval (min p1 p2 p3 p4)
								 (max p1 p2 p3 p4))))))