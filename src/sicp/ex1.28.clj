(ns sicp.ex1.28
  (:use [sicp utils]
	[clojure.contrib trace test-is]))


;;; exercise 1.28
(defn expmod2 [base exp m]
  (cond (= exp 0) 1
	(even? exp) (square-test (expmod2 base (/ exp 2) m) m)
	:else (rem (* base (expmod2 base (dec exp) m))
		   m)))

(defn square-test [x m]
  (if (and (not (or (= x 1) (= x (- m 1))))
	   (= (rem (square x) m) 1))
    0
    (rem (square x) m)))

(defn miller-rabin-test [n]
  (try-it (+ 2 (rand-int (- n 2)))
	  n))

(defn try-it [a n]
  (= (expmod2 a (- n 1) n) 1))

(comment
  "If the random number generated (a) is 1, then this returns false
   positives. So generate random numbers between 2 and n-1. (is this
   assumption correct?) "
)