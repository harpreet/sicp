(ns sicp.ex1_14
  (:use [sicp utils]
	[clojure.contrib trace test-is]))

;; exercise 1.14: tree of (count-changes 11)
(comment
  See the notebook (sorry, don't have the time to scan it and put it on the web)
  for the tree representation.
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
