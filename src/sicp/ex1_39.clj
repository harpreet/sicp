(ns sicp.ex1_39
  (:use [sicp utils ex1_37]
	[clojure.contrib test-is]))

;; approximating tangent using lambert's cont fract
;; if you look at the formula, -xtan (x) = -x^2/(1-(x^2/3-x^2/5-.... ))
(defn tan-approximation [x len]
  (* (/ -1.0 x) 
     (cont-frac (fn [k] (* -1.0 (square x)))
		(fn [k] (- (* 2 k) 1))
		len)))

(comment
user> (tan-approximation (/ Math/PI 4) 20)
1.0
user> (Math/tan (/ Math/PI 4))
0.9999999999999999
user> (Math/tan (/ Math/PI 10))
0.3249196962329063
user> (tan-approximation (/ Math/PI 10) 20)
0.32491969623290634
user> (tan-approximation Math/PI 20)
-1.4135798584282297E-16
user> (Math/tan Math/PI)
-1.2246467991473532E-16
user> (Math/tan (/ Math/PI 2.0))
1.633123935319537E16
user> (tan-approximation (/ Math/PI 2) 20)
; Evaluation aborted.  
)
