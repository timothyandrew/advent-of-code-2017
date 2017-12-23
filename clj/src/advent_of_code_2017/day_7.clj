(ns advent-of-code-2017.day-7
  (:require [clojure.string :as s]
            [clojure.set :as set]))

(defn name->programs [lines]
  (reduce
   (fn [memo [name weight _ :as program]] (assoc memo name program))
   {}
   lines))

(defn cumulative-weight-for-program [programs [name weight above :as program]]
  (if above
    (apply + weight (map #(cumulative-weight-for-program programs %) (map programs above)))
    weight))

(defn name->cumulative-weights [lines programs]
  (reduce
   (fn [memo [name weight above :as program]]
     (assoc memo name (cumulative-weight-for-program programs program)))
   {}
   lines))

(defn parse-line [line]
  (if-let [[_ name weight] (re-matches #"(\w+)\s\((\d+)\)" line)]
    [name (Integer/parseInt weight)]
    (let [[_ name weight above] (re-matches #"(\w+)\s\((\d+)\)\s->\s(.*)" line)]
      [name (Integer/parseInt weight) (map s/trim (s/split above #","))])))

(defn below-names [memo [name weight above]]
  (if above
    (reduce
     (fn [memo above-name]
       (assoc memo above-name name))
     memo above)
    memo))

(defn above-balanced? [programs cumulative-weights [name weight above]]
  (if above
    (= 1 (count (set (map #(cumulative-weights %) above))))
    true))

(defn run [lines]
  (let [lines (map parse-line lines)
        below (set (keys (reduce below-names {} lines)))
        all-names (set (map first lines))]
    (first (set/difference all-names below))))

(defn run-2 [lines]
  (let [lines (map parse-line lines)
        programs (name->programs lines)
        cumulative-weights (name->cumulative-weights lines programs)
        [name weight above :as unbalanced] (first (remove (partial above-balanced? programs cumulative-weights) lines))]

    [unbalanced
     (cumulative-weights "amhaz")
     (cumulative-weights "cumah")
     (cumulative-weights "zqfvypo")
     (cumulative-weights "dribos")
     (programs "cumah")]))
