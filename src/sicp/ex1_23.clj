(ns sicp.ex1_23
  (:use [sicp utils]
	[clojure.contrib trace test-is]))

;;; exercise 1.23
(defn next-divisor [n]
  (if (= n 2)
    3
    (+ n 2)))


(defn find-divisor [n test-divisor]
  (cond (> (square test-divisor)  n) n
	(divides? test-divisor n) test-divisor
	:else (find-divisor n (next-divisor test-divisor))))

(comment
  I can't see any noticable difference in the speed.
)
