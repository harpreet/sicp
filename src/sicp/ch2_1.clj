(ns sicp.ch2_1
  (:use [sicp utils]
	[clojure.contrib test-is]))

(declare make-rat numer denom)

(defn add-rat [x y]
  (make-rat (+ (* (numer x) (denom y))
	       (* (numer y) (denom x)))
	    (* (denom x) (denom y))))

(defn sub-rat [x y]
  (make-rat (- (* (numer x) (denom y))
	       (* (numer y) (denom x)))
	    (* (denom x) (denom y))))

(defn mul-rat [x y]
  (make-rat (* (numer x) (numer y))
	    (* (denom x) (denom y))))

(defn div-rat [x y]
  (make-rat (* (numer x) (denom y))
	    (* (numer y) (denom x))))

(defn equal-rat? [x y]
  (= (* (numer x) (denom y))
     (* (denom x) (numer y))))

;; until now, we haven't even defined the data representation.
;; now define make-rat numer and denom.
(defn make-rat [x y]
  (let [g (gcd x y)]
    (list (/ x g)
	  (/ y g))))

(defn numer [x]
  (first x))

(defn denom [x]
  (first (rest x)))

(defn print-rat [x]
  (println (numer x)"/"(denom x)))