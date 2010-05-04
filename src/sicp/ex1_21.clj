(ns sicp.ex1_21
  (:use [sicp utils]
	[clojure.contrib trace test-is]))

;; exercise 1.21
(comment
user> (smallest-divisor 199)
199
user> (smallest-divisor 1999)
1999
user> (smallest-divisor 19999)
7
)
