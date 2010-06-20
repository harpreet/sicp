(ns sicp.ex2_18
  (:use [clojure test]))

(defn turn-around-1 [lst]
  (reduce #(cons %2 %1) '() lst))


(defn turn-around-2 [lst]
  (let [reverse (fn [lst1 lst2]
		  (if (empty? lst1)
		    lst2
		    (recur (rest lst1) (cons (first lst1) lst2))))]
    (reverse lst '())))


(deftest test-turn-around-1
  (are [x y] [= x y]
       (turn-around-1 (list 1 2 3 4)) (list 4 3 2 1)
       (turn-around-1 (list 1 4 9 16 25)) (list 25 16 9 4 1)))

(deftest test-turn-around-2
  (are [x y] [= x y]
       (turn-around-2 (list 1 2 3 4)) (list 4 3 2 1)
       (turn-around-2 (list 1 4 9 16 25)) (list 25 16 9 4 1)))
