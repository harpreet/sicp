(ns sicp.ch1_3
  (:use [sicp utils]
	[clojure.contrib test-is]))

;; 1.3.1: Procedures as arguments
(defn sum-integers [a b]
  (if (> a b)
    0
    (+ a (sum-integers (+ a 1) b))))

(defn sum-cubes [a b]
  (if (> a b)
    0
    (+ (cube a) (sum-cubes (+ a 1) b))))

(defn pi-sum [a b]
  (if (> a b)
    0
    (+ (/ 1.0 (* a (+ a 2))) (pi-sum (+ a 1) b))))

(defn sum [term a next b]
  (if (> a b)
    0
    (+ (term a)
       (sum term (next a) next b))))

(def sum-cubes-new (fn[a b] (sum cube a inc b)))

(deftest test-sum-of-first-10-integers
  (is (sum #(identity %) 1 inc 10) 55))

;; (* (sum #(/ 1.0 (* % (+ % 2))) 1 #(+ % 4) 1000) 8)
;;=> 3.139592655589783  (approaches PI)

(defn integral [f a b dx]
  (* (sum f (+ a (/ dx 2)) #(+ % dx) b)
     dx))

(integral cube 0 1 0.001)
;;=>0.249999875000001
(integral cube 0 1 0.005)
;;=>0.24999687500000028

;; section 1.3.3
(defn close-enough? [x y tolerance]
  (< (abs (- x y)) tolerance))

(defn search [f neg-point pos-point]
  (let [mid-point (average neg-point pos-point)]
    (if (close-enough? neg-point pos-point 0.001)
      mid-point
      (let [test-value (f mid-point)]
	(cond (pos? test-value) (search f neg-point mid-point)
	      (neg? test-value) (search f mid-point pos-point)
	      :else mid-point)))))

(defn half-interval-method [f a b]
  (let [a-val (f a)
	b-val (f b)]
    (cond (and (neg? a-val) (pos? b-val)) (search f a b)
	  (and (pos? a-val) (neg? b-val)) (search f b a)
	  :else (str "values are not of opposite sign"))))

(comment
  (half-interval-method #(Math/sin %) 2.0 4.0)
  ;;=> 3.14111328125
  (half-interval-method (fn [x] (- (* x x x) (* 2 x) 3)) 1.0 2.0)
  ;;=> 1.89306640625
)

;;; fixed point of a function
(defn fixed-point [f guess]
  (let [next (f guess)]
    (if (close-enough? next guess 0.00001)
      next
      (fixed-point f next))))

(comment
(fixed-point #(Math/cos %) 1.0)
;;;=> 0.7390822985224024
(fixed-point #(+ (Math/cos %) (Math/sin %)) 1.0)
;;;=> 1.2587315962971173
)

;; sqrt as fixed point of y/x
(defn mysqrt [x]
  (fixed-point (fn [y] (average y (/ x y)))
	       1.0))

(comment
(mysqrt 10)
;;;=> 3.162277660168379
(mysqrt 4)
;;;=> 2.000000000000002
)

;; section 1.3.4
(defn average-damp [f]
  (fn [x] (average x (f x))))

(defn new-sqrt [x]
  (fixed-point (average-damp (fn [y] (/ x y)))
	       1.0))

(defn new-cuberoot [x]
  (fixed-point (average-damp (fn [y] (/ x (square y))))
	       1.0))

;; newton's method of root finding
;; values of x for which g(x) = 0 is the same as
;; fixed point of f(x) where f(x) = x - g(x)/Dg(x)
;; where Dg(x) is the derivative of g(x)

(def dx 0.00001)

(defn deriv [g]
  (fn [x] (/ (- (g (+ x dx))
		(g x))
	     dx)))

(defn newton-transform [g]
  (fn [x] (- x
	     (/ (g x)
		((deriv g) x)))))

(defn newton-method [g guess]
  (fixed-point (newton-transform g)
	       1.0))

(defn newton-sqrt [x]
  (newton-method (fn [y] (- (square y) x))
		 1.0))

(defn fixed-point-of-transform [g transform guess]
  (fixed-point (transform g) guess))

(defn sqrt1 [x]
  (fixed-point-of-transform (fn [y] (/ x y))
			    average-damp
			    1.0))

(defn sqrt2 [x]
  (fixed-point-of-transform (fn [y] (- (square y) x))
			    newton-transform
			    1.0))