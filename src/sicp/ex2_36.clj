(ns sicp.ex2_36
  (:use [clojure.test]
        [sicp [ch2-2 :only (accumulate)]]))

;; (define (accumulate-n op init seqs)
;;   (if (null? (car seqs))
;;       nil
;;       (cons (accumulate op init <??>)
;;             (accumulate-n op init <??>))))
(defn accumulate-n [op init seqs]
  (if (nil? (first seqs))
    nil
    (cons (accumulate op init (map first seqs))
          (accumulate-n op init (map next seqs)))))

(deftest test-accumulate-n
  (let [s (list (list 1 2 3) (list 4 5 6) (list 7 8 9) (list 10 11 12))]
    (is [= (accumulate-n + 0 s)
           (list 22 26 30)])))