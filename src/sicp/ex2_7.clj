(ns sicp.ex2_7
  (:use [sicp utils ch2_1_extended]
	[clojure.test]))

(defn make-interval [x y]
  (list x y))

(defn upper-bound [i]
  (first i))

(defn lower-bound [i]
  (first (rest i)))
