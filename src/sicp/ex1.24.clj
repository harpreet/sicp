(ns sicp.ex1.24
  (:use [sicp utils]
	[clojure.contrib trace test-is]))

;;; exercise 1.24
(comment
  (microbench 10 (take 3 (filter #(fast-prime? %) (iterate inc 1000))))
  ...
  "I did not observe any difference".
)
