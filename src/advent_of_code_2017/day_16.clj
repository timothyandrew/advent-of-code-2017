(ns advent-of-code-2017.day-16
  (:require [clojure.string :as s]))

(defn inc-nil [n]
  (if (nil? n) 1 (inc n)))

(defn build-program [start end]
  (vec (map char (range (int start) (inc (int end))))))

(defn spin [p n]
  (concat (reverse (take n (reverse p))) (take (- (count p) n) p)))

(defn exchange [p l-idx r-idx]
  (let [p (vec p)
        l (nth p l-idx)
        r (nth p r-idx)]
    (-> p
        (assoc l-idx r)
        (assoc r-idx l))))

(defn partner [p l-name r-name]
  (map
   (fn [c]
     (cond
       (= c l-name) r-name
       (= c r-name) l-name
       :default c))
   p))

(defn parse-instruction [instruction]
  (let [[_ move l r] (re-matches #"(\w)([^/]*)\/?(.*)?" instruction)]
    {:move move :l l :r r}))

(defn apply-instruction [p {move :move l :l r :r}]
  (case move
    "s" (spin p (Integer/parseInt l))
    "p" (partner p (first (seq l)) (first (seq r)))
    "x" (exchange p (Integer/parseInt l) (Integer/parseInt r))))

(def cache (atom {}))
(defn apply-instructions [p instructions]
  (let [result (reduce apply-instruction p instructions)]
    (swap! cache update (s/join result) inc-nil)
    result))

(defn run [[start end] instructions]
  (let [program (build-program start end)]
    (->> instructions
         (map parse-instruction)
         (reduce apply-instruction program)
         (s/join))))

(defn run-2 [[start end] instructions]
  (let [program (build-program start end)]
    (->> instructions
         (map parse-instruction)
         cycle
         (partition (count instructions))
         (take (rem 1000000000 42)) ;; Dance cycles every 42 iterations
         (reduce apply-instructions program)
         (s/join))))
