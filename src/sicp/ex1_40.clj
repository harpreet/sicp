(ns sicp.ex1_40
  (:use [clojure.contrib test-is]
	[sicp utils]
	[sicp ch1_3]))

(defn cubic [a b c]
  (fn [x] (+ (cube x)
	     (* a (square x))
	     (* b x)
	     c)))

(comment
(newton-method (cubic 0 0 (* 3 3 -3)) 1.0)
;;=> 3.0000000000019176
)
