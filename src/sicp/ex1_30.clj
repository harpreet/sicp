(ns sicp.ex1_30
  (:use [sicp utils]
	[clojure.contrib test-is]))

(defn sum [term a next b]
  (letfn [(iter-fn [a result]
		   (if (> a b)
		     result
		     (iter-fn (next a) (+ result (term a)))))]
    (iter-fn a 0)))