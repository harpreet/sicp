;; Exercise 1.36.  Modify fixed-point so that it prints the sequence
;; of approximations it generates, using the newline and display
;; primitives shown in exercise 1.22. Then find a solution to
;; x^x = 1000
;; by finding a fixed point of x |->  log(1000)/log(x).
;;
;; Compare the number of steps this takes with and without average damping.
(ns sicp.ex1_36
  (:use [sicp utils]
	[clojure.contrib test-is]))

(defn close-enough? [x y tolerance]
  (< (abs (- x y)) tolerance))

(defn fixed-point [f guess]
  (let [next (f guess)
	nsteps 0]
    (do
      (println "new guess is " next)
      (if (close-enough? next guess 0.00001)
	next
	(fixed-point f next)))))

;; fixed point
(comment
(fixed-point #(/ (Math/log 1000) (Math/log %)) 1.1)
new guess is  72.47657378429035
new guess is  1.6127318474109593
new guess is  14.45350138636525
new guess is  2.5862669415385087
new guess is  7.269672273367045
new guess is  3.4822383620848467
new guess is  5.536500810236703
new guess is  4.036406406288111
new guess is  4.95053682041456
new guess is  4.318707390180805
new guess is  4.721778787145103
new guess is  4.450341068884912
new guess is  4.626821434106115
new guess is  4.509360945293209
new guess is  4.586349500915509
new guess is  4.535372639594589
new guess is  4.568901484845316
new guess is  4.546751100777536
new guess is  4.561341971741742
new guess is  4.551712230641226
new guess is  4.558059671677587
new guess is  4.55387226495538
new guess is  4.556633177654167
new guess is  4.554812144696459
new guess is  4.556012967736543
new guess is  4.555220997683307
new guess is  4.555743265552239
new guess is  4.555398830243649
new guess is  4.555625974816275
new guess is  4.555476175432173
new guess is  4.555574964557791
new guess is  4.555509814636753
new guess is  4.555552779647764
new guess is  4.555524444961165
new guess is  4.555543131130589
new guess is  4.555530807938518
new guess is  4.555538934848503
;; => 4.555538934848503  
)

;; with average damping
(comment
(defn average [x y] (/ (+ x y) 2.0))
(fixed-point #(average % (/ (Math/log 1000) (Math/log %))) 1.1)
new guess is  36.78828689214517
new guess is  19.352175531882512
new guess is  10.84183367957568
new guess is  6.870048352141772
new guess is  5.227224961967156
new guess is  4.701960195159289
new guess is  4.582196773201124
new guess is  4.560134229703681
new guess is  4.5563204194309606
new guess is  4.555669361784037
new guess is  4.555558462975639
new guess is  4.55553957996306
new guess is  4.555536364911781
;; => 4.555536364911781  
)

(comment
"As we can see, average damping significantly reduces the number of steps."
)