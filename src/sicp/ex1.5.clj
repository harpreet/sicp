(ns sicp.ex1.5
  (:use [sicp utils]
	[clojure.contrib trace test-is]))

;; exercise 1.5:  Ben Bitdiddle has invented a test to determine
;;                whether the interpreter he is faced with is
;;                using applicative-order evaluation or normal-order
;;                evaluation. He defines the following two procedures:
;; (defn p [] (p))
;; (defn test [x y]
;;   (if (= x 0)
;;    0
;;    y))
;;
;; Then he evaluates the expression
;;
;; (test 0 (p))
;;
;; What behavior will Ben observe with an interpreter that uses
;; applicative-order evaluation?
(comment
  In the case of applicative order evaluation, the test gets into
  and infinite loop (eventually using all the stack), as the parameters
  are evaluated before they are actualy used in the function.
  )
;; What behavior will he observe with an interpreter that uses
;; normal-order evaluation? Explain your answer.
(comment
  It will print 0, as (p) is not evaluated in this case.
  )
