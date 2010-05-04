
(ns sicp.ch1-3
  (:use [sicp utils]
	[clojure.contrib test-is]))

;; 1.3.1: Procedures as arguments
(defn sum-integers [a b]
  (if (> a b)
    0
    (+ a (sum-integers (+ a 1) b))))

(defn sum-cubes [a b]
  (if (> a b)
    0
    (+ (cube a) (sum-cubes (+ a 1) b))))

(defn pi-sum [a b]
  (if (> a b)
    0
    (+ (/ 1.0 (* a (+ a 2))) (pi-sum (+ a 1) b))))

(defn sum [term a next b]
  (if (> a b)
    0
    (+ (term a)
       (sum term (next a) next b))))

(def sum-cubes-new (fn[a b] (sum cube a inc b)))

(deftest test-sum-of-first-10-integers
  (is (sum #(identity %) 1 inc 10) 55))

;; (* (sum #(/ 1.0 (* % (+ % 2))) 1 #(+ % 4) 1000) 8)
;;=> 3.139592655589783  (approaches PI)

(defn integral [f a b dx]
  (* (sum f (+ a (/ dx 2)) #(+ % dx) b)
     dx))

(integral cube 0 1 0.001)
;;=>0.249999875000001
(integral cube 0 1 0.005)
;;=>0.24999687500000028