(ns sicp.ex2_22
  (:use [sicp [utils :only (square)]]
	[clojure test]))

(defn square-list-1 [items]
  (let [f (fn [things answer]
	    (if (empty? things)
	      answer
	      (recur (rest things) 
		     (cons (square (first things))
			   answer))))]
    (f items nil)))

(comment
  "This produced reversed list, as we cons into an empty list and
   consing into a list always adds elements into the head as this
   is the most efficient - O(1) - way to add elements into the list."
)

(deftest test-square-list
  (is (= (square-list-1 '(1 2 3 4)) '(16 9 4 1))))

;; Louis then tries to fix his bug by interchanging the arguments to cons:

(defn square-list-2 [items]
  (let [f (fn [things answer]
	    (if (empty? things)
	      answer
	      (recur (rest things)
		     (cons answer
			   (square (first things))))))]
    (f items nil)))

(comment
  "This won't work because a 'nil' in the cons cell represents the
   end of the list and cons is used to construct a list by appending
   elements at the head of the list and not by appending at the tail."
)