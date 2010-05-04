(ns sicp.ex1_19
  (:use [sicp utils]
	[clojure.contrib trace test-is]))

;; exercise 1.19: fast fibonacci
;; see the pdf of the notebook scan for the derivation of p' and q'
(defn ffib-iter [a b p q count]
  (cond (= count 0) b
	(even? count)
	(ffib-iter a
		   b
		   (+ (* p p) (* q q))
		   (+ (* 2 p q) (* q q))
		   (/ count 2))
	:else (ffib-iter (+ (* b q) (* a q) (* a p))
			 (+ (* b p) (* a q))
			 p
			 q
			 (- count 1))))

(defn ffib [n]
  (ffib-iter 1 0 0 1 n))
