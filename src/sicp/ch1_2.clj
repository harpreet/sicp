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

;; exercise 1.9
(defn ++ [a b]
  (if (= a 0)
    b
    (inc (++ (dec a) b))))

;; (comment
;;   This version is a recursive process, where the previous call increments
;;   the sum by 1 and each call decrement the first operand by 1.
  
;; user> (dotrace [++] (++ 4 5))
;; TRACE t3745: (++ 4 5)
;; TRACE t3746: |    (++ 3 5)
;; TRACE t3747: |    |    (++ 2 5)
;; TRACE t3748: |    |    |    (++ 1 5)
;; TRACE t3749: |    |    |    |    (++ 0 5)
;; TRACE t3749: |    |    |    |    => 5
;; TRACE t3748: |    |    |    => 6
;; TRACE t3747: |    |    => 7
;; TRACE t3746: |    => 8
;; TRACE t3745: => 9
;; 9
;; )

(defn ++ [a b]
  (if (= a 0)
    b
    (++ (dec a) (inc b))))

;; (comment
  
;; user> (dotrace [++] (++ 4 5))
;; TRACE t3766: (++ 4 5)
;; TRACE t3767: |    (++ 3 6)
;; TRACE t3768: |    |    (++ 2 7)
;; TRACE t3769: |    |    |    (++ 1 8)
;; TRACE t3770: |    |    |    |    (++ 0 9)
;; TRACE t3770: |    |    |    |    => 9
;; TRACE t3769: |    |    |    => 9
;; TRACE t3768: |    |    => 9
;; TRACE t3767: |    => 9
;; TRACE t3766: => 9
;; 9
;; )

;; exercise 1.10
;; ackerman functions
(defn A [x y]
  (cond (= y 0) 0
	(= x 0) (* 2 y)
	(= y 1) 2
	:else (A (- x 1)
		 (A x (- y 1)))))

;; (comment
;; user> (A 1 10)
;; 1024
;; user> (A 2 4)
;; 65536
;; user> (A 3 3)
;; 65536
;; )

(defn f [n] (A 0 n)) ; f(n) = 2n
(defn g [n] (A 1 n)) ; g(n) = 2^n
;; (comment
;;   g (n) = A (1,n)
;;         = A (0, A (1, n-1)) = f (A(1,n-1))
;; 	  = f (f (1,n-2)) 
;; 	    .....
;; 	    = f (f (f ... f (1,(n- (n-1)))))
;; 	      = f (f (f ... 2))
;; 		= 2 * (2^(n-1))
;; 		  = 2^n
;; )

(defn h [n] (A 2 n)) ; h(n) = 2^(n^2)

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

;; (comment
;; user> (map f (range 10))
;; (0 1 2 4 11 25 59 142 335 796)  
;; )

;; ex 1.11: iterative version
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

(defn f [n]
  (if (< n 3)
    n
    (f-iter n 2 1 0)))

;; ex 1.11: iterative version with let
(defn f-iter [count prev0 prev1 prev2]
  (let [res (+ prev0 (* 2 prev1) (* 3 prev2))]
    (if (= count 3)
      res
      (f-iter (dec count)
	      res
	      prev0
	      prev1))))

;; exercise 1.12.  The following pattern of numbers is called Pascal's triangle.
;;          1
;;        1   1
;;      1   2   1
;;    1   3   3   1
;;  1   4   6   4   1
;; ...................
;;
;;                 The numbers at the edge of the triangle are all 1, and each
;;                 number inside the triangle is the sum of the two numbers above
;;                 it. Write a procedure that computes elements of Pascal's triangle
;;                 by means of a recursive process. 
(defn pascal [row col]
  (when (<= col row)
    (if (or (= col 0) (= row col))
      1
      (+ (pascal (dec row) col)
	 (pascal (dec row) (dec col))))))

;; exercise 1.13:
(comment
See the pdfs in the directory for the answers.
)

;; ex 1.13 (contd)
(defn fib-approx [n]
  (let [phi (/ (+ 1 (sqrt 5)) 2)]
    (/ (expt phi n) (sqrt 5))))

