(ns sicp.ex1_29
  (:use [sicp utils]
	[clojure.contrib trace test-is]))

;; simpson's rule of integration
(defn make-fac [k n]
  (cond (= k 0) 1
	(= k n) 1
	(even? k) 2
	:else 4))

(defn simpson-sum [term a next-fn b n k]
  (let [fac (make-fac k n)]
    (if (> k n)
      0
      (+ (* fac (term a))
	 (simpson-sum term (next-fn a) next-fn b n (+ k 1))))))

(defn simpson-rule [f a b n]
  (let [h (/ (- b a) n)]
    (* (simpson-sum f a #(+ h %) b n 0)
       (/ h 3.0))))
