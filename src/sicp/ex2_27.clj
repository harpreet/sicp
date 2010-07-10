(ns sicp.ex2_27
  (:refer-clojure :exclude (reverse))
  (:use [clojure.test]))

(def x (list (list 1 2) (list 3 4)))

(defn deep-reverse [lst]
  (cond (not (seq? lst)) lst
	(empty? lst) nil
	:else (cons (deep-reverse (last lst)) (deep-reverse (butlast lst)))))

(deftest test-simple-list
  (are [x y] [= x y]
       (deep-reverse '(1 2 3 4))     '(4 3 2 1)
       (deep-reverse '((1 2) 3 4))   '(4 3 (2 1))
       (deep-reverse '((1 2) (3 4))) '((4 3) (2 1))))
