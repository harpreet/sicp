(ns sicp.ex1_34
  (:use [sicp utils]))

(defn f [g]
  (g 2))

(deftest test-f-with-square-as-arg
  (is (= (f square) 4)))

;; (what iwll happen if we do (f f)
(comment
"It will first invoke (f 2), which will again invoke (2 2), but
 2 is not a function, so it will give an error at that point."
)