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


;; too boring a problem. Skipping the rest of it.
