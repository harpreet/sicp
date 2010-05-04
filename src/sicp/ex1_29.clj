(ns sicp.ex1_29
  (:use [sicp utils]
	[clojure.contrib trace test-is]))

;; simpson's rule of integration
(defn sum [term a next b]
  (if (> a b)
    0
    (+ (term a)
       (sum term (next a) next b))))

(defn simpson-rule [f a b n]
  (let [h (/ (- b a) n)
	make-fac (fn [k]
		   (cond (= k 0) 1
			 (= k n) 1
			 (even? k) 2
			 :else 4))
	simp-term (fn [k]
		    (* (make-fac k)
		       (f (+ a (* k h)))))]
    (* (/ h 3.0)
       (sum simp-term 0 inc n))))

;;; lots of great ideas in the above exercise. lexical closures make it easy to
;;; implement as a and b are constants.