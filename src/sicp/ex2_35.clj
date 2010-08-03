(ns sicp.ex2_35
  (:use [clojure.test]
        [sicp [ch2-2 :only (accumulate)]]))

;; Redefine count-leaves from section 2.2.2 as an accumulation:

;; (define (count-leaves t)
;;   (accumulate <??> <??> (map <??> <??>)))
(defn count-leaves-with-accumulate [t]
  (accumulate + 0 (map
                   (fn [x] (if (seq? x)
                             (count-leaves-with-accumulate x)
                             1))
                   t)))

(deftest test-count-leaves
  (let [foo (list (list 1 2) 3 4)]
    (are [x y] [= x y]
         (count-leaves-with-accumulate foo) 4
         (count-leaves-with-accumulate (list foo)) 4
         (count-leaves-with-accumulate (list foo foo)) 8)))