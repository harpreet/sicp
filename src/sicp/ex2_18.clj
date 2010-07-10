(ns sicp.ex2_18
  (:use [clojure test])
  (:refer-clojure :exclude (reverse)))

(defn reverse-1 [lst]
  (reduce #(cons %2 %1) '() lst))


(defn reverse-2 [lst]
  (let [reverse (fn [lst1 lst2]
		  (if (empty? lst1)
		    lst2
		    (recur (rest lst1) (cons (first lst1) lst2))))]
    (reverse lst '())))


(defn reverse-3 [lst]
  (if (empty? lst)
    nil
    (cons (last lst) (reverse-3 (butlast lst)))))

(deftest test-reverse-1
  (are [x y] [= x y]
       (reverse-1 (list 1 2 3 4)) (list 4 3 2 1)
       (reverse-1 (list 1 4 9 16 25)) (list 25 16 9 4 1)
       (reverse-1 (list (list 1 2) (list 3 4))) (list (list 3 4) (list 1 2))))

(deftest test-reverse-2
  (are [x y] [= x y]
       (reverse-2 (list 1 2 3 4)) (list 4 3 2 1)
       (reverse-2 (list 1 4 9 16 25)) (list 25 16 9 4 1)
       (reverse-1 (list (list 1 2) (list 3 4))) (list (list 3 4) (list 1 2))))
