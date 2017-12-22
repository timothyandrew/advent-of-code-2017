(ns advent-of-code-2017.day-19
  (:require [clojure.string :as s]))

(defn pad-input [input]
  (let [max-count (apply max (map count input))]
    (mapv #(vec (take max-count (concat % (repeat ""))))
          (map #(s/split % #"") input))))

(defn north [[x y]] [[(dec x) y] :n])
(defn south [[x y]] [[(inc x) y] :s])
(defn east  [[x y]] [[x (inc y)] :e])
(defn west  [[x y]] [[x (dec y)] :w])

(defn character? [str]
  (and (not= str "-")
       (not= str "+")
       (not= str "|")
       (not= str " ")))

(defn blank? [str]
  (or (= nil? str)
      (= " " str)))

(defn next-position [board [x y :as current] direction]
  (let [value (get-in board current)]
    (if (= value "+")
      (cond
        (some #{direction} [:n :s])
        (if (= "-" (get-in board (first (east current))))
          (east current)
          (if (= "-" (get-in board (first (west current))))
            (west current)))

        (some #{direction} [:e :w])
        (if (= "|" (get-in board (first (north current))))
          (north current)
          (if (= "|" (get-in board (first (south current))))
            (south current))))

      (case direction
        :n (north current)
        :s (south current)
        :e (east current)
        :w (west current)))))

(defn move [{current :current direction :direction letters :letters board :board} _]
  (let [value (get-in board current)
        letters (if (character? value) (conj letters value) letters)
        [current direction] (next-position board current direction)]

    (if (or (nil? current) (blank? (get-in board current)))
      (reduced letters)
      {:current current
       :direction direction
       :letters letters
       :board board
       :value value})))

(defn run [input]
  (let [board (-> input pad-input)
        initial {:board board
                 :current [0 (.indexOf (first board) "|")]
                 :direction :s
                 :letters []}]
    (s/join (reduce move initial (range)))))
