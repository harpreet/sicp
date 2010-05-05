(ns sicp.ex1_31
  (:use [clojure.contrib test-is]
	[sicp utils]))

(defn product [a b]
  (if (> a b)
    1
    (* a (product (+ a 1) b))))

(defn prod [term a next b]
  (if (> a b)
    1
    (* (term a)
       (prod term (next a) next b))))

;; factorial
;; fact (n) = n * factorial (n - 1)

(defn factorial [n]
  (prod identity 1 inc n))

(deftest test-factorial-of-6
  (is (= (factorial 6)
	 (reduce * (range 1 7)))))

(deftest test-factorial-of-10
  (is (= (factorial 10)
	 (reduce * (range 1 11)))))

(defn prod-pi [a b]
  (prod #(/ (* % (+ % 2)) (square (+ % 1.0))) a #(+ % 2) b))

(deftest test-pi-2-digits-accuracy
  (is (= (> (Math/abs (- 3.14159 (* 4 (prod-pi 2 1000)))) 0.001) true)))

(defn iter-prod [term a next b result]
  (if (> a b)
    result
    (iter-prod term (next a) next b (* a result))))

(defn iprod [term a next b]
  (iter-prod term a next b 1))

(deftest test-iprod-for-prod-of-1-to-10
  (is (= (iprod identity 1 inc 10)
	 (prod identity 1 inc 10))))
