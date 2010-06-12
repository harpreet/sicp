(ns sicp.ex2_12
  (:use [sicp utils ch2_1_extended ex2_7]
	[clojure.test]))

(defn make-center-percent [c p]
  (let [u (+ c (* c p 0.01))
	l (- c (* c p 0.01))]
    (make-interval l u)))

(defn percentage [i]
  (let [c (center i)
	l (lower-bound i)
	u (upper-bound i)]
    (* (/ (- u c) c) 100)))