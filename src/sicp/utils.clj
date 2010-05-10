(ns sicp.utils)

(defn square [x] (* x x))

(defn abs
  "find absolute value of x"
  [x]
  (if (< x 0) (- x) x))

(defn cube [x]
  (* x x x))

(defn twice [x]
  (* 2 x))

(defn half [x]
  (/ x 2))

(defn divides? [a b]
  (= (rem b a) 0))

(defn- find-divisor [n test-divisor]
  (cond (> (square test-divisor)  n) n
	(divides? test-divisor n) test-divisor
	:else (find-divisor n (inc test-divisor))))

(defn- smallest-divisor [n]
  (find-divisor n 2))

(defn prime? [n]
  (= (smallest-divisor n) n))

(defn gcd [a b]
  (if (= b 0)
    a
    (gcd b (rem a b))))

(defn average [a b]
  (/ (+ a b) 2.0))

(defmacro microbench
  " Evaluates the expression n number of times, returning the average
    time spent in computation, removing highest and lowest values.

    If the body of expr returns nil, only the timing is returned otherwise
    the result is printed - does not affect timing.

    Before timings begin, a warmup is performed lasting either 1 minute or
    1 full computational cycle, depending on which comes first."
  [n expr] {:pre [(> n 2)]}
  `(let [warm-up#  (let [start# (System/currentTimeMillis)]
                     (println "Warming up!")
                     (while (< (System/currentTimeMillis) (+ start# (* 60 1000)))
                            (with-out-str ~expr)
                            (System/gc))
                     (println "Benchmarking..."))
         timings#  (doall
                    (for [pass# (range ~n)]
                      (let [start#    (System/nanoTime)
                            retr#     ~expr
                            timing#   (/ (double (- (System/nanoTime) start#))
                                         1000000.0)]
                        (when retr# (println retr#))
                        (System/gc)
                        timing#)))
         runtime#  (reduce + timings#)
         highest#  (apply max timings#)
         lowest#   (apply min timings#)]
     (println "Total runtime: " runtime#)
     (println "Highest time : " highest#)
     (println "Lowest time  : " lowest#)
     (println "Average      : " (/ (- runtime# (+ highest# lowest#))
                                   (- (count timings#) 2)))
     timings#))
