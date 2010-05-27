(ns sicp.ex1_42
  (:use [clojure.contrib test-is]
	[sicp utils]
	[sicp ch1_3]))

(defn compose [f g]
  (fn [x] (f (g x))))


(deftest test-compose-square-of-inc-of-6
  (is (= ((compose square inc) 6)
	 49)))

(deftest test-compose-square-of-square-of-2
  (is (= ((compose square square) 2)
	 16)))

(comment
;; from repl do
  (use 'sicp.ex1_42 :reload)
  (run-tests 'sicp.ex1_42)
 )