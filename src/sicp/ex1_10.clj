(ns sicp.ex1_10
  (:use [sicp utils]
	[clojure.contrib trace test-is]))

;; exercise 1.10
;; ackerman functions
(defn A [x y]
  (cond (= y 0) 0
	(= x 0) (* 2 y)
	(= y 1) 2
	:else (A (- x 1)
		 (A x (- y 1)))))

;; (comment
;; user> (A 1 10)
;; 1024
;; user> (A 2 4)
;; 65536
;; user> (A 3 3)
;; 65536
;; )

(defn f [n] (A 0 n)) ; f(n) = 2n
(defn g [n] (A 1 n)) ; g(n) = 2^n
;; (comment
;;   g (n) = A (1,n)
;;         = A (0, A (1, n-1)) = f (A(1,n-1))
;; 	  = f (f (1,n-2)) 
;; 	    .....
;; 	    = f (f (f ... f (1,(n- (n-1)))))
;; 	      = f (f (f ... 2))
;; 		= 2 * (2^(n-1))
;; 		  = 2^n
;; )

(defn h [n] (A 2 n)) ; h(n) = 2^(n^2)
