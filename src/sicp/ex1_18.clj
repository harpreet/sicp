(ns sicp.ex1_18
  (:use [sicp utils]
	[clojure.contrib trace test-is]))

;; exercise 1.18: iterative multiply thru addition
;; the idea is to keep a state variable.
(defn fast-mult-iter [a b k]
  (cond (= b 0) k
	(even? b) (fast-mult-iter (twice a) (half b) k)
	:else (fast-mult-iter a (- b 1) (+ k a))))

(defn fast-mult-new [a b]
  (fast-mult-iter a b 0))

;; (comment
;; user> (dotrace [fast-mult-new fast-mult-iter] (fast-mult-new 2 3))
;; TRACE t2915: (fast-mult-new 2 3)
;; TRACE t2916: |    (fast-mult-iter 2 3 0)
;; TRACE t2917: |    |    (fast-mult-iter 2 2 2)
;; TRACE t2918: |    |    |    (fast-mult-iter 4 1 2)
;; TRACE t2919: |    |    |    |    (fast-mult-iter 4 0 6)
;; TRACE t2919: |    |    |    |    => 6
;; TRACE t2918: |    |    |    => 6
;; TRACE t2917: |    |    => 6
;; TRACE t2916: |    => 6
;; TRACE t2915: => 6
;; 6
;; )
