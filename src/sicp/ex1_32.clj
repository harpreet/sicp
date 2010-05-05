(ns sicp.ex1_32
  (:use [sicp utils]
	[clojure.contrib test-is]))

(defn accumulate [combiner null-value term a next b]
  (if (> a b)
    null-value
    (combiner (term a)
	      (accumulate combiner null-value term (next a) next b))))

(def sum (fn [a b] (accumulate + 0 identity a inc b)))

(def sum-cube (fn [a b] (accumulate + 0 cube a inc b)))

(def prod (fn [a b] (accumulate * 1 identity a inc b)))

(defn iaccumulate [combiner null-value term a next b]
  (iter combiner null-value term a next b null-value))

(defn iter [combiner null-value term a next b result]
  (if (> a b)
    result
    (iter combiner null-value term (next a) next b (combiner (term a) result))))

(deftest test-sum-of-integers-from-1-to-10
  (is (= (sum 1 10) (reduce + (range 1 11)))))

(deftest test-sum-cube-of-integers-from-1-to-10
  (is (= (sum-cube 1 10) (reduce + (map cube (range 1 11))))))

(deftest test-prod-of-ints-from-1-to-10
  (is (= (prod 1 10) (reduce * (range 1 11)))))

(def isum (fn [a b] (iaccumulate + 0 identity a inc b)))

(def isum-cube (fn [a b] (iaccumulate + 0 cube a inc b)))

(def iprod (fn [a b] (iaccumulate * 1 identity a inc b)))

(deftest test-isum-of-integers-from-1-to-10
  (is (= (isum 1 10) (reduce + (range 1 11)))))

(deftest test-isum-cube-of-integers-from-1-to-10
  (is (= (isum-cube 1 10) (reduce + (map cube (range 1 11))))))

(deftest test-iprod-of-ints-from-1-to-10
  (is (= (iprod 1 10) (reduce * (range 1 11)))))
