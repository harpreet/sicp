(ns sicp.chapter1.ch1_2
  (:use (clojure.contrib trace)))


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

(comment
 user> (dotrace [factorial] (factorial 3))
 TRACE t2701: (factorial 3)
 TRACE t2702: |    (factorial 2)
 TRACE t2703: |    |    (factorial 1)
 TRACE t2703: |    |    => 1
 TRACE t2702: |    => 2
 TRACE t2701: => 6
 6
)

(comment
  sicp.chapter1.ch1_2> (dotrace [factorial] (factorial 6))
  TRACE t2778: (factorial 6)
  TRACE t2779: |    (factorial 5)
  TRACE t2780: |    |    (factorial 4)
  TRACE t2781: |    |    |    (factorial 3)
  TRACE t2782: |    |    |    |    (factorial 2)
  TRACE t2783: |    |    |    |    |    (factorial 1)
  TRACE t2783: |    |    |    |    |    => 1
  TRACE t2782: |    |    |    |    => 2
  TRACE t2781: |    |    |    => 6
  TRACE t2780: |    |    => 24
  TRACE t2779: |    => 120
  TRACE t2778: => 720
  720
  )

(comment
  sicp.chapter1.ch1_2> (dotrace [factorial2] (factorial2 6))
  TRACE t2806: (factorial2 6)
  TRACE t2806: => 720
  720
  )

(defn fact-iter [product counter max-count]
  (if (> counter max-count)
    product
    (fact-iter (* product counter)
	       (inc counter)
	       max-count)))

(defn factorial [n]
  (fact-iter 1 1 n))

(comment
user> (dotrace [factorial fact-iter] (factorial 6))
TRACE t2378: (factorial 6)
TRACE t2379: |    (fact-iter 1 1 6)
TRACE t2380: |    |    (fact-iter 1 2 6)
TRACE t2381: |    |    |    (fact-iter 2 3 6)
TRACE t2382: |    |    |    |    (fact-iter 6 4 6)
TRACE t2383: |    |    |    |    |    (fact-iter 24 5 6)
TRACE t2384: |    |    |    |    |    |    (fact-iter 120 6 6)
TRACE t2385: |    |    |    |    |    |    |    (fact-iter 720 7 6)
TRACE t2385: |    |    |    |    |    |    |    => 720
TRACE t2384: |    |    |    |    |    |    => 720
TRACE t2383: |    |    |    |    |    => 720
TRACE t2382: |    |    |    |    => 720
TRACE t2381: |    |    |    => 720
TRACE t2380: |    |    => 720
TRACE t2379: |    => 720
TRACE t2378: => 720
720
)

;; observation: clojure loop-recur construct is essentially the same as
;; the iterative version.

;; exercise 1.9
(defn ++ [a b]
  (if (= a 0)
    b
    (inc (++ (dec a) b))))

(comment
  This version is a recursive process, where the previous call increments
  the sum by 1 and each call decrement the first operand by 1.
  
user> (dotrace [++] (++ 4 5))
TRACE t3745: (++ 4 5)
TRACE t3746: |    (++ 3 5)
TRACE t3747: |    |    (++ 2 5)
TRACE t3748: |    |    |    (++ 1 5)
TRACE t3749: |    |    |    |    (++ 0 5)
TRACE t3749: |    |    |    |    => 5
TRACE t3748: |    |    |    => 6
TRACE t3747: |    |    => 7
TRACE t3746: |    => 8
TRACE t3745: => 9
9
)
(defn ++ [a b]
  (if (= a 0)
    b
    (++ (dec a) (inc b))))

(comment
  
user> (dotrace [++] (++ 4 5))
TRACE t3766: (++ 4 5)
TRACE t3767: |    (++ 3 6)
TRACE t3768: |    |    (++ 2 7)
TRACE t3769: |    |    |    (++ 1 8)
TRACE t3770: |    |    |    |    (++ 0 9)
TRACE t3770: |    |    |    |    => 9
TRACE t3769: |    |    |    => 9
TRACE t3768: |    |    => 9
TRACE t3767: |    => 9
TRACE t3766: => 9
9
)

