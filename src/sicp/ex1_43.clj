(ns sicp.ex1_43
  (:use [clojure.contrib test-is]
	[sicp utils]
	[sicp ch1_3]
	[sicp ex1_42]))

(defn repeated-1 [f1 f2 m]
  (cond (= m 0) f1
	(= m 1) f2
	:else (repeated-1 f1 (compose f2 f1) (- m 1))))

(defn repeated [f n]
  (repeated-1 f (compose f f) (- n 1)))

