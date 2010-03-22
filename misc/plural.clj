;; from Brian Harvey's Spring 2008 lecture 01.
(defn plural [wd]
  (if (= (last wd) \y)
    (apply str (concat (butlast wd) "ies"))
    (apply str (concat wd "s"))))