(ns sicp.ex2_42
  (:use [sicp.ch2-2 :only (enumerate-interval accumulate append flatmap)]))

(declare safe? empty-board adjoin-position)

(defn queens [board-size]
  (let [f (fn queen-cols [k]
            (if (= k 0)
              (list empty-board)
              (filter
               (fn [positions] (safe? k positions))
               (flatmap
                (fn [rest-of-queens]
                  (map (fn [new-row]
                         (adjoin-position new-row k rest-of-queens))
                       (enumerate-interval 1 board-size)))
                (queen-cols (- k 1))))))]
    (f board-size)))

(def empty-board nil)

(defn adjoin-position [row col pos]
  (cons row pos))

(defn get-row [pos]
  (first pos))

(defn safe? [col positions]
  (loop [this-row (first positions)
         pos      (rest positions)
         offset  1]
    (if (empty? pos)
      true
      (let [new-row (first pos)]
        (if (or (= this-row new-row)
                (= (+ this-row offset) new-row)
                (= (- this-row offset) new-row))
          false
          (recur this-row (rest pos) (inc offset)))))))