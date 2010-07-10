(ns sicp.ex2_28
  (:use [clojure.test]))

;; take a list of nested lists and return a flat list with elements
;; in the same left-to-right order
(defn fringe [lst]
  (cond (not (seq? lst)) (list lst)
	(empty? lst)     nil
	:else (concat (fringe (first lst))
		      (fringe (rest lst)))))

(deftest test-fringe-with-simple-list
  (is (= (fringe '(1 2 3 4)) '(1 2 3 4))))

(deftest test-fringe-with-nested-list
  (are [x y] [= x y]
       (fringe '((1 2) 3 4))   '(1 2 3 4)
       (fringe '(1 2 (3 4)))   '(1 2 3 4)
       (fringe '((1 2) (3 4))) '(1 2 3 4)))
