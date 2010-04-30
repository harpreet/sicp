(ns sicp.ch1-2
  (:use [sicp utils]
	[clojure.contrib.math :only (sqrt expt)]
	[clojure.contrib.trace :only (dotrace)]))


(defn factorial [n]
  (if (= n 1)
    1
    (* n (factorial (- n 1)))))

;; stack friendly version
(defn factorial2 [n]
  (loop [x n acc 1] 
    (if (= x 1)
      acc
      (recur (- x 1) (* acc x)))))

;; even better
(defn factorial3 [n]
  (reduce * (range 1 (inc n))))

;; (comment
;;  user> (dotrace [factorial] (factorial 3))
;;  TRACE t2701: (factorial 3)
;;  TRACE t2702: |    (factorial 2)
;;  TRACE t2703: |    |    (factorial 1)
;;  TRACE t2703: |    |    => 1
;;  TRACE t2702: |    => 2
;;  TRACE t2701: => 6
;;  6
;; )

;; (comment
;;   sicp.chapter1.ch1_2> (dotrace [factorial] (factorial 6))
;;   TRACE t2778: (factorial 6)
;;   TRACE t2779: |    (factorial 5)
;;   TRACE t2780: |    |    (factorial 4)
;;   TRACE t2781: |    |    |    (factorial 3)
;;   TRACE t2782: |    |    |    |    (factorial 2)
;;   TRACE t2783: |    |    |    |    |    (factorial 1)
;;   TRACE t2783: |    |    |    |    |    => 1
;;   TRACE t2782: |    |    |    |    => 2
;;   TRACE t2781: |    |    |    => 6
;;   TRACE t2780: |    |    => 24
;;   TRACE t2779: |    => 120
;;   TRACE t2778: => 720
;;   720
;;   )

;; (comment
;;   sicp.chapter1.ch1_2> (dotrace [factorial2] (factorial2 6))
;;   TRACE t2806: (factorial2 6)
;;   TRACE t2806: => 720
;;   720
;;   )

(defn fact-iter [product counter max-count]
  (if (> counter max-count)
    product
    (fact-iter (* product counter)
	       (inc counter)
	       max-count)))

(defn factorial [n]
  (fact-iter 1 1 n))

;; (comment
;; user> (dotrace [factorial fact-iter] (factorial 6))
;; TRACE t2378: (factorial 6)
;; TRACE t2379: |    (fact-iter 1 1 6)
;; TRACE t2380: |    |    (fact-iter 1 2 6)
;; TRACE t2381: |    |    |    (fact-iter 2 3 6)
;; TRACE t2382: |    |    |    |    (fact-iter 6 4 6)
;; TRACE t2383: |    |    |    |    |    (fact-iter 24 5 6)
;; TRACE t2384: |    |    |    |    |    |    (fact-iter 120 6 6)
;; TRACE t2385: |    |    |    |    |    |    |    (fact-iter 720 7 6)
;; TRACE t2385: |    |    |    |    |    |    |    => 720
;; TRACE t2384: |    |    |    |    |    |    => 720
;; TRACE t2383: |    |    |    |    |    => 720
;; TRACE t2382: |    |    |    |    => 720
;; TRACE t2381: |    |    |    => 720
;; TRACE t2380: |    |    => 720
;; TRACE t2379: |    => 720
;; TRACE t2378: => 720
;; 720
;; )

;; observation: clojure loop-recur construct is essentially the same as
;; the iterative version.

;; section 1.2.2: Tree Recursion
(defn fib [n]
  (cond (= n 0) 0
	(= n 1) 1
	:else (+ (fib (- n 1))
		 (fib (- n 2)))))

;; iterative version
(defn fib-iter [a b count]
  (if (= count 0)
    b
    (fib-iter (+ a b) a (dec count))))

(defn fib [n]
  (fib-iter 1 0 n))

;; example: counting changes
(defn first-denomination [kinds-of-coins]
  (cond (= kinds-of-coins 1) 1
	(= kinds-of-coins 2) 5
	(= kinds-of-coins 3) 10
	(= kinds-of-coins 4) 25
	(= kinds-of-coins 5) 50))


(defn first-denomination [kinds-of-coins]
  ({1 1 2 5 3 10 4 25 5 50} kinds-of-coins))

(defn cc [amount kinds-of-coins]
  (cond (= amount 0) 1
	(or (< amount 0) (= kinds-of-coins 0)) 0
	:else (+ (cc amount (- kinds-of-coins 1))
		 (cc (- amount
			(first-denomination kinds-of-coins))
		     kinds-of-coins))))

(defn count-change [amount]
  (cc amount 5))





;; 1.2.4: exponentiation
;; computing b^n

;; iterative
(defn expt-iter [base counter product]
  (if (= counter 0)
    product
    (expt-iter base
	       (dec counter)
	       (* product base))))

(defn myexpt [b n]
  (expt-iter b n 1))

(defn myexpt [b n]
  (if (= n 0)
    1
    (* b (myexpt b (dec n)))))

;; fast version
(defn fast-expt [b n]
  (cond (= n 0) 1
	(even? n) (square (fast-expt b (/ n 2)))
	:else (* b (fast-expt b (dec n)))))



;;;  Section 1.2.5: GCD
(defn mygcd [a b]
  (if (= b 0)
    a
    (mygcd b (rem a b))))

;;; section 1.2.6 Primality testing.
(defn prime? [n]
  (= (smallest-divisor n) n))

(defn smallest-divisor [n]
  (find-divisor n 2))

(defn find-divisor [n test-divisor]
  (cond (> (square test-divisor)  n) n
	(divides? test-divisor n) test-divisor
	:else (find-divisor n (inc test-divisor))))

(defn divides? [a b]
  (= (rem b a) 0))

;; fermat's little theorem
(defn expmod [base exp m]
  (cond (= exp 0) 1
	(even? exp) (rem (square (expmod base (/ exp 2) m))
			 m)
	:else (rem (* base (expmod base (dec exp) m))
		   m)))

(defn fermat-test [n]
  (try-it (+ 1 (rand-int (- n 1))) n))

(defn try-it [a n]
  (= a (expmod a n n)))

(defn fast-prime? [n times]
  (cond (= times 0) true
	(fermat-test n) (fast-prime? n (dec times))
	:else false))


