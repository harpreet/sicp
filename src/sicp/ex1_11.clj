(ns sicp.ex1_11
  (:use [sicp utils]
	[clojure.contrib trace test-is]))

;; exercise 1.11: A function f is defined by the rule that f(n) = n if n < 3
;;                and f(n) = f(n - 1) + 2f(n  - 2) + 3f(n - 3) if n> 3.
;;                Write a procedure that computes f by means of a recursive
;;                process. Write a procedure that computes f by means of an
;;                iterative process. 
(defn f [n]
  (if (< n 3)
    n
    (+ (f (- n 1))
       (* 2 (f (- n 2)))
       (* 3 (f (- n 3))))))

;; (comment
;; user> (map f (range 10))
;; (0 1 2 4 11 25 59 142 335 796)  
;; )

;; ex 1.11: iterative version
(defn f-iter [count prev0 prev1 prev2]
  (if (= count 3)
    (+ prev0
       (* 2 prev1)
       (* 3 prev2))
    (f-iter (dec count)
	    (+ prev0
	       (* 2 prev1)
	       (* 3 prev2))
	    prev0
	    prev1)))

(defn f [n]
  (if (< n 3)
    n
    (f-iter n 2 1 0)))

;; ex 1.11: iterative version with let
(defn f-iter [count prev0 prev1 prev2]
  (let [res (+ prev0 (* 2 prev1) (* 3 prev2))]
    (if (= count 3)
      res
      (f-iter (dec count)
	      res
	      prev0
	      prev1))))
