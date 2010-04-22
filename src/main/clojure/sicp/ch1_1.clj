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
(defn average [x y]
  (/ (+ x y) 2))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn good-enough? [guess x]
  (< (abs (- (square guess) x)) 0.001))

(defn sqrt-iter [guess x]
  (if (good-enough? guess x)
    guess
    (sqrt-iter (improve guess x)
	       x)))

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
;; An alternative strategy for implementing good-enough? is to watch how guess
;; changes from one iteration to the next and to stop when the change is a very
;; small fraction of the guess.
(defn sqrt-iter [old-guess new-guess x]
  (if (good-enough? old-guess new-guess x)
    new-guess
    (sqrt-iter new-guess (improve new-guess x)
	       x)))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn average [x y]
  (/ (+ x y) 2))

(defn good-enough? [old-guess new-guess x]
  (< (/ (abs (- new-guess old-guess)) new-guess) 0.0001))

(defn sqrt [x]
  (sqrt-iter x 1.0 x))
(comment
user> (sqrt (square 0.01))
0.010000000025490743
user> (sqrt (square 0.001))
0.0010000000000000117
user> (sqrt (square 0.0001))
1.0000000000082464E-4
user> (sqrt (square 0.02))
0.020000000050877154
user> (sqrt (square 0.002))
0.0020000000000000235
user> (sqrt (square 4))
4.000000000000051
user> (sqrt (square 20))
20.000000000298428
user> (sqrt (square 25))
25.000000063076968
user> (sqrt 5)
2.236067977499978
user> (sqrt 25)
5.000000000053722
user> (sqrt 9)
3.000000001396984
user> (sqrt 81)
9.000000000007091
)
;; exercise 1.8: cube root
(defn cube [x]
  (* x x x))

(defn improve [guess x]
  (/ (+ (/ x (square guess)) (* 2 guess)) 3))

(defn cubert-iter [old-guess new-guess x]
  (if (good-enough? old-guess new-guess x)
    new-guess
    (cubert-iter new-guess (improve new-guess x)
		 x)))

(defn cuberoot [x]
  (cubert-iter x 1.0 x))

(comment
user> (cuberoot (cube 2))
2.000000000012062
user> (cuberoot (cube 10))
10.000000000000002
user> (cuberoot (cube 9))
9.000000000053902
user> (cuberoot (cube 0.001))
0.001000000000000962
user> (cuberoot (cube 0.0001))
1.000000000000001E-4
user>
)
;; section 1.1.8
;; hiding the non-public procedure definitions
(defn- sqrt-iter [guess x]
  (if (good-enough? guess x)
    guess
    (sqrt-iter (improve guess x)
	       x)))

(defn- improve [guess x]
  (average guess (/ x guess)))

(defn- average [x y]
  (/ (+ x y) 2))

(defn- good-enough? [guess x]
  (< (abs (- (square guess) x)) 0.001))

(defn sqrt [x]
  (sqrt-iter 1.0 x))
