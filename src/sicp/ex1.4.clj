(ns sicp.ex1.4
  (:use [sicp utils]
	[clojure.contrib trace test-is]))

;; exercise 1.4: Observe that our model of evaluation allows for
;;               combinations whose operators are compound
;;               expressions. Use this observation to describe the
;;               behavior of the following procedure:
;; (defn a-plus-abs-b [a b]
;;   ((if (> b 0) + -) a b))
(comment
  If b is positive, we do (+ a b) and if it is negative, we do (- a b).
  This makes use of the fact that the first element in a list is an
  operand. Here, the operand is chosen based on other operators.
  )
