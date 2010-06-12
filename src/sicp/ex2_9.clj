(ns sicp.ex2_9
  (:use [sicp utils ch2_1_extended ex2_7]
	[clojure.test]))

;; let us say, width of an interval [x,y] = d = (y-x)/2.
;; add_interval [x,y] = [l1+l2, u1+u2]
;; width of sum = (u1+u2 - (l1+l2))/2
;;              = (u1-l1 + u2 - l2)/2
;;              = d1  + d2
;;
;; sub_interval [x,y] = [l1-u2, u1-l2]
;; width of diff = (u1-l2 - (l1-u2))/2
;;               = (u1 - l2 - l1 + u2) / 2
;;               = ((u1 - l1) + (u2 - l2))/2
;;               = d1 + d2

;; Now, intuitively multiplication (and division), scales a range
;; up or down. Since mul-range was essentially non-linear (is that the
;; right term? ) because of the min/max calculations, these depend on
;; absolute values of the range rather than the width.
;; eg: [2 3] [3 4]. Width (d1, d2) => (1,1)
;; mul-interval => [6 12]

;; same with division as well.
