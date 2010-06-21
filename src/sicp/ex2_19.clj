(ns sicp.ex2_19
  (:use [sicp utils]
	[clojure test]))

(declare no-more? first-denomination except-first-denomination)

(defn cc [amount coin-values]
  (cond (= amount 0) 1
	(or (< amount 0) (no-more? coin-values)) 0
	:else
	(+ (cc amount
	       (except-first-denomination coin-values))
	   (cc (- amount
		  (first-denomination coin-values))
	       coin-values))))

(defn no-more? [lst] (empty? lst))
(defn first-denomination [lst] (first lst))
(defn except-first-denomination [lst] (rest lst))

;; tests
(def *us-coins* (list 50 25 10 5 1))
(def *uk-coins* (list 100 50 20 10 5 2 1 0.5))

(deftest test-us-coins-change-for-100-cents
  (are [x y] [= x y]
       (cc 100 *us-coins*) 292
       (cc 100 *uk-coins*) 104561))