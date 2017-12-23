(ns advent-of-code-2017.day-17)

(defn insert-2 [[n current current-idx] step]
  (let [insert-idx (rem (inc (+ current-idx step)) n)]
    [(inc n)
     (inc current)
     insert-idx]))

(defn insert [[buf current current-idx] step]
  (let [insert-idx (rem (inc (+ current-idx step)) (inc current))]
    [(into []
           (concat (subvec buf 0 insert-idx)
                   [(inc current)]
                   (subvec buf insert-idx)))
     (inc current)
     insert-idx]))

(defn track-zero-and-adj [[[zero adj] current current-idx] step]
  (let [current (inc current)
        insert-idx (rem (inc (+ current-idx step)) current)
        result (cond
                 (< insert-idx (:index adj)) [(update zero :index inc) (update adj :index inc)]
                 (= insert-idx (:index adj)) [zero (assoc adj :n current)]
                 (> insert-idx (:index adj)) [zero adj])]
    [result current insert-idx]))

(defn run-1 [step rounds target]
  (let [result (reduce
                insert
                [[0 1] 1 1]
                (take (dec rounds) (repeat step)))]
    (second (drop-while #(not= % target) (first result)))))

(defn run-2 [step rounds]
  (let [[[zero adj] _ _] (reduce
                          track-zero-and-adj
                          [[{:n 0 :index 0} {:n 1 :index 1}] 1 1]
                          (take (dec rounds) (repeat step)))]
    (:n adj)))
