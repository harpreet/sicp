(ns sicp.ex1_38
  (:use [sicp utils ex1_37]
	[clojure.contrib test-is]))

(defn gen-sequence [len]
  (let [l (+ 1 (int (/ len 3)))
	s1 (map #(* % 2) (range 1 (inc l)))
	s2 (map (fn [_] 1) (range 1 (inc l)))]
    (concat [0 1] (interleave s1 s2 s2))))
;; we concat [0 1] because nth sequences are indexed from 0 

;; approximating e
(defn e-approximation [len]
  (let [l (* 3 (int (/ len 3.0)))
	den (gen-sequence l)]
    (+ 2.0
       (cont-frac (fn [k] 1.0)
		  (fn [k] (nth den k))
		  l))))

(comment
user> (e-approximation 10)
2.718283582089552
user> (e-approximation 20)
2.7182818284590278
user> (e-approximation 40)
2.7182818284590455
user> (e-approximation 50)
2.7182818284590455
user> (e-approximation 60)
2.7182818284590455
user> (e-approximation 100)
2.7182818284590455
)