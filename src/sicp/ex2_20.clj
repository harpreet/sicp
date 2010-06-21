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

(deftest test-same-parity
  (are [x y] [= x y]
       (same-parity 1 2 3 4 5 6 7) (list 1 3 5 7)
       (same-parity 2 3 4 5 6 7) (list 2 4 6)))

