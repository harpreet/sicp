(ns sicp.ex2_39
  (:use [clojure.test]
        [sicp [ch2-2 :only (accumulate)]
              [ex2_36 :only (accumulate-n)]
              [ex2_38 :only (fold-right fold-left)]]))

;; Complete the following definitions of reverse (exercise 2.18) in
;; terms of fold-right and fold-left from exercise 2.38:

;; (define (reverse sequence)
;;   (fold-right (lambda (x y) <??>) nil sequence))
;; (define (reverse sequence)
;;   (fold-left (lambda (x y) <??>) nil sequence))
(defn reverse-with-foldr [sequence]
  (fold-right (fn [x y]
                (concat y (list x)))
              nil
              sequence))

(defn reverse-with-foldl [sequence]
  (fold-left (fn [x y]
               (cons y x))
             nil
             sequence))

(deftest test-reverses
  (let [s1 '(1 2 3 4 5)
        s2 '(1 2 3 (4 5))]
    (are [x y] [= x y]
         (reverse-with-foldr s1) (reverse s1)
         (reverse-with-foldl s1) (reverse s1)
         (reverse-with-foldr s2) (reverse s2)
         (reverse-with-foldl s2) (reverse s2))))
