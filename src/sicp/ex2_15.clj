(ns sicp.ex2_15
  (:use [sicp utils ch2_1_extended ex2_7 ex2_8 ex2_12]
	[clojure.test]))

(def r1 (make-center-percent 1000 0.01))
(def r2 (make-center-percent 100  0.01))

(def r3 (sub-interval (add-interval r1 r2) r2))
;;=> (999.8799999999999 1000.1200000000001)

(percentage r3)
;;=> 0.012000000000011824

(center r3)
;;=> 1000.0

(comment
"This shows that even if you add and subtract an interval (r2) from
another interval, we get back an interval which is not the same as
the original one r1. Let us look at the range as (center, percentage)
pair.
 r1 = c1 +/- w1
 r2 = c2 +/- w2

Now r1+r2-r2 = c1 + c2 - c1 +/- (2*w1+w2)
             = c1 +/- (2*w1 + w2)

i.e. we have a new percentage width as the errors add up. The inference
from this is that, we reduce the number of ranges in a calculation to
get a more accurate range. So, yes, Eva Lu Ator is right.
"
  )