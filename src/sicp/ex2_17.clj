(ns sicp.ex2_17
  (:use [sicp utils ch2_2]
	[clojure test]))

(defn last-pair [lst]
  (if (= (length lst) 1)
    lst
    (last-pair (rest lst))))

(deftest test-last-pair-1
  (are [x y] [= x y]
       (last-pair (list 1 2 3 4)) (list 4)
       (last-pair (list 23 72 149 34)) (list 34)))