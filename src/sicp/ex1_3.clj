(ns sicp.ex1_3
  (:use [sicp utils]
	[clojure.contrib trace test-is]))

;; exercise 1.3:  Define a procedure that takes three numbers as
;;                arguments and returns the sum of the squares of
;;                the two larger numbers.
(defn sort3 [a b c]
  (cond (> b a) (sort3 b a c)
	(< b c) (sort3 a c b)
	:else [a b c]))

(defn sum-of-sq-of-two-largest [a b c]
  (apply sum-of-squares (take 2 (sort3 a b c))))

;; well, I cheated above. Let me use only the constructs introduced
;; so far in the book. (follows after the sicp meetup #2 on 28/mar/2010.
(defn sum-of-square-of-two-largest [a b c]
  (if (> a b)
    (if (> b c)
      (sum-of-squares a b) ; a > b > c
      (sum-of-squares a c))
    (if (> a c)
      (sum-of-squares b a)
      (sum-of-squares b c))))
