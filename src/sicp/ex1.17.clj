(ns sicp.ex1.17
  (:use [sicp utils]
	[clojure.contrib trace test-is]))

;; exercise 1.17:
(defn mult [a b]
  (if (= b 0)
    0
    (+ a (mult a (- b 1)))))

;; double
;; product = 2 * (a * (b/2)) for even b
;;         = a    + (a * (b - 1)) for odd b
(defn fast-mult [a b]
  (cond (= b 0) 0
	(= b 1) a
	(even? b) (twice (fast-mult a (half b)))
	:else (+ a (fast-mult a (- b 1)))))
