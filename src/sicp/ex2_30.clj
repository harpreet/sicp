(ns sicp.ex2_30
  (:use [clojure.test]))

;; without map
(defn square-tree [tree]
  (cond (nil? tree) nil
	(not (seq? tree)) (* tree tree)
	:else (cons (square-tree (first tree))
		    (square-tree (next tree)))))

(deftest test-sq-tree
  (are [x y] [= x y]
       (square-tree '(1 (2 (3 4) 5) (6 7))) '(1 (4 (9 16) 25) (36 49))))

;; with map
(defn square-tree-with-map [tree]
  (map (fn [sub-tree]
	 (if (seq? sub-tree)
	   (square-tree-with-map sub-tree)
	   (* sub-tree sub-tree)))
       tree))

(deftest test-sq-tree-map
  (are [x y] [= x y]
       (square-tree-with-map '(1 (2 (3 4) 5) (6 7))) '(1 (4 (9 16) 25) (36 49))))
