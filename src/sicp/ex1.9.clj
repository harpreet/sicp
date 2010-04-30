(ns sicp.ex1.9
  (:use [sicp utils]
	[clojure.contrib trace test-is]))

;; exercise 1.9
(defn ++ [a b]
  (if (= a 0)
    b
    (inc (++ (dec a) b))))

;; (comment
;;   This version is a recursive process, where the previous call increments
;;   the sum by 1 and each call decrement the first operand by 1.
  
;; user> (dotrace [++] (++ 4 5))
;; TRACE t3745: (++ 4 5)
;; TRACE t3746: |    (++ 3 5)
;; TRACE t3747: |    |    (++ 2 5)
;; TRACE t3748: |    |    |    (++ 1 5)
;; TRACE t3749: |    |    |    |    (++ 0 5)
;; TRACE t3749: |    |    |    |    => 5
;; TRACE t3748: |    |    |    => 6
;; TRACE t3747: |    |    => 7
;; TRACE t3746: |    => 8
;; TRACE t3745: => 9
;; 9
;; )

(defn ++ [a b]
  (if (= a 0)
    b
    (++ (dec a) (inc b))))

;; (comment
  
;; user> (dotrace [++] (++ 4 5))
;; TRACE t3766: (++ 4 5)
;; TRACE t3767: |    (++ 3 6)
;; TRACE t3768: |    |    (++ 2 7)
;; TRACE t3769: |    |    |    (++ 1 8)
;; TRACE t3770: |    |    |    |    (++ 0 9)
;; TRACE t3770: |    |    |    |    => 9
;; TRACE t3769: |    |    |    => 9
;; TRACE t3768: |    |    => 9
;; TRACE t3767: |    => 9
;; TRACE t3766: => 9
;; 9
;; )
