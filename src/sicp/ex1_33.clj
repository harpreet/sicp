(ns sicp.ex1_33
  (:use [sicp utils]
	[clojure.contrib test-is]))

(defn filtered-accumulate [predicate? combiner null-value term a next b]
  (if (> a b)
    null-value
    (combiner (term (if (predicate? a)
		      a
		      null-value))
	      (filtered-accumulate predicate?
				   combiner
				   null-value
				   term
				   (next a)
				   next
				   b))))

(deftest test-filtered-sum-of-primes-from-1-to-10
  (is (= (filtered-accumulate prime? + 0 identity 1 inc 10)
	 (reduce + (filter prime? (range 1 11))))))

(deftest test-filtered-prod-of-relative-primes-of-10
  (is (= (filtered-accumulate #(= 1 (gcd % 10)) * 1 identity 1 inc 10)
	 (reduce * (filter #(= 1 (gcd % 10)) (range 1 11))))))