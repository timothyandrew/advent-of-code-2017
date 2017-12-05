(ns advent-of-code-2017.day-5)

(defn update-offset [current current-idx]
  (if (>= current 3)
    (dec current)
    (inc current)))

(defn run [input]
  (loop [path input current-idx 0 steps-taken 0]
    (if (< current-idx (count path))
      (let [current (nth path current-idx)]
        (recur (update path current-idx inc) (+ current-idx current) (inc steps-taken)))
      steps-taken)))

(defn run-2 [input]
  (loop [path input current-idx 0 steps-taken 0]
    (if (< current-idx (count path))
      (let [current (nth path current-idx)
            new-offset (update-offset current current-idx)]
        (recur (assoc path current-idx new-offset)
               (+ current-idx current)
               (inc steps-taken)))
      steps-taken)))
