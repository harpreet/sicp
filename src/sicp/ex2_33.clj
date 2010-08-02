(ns sicp.ex2_33
  (:use [clojure.test]
        [sicp.ch2-2 :only (accumulate)]))

;; Fill in the missing expressions to complete the
;; following definitions of some basic list-manipulation
;; operations as accumulations:

;; (define (map p sequence)
;;   (accumulate (lambda (x y) <??>) nil sequence))
(defn mymap [p sequence]
  (accumulate (fn [x y] (cons (p x) y)) nil sequence))

(deftest test-mymap
  (is [= (mymap #(* % %) '(1 2 3))
         '(1 4 9)]))

;; (define (append seq1 seq2)
;;   (accumulate cons <??> <??>))
(defn append [seq1 seq2]
  (accumulate cons seq2 seq1))

(deftest test-append
  (is [= (append '(1 2 3) '(4 5 6))
         '(1 2 3 4 5 6)]))

;; (define (length sequence)
;;   (accumulate <??> 0 sequence))
(defn length [sequence]
  (accumulate (fn [x y] (inc y)) 0 sequence))

(deftest test-length
  (are [x y] [= x y]
       (length '(1 2 3 4 5)) 5
       (length '((1 2) 3 4 5) 4)))

