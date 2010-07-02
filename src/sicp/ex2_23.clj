(ns sicp.ex2_23
  (:use [sicp utils]))

(defn for-each [f lst]
  (when (not (empty? lst))
    (do
      (f (first lst))
      (recur f (rest lst)))))