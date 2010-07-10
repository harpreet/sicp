(ns sicp.ex2_26)

(def x (list 1 2 3))
(def y (list 4 5 6))

;; (append x y) will produce (1 2 3 4 5 6)
;; (cons x y) will produce ((1 2 3) 4 5 6)
;; (lis x y) => ((1 2 3) (4 5 6))

(concat x y)
;;=> (1 2 3 4 5 6)
(cons x y)
;;=> ((1 2 3) 4 5 6)
(list x y)
;;=> ((1 2 3) (4 5 6))