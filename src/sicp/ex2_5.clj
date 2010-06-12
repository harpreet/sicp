(ns sicp.ex2_5
  (:use [sicp utils]
	[clojure.test]))

(defn my-cons [x y]
  (* (int (Math/pow 2 x))
     (int (Math/pow 3 y))))

(defn- power-of-n* [x n cnt]
  (let [q (quot x n)
	r (rem x n)]
    (if (= r 0)
      (power-of-n* (quot x n) n (inc cnt))
      cnt)))

(defn car [x]
  (power-of-n* x 2 0))

(defn cdr [x]
  (let [p3 (quot x (Math/pow 2 (car x)))]
    (power-of-n* p3 3 0)))

(deftest test-car-for-various-cons-cell-values
  (let [c1 (my-cons 2 3)
	c2 (my-cons 2 0)
	c3 (my-cons 0 0)
	c4 (my-cons 0 1)]
    (are [p] [= p true]
	 (and (= (car c1) 2)
	      (= (cdr c1) 3))
	 (and (= (car c2) 2)
	      (= (cdr c2) 0))
	 (and (= (car c3) 0)
	      (= (cdr c3) 0))
	 (and (= (car c4) 0)
	      (= (cdr c4) 1)))))
