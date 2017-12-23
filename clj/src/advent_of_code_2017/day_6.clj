(ns advent-of-code-2017.day-6)

(defn bank-to-empty
  "Return index of the bank that is to be emptied this cycle."
  [banks]
  (last (sort-by (fn [[blocks index]] [blocks (- (count banks) index)]) banks)))

(defn add-block [[blocks index]]
  [(inc blocks) index])

(defn wrapping-range [step]
  (map #(mod % step) (range)))

(defn redistribute [banks [blocks from-index :as from-bank]]
  (let [indexes (drop (inc from-index) (wrapping-range (count banks)))]
    (reduce
     (fn [banks index]
       (update banks index add-block))
     (assoc banks from-index [0 from-index])
     (take blocks indexes))))

(defn indexed-banks [banks]
  (vec (map-indexed #(vector %2 %1) banks)))

(defn run [input]
  (loop [banks (indexed-banks input) seen #{} count 1]
    (let [redistributed (redistribute banks (bank-to-empty banks))]
      (if (contains? seen redistributed)
        count
        (recur redistributed (conj seen redistributed) (inc count))))))

(defn run-2 [input]
  (let [first-cycle (run input)
        first-seen (reduce (fn [banks _]
                             (redistribute banks (bank-to-empty banks)))
                           (indexed-banks input) (range first-cycle))]
    (loop [banks first-seen count 1]
      (let [redistributed (redistribute banks (bank-to-empty banks))]
        (if (= first-seen redistributed)
          count
          (recur redistributed (inc count)))))))
