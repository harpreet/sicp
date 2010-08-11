(ns sicp.ex2_41
  (:use [sicp.ch2-2 :only (enumerate-interval accumulate append)]))

(defn triplets [n]
  (accumulate append
              nil
              (accumulate append
                          nil
                          (map (fn [i]
                                 (map (fn [j]
                                        (map (fn [k] (list i j k))
                                             (enumerate-interval 1 (- j 1))))
                                      (enumerate-interval 1 (- i 1))))
                               (enumerate-interval 1 n)))))

(defn sum-triplets [triplet]
  (accumulate + 0 triplet))

(defn ordered-triplets [n sum]
  (filter #(= (sum-triplets %) sum)
          (triplets n)))