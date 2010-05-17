;; finite continued fraction
;;
;; f = N1/ (D1 + (N2/ D2 + (N3/ D3 + (.... + Nk/Dk))))

(ns sicp.ex1_37
  (:use [sicp utils]
	[clojure.contrib test-is]))

(defn- calc-cont-frac [n-fn d-fn k cnt]
  (if (= cnt k)
    (/ (n-fn cnt) (d-fn cnt))
    (/ (n-fn cnt) (+ (d-fn cnt) (calc-cont-frac n-fn d-fn k (inc cnt))))))

(defn cont-frac [n-fn d-fn k]
  (calc-cont-frac n-fn d-fn k 1))

(defn good-enough? [x y tolerance]
  (< (abs (- x y)) tolerance))

(deftest test-cont-frac-of-all-one
  (is (good-enough? (/ 1.0 (cont-frac (fn [_] 1.0) (fn [_] 1.0) 100))
		     1.618
		     0.01)))

;; iterative version
;; work backwards from kth value to 1st value.
(defn- calc-cont-frac-i [n-fn d-fn k cnt result]
  (if (= cnt 0)
    result
    (calc-cont-frac-i n-fn d-fn k (dec cnt) (/ (n-fn cnt)
					     (+ (d-fn cnt)
						result)))))

(defn cont-frac-iter [n-fn d-fn k]
  (calc-cont-frac-i n-fn d-fn k k 0.0))

(deftest test-cont-frac-iter-of-all-one
  (is (good-enough? (/ 1.0 (cont-frac-iter (fn [_] 1.0) (fn [_] 1.0) 100))
		    1.618
		    0.01)))
