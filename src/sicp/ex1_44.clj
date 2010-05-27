(ns sicp.ex1_44
  (:use [clojure.contrib test-is]
	[sicp utils]
	[sicp ch1_3]
	[sicp ex1_43]))

(def dx1 0.00001)

(defn smooth [f]
  (fn [x]
    (average (f (- x dx1))
	     (f x)
	     (f (+ x dx1)))))

;; smooth takes a function as argument. repeated takes a function
;; which needs to be repeated. Here it is the smooth function and
;; the number of times it needs to be repeated. We call the function
;; returned by repeated with f.
(defn smooth-repeatedly [f n]
  ((repeated smooth n) f))

;; borrowing an idea from Drew Hess' solutions, we generate a test impulse
;; function
(defn impulse-maker [x a]
  (fn [y] (if (= y x)
	    a
	    0)))

;; we define a function which has an impulse at x=2 with a value 10.
(defn my-impulse-func [x]
  ((impulse-maker 2 10) x))

(comment
((smooth my-impulse-func) 2)
;;=> 3.3333333
user> ((smooth my-impulse-func) 1)
;;=> 0.0
user> ((smooth (smooth my-impulse-func)) 2)
;;=> 3.3333333
user> ((smooth (smooth (smooth my-impulse-func))) 2)
;;=> 2.5925925

;; now using our new function
((smooth-repeatedly my-impulse-func 1) 2)
;;=> 3.3333333
user> ((smooth-repeatedly my-impulse-func 2) 2)
;;=> 3.3333333
user> ((smooth-repeatedly my-impulse-func 3) 2)
;;=> 2.5925925
)