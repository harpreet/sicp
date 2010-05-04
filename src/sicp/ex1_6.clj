(ns sicp.ex1_6
  (:use [sicp utils]
	[clojure.contrib trace test-is]))

;; exercise 1.6
;; Alyssa P. Hacker doesn't see why if needs to be provided as a special form.
;; ``Why can't I just define it as an ordinary procedure in terms of cond?''
(defn new-if [predicate then-clause else-clause]
  (cond predicate then-clause
	:else else-clause))

(new-if (= 3 2) 0 5)  ; 5
(new-if (= 1 1) 0 5)  ; 0

;; Delighted, Alyssa uses new-if to rewrite the square-root program:

(defn sqrt-iter [guess x]
  (new-if (good-enough? guess x)
	  guess
	  (sqrt-iter (improve guess x)
		     x)))

;; what happens when Alyssa attempts to use this to compute square roots? Explain.
(comment
  Since `new-if' is a function, when it is called from sqrt-iter, the parameters
  are evaluated before it gets called. good-enough? will return a false unless
  the guess and x are almost the same. guess evaluated to the initial value of
  guess. sqrt-iter gets evaluated, but gets into an infinite loop. The predicate
  will have no effect.)
