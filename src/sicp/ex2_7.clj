(ns sicp.ex2_7
  (:use [sicp utils ch2_1_extended]
	[clojure.test]))

(defn make-interval [x y]
  (list x y))

(defn lower-bound [i]
  (first i))

(defn upper-bound [i]
  (first (rest i)))
