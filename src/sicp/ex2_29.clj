(ns sicp.ex2_29
  (:use [clojure.test]))

(defn make-mobile [left right]
  (list left right))

(defn make-branch [length structure]
  (list length structure))

;; a.  Write the corresponding selectors left-branch and
;;     right-branch, which return the branches of a mobile, and
;;     branch-length and branch-structure, which return the
;;     components of a branch.
(defn left-branch [mobile]
  (first mobile))

(defn right-branch [mobile]
  (first (rest mobile)))

(defn branch-length [branch]
  (first branch))

(defn branch-structure [branch]
  (first (rest branch)))

(deftest test-constructors-selectors
  (let [m (make-mobile (make-branch 3 8)
		       (make-branch 9
				    (make-mobile (make-branch 2 1)
						 (make-branch 1 2))))]
    (are [x y] [= x y]
	 m '((3 8) (9 ((2 1) (1 2))))
	 (right-branch m) '(9 ((2 1) (1 2)))
	 (left-branch m) '(3 8)
	 (branch-length (left-branch m)) 3
	 (branch-length (right-branch m)) 9
	 (branch-structure (left-branch m)) 8
	 (branch-structure (right-branch m)) '((2 1) (1 2)))))

;; b.  Using your selectors, define a procedure total-weight that
;;     returns the total weight of a mobile.

(declare total-branch-weight)

(defn total-weight [mobile]
  (+ (total-branch-weight (right-branch mobile))
     (total-branch-weight (left-branch mobile))))

(defn total-branch-weight [branch]
  (let [structure (branch-structure branch)]
    (cond (number? structure) structure
	  :else (total-weight structure))))

(deftest test-weights
  (let [m (make-mobile (make-branch 3 8)
		       (make-branch 9
				    (make-mobile (make-branch 2 1)
						 (make-branch 1 2))))]
    (are [x y] [= x y]
	 (total-weight m) 11)))

;; c. A mobile is said to be balanced if the torque applied by its
;;    top-left branch is equal to that applied by its top-right branch
;;    (that is, if the length of the left rod multiplied by the weight
;;    hanging from that rod is equal to the corresponding product for
;;    the right side) and if each of the submobiles hanging off its
;;    branches is balanced. Design a predicate that tests whether a
;;    binary mobile is balanced.

(defn torque [branch]
  (* (branch-length branch) (total-branch-weight branch)))

(defn mobile? [structure]
  (seq? structure))

(defn balanced? [mobile]
  (let [lstructure   (branch-structure (left-branch mobile))
	rstructure   (branch-structure (right-branch mobile))]
    (and (= (torque (left-branch mobile))
	    (torque (right-branch mobile)))
	 (if (mobile? lstructure)
	   (balanced? lstructure)
	   true)
	 (if (mobile? rstructure)
	   (balanced? rstructure)
	   true))))

(deftest test-balance
  (let [m1 (make-mobile (make-branch 3 9)
			(make-branch 9
				     (make-mobile (make-branch 2 1)
						  (make-branch 1 2))))
	m2 (make-mobile (make-branch 2 4)
			(make-branch 3
				     (make-mobile (make-branch 2 1)
						  (make-branch 4 2))))]
    (are [x y] [= x y]
	 (balanced? m1) true
	 (balanced? m2) false)))

;; d. Suppose we change the representation of mobiles.
;;    How much do you need to change your programs to convert
;;    to the new representation?

;; soln
;; if I were to do it in scheme, then oly selectors would change.