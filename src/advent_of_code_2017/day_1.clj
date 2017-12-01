(ns advent-of-code-2017.day-1
  (:require [clojure.string :as s]))

(defn split-numstring-into-digits [numstring]
  (map #(Integer/parseInt %) (s/split numstring #"")))

(defn find-sum [digits sum]
  (if (next digits)
    (if (= (first digits) (second digits))
      (find-sum (next digits) (+ sum (first digits)))
      (find-sum (next digits) sum))
    sum))

(defn find-part-two-sum [pairs]
  (reduce
   (fn [sum [left right]]
     (if (= left right)
       (+ sum left)
       sum))
   0
   pairs))

(defn copy-first-digit-to-end [digits]
  (conj (vec digits) (first digits)))

(defn group-halfway-around-digits [digits]
  (let [forward (apply map vector(partition (/ (count digits) 2) digits))]
    (concat forward (map (comp vec reverse) forward))))

(defn run [numstring]
  (-> numstring
      split-numstring-into-digits
      copy-first-digit-to-end
      (find-sum 0)))

(defn run-part-two [numstring]
  (-> numstring
      split-numstring-into-digits
      group-halfway-around-digits
      find-part-two-sum))
