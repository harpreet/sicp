(ns sicp.ex1_8
  (:use [sicp utils]
	[clojure.contrib trace test-is]))

;; exercise 1.8: cube root
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
