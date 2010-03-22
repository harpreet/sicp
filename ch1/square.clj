(ns sicp-clj.ch1)

(defn square [x] (* x x))

(defn sum-of-squares [x y]
  (+ (square x) (square y)))

(defn f [a]
  (sum-of-squares (+ a 1) (* a 2)))