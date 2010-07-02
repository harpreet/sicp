(ns sicp.ch2_2)

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