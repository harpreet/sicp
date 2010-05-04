(ns sicp.ex1_16
  (:use [sicp utils]
	[clojure.contrib trace test-is]))

;; exercise 1.16:
(defn myexpt [b n]
  (expt-iter b n 1))

(defn expt-iter [b n a]
  (cond (= n 0) a
	(even? n) (expt-iter (square b) (/ n 2) a)
	:else (expt-iter b (- n 1) (* a b))))
