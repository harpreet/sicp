(ns sicp.ex2_2
  (:use [sicp utils]
	[clojure.test]))

(defn make-segment [x y]
  (list x y))

(defn start-segment [seg]
  (first seg))

(defn end-segment [seg]
  (first (rest seg)))

(defn make-point [x y]
  (list x y))

(defn x-point [p]
  (first p))

(defn y-point [p]
  (first (rest p)))

(defn midpoint-segment [seg]
  (let [x1 (x-point (start-segment seg))
	y1 (y-point (start-segment seg))
	x2 (x-point (end-segment seg))
	y2 (y-point (end-segment seg))]
    (make-point (average x1 x2)
		(average y1 y2))))


(deftest test-mid-point-1-1-3-3
  (let [m (midpoint-segment (make-segment  (make-point 1 1)
					   (make-point 3 3)))]
    (are [x y] [approx-equal x y]
	 (x-point m) 2.0
	 (y-point m) 2.0)))