(ns sicp.ch2_1_extended
  (:use [sicp utils ex2_7]
	[clojure.test]))

;; 2.1.4 - interval arithmetic
;;(declare make-interval lower-bound upper-bound)

(defn add-interval [x y]
  (make-interval (+ (lower-bound x) (lower-bound y))
                 (+ (upper-bound x) (upper-bound y))))

(defn mul-interval [x y]
  (let [p1 (* (lower-bound x) (lower-bound y))
	p2 (* (lower-bound x) (upper-bound y))
	p3 (* (upper-bound x) (lower-bound y))
	p4 (* (upper-bound x) (upper-bound y))]
    (make-interval (min p1 p2 p3 p4)
		   (max p1 p2 p3 p4))))

(defn div-interval [x y]
  (mul-interval x 
                (make-interval (/ 1.0 (upper-bound y))
                               (/ 1.0 (lower-bound y)))))


;; provide new constructors
(defn make-center-width [c w]
  (make-interval (- c w) (+ c w)))

(defn center [i]
  (/ (+ (lower-bound i) (upper-bound i)) 2))

(defn width [i]
  (/ (- (upper-bound i) (lower-bound i)) 2))

;; parallel-resistor formula
(defn par1 [r1 r2]
  (div-interval (mul-interval r1 r2)
		(add-interval r1 r2)))

(defn par2 [r1 r2]
  (let [one (make-interval 1 1)]
    (div-interval one
		  (add-interval (div-interval one r1)
				(div-interval one r2)))))