(ns sicp.ex1.7
  (:use [sicp utils]
	[clojure.contrib trace test-is]))

;; Exercise 1.7: The good-enough? test used in computing square roots will not
;; be very effective for finding the square roots of very small numbers. Also,
;; in real computers, arithmetic operations are almost always performed with
;; limited precision. This makes our test inadequate for very large numbers.
;; Explain these statements, with examples showing how the test fails for small
;; and large numbers.
(comment
 user> (sqrt (square 0.001))
 0.031260655525445276
 user> (sqrt (square 0.2))
 0.20060990407779591
 user> (sqrt (square 0.01))
 0.03230844833048122
 user> (sqrt (square 0.02))
 0.0354008825558513
 user> (sqrt (square 10))
 10.000000000139897
 user> (sqrt (square 100))
 100.00000025490743
 user> (sqrt (square 200))
 200.000000510076
 user> (sqrt (square 2))
 2.0000000929222947
 user> (sqrt (square 0.1))
 0.10032578510960607
 user> (sqrt (square 0.01))
 0.03230844833048122
 user> (sqrt (square 10000))
 10000.0
 user> (sqrt (square 20000))
 20000.0
 user> (sqrt (square 200000))
 200000.0
 user> (sqrt (square 20000000))
 2.0E7
 user> (sqrt (square 20000000000))
 2.0E10
 user> (sqrt (square 200000.012))
 200000.012
 user> (sqrt (square 2000000.123))
 2000000.123
 user> (sqrt (square 200000000.123))
 2.00000000123E8
 user> (sqrt (square 2000000000.123))
 2.000000000123E9
 user> (sqrt (square 20000000000.123))
 2.0000000000123E10
 user> (sqrt (square 2000000000000.123))
 2.000000000000123E12
 )