(ns sicp.ex2_40
  (:use [sicp.ch2-2 :only (enumerate-interval accumulate append)]
        [sicp.ch1-2 :only (prime?)]))

;; Define a procedure unique-pairs that, given an integer n,
;; generates the sequence of pairs (i,j) with 1< j< i< n.
;; Use unique-pairs to simplify the definition of prime-sum-pairs
;; given above.
(defn unique-pairs [n]
  (accumulate append
              nil
              (map (fn [i]
                     (map (fn [j] (list i j))
                          (enumerate-interval 1 (- i 1))))
                   (enumerate-interval 1 n))))

(defn prime-sum? [pair]
  (prime? (+ (first pair)
             (first (rest pair)))))

(defn make-pair-sum [pair]
  (list (first pair) (first (rest pair)) (+ (first pair)
                                            (first (rest pair)))))

(defn prime-sum-pairs [n]
  (map make-pair-sum (filter prime-sum? (unique-pairs n))))