;; (comment
;; user> (map fib-approx (range 10))
;; (0.4472135954999579 0.7236067977499789 1.1708203932499368 1.8944271909999157 3.065247584249853 4.959674775249769 8.024922359499623 12.984597134749393 21.009519494249016 33.99411662899841)
;; )

;; exercise 1.14: tree of (count-changes 11)
(comment
  See the pdf for the tree representation.
)


;; order of size and computation
;; see PDF, but below is the trace tree.
;; (comment
;; user> (use 'clojure.contrib.trace)
;; nil
;; user> (dotrace [count-change cc] (count-change 11))
;; TRACE t2609: (count-change 11)
;; TRACE t2610: |    (cc 11 5)
;; TRACE t2611: |    |    (cc 11 4)
;; TRACE t2612: |    |    |    (cc 11 3)
;; TRACE t2613: |    |    |    |    (cc 11 2)
;; TRACE t2614: |    |    |    |    |    (cc 11 1)
;; TRACE t2615: |    |    |    |    |    |    (cc 11 0)
;; TRACE t2615: |    |    |    |    |    |    => 0
;; TRACE t2616: |    |    |    |    |    |    (cc 10 1)
;; TRACE t2617: |    |    |    |    |    |    |    (cc 10 0)
;; TRACE t2617: |    |    |    |    |    |    |    => 0
;; TRACE t2618: |    |    |    |    |    |    |    (cc 9 1)
;; TRACE t2619: |    |    |    |    |    |    |    |    (cc 9 0)
;; TRACE t2619: |    |    |    |    |    |    |    |    => 0
;; TRACE t2620: |    |    |    |    |    |    |    |    (cc 8 1)
;; TRACE t2621: |    |    |    |    |    |    |    |    |    (cc 8 0)
;; TRACE t2621: |    |    |    |    |    |    |    |    |    => 0
;; TRACE t2622: |    |    |    |    |    |    |    |    |    (cc 7 1)
;; TRACE t2623: |    |    |    |    |    |    |    |    |    |    (cc 7 0)
;; TRACE t2623: |    |    |    |    |    |    |    |    |    |    => 0
;; TRACE t2624: |    |    |    |    |    |    |    |    |    |    (cc 6 1)
;; TRACE t2625: |    |    |    |    |    |    |    |    |    |    |    (cc 6 0)
;; TRACE t2625: |    |    |    |    |    |    |    |    |    |    |    => 0
;; TRACE t2626: |    |    |    |    |    |    |    |    |    |    |    (cc 5 1)
;; TRACE t2627: |    |    |    |    |    |    |    |    |    |    |    |    (cc 5 0)
;; TRACE t2627: |    |    |    |    |    |    |    |    |    |    |    |    => 0
;; TRACE t2628: |    |    |    |    |    |    |    |    |    |    |    |    (cc 4 1)
;; TRACE t2629: |    |    |    |    |    |    |    |    |    |    |    |    |    (cc 4 0)
;; TRACE t2629: |    |    |    |    |    |    |    |    |    |    |    |    |    => 0
;; TRACE t2630: |    |    |    |    |    |    |    |    |    |    |    |    |    (cc 3 1)
;; TRACE t2631: |    |    |    |    |    |    |    |    |    |    |    |    |    |    (cc 3 0)
;; TRACE t2631: |    |    |    |    |    |    |    |    |    |    |    |    |    |    => 0
;; TRACE t2632: |    |    |    |    |    |    |    |    |    |    |    |    |    |    (cc 2 1)
;; TRACE t2633: |    |    |    |    |    |    |    |    |    |    |    |    |    |    |    (cc 2 0)
;; TRACE t2633: |    |    |    |    |    |    |    |    |    |    |    |    |    |    |    => 0
;; TRACE t2634: |    |    |    |    |    |    |    |    |    |    |    |    |    |    |    (cc 1 1)
;; TRACE t2635: |    |    |    |    |    |    |    |    |    |    |    |    |    |    |    |    (cc 1 0)
;; TRACE t2635: |    |    |    |    |    |    |    |    |    |    |    |    |    |    |    |    => 0
;; TRACE t2636: |    |    |    |    |    |    |    |    |    |    |    |    |    |    |    |    (cc 0 1)
;; TRACE t2636: |    |    |    |    |    |    |    |    |    |    |    |    |    |    |    |    => 1
;; TRACE t2634: |    |    |    |    |    |    |    |    |    |    |    |    |    |    |    => 1
;; TRACE t2632: |    |    |    |    |    |    |    |    |    |    |    |    |    |    => 1
;; TRACE t2630: |    |    |    |    |    |    |    |    |    |    |    |    |    => 1
;; TRACE t2628: |    |    |    |    |    |    |    |    |    |    |    |    => 1
;; TRACE t2626: |    |    |    |    |    |    |    |    |    |    |    => 1
;; TRACE t2624: |    |    |    |    |    |    |    |    |    |    => 1
;; TRACE t2622: |    |    |    |    |    |    |    |    |    => 1
;; TRACE t2620: |    |    |    |    |    |    |    |    => 1
;; TRACE t2618: |    |    |    |    |    |    |    => 1
;; TRACE t2616: |    |    |    |    |    |    => 1
;; TRACE t2614: |    |    |    |    |    => 1
;; TRACE t2637: |    |    |    |    |    (cc 6 2)
;; TRACE t2638: |    |    |    |    |    |    (cc 6 1)
;; TRACE t2639: |    |    |    |    |    |    |    (cc 6 0)
;; TRACE t2639: |    |    |    |    |    |    |    => 0
;; TRACE t2640: |    |    |    |    |    |    |    (cc 5 1)
;; TRACE t2641: |    |    |    |    |    |    |    |    (cc 5 0)
;; TRACE t2641: |    |    |    |    |    |    |    |    => 0
;; TRACE t2642: |    |    |    |    |    |    |    |    (cc 4 1)
;; TRACE t2643: |    |    |    |    |    |    |    |    |    (cc 4 0)
;; TRACE t2643: |    |    |    |    |    |    |    |    |    => 0
;; TRACE t2644: |    |    |    |    |    |    |    |    |    (cc 3 1)
;; TRACE t2645: |    |    |    |    |    |    |    |    |    |    (cc 3 0)
;; TRACE t2645: |    |    |    |    |    |    |    |    |    |    => 0
;; TRACE t2646: |    |    |    |    |    |    |    |    |    |    (cc 2 1)
;; TRACE t2647: |    |    |    |    |    |    |    |    |    |    |    (cc 2 0)
;; TRACE t2647: |    |    |    |    |    |    |    |    |    |    |    => 0
;; TRACE t2648: |    |    |    |    |    |    |    |    |    |    |    (cc 1 1)
;; TRACE t2649: |    |    |    |    |    |    |    |    |    |    |    |    (cc 1 0)
;; TRACE t2649: |    |    |    |    |    |    |    |    |    |    |    |    => 0
;; TRACE t2650: |    |    |    |    |    |    |    |    |    |    |    |    (cc 0 1)
;; TRACE t2650: |    |    |    |    |    |    |    |    |    |    |    |    => 1
;; TRACE t2648: |    |    |    |    |    |    |    |    |    |    |    => 1
;; TRACE t2646: |    |    |    |    |    |    |    |    |    |    => 1
;; TRACE t2644: |    |    |    |    |    |    |    |    |    => 1
;; TRACE t2642: |    |    |    |    |    |    |    |    => 1
;; TRACE t2640: |    |    |    |    |    |    |    => 1
;; TRACE t2638: |    |    |    |    |    |    => 1
;; TRACE t2651: |    |    |    |    |    |    (cc 1 2)
;; TRACE t2652: |    |    |    |    |    |    |    (cc 1 1)
;; TRACE t2653: |    |    |    |    |    |    |    |    (cc 1 0)
;; TRACE t2653: |    |    |    |    |    |    |    |    => 0
;; TRACE t2654: |    |    |    |    |    |    |    |    (cc 0 1)
;; TRACE t2654: |    |    |    |    |    |    |    |    => 1
;; TRACE t2652: |    |    |    |    |    |    |    => 1
;; TRACE t2655: |    |    |    |    |    |    |    (cc -4 2)
;; TRACE t2655: |    |    |    |    |    |    |    => 0
;; TRACE t2651: |    |    |    |    |    |    => 1
;; TRACE t2637: |    |    |    |    |    => 2
;; TRACE t2613: |    |    |    |    => 3
;; TRACE t2656: |    |    |    |    (cc 1 3)
;; TRACE t2657: |    |    |    |    |    (cc 1 2)
;; TRACE t2658: |    |    |    |    |    |    (cc 1 1)
;; TRACE t2659: |    |    |    |    |    |    |    (cc 1 0)
;; TRACE t2659: |    |    |    |    |    |    |    => 0
;; TRACE t2660: |    |    |    |    |    |    |    (cc 0 1)
;; TRACE t2660: |    |    |    |    |    |    |    => 1
;; TRACE t2658: |    |    |    |    |    |    => 1
;; TRACE t2661: |    |    |    |    |    |    (cc -4 2)
;; TRACE t2661: |    |    |    |    |    |    => 0
;; TRACE t2657: |    |    |    |    |    => 1
;; TRACE t2662: |    |    |    |    |    (cc -9 3)
;; TRACE t2662: |    |    |    |    |    => 0
;; TRACE t2656: |    |    |    |    => 1
;; TRACE t2612: |    |    |    => 4
;; TRACE t2663: |    |    |    (cc -14 4)
;; TRACE t2663: |    |    |    => 0
;; TRACE t2611: |    |    => 4
;; TRACE t2664: |    |    (cc -39 5)
;; TRACE t2664: |    |    => 0
;; TRACE t2610: |    => 4
;; TRACE t2609: => 4
;; 4  
;; )


;; TODO: orders of growth in space and number of steps.

;; exercise 1.15: sin (x) calculation
;;    a.  How many times is the procedure p applied when (sine 12.15)
;;        is evaluated?
;;    b.  What is the order of growth in space and number of steps (as
;;        a function of a) used by the process generated by the sine
;;        procedure when (sine a) is evaluated?
(defn p [x] (- (* 3 x) (* 4 (cube x))))

(defn sine [angle]
  (if (not (> (myabs angle) 0.1))
    angle
    (p (sine (/ angle 3.0)))))

;; solution to (a) => 5
;; (comment
;; user> (dotrace [p] (sine 12.15))
;; TRACE t2490: (p 0.049999999999999996)
;; TRACE t2490: => 0.1495
;; TRACE t2491: (p 0.1495)
;; TRACE t2491: => 0.4351345505
;; TRACE t2492: (p 0.4351345505)
;; TRACE t2492: => 0.9758465331678772
;; TRACE t2493: (p 0.9758465331678772)
;; TRACE t2493: => -0.7895631144708228
;; TRACE t2494: (p -0.7895631144708228)
;; TRACE t2494: => -0.39980345741334
;; -0.39980345741334
;; )

;; solution to b
;; both space and number of steps grows as log3(a) -> log a to the base 3.
;;
;; proof:
;;   a * (1/3)^n <= 0.1
;;   => take log to the base 3 on both the sides.

;; Note: Finding the order of space in a recursive process is sort of, equiv
;;       to finding the number of deferred operations. Which is in-turn the
;;       same as the depth of the evaluation tree.

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

;; exercise 1.16:
(defn myexpt [b n]
  (expt-iter b n 1))

(defn expt-iter [b n a]
  (cond (= n 0) a
	(even? n) (expt-iter (square b) (/ n 2) a)
	:else (expt-iter b (- n 1) (* a b))))

;; exercise 1.17:
(defn mult [a b]
  (if (= b 0)
    0
    (+ a (mult a (- b 1)))))

;; double
;; product = 2 * (a * (b/2)) for even b
;;         = a    + (a * (b - 1)) for odd b
(defn fast-mult [a b]
  (cond (= b 0) 0
	(= b 1) a
	(even? b) (twice (fast-mult a (half b)))
	:else (+ a (fast-mult a (- b 1)))))

;; exercise 1.18: iterative multiply thru addition
;; the idea is to keep a state variable.
(defn fast-mult-iter [a b k]
  (cond (= b 0) k
	(even? b) (fast-mult-iter (twice a) (half b) k)
	:else (fast-mult-iter a (- b 1) (+ k a))))

(defn fast-mult-new [a b]
  (fast-mult-iter a b 0))

;; (comment
;; user> (dotrace [fast-mult-new fast-mult-iter] (fast-mult-new 2 3))
;; TRACE t2915: (fast-mult-new 2 3)
;; TRACE t2916: |    (fast-mult-iter 2 3 0)
;; TRACE t2917: |    |    (fast-mult-iter 2 2 2)
;; TRACE t2918: |    |    |    (fast-mult-iter 4 1 2)
;; TRACE t2919: |    |    |    |    (fast-mult-iter 4 0 6)
;; TRACE t2919: |    |    |    |    => 6
;; TRACE t2918: |    |    |    => 6
;; TRACE t2917: |    |    => 6
;; TRACE t2916: |    => 6
;; TRACE t2915: => 6
;; 6
;; )

;; exercise 1.19: fast fibonacci
;; see the pdf of the notebook scan for the derivation of p' and q'
(defn ffib-iter [a b p q count]
  (cond (= count 0) b
	(even? count)
	(ffib-iter a
		   b
		   (+ (* p p) (* q q))
		   (+ (* 2 p q) (* q q))
		   (/ count 2))
	:else (ffib-iter (+ (* b q) (* a q) (* a p))
			 (+ (* b p) (* a q))
			 p
			 q
			 (- count 1))))

(defn ffib [n]
  (ffib-iter 1 0 0 1 n))

;;;  Section 1.2.5: GCD
(defn mygcd [a b]
  (if (= b 0)
    a
    (mygcd b (rem a b))))

;;; exercise 1.20.
;;
;;  normal order - 18, applicative order - 4.
;;
;;   too lazy to scan things from the notebook. May be I should instead
;;   use a wiki.

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

;; exercise 1.21
(comment
user> (smallest-divisor 199)
199
user> (smallest-divisor 1999)
1999
user> (smallest-divisor 19999)
7
)

;; exercise 1.22
(defn timed-prime-test [n]
  (prn)
  (print n)
  (start-prime-test n (System/nanoTime)))

(defn start-prime-test [n start-time]
  (if (prime? n)
    (report-prime (- (System/nanoTime) start-time))))

(defn report-prime [elapsed-time]
  (print " *** ")
  (print elapsed-time))

(defn search-for-primes [a b]
  (cond (>= a b) nil
	(even? a) (search-for-primes (+ 1 a) b)
	(timed-prime-test a) (search-for-primes (+ 2 a) b)
	:else (search-for-primes (+ 2 a) b)))

;;; three smallest primes greater than 1000
;;; 1009, 1013, 1019
(take 3 (filter #(prime? %) (iterate inc 1000)))
;=> (1009 1013 1019)

;=> 0.9642028750000001

;;; > 10,000: 10007, 10009, 10037
(take 3 (filter #(prime? %) (iterate inc 10000)))
;=> (10007 10009 10037)

;=> 1.5897884999999998

;;; > 100,000: 100003, 100019, 100043
(take 3 (filter #(prime? %) (iterate inc 100000)))
;=> (100003 100019 100043)

;=> 1.8525091250000003

;;; > 1,000,000: 1000003, 1000033, 1000037
(take 3 (filter #(prime? %) (iterate inc 1000000)))
;=> (1000003 1000033 1000037)

;=> 1.908832125

;; time taken seem to increase as the range increases.
;; but they are totally random on the jvm, so I can't find
;; the exact relation.

(comment
user> (microbench 10 (take 3 (filter #(prime? %) (iterate inc 1000))))
Warming up!
Benchmarking...
(1009 1013 1019)
(1009 1013 1019)
(1009 1013 1019)
(1009 1013 1019)
(1009 1013 1019)
(1009 1013 1019)
(1009 1013 1019)
(1009 1013 1019)
(1009 1013 1019)
(1009 1013 1019)
Total runtime:  0.28404500000000005
Highest time :  0.083949
Lowest time  :  0.019416
Average      :  0.022585000000000008
(0.083949 0.019416 0.023257 0.020394 0.024165 0.024514 0.024374 0.020813 0.021721 0.021442)
user> (microbench 10 (take 3 (filter #(prime? %) (iterate inc 10000))))
Warming up!
Benchmarking...
(10007 10009 10037)
(10007 10009 10037)
(10007 10009 10037)
(10007 10009 10037)
(10007 10009 10037)
(10007 10009 10037)
(10007 10009 10037)
(10007 10009 10037)
(10007 10009 10037)
(10007 10009 10037)
Total runtime:  0.26462800000000003
Highest time :  0.067118
Lowest time  :  0.020533
Average      :  0.022122125000000006
(0.067118 0.022698 0.024095 0.023537 0.020533 0.020882 0.020603 0.021372 0.020603 0.023187)
user> (microbench 10 (take 3 (filter #(prime? %) (iterate inc 100000))))
Warming up!
Benchmarking...
(100003 100019 100043)
(100003 100019 100043)
(100003 100019 100043)
(100003 100019 100043)
(100003 100019 100043)
(100003 100019 100043)
(100003 100019 100043)
(100003 100019 100043)
(100003 100019 100043)
(100003 100019 100043)
Total runtime:  0.265118
Highest time :  0.073263
Lowest time  :  0.020254
Average      :  0.021450125000000004
(0.073263 0.023048 0.023467 0.021022 0.020394 0.020254 0.021302 0.020812 0.020743 0.020813)  
)

;;; can't make out any sqrt(10) relation between the numbers. may be because
;;; jvm compilation is doing some behind the scene tricks.

;;; exercise 1.23
(defn next-divisor [n]
  (if (= n 2)
    3
    (+ n 2)))


(defn find-divisor [n test-divisor]
  (cond (> (square test-divisor)  n) n
	(divides? test-divisor n) test-divisor
	:else (find-divisor n (next-divisor test-divisor))))

(comment
  I can't see any noticable difference in the speed.
  )

;;; exercise 1.24
(comment
  (microbench 10 (take 3 (filter #(fast-prime? %) (iterate inc 1000))))
  ...
  "I did not observe any difference".
  )

;; exercise 1.25
(defn expmod [base exp m]
  (rem (fast-expt base exp) m))

(comment
  In the case of the original expmod implementation, square and remainder
  calls are interspersed, so square always deals with a small number, whereas
  with the above way, we do a series of squaring and then in the end take
  remainder. Squaring of big numbers are very inefficient as the CPU has to
  do multi-byte arithmetic which consumes many cycles.

  So the new version is several times slower than the original.
)

;;; exercise 1.26
(comment
  "Instead of calling (square x), Louis now makes does (* x x). In the former,
   case, x is evaluated only once, where as in the second, x gets evaluated
   2x, 4x, 8x, 16x and so on (for any x which is recursive). So, if the original
   computation is considered T(log_n), then the new process T(n). This can also
   be illustrated with the call tree."
)

;; exercise 1.27
(comment
  "Some notes on Carmichael numbers: Carmichael numbers are those that fail
   Fermat little test. That is, for any n in the Carmichael set,
   (prime? n)      => false
   (fermat-test n) => true."
  )
(defn brute-force-fermat-test [n]
  (try-all 2 n))

(defn try-all [a n]
  (cond (= a n) true
	(try-it a n) (try-all (inc a) n)
	:else false))
(comment
  "all the given numbers pass the above test, i.e. for every a < n,
   a^n mod n === a mod n"
  user> (brute-force-fermat-test 561)
  true
  user> (brute-force-fermat-test 1105)
  true
  user> (brute-force-fermat-test 1729)
  true
  user> (brute-force-fermat-test 2465)
  true
  user> (brute-force-fermat-test 2821)
  true
  user> (brute-force-fermat-test 6601)
  true
  )

;;; exercise 1.28
(defn expmod2 [base exp m]
  (cond (= exp 0) 1
	(even? exp) (square-test (expmod2 base (/ exp 2) m) m)
	:else (rem (* base (expmod2 base (dec exp) m))
		   m)))

(defn square-test [x m]
  (if (and (not (or (= x 1) (= x (- m 1))))
	   (= (rem (square x) m) 1))
    0
    (rem (square x) m)))

(defn miller-rabin-test [n]
  (try-it (+ 2 (rand-int (- n 2)))
	  n))

(defn try-it [a n]
  (= (expmod2 a (- n 1) n) 1))

(comment
  "If the random number generated (a) is 1, then this returns false
   positives. So generate random numbers between 2 and n-1. (is this
   assumption correct?) "
  
  )