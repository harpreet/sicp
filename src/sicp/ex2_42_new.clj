(ns sicp.ex2_42_new
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

(defn adjoin-position [row col positions]
  (append positions (list (list row col))))

(defn get-row [p]
  (first p))

(defn get-col [p]
  (second p))

(defn same-row? [p q]
  (= (get-row p)
     (get-row q)))

(defn same-col? [p q]
  (= (get-col p)
     (get-col q)))

(defn same-diag? [p q]
  (= (Math/abs (- (get-row p) (get-row q)))
     (Math/abs (- (get-col p) (get-col q)))))

(defn under-attack? [p q]
  (or (same-row? p q)
      (same-col? p q)
      (same-diag? p q)))

(defn safe? [col positions]
  (loop [this-pos (nth positions (- col 1))
         rest-pos (remove #(= this-pos %) positions)]
    (if (empty? rest-pos)
      true
      (let [new-pos (first rest-pos)]
        (if (under-attack? this-pos new-pos)
          false
          (recur this-pos (rest rest-pos)))))))