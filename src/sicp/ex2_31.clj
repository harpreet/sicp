(ns sicp.ex2_31
  (:use [clojure.test]))

(defn tree-map [func tree]
  (map (fn [sub-tree]
	 (if (seq? sub-tree)
	   (tree-map func sub-tree)
	   (func sub-tree)))
       tree))

(defn square-tree [tree]
  (tree-map #(* % %) tree))

(deftest test-tree-map-with-sq
  (are [x y] [= x y]
       (square-tree '(1 (2 (3 4) 5) (6 7))) '(1 (4 (9 16) 25) (36 49))))
