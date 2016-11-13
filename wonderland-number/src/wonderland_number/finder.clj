(ns wonderland-number.finder)

(defn same-digits? [n1 n2]
  (let [s1 (set (str n1))
        s2 (set (str n2))
        ]
    (= s1 s2))
  )

(defn enough-digits? [n]
  (= 6 (count (str n))))

(defn wonderland-number? [n]
  (and
   (enough-digits? n)
   (every? identity (map #(same-digits? n (* n %)) [2 3 4 5 6]))
   )
  )

(defn wonderland-number []
  ;; calculate me
  (first (filter wonderland-number? (range 100000 1000000)))
  )

