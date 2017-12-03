(ns advent-of-code-2017.day-2)

(defn diff-largest-and-smallest [row]
  (- (apply max (remove nil? row))
     (apply min (remove nil? row))))

(defn evenly-divisible? [a b]
  (and (not= a 0) (not= b 0)
       (or (integer? (/ a b))
           (integer? (/ b a)))))

(defn find-evenly-divisible-pair [row]
  (when (next row)
    (if-let [match (first (filter #(evenly-divisible? (first row) %) (next row)))]
      [(first row) match]
      (find-evenly-divisible-pair (next row)))))

(defn divide-pair [[a b]]
  (if (> a b)
    (/ a b)
    (/ b a)))

(defn run [spreadsheet]
  (->> spreadsheet
       (map diff-largest-and-smallest)
       (apply +)))

(defn run-2 [spreadsheet]
  (->> spreadsheet
       (map find-evenly-divisible-pair)
       (map divide-pair)
       (reduce +)))
