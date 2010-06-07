(ns sicp.ex2_4
  (:use [sicp utils]
	[clojure.test]))

(defn cons [x y]
  (fn [f] (f x y)))

(defn car [x]
  (x (fn [p q] p)))

(defn cdr [x]
  (x (fn [p q] q)))

;; cons takes x and y and returns a function which takes a param as input
;; which is nother function and calls that function with x and y. Now
;; car of a cons cell, calls this function returned by cons, with another
;; function as input parameter. Remember that cons returns a func which takes
;; another func as input. This function passed takes 2 inputs p and q and
;; returns p in the case of car and q for cdr.
