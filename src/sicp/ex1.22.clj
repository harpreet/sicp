(ns sicp.ex1.22
  (:use [sicp utils]
	[clojure.contrib trace test-is]))

;; exercise 1.22
(defn timed-prime-test [n]
  (prn)
  (print n)
  (start-prime-test n (System/nanoTime)))

(defn start-prime-test [n start-time]
  (if (prime? n)
    (report-prime (- (System/nanoTime) start-time))))

(defn report-prime [elapsed-time]
  (print " *** ")
  (print elapsed-time))

(defn search-for-primes [a b]
  (cond (>= a b) nil
	(even? a) (search-for-primes (+ 1 a) b)
	(timed-prime-test a) (search-for-primes (+ 2 a) b)
	:else (search-for-primes (+ 2 a) b)))

;;; three smallest primes greater than 1000
;;; 1009, 1013, 1019
(take 3 (filter #(prime? %) (iterate inc 1000)))
;=> (1009 1013 1019)

;=> 0.9642028750000001

;;; > 10,000: 10007, 10009, 10037
(take 3 (filter #(prime? %) (iterate inc 10000)))
;=> (10007 10009 10037)

;=> 1.5897884999999998

;;; > 100,000: 100003, 100019, 100043
(take 3 (filter #(prime? %) (iterate inc 100000)))
;=> (100003 100019 100043)

;=> 1.8525091250000003

;;; > 1,000,000: 1000003, 1000033, 1000037
(take 3 (filter #(prime? %) (iterate inc 1000000)))
;=> (1000003 1000033 1000037)

;=> 1.908832125

;; time taken seem to increase as the range increases.
;; but they are totally random on the jvm, so I can't find
;; the exact relation.

(comment
user> (microbench 10 (take 3 (filter #(prime? %) (iterate inc 1000))))
Warming up!
Benchmarking...
(1009 1013 1019)
(1009 1013 1019)
(1009 1013 1019)
(1009 1013 1019)
(1009 1013 1019)
(1009 1013 1019)
(1009 1013 1019)
(1009 1013 1019)
(1009 1013 1019)
(1009 1013 1019)
Total runtime:  0.28404500000000005
Highest time :  0.083949
Lowest time  :  0.019416
Average      :  0.022585000000000008
(0.083949 0.019416 0.023257 0.020394 0.024165 0.024514 0.024374 0.020813 0.021721 0.021442)
user> (microbench 10 (take 3 (filter #(prime? %) (iterate inc 10000))))
Warming up!
Benchmarking...
(10007 10009 10037)
(10007 10009 10037)
(10007 10009 10037)
(10007 10009 10037)
(10007 10009 10037)
(10007 10009 10037)
(10007 10009 10037)
(10007 10009 10037)
(10007 10009 10037)
(10007 10009 10037)
Total runtime:  0.26462800000000003
Highest time :  0.067118
Lowest time  :  0.020533
Average      :  0.022122125000000006
(0.067118 0.022698 0.024095 0.023537 0.020533 0.020882 0.020603 0.021372 0.020603 0.023187)
user> (microbench 10 (take 3 (filter #(prime? %) (iterate inc 100000))))
Warming up!
Benchmarking...
(100003 100019 100043)
(100003 100019 100043)
(100003 100019 100043)
(100003 100019 100043)
(100003 100019 100043)
(100003 100019 100043)
(100003 100019 100043)
(100003 100019 100043)
(100003 100019 100043)
(100003 100019 100043)
Total runtime:  0.265118
Highest time :  0.073263
Lowest time  :  0.020254
Average      :  0.021450125000000004
(0.073263 0.023048 0.023467 0.021022 0.020394 0.020254 0.021302 0.020812 0.020743 0.020813)  
)

;;; can't make out any sqrt(10) relation between the numbers. may be because
;;; jvm compilation is doing some behind the scene tricks.
