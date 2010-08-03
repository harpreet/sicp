(ns sicp.ex2_38
  (:use [clojure.test]
        [sicp [ch2-2 :only (accumulate)]
              [ex2_36 :only (accumulate-n)]]))

;; The accumulate procedure is also known as fold-right, because it combines
;; the first element of the sequence with the result of combining all the elements
;; to the right. There is also a fold-left, which is similar to fold-right, except
;; that it combines elements working in the opposite direction:
(def fold-right accumulate)

(defn fold-left [op initial sequence]
  (if (nil? sequence)
    initial
    (fold-left op
               (op initial (first sequence))
               (next sequence))))

;; What are the values of
(comment
  "answers inlined")

(fold-right / 1 (list 1 2 3))
;;=> 3/2

(fold-left / 1 (list 1 2 3))
;;=> 1/6

(fold-right list nil (list 1 2 3))
;;=> (1 (2 (3 nil)))

(fold-left list nil (list 1 2 3))
;;=> (((nil 1) 2) 3)