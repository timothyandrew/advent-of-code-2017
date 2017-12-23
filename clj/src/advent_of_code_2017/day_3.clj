(ns advent-of-code-2017.day-3)

(defn abs [n] (Math/abs n))

;; Directions
(defn up [x y] [x (inc y)])
(defn down [x y] [x (dec y)])
(defn left [x y] [(dec x) y])
(defn right [x y] [(inc x) y])
(defn up-left [x y] [(dec x) (inc y)])
(defn up-right [x y] [(inc x) (inc y)])
(defn down-left [x y] [(dec x) (dec y)])
(defn down-right [x y] [(inc x) (dec y)])
(def all-directions (juxt up down left right up-left up-right down-left down-right))

(defn calc-side [x y] (max (abs x) (abs y)))

(defn next-step-in-spiral [[x y]]
  (let [side (calc-side x y)]
    (cond
      ;; Corners
      (and (= x side) (= y side)) (left x y)
      (and (= x (- side)) (= y side)) (down x y)
      (and (= x (- side)) (= y (- side))) (right x y)
      (and (= x side) (= y (- side))) (right x y)

      ;; All others
      (= x side) (up x y)
      (= y side) (left x y)
      (= x (- side)) (down x y)
      (= y (- side)) (right x y))))

(defn spiral-numbers
  ([] (cons {:n 1 :xy [0 0]} (spiral-numbers 2 [1 0])))
  ([n xy]
   (let [annotated {:n n :xy xy}]
     (lazy-seq (cons annotated (spiral-numbers (inc n) (next-step-in-spiral xy)))))))

(defn spiral-number
  "Get the nth spiral number with coordinates"
  [n]
  (first (drop-while #(not= (:n %) n) (spiral-numbers))))

(defn spiral-adj-find-n
  "Given coordinates and a map of already filled positions,
   what number goes in this position?"
  [[x y] filled]
  (->> (all-directions x y)
       (map filled)
       (remove nil?)
       (apply +)))

(defn spiral-adj-numbers
  "Spiral in part 2"
  ([]
   (let [root 1]
     (cons root (spiral-adj-numbers [1 0] {[0 0] root}))))
  ([xy filled]
   (let [n (spiral-adj-find-n xy filled)]
     (lazy-seq (cons n (spiral-adj-numbers (next-step-in-spiral xy) (assoc filled xy n)))))))

(defn run [n]
  (let [{[x y] :xy} (spiral-number n)]
    (+ (abs x) (abs y))))

(defn run-part-2 []
  (first (drop-while #(< % 265149) (spiral-adj-numbers))))
