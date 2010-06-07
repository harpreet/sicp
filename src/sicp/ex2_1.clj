(ns sicp.ex2_1
  (:use [sicp ch2_1 utils]
	[clojure.test]))

;; the gcd version of make-rat does this automatically for us. But assuming
;; we don't use gcd, here is the solution
(defn new-make-rat [x y]
  (cond (and (< x 0) (< y 0)) (list (- x) (- y))
	(or (< x 0) (< y 0)) (if (< x 0) (list x y) (list (- x) (- y)))
	:else (list x y)))

(deftest test-num-p-den-p
  (are [x y] (= x y)
       (numer (new-make-rat 2 3)) 2
       (denom (new-make-rat 2 3)) 3))

(deftest test-num-p-den-n
  (are [x y] (= x y)
       (numer (new-make-rat 2 -3)) -2
       (denom (new-make-rat 2 -3)) 3))

(deftest test-num-n-den-n
  (are [x y] (= x y)
       (numer (new-make-rat -2 -3)) 2
       (denom (new-make-rat -2 -3)) 3))

(deftest test-num-n-den-p
  (are [x y] (= x y)
       (numer (new-make-rat -2 3)) -2
       (denom (new-make-rat -2 3)) 3))