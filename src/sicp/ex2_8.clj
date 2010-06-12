(ns sicp.ex2_8
  (:use [sicp utils ch2_1_extended ex2_7]
	[clojure.test]))

;; sub-interval
(defn sub-interval [x y]
  (make-interval (- (lower-bound x) (upper-bound y))
		 (- (upper-bound x) (lower-bound y))))

;; subtraction of an interval can be seen as addition of a
;; negative range. Intuitively, if you plot a range in a 2-D graph,
;; negative of a range, say [a1, a2] for say a1 and a2 positive
;; is [-a2, -a1]. i.e. the reflection of the range w.r.t the x=0
;; axis. In this new light, we are adding two ranges [-a2,-a2] and [b1,b2]
;; and hence the above answer.