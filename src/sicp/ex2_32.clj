(ns sicp.ex2_32
  (:use [clojure.test]))

(defn subsets [s]
  (if (empty? s)
    (list ())
    (let [r (subsets (rest s))]
      (concat r (map #(cons (first s) %) r)))))

(comment
"
Let s be '(1 2 3).

1. (subset '(1 2 3)) => (subset '(2 3)) => (subset '(3)) => (subset '())
2. At this point subset returns.
3. r becomes (). value of s is '(3).
4. concat () with result of (map #(cons 3 %) '()) => (() (3))
5. returns to previous call, where s is '(2 3).
6. (map #(cons 2 %) '(() (3))) => ((2) (2 3))
7. concat the above with previous r => (() (3) (2) (2 3))
8. return to the previous call, where s is '(1 2 3)
9. (map #(cons 1 %) '(() (3) (2) (2 3))) => ((1) (1 3) (1 2) (1 2 3))
10. Append the above with previous value of r => (() (3) (2) (2 3) (1) (1 3) (1 2) (1 2 3))
"
)