(ns advent-of-code-2017.day-11
  (:require [clojure.string :as s]))

(defn move [[x y] direction]
  (cond
    (= direction "n") [x (inc y)]
    (= direction "s") [x (dec y)]
    (= direction "ne") [(inc x) (+ y 0.5)]
    (= direction "se") [(inc x) (- y 0.5)]
    (= direction "nw") [(dec x) (+ y 0.5)]
    (= direction "sw") [(dec x) (- y 0.5)]))


(defn distance-to-origin [[x y]]
  (if (= y 0.0)
    (Math/abs x)
    (+ (Math/abs (- y (/ x 2))) x)))

(defn run [input]
  (let [[x y] (reduce move [0 0] (s/split input #","))]
    (distance-to-origin [(float (Math/abs x)) (float (Math/abs y))])))

(defn run-2 [input]
  (apply max (map
              (fn [[x y]] (distance-to-origin [(float (Math/abs x)) (float (Math/abs y))]))
              (reductions move [0 0] (s/split input #",")))))
