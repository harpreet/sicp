(ns sicp.ex2_10
  (:use [sicp utils ch2_1_extended ex2_7]
	[clojure.test]))

(defn new-div-interval [x y]
  (let [l (lower-bound y)
	u (upper-bound y)]
    (if (or (and (pos? l)
		 (neg? u))
	    (and (pos? u)
		 (neg? l)))
      (error "Division by a range spanning 0")
      (div-interval x y))))