;; exercise 1.10
;; ackerman functions
(defn A [x y]
  (cond (= y 0) 0
	(= x 0) (* 2 y)
	(= y 1) 2
	:else (A (- x 1)
		 (A x (- y 1)))))

(comment
user> (A 1 10)
1024
user> (A 2 4)
65536
user> (A 3 3)
65536
)

(defn f [n] (A 0 n)) ; f(n) = 2n
(defn g [n] (A 1 n)) ; g(n) = 2^n
(comment
  g (n) = A (1,n)
        = A (0, A (1, n-1)) = f (A(1,n-1))
	  = f (f (1,n-2)) 
	    .....
	    = f (f (f ... f (1,(n- (n-1)))))
	      = f (f (f ... 2))
		= 2 * (2^(n-1))
		  = 2^n
)

(defn h [n] (A 2 n)) ; h(n) = 2^(n^2)

;; section 1.2.2: Tree Recursion
(defn fib [n]
  (cond (= n 0) 0
	(= n 1) 1
	:else (+ (fib (- n 1))
		 (fib (- n 2)))))

;; iterative version
(defn fib [n]
  (fib-iter 1 0 n))

(defn fib-iter [a b count]
  (if (= count 0)
    b
    (fib-iter (+ a b) a (dec count))))

;; example: counting changes
(defn count-change [amount]
  (cc amount 5))

(defn cc [amount kinds-of-coins]
  (cond (= amount 0) 1
	(or (< amount 0) (= kinds-of-coins 0)) 0
	:else (+ (cc amount (- kinds-of-coins 1))
		 (cc (- amount
			(first-denomination kinds-of-coins))
		     kinds-of-coins))))

(defn first-denomination [kinds-of-coins]
  (cond (= kinds-of-coins 1) 1
	(= kinds-of-coins 2) 5
	(= kinds-of-coins 3) 10
	(= kinds-of-coins 4) 25
	(= kinds-of-coins 5) 50))
(defn first-denomination [kinds-of-coins]
  ({1 1 2 5 3 10 4 25 5 50} kinds-of-coins))

;; exercise 1.11: A function f is defined by the rule that f(n) = n if n < 3
;;                and f(n) = f(n - 1) + 2f(n  - 2) + 3f(n - 3) if n> 3.
;;                Write a procedure that computes f by means of a recursive
;;                process. Write a procedure that computes f by means of an
;;                iterative process. 
(defn f [n]
  (if (< n 3)
    n
    (+ (f (- n 1))
       (* 2 (f (- n 2)))
       (* 3 (f (- n 3))))))

(comment
user> (map f (range 10))
(0 1 2 4 11 25 59 142 335 796)  
)

;; ex 1.11: iterative version
(defn f [n]
  (if (< n 3)
    n
    (f-iter n 2 1 0)))

(defn f-iter [count prev0 prev1 prev2]
  (if (= count 3)
    (+ prev0
       (* 2 prev1)
       (* 3 prev2))
    (f-iter (dec count)
	    (+ prev0
	       (* 2 prev1)
	       (* 3 prev2))
	    prev0
	    prev1)))

;; ex 1.11: iterative version with let
(defn f-iter [count prev0 prev1 prev2]
  (let [res (+ prev0 (* 2 prev1) (* 3 prev2))]
    (if (= count 3)
      res
      (f-iter (dec count)
	      res
	      prev0
	      prev1))))

;; 1.2.4: exponentiation
;; computing b^n
(defn expt [b n]
  (if (= n 0)
    1
    (* b (expt b (dec n)))))

;; iterative
(defn expt [b n]
  (expt-iter b n 1))

(defn expt-iter [base counter product]
  (if (= counter 0)
    product
    (expt-iter b
	       (dec counter)
	       (* product base))))

;; fast version
(defn fast-expt [b n]
  (cond (= n 0) 1
	(even? n) (square (fast-expt b (/ n 2)))
	:else (* b (fast-expt b (dec n)))))

(defn even? [x]
  (= (rem x 2) 0))