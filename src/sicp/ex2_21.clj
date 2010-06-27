(ns sicp.ex2_21
  (:use [clojure test]
	[sicp [utils :only (square)]]))

(defn square-list-1 [items]
  (if (empty? items)
    nil
    (cons (square (first items))
	  (square-list-1 (rest items)))))

(deftest test-square-list-1
  (is (= (square-list-1 (list 1 2 3 4)) (list 1 4 9 16))))

(defn square-list-2 [items]
  (map (fn [x] (square x)) items))

(deftest test-square-list-2
  (is (= (square-list-2 (list 1 2 3 4)) (list 1 4 9 16))))
