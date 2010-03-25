(ns sicp.chapter1.ch1_1)

(defn square [x] (* x x))

(defn sum-of-squares [x y]
  (+ (square x) (square y)))

(defn f [a]
  (sum-of-squares (+ a 1) (* a 2)))

(defn abs
  "find absolute value of x"
  [x]
  (if (< x 0) (- x) x))

(defn abs1 [x]
  (cond (< x 0) (- x)
	:else x))

;; exercise 1.1: What is the result printed by the interpreter in response
;;               to each expression (in order) ?

(def a 3)
(def b (+ a 1))

(+ a b (* a b))  ; 19
(= a b) ; false

(if (and (> b a) (< b (* a b)))
    b
    a)   ; 4

(cond (= a 4) 6
      (= b 4) (+ 6 7 a)
      :else 25)  ; 16

(+ 2 (if (> b a) b a)) ; 6

(* (cond (> a b) a
	 (< a b) b
	 :else -1)
   (+ a 1))      ; 16

;; exercise 1.2: Translate the given expression into prefix form
(/ (+ 5 4 (- 2 (- 3 (+ 6 (/ 1 5)))))
   (* 3 (- 6 2) (- 2 7)))  ; -71/300

;; exercise 1.3:  Define a procedure that takes three numbers as
;;                arguments and returns the sum of the squares of
;;                the two larger numbers.
(defn sort3 [a b c]
  (cond (> b a) (sort3 b a c)
	(< b c) (sort3 a c b)
	:else [a b c]))

(defn sum-of-sq-of-two-largest [a b c]
  (apply sum-of-squares (take 2 (sort3 a b c))))

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

;; 1.1.7 Square root finding using Newton's method
(defn sqrt-iter [guess x]
  (if (good-enough? guess x)
    guess
    (sqrt-iter (improve guess x)
	       x)))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn average [x y]
  (/ (+ x y) 2))

(defn good-enough? [guess x]
  (< (abs (- (square guess) x)) 0.001))

(defn sqrt [x]
  (sqrt-iter 1.0 x))

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