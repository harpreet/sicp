(ns sicp.ex1_1
  (:use [sicp utils]
	[clojure.contrib trace test-is]))

;; exercise 1.1: What is the result printed by the interpreter in response
;;               to each expression (in order) ?

(def a 3)
(def b (+ a 1))

(+ a b (* a b))  ; 19
(= a b) ; false

(if (and (> b a) (< b (* a b)))
    b
    a)   ; 4

(cond (= a 4) 6
      (= b 4) (+ 6 7 a)
      :else 25)  ; 16

(+ 2 (if (> b a) b a)) ; 6

(* (cond (> a b) a
	 (< a b) b
	 :else -1)
   (+ a 1))      ; 16
