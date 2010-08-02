(ns sicp.ch2-2
  (:refer-clojure :exclude (map))
  (:use (sicp [ch1-2 :only (fib)])))

(cons 1
      (cons 2
	    (cons 3
		  (cons 4 nil))))
;;=> (1 2 3 4)
(list 1 2 3 4)

(def one-thru-four (list 1 2 3 4))
;;=> #'user/one-thru-four
(first one-thru-four)
;;=> 1
(rest one-thru-four)
;;=> (2 3 4)
(cons 10 one-thru-four)
;;=> (10 1 2 3 4)
(cons 5 one-thru-four)
;;=> (5 1 2 3 4)

;; get nth element of a list
(defn list-ref [items n]
  (if (= n 0)
    (first items)
    (list-ref (rest items) (- n 1))))

(list-ref one-thru-four 3)
;;=> 4
(list-ref one-thru-four 5)
;;=> nil
(list-ref one-thru-four 1)
;;=> 2
(list-ref one-thru-four 0)
;;=> 1

(defn length [items]
  (if (empty? items)
    0
    (+ 1 (length (rest items)))))

(length one-thru-four)
;;=> 4

(defn- length-i [items n]
  (if (empty? items)
    n
    (length-i (rest items) (+ 1 n))))

(defn length-iter [items]
  (length-i items 0))

(length-iter one-thru-four)
;;=> 4

(defn append [list1 list2]
  (if (empty? list1)
    list2
    (cons (first list1)
	  (append (rest list1) list2))))

(append one-thru-four one-thru-four)
;;=> (1 2 3 4 1 2 3 4)

;; mapping over lists
(defn scale-list [items factor]
  (if (empty? items)
    nil
    (cons (* factor (first items))
	  (scale-list (rest items) factor))))

(defn map [proc items]
  (if (empty? items)
    nil
    (cons (proc (first items))
	  (map proc (rest items)))))

(defn scale-list-with-map [items factor]
  (map (fn [item] (* item factor)) items))

;; 2.2.2
(def x (cons (list 1 2) (list 3 4)))

(length x)
;;=> 3

;; count-leaves
(defn count-leaves [coll]
  (cond (nil? coll)       0
	(not (seq? coll)) 1
	:else (+ (count-leaves (first coll))
		 (count-leaves (next coll)))))

;; mapping over trees
(defn scale-tree [tree factor]
  (cond (nil? tree) nil
	(not (seq? tree)) (* tree factor)
	:else (cons (scale-tree (first tree) factor)
		    (scale-tree (next tree) factor))))

;; using map
(defn scale-tree-with-map [tree factor]
  (map (fn [sub-tree]
	 (if (seq? sub-tree)
	   (scale-tree-with-map sub-tree factor)
	   (* sub-tree factor)))
       tree))

;;; 2.2.3
(defn sum-odd-squares [tree]
  (cond (nil? tree) 0
	(not (seq? tree)) (if (odd? tree)
			    ((fn [x] (* x x)) tree)
			    0)
	:else (+ (sum-odd-squares (first tree))
		 (sum-odd-squares (next tree)))))

(defn even-fibs [n]
  (letfn [(next-fib [k]
		    (if (> k n)
		      nil
		      (let [f (fib k)]
			(if (even? f)
			  (cons f (next-fib (+ k 1)))
			  (next-fib (+ k 1))))))]
    (next-fib 0)))

(map #(* % %) (list 1 2 3 4 5))

(defn myfilter-1 [pred? xs]
  (cond (nil? xs) nil
	(not (seq? xs)) (if (pred? xs)
			  (list xs)
			  ())
	:else (concat (myfilter-1 pred? (first xs))
		      (myfilter-1 pred? (next xs)))))

(defn myfilter-2 [pred? xs]
  (cond (nil? xs) nil
	(pred? (first xs)) (cons (first xs)
				 (myfilter-2 pred? (next xs)))
	:else (myfilter-1 pred? (next xs))))

;; accumulate
(defn accumulate [op init xs]
  (if (nil? xs)
    init
    (op (first xs)
	(accumulate op init (next xs)))))

(defn enumerate-interval
  ([high]
     (enumerate-interval 0 high))
  ([low high]
     (if (> low high)
       nil
       (cons low (enumerate-interval (+ low 1) high)))))