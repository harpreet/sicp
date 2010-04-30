(ns sicp.ex1.26
  (:use [sicp utils]
	[clojure.contrib trace test-is]))

;;; exercise 1.26
(comment
  "Instead of calling (square x), Louis now makes does (* x x). In the former,
   case, x is evaluated only once, where as in the second, x gets evaluated
   2x, 4x, 8x, 16x and so on (for any x which is recursive). So, if the original
   computation is considered T(log_n), then the new process T(n). This can also
   be illustrated with the call tree."
)
