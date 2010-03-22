(ns sicp-clj)

(defn argue [sentence]
  (when-not (empty? sentence)
    (concat (opposite (first sentence)) (argue (rest sentence)))))

(defn opposite [word]
  (cond (= word "like") "hate"
	(= word "hate") "like"
	(= word "wonderful") "terrible"
	(= word "terrible") "wonderful"
	:else word))