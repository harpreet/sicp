(ns sicp.ex1_45
  (:use [clojure.contrib test-is]
	[sicp utils]
	[sicp ch1_3]
	[sicp ex1_43]))

(defn nthroot [x n]
  (fixed-point ((repeated average-damp
			  (int (/ (Math/log n) (Math/log 2)))) 
		(fn [y] (/ x (Math/pow y (- n 1)))))
	       1.0))

;; experiments show that average damp needs to e applied log2n times.
(comment
user> (nthroot (* 3 3 3) 3)
;;=> 2.9999972321057697
user> (nthroot (* 3 3 3 3) 4)
; Evaluation aborted.
user> (use 'sicp.ex1_45 :reload)
;;=> nil
user> (nthroot (* 3 3 3 3) 4)
;;=> 3.000000000000033
user> (nthroot (* 3 3 3 3 3) 5)
;;=> 3.0000008877496294
user> (nthroot (* 3 3 3 3 3 3) 5)
;;=> 3.737194011460545
user> (nthroot (* 3 3 3 3 3 3) 6)
;;=> 2.999996785898161
user> (nthroot (* 3 3 3 3 3 3 3) 7)
;;=> 3.0000041735235947
user> (nthroot (* 3 3 3 3 3 3 3 3) 8)
;;=> ; Evaluation aborted.
user> (use 'sicp.ex1_45 :reload)
;;=> nil
user> (nthroot (* 3 3 3 3 3 3 3 3) 8)
;;=> 3.0000000000173292
user> (nthroot (* 3 3 3 3 3 3 3 3 3 3 3 3 3 3) 14)
;;=> 2.9999959148601363
user> (nthroot (* 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3) 15)
;;=> 3.000004202219401
user> (nthroot (* 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3) 16)
;;=> ; Evaluation aborted.
user> (Math/log2 10)
;;=> ; Evaluation aborted.
user> (/ (Math/log 10) (Math/log 2))
;;=> 3.3219280948873626
user> (/ (Math/log 16) (Math/log 2))
;;=> 4.0
user> (use 'sicp.ex1_45 :reload)
;;=> nil
user> (nthroot (* 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3 3) 16)
;;=> 3.0
user> (nthroot (Math/pow 3 16) 16)
;;=> 3.0
user> (nthroot (Math/pow 3 35) 35)
;;=> 3.000000146591681
user> (nthroot (Math/pow 3 50) 50)
;;=> 2.9999967255008917
user>   
)