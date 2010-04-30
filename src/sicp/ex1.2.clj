(ns sicp.ex1.2
  (:use [sicp utils]
	[clojure.contrib trace test-is]))

;; exercise 1.2: Translate the given expression into prefix form
(/ (+ 5 4 (- 2 (- 3 (+ 6 (/ 1 5)))))
   (* 3 (- 6 2) (- 2 7)))  ; -71/300
