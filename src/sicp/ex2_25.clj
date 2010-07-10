(ns sicp.ex2_25)

;; to pick 7
(first (rest (first (rest (rest '(1 3 (5 7) 9))))))
(first (first '((7))))
(first (rest (first (rest (first (rest (first (rest (first (rest (first (rest '(1 (2 (3 (4 (5 (6 7))))))))))))))))))