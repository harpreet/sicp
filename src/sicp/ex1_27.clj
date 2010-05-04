(ns sicp.ex1_27
  (:use [sicp utils]
	[clojure.contrib trace test-is]))

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
