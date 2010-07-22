(ns sicp.ex2_20
  (:use [clojure test]
	[sicp utils]))
;; clojure has a slightly different notation compared to Scheme for the
;; arbitraty number of argument notation.
(defn same-parity [x & xs]
  (cons x (filter (fn [y] (if (even? x)
			    (even? y)
			    (odd? y)))
		  xs)))

;; another implementation
(defn- calc-parity [pred xs result]
  (if (empty? xs)
    (reverse result)
    (let [a (first xs)
	t (pred a)]
      (if t
	(calc-parity pred (rest xs) (cons a result))
	(calc-parity pred (rest xs) result)))))

(defn same-parity-2 [x & xs]
  (if (even? x)
    (cons x (calc-parity even? xs '()))
    (cons x (calc-parity odd? xs '()))))

(defn- calc-parity-3 [pred xs]
  (if (empty? xs)
    nil
    (let [a (first xs)
	t (pred a)]
      (if t
	(cons a (calc-parity-3 pred (rest xs)))
	(calc-parity-3 pred (rest xs))))))

(defn same-parity-3 [x & xs]
  (if (even? x)
    (cons x (calc-parity-3 even? xs))
    (cons x (calc-parity-3 odd? xs))))

(defn same-parity-4 [x & xs]
  (conj (filter #(if (even? x) (even? %) (odd? %)) xs) x))

;; result of the discussion in bang-fp
(defn same-parity-5 [x & xs]
  (cond (nil? xs) (list x)
	(= (even? x) (even? (first xs))) (cons x (apply same-parity-5 xs))
	:else (apply same-parity-5 (cons x (rest xs)))))

(deftest test-same-parity
  (are [x y] [= x y]
       (same-parity 1 2 3 4 5 6 7) (list 1 3 5 7)
       (same-parity 2 3 4 5 6 7) (list 2 4 6)))

(deftest test-same-parity-2
  (are [x y] [= x y]
       (same-parity-2 1 2 3 4 5 6 7) (list 1 3 5 7)
       (same-parity-2 2 3 4 5 6 7) (list 2 4 6)))

(deftest test-same-parity-3
  (are [x y] [= x y]
       (same-parity-3 1 2 3 4 5 6 7) (list 1 3 5 7)
       (same-parity-3 2 3 4 5 6 7) (list 2 4 6)))

(deftest test-same-parity-4
  (are [x y] [= x y]
       (same-parity-4 1 2 3 4 5 6 7) (list 1 3 5 7)
       (same-parity-4 2 3 4 5 6 7) (list 2 4 6)))

(deftest test-same-parity-5
  (are [x y] [= x y]
       (same-parity-5 1 2 3 4 5 6 7) (list 1 3 5 7)
       (same-parity-5 2 3 4 5 6 7) (list 2 4 6)))
