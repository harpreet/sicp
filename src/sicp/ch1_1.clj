(ns sicp.ch1-1
  (:use sicp.utils))

(defn sum-of-squares [x y]
  (+ (square x) (square y)))

(defn ff [a]
  (sum-of-squares (+ a 1) (* a 2)))

(defn abs1 [x]
  (cond (< x 0) (- x)
	:else x))

;; 1.1.7 Square root finding using Newton's method
(defn average [x y]
  (/ (+ x y) 2))

(defn improve [guess x]
  (average guess (/ x guess)))

(defn good-enough? [guess x]
  (< (myabs (- (square guess) x)) 0.001))

(defn sqrt-iter [guess x]
  (if (good-enough? guess x)
    guess
    (sqrt-iter (improve guess x)
	       x)))

(defn sqrt [x]
  (sqrt-iter 1.0 x))


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
  (< (/ (myabs (- new-guess old-guess)) new-guess) 0.0001))

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
  (< (myabs (- (square guess) x)) 0.001))

(defn sqrt [x]
  (sqrt-iter 1.0 x))
