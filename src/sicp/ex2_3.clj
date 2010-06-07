(ns sicp.ex2_3
  (:use [sicp ex2_2 utils]
	[clojure.test]))

(declare width-rect height-rect)
;; wishful thinking. Assume that constructors and selectors are
;; available. Now compute perimeter.
(defn perimeter-rect [rect]
  (* 2 (+ (width-rect rect)
	  (height-rect rect))))

(defn area-rect [rect]
  (* (width-rect rect)
     (height-rect rect)))

;; now implement the rectangle. One way to represent a rectangle
;; is to use a pair of points representing diagonal corners.
(defn make-rect [p1 p2]
  (list p1 p2))

(defn p1-rect [r]
  (first r))

(defn p2-rect [r]
  (first (rest r)))

(defn width-rect [r]
  (let [p1 (p1-rect r)
	p2 (p2-rect r)]
    (abs (- (x-point p1) (x-point p2)))))

(defn height-rect [r]
  (let [p1 (p1-rect r)
	p2 (p2-rect r)]
    (abs (- (y-point p1) (y-point p2)))))

(deftest test-rect-perimeter-square
  (is (= (perimeter-rect (make-rect (make-point 0 0) (make-point 1 1))) 4)))

(deftest test-rect-area-square
  (is (= (area-rect (make-rect (make-point 0 0) (make-point 2 2))) 4)))

;; part2 - another implementation
;; instead of coordinates, we store height and width itself.
(defn make-rect-1 [h w]
  (list h w))

(defn width-rect-1 [r]
  (first r))

(defn height-rect-1 [r]
  (first (rest r)))

;; the below routines are unmodified versions of the above
;; procedures, but renamed to work around the compiler. Is
;; there a simpler way?
(defn perimeter-rect-1 [rect]
  (* 2 (+ (width-rect-1 rect)
	  (height-rect-1 rect))))

(defn area-rect-1 [rect]
  (* (width-rect-1 rect)
     (height-rect-1 rect)))

(deftest test-rect-perimeter-square-1
 (is (= (perimeter-rect-1 (make-rect-1 1 1)) 4)))

(deftest test-rect-area-square-1
 (is (= (area-rect-1 (make-rect-1 2 2)) 4)))
