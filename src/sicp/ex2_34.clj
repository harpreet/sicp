(ns sicp.ex2_34
  (:use [clojure.test]
        [sicp [ch2-2 :only (accumulate)]]))

;; horner's rule. Extensively used in signal processing world
;; Assume that the coefficients of the polynomial are arranged
;; in a sequence, from a0 through an. 
(defn horner-eval [x coeff-seq]
  (accumulate (fn [this-coeff higher-terms]
                (+ this-coeff
                   (* x higher-terms)))
              0
              coeff-seq))

;; 1 + 3x + 5x^3 + x^5 at x = 2
(deftest test-horners-poly
  (is [= (horner-eval 2 '(1 3 0 5 0 1))
         79]))