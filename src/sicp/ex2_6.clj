(ns sicp.ex2_6
  (:use [sicp utils]
	[clojure.test]))

;; from the problem
(def zero
  (fn [f] (fn [x] 
	    x)))

(defn add-1 [n]
  (fn [f] (fn [x]
	    (f ((n f) x)))))

(comment
(((add-1 zero) inc) 0)
;; => 1
(((add-1 zero) inc) 1)
;; => 2
)
;; the above examples should be read as follows:
;; (add-1 zero) returns the function which represents 1.
;; i.e. You apply inc once to 0 and that yields 1.
;; in the second example, we apply inc once to 1, and
;; that yields 2. This is just to convert Churl numeral
;; definition to roman numeral definition.

;; as said in the hint, using substitution to evaluate (add-1 zero)
;; tells us that (add-1 zero) == one has one composition of (f x)
;; and (add-1 (add-1 zero)) == 1 is (f (f x)), so here is their formal
;; definition.
(def one (fn [f] (fn [x] (f x))))

(def two (fn [f] (fn [x] (f (f x)))))

(comment
(((add-1 one) inc) 1)
;;=> 3
(((add-1 one) inc) 2)
;;=> 4
(((add-1 two) inc) 2)
;;=> 5
)
;; (add-1 one) => two. Apply inc twice on 1 => 3
;; similarly the other examples.

;; Definition of an add operator
(defn add [m n]
  (fn [f] (fn [x]
	    ((m f) ((n f) x)))))

(comment
(((add one two) inc) 1)
;;=> 4  
)
;; continueing the same logic, (add one two) => three
;; and inc applied three times to 1 is 4. This proves
;; that our definition of add is correct.

;; I used the wikipedia article to study Church Numerals:
;; <http://en.wikipedia.org/wiki/Church_encoding#Computation_with_Church_numerals>

;; comments?

(defn church-to-numeral [chfn]
  ((chfn inc) 0))

(deftest test-church-to-numeral
  (are [x y] [= x y]
       (church-to-numeral one) 1
       (church-to-numeral two) 2))