(ns sicp.ex1.12
  (:use [sicp utils]
	[clojure.contrib trace test-is]))

;; exercise 1.12.  The following pattern of numbers is called Pascal's triangle.
;;          1
;;        1   1
;;      1   2   1
;;    1   3   3   1
;;  1   4   6   4   1
;; ...................
;;
;;                 The numbers at the edge of the triangle are all 1, and each
;;                 number inside the triangle is the sum of the two numbers above
;;                 it. Write a procedure that computes elements of Pascal's triangle
;;                 by means of a recursive process. 
(defn pascal [row col]
  (when (<= col row)
    (if (or (= col 0) (= row col))
      1
      (+ (pascal (dec row) col)
	 (pascal (dec row) (dec col))))))
