(ns sicp.ex1_42
  (:use [clojure.contrib test-is]
	[sicp utils]
	[sicp ch1_3]))

(defn compose [f g]
  (fn [x] (f (g x))))

(comment
((compose square inc) 6)
;;=> 49
((compose square square) 2)
;;=> 16
 )