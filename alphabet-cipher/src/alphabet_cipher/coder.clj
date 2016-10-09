(ns alphabet-cipher.coder
  (:import java.lang.String))

(defn encode [keyword message]
  (let [key-code (map int (take (count message) (cycle keyword)))
        mes-code (map int message)
        alphabet "abcdefghijklmnopqrstuvwxyz"
        ]    
    (apply str (map
     #(nth (drop (- %2 97) (cycle alphabet)) (- %1 97))
     key-code mes-code)
         )))



(defn decode [keyword message]
  "decodeme"
  (let [
        keyword-full  (take (count message) (cycle keyword))
        alphabet "abcdefghijklmnopqrstuvwxyz"
        ]
    (apply str (map
     #(nth alphabet (.indexOf (apply str (take 26 (drop (- (int %1) 97) (cycle alphabet)))) (str %2)))
    keyword-full
    message
    )))
  )

(defn test-generating-substring [rep-key i]
  (not-any? false? (map #(= %1 %2) (take i rep-key) (take i (drop i (cycle rep-key))))))


(defn create-sub-chart-row-LUT [c]
  (let [charmap (map identity "abcdefghijklmnopqrstuvwxyz")]
    (into {} (zipmap (take (count charmap) (drop-while #(not= % c) (cycle charmap) )) charmap))
    )
  )

(defn decipher [cipher message]
  (let [rep-key (apply str (map #((create-sub-chart-row-LUT %1) %2) (map identity message) (map identity cipher)))]
    (some #(when (test-generating-substring rep-key %) (apply str (take % rep-key))) (range 2 (count rep-key)))
    )
  )



  
  
