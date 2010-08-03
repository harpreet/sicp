(ns sicp.ex2_37
  (:use [clojure.test]
        [sicp [ch2-2 :only (accumulate)]
              [ex2_36 :only (accumulate-n)]]))

;; (define (dot-product v w)
;;  (accumulate + 0 (map * v w)))
(defn dot-product [v w]
  (accumulate + 0 (map * v w)))

(deftest test-dot-prod
  (let [v '(1 2 3)
        w '(10 20 30)]
    (is [= (dot-product v w)
           140])))

;; (define (matrix-*-vector m v)
;;   (map <??> m))
(defn matrix-*-vector [m v]
  (map (fn [r] (dot-product r v)) m))

(deftest test-matrix*-vector
  (let [m '((1 2 3) (4 5 6) (7 8 9))
        v '(1 1 1)]
    (is [= (matrix-*-vector m v)
           '(6 15 24)])))

;; (define (transpose mat)
;;   (accumulate-n <??> <??> mat))
(defn transpose [mat]
  (accumulate-n cons nil mat))

(deftest test-transpose
  (let [s '((1 2 3) (4 5 6) (7 8 9))]
    (is [= (transpose s)
           '((1 4 7) (2 5 8) (3 6 9))])))

;; (define (matrix-*-matrix m n)
;;   (let ((cols (transpose n)))
;;     (map <??> m)))
(defn matrix-*-matrix [m n]
  (let [cols (transpose n)]
    (map (fn [v]
           (matrix-*-vector cols v))
         m)))

(deftest test-matrix-*-matrix
  (are [x y] [= x y]
       (matrix-*-matrix '((8 9) (5 -1)) '((-2 3) (4 0))) '((20 24) (-14 15))
       (matrix-*-matrix '((0 -1 2) (4 11 2)) '((3 -1) (1 2) (6 1))) '((11 0) (35 20))))