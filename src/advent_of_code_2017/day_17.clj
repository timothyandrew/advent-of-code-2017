(ns advent-of-code-2017.day-17)

(defn insert-2 [[n current current-idx] step]
  (let [insert-idx (rem (inc (+ current-idx step)) n)]
    [(inc n)
     (inc current)
     insert-idx]))

(defn insert [[buf current current-idx] step]
  (let [insert-idx (rem (inc (+ current-idx step)) (count buf))]
    [(into []
           (concat (subvec buf 0 insert-idx)
                   [(inc current)]
                   (subvec buf insert-idx)))
     (inc current)
     insert-idx]))

(defn run-1 [step rounds target]
  (let [result (reduce
                insert
                [[0 1] 1 1]
                (take (dec rounds) (repeat step)))]
    (second (drop-while #(not= % target) (first result)))))
