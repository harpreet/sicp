(ns sicp.ex1_25
  (:use [sicp utils]
	[clojure.contrib trace test-is]))

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
