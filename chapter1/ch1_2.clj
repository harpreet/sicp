(ns sicp.chapter1.ch1_2
  (:use (clojure.contrib trace)))


(defn factorial [n]
  (if (= n 1)
    1
    (* n (factorial (- n 1)))))

;; stack friendly version
(defn factorial2 [n]
  (loop [x n acc 1] 
    (if (= x 1)
      acc
      (recur (- x 1) (* acc x)))))

;; even better
(defn factorial3 [n]
  (reduce * (range 1 (inc n))))

(comment
 user> (dotrace [factorial] (factorial 3))
 TRACE t2701: (factorial 3)
 TRACE t2702: |    (factorial 2)
 TRACE t2703: |    |    (factorial 1)
 TRACE t2703: |    |    => 1
 TRACE t2702: |    => 2
 TRACE t2701: => 6
 6
)

(comment
  sicp.chapter1.ch1_2> (dotrace [factorial] (factorial 6))
  TRACE t2778: (factorial 6)
  TRACE t2779: |    (factorial 5)
  TRACE t2780: |    |    (factorial 4)
  TRACE t2781: |    |    |    (factorial 3)
  TRACE t2782: |    |    |    |    (factorial 2)
  TRACE t2783: |    |    |    |    |    (factorial 1)
  TRACE t2783: |    |    |    |    |    => 1
  TRACE t2782: |    |    |    |    => 2
  TRACE t2781: |    |    |    => 6
  TRACE t2780: |    |    => 24
  TRACE t2779: |    => 120
  TRACE t2778: => 720
  720
  )

(comment
  sicp.chapter1.ch1_2> (dotrace [factorial2] (factorial2 6))
  TRACE t2806: (factorial2 6)
  TRACE t2806: => 720
  720
  )