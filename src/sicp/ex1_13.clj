(ns sicp.ex1_13
  (:use [sicp utils]
	[clojure.contrib trace test-is]))

;; exercise 1.13:
(comment
See the pdfs in the directory for the answers.
)

;; ex 1.13 (contd)
(defn fib-approx [n]
  (let [phi (/ (+ 1 (sqrt 5)) 2)]
    (/ (expt phi n) (sqrt 5))))

;; (comment
;; user> (map fib-approx (range 10))
;; (0.4472135954999579 0.7236067977499789 1.1708203932499368 1.8944271909999157 3.065247584249853 4.959674775249769 8.024922359499623 12.984597134749393 21.009519494249016 33.99411662899841)
;; )
