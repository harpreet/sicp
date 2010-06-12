(ns sicp.ex2_13
  (:use [sicp utils ch2_1_extended ex2_7]
	[clojure.test]))
(comment
  "let l1 = c1 - t1,
       u1 = c1 + t1,
       l2 = c2 - t2 and
       u2 = c2 + t2.

Product of two intervals = min (multiples), max (multiples). But since
t1 and t2 are small, we ignore the t1*t2 terms, so the products
p1,p2,p3 and p4 are:

 p1 = c1*c2 - c1*t2 - c2*t1
 p2 = c1*c2 + c1*t2 - c2*t1
 p3 = c1*c2 - c1*t2 + c2*t1
 p4 = c1*c2 + c1*t2 + c2*t1

Now, since all numbers are positive, p1 is the min and p4 is the max.
So, percentage tolerance of the product =
 (c1t2 + c2t1)/c1c2   * 100

 But t1 = c1 * p1 and t2 = c2 *p2

So, product tolerance is p1 + p2. i.e. sum of individual tolerances."
)