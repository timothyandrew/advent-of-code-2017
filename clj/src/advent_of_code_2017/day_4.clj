(ns advent-of-code-2017.day-4
  (:require [clojure.string :as s]))

(defn inc-nil [n]
  (if (nil? n) 1 (inc n)))

(defn canonical [word]
  (-> word seq sort s/join))

(defn validate [passphrase]
  (let [coll (s/split passphrase #" ")
        counts (reduce (fn [counter word] (update counter word inc-nil)) {} coll)]
    (every? #(= % 1) (vals counts))))

(defn validate-2
  "Invalid if any words are anagrams of other words"
  [passphrase]
  (let [coll (s/split passphrase #" ")
        counts (reduce (fn [counter word] (update counter (canonical word) inc-nil)) {} coll)]
    (every? #(= % 1) (vals counts))))

(defn run [input-file]
  (with-open [rdr (clojure.java.io/reader input-file)]
    (count (filter true? (mapv validate (line-seq rdr))))))

(defn run-2 [input-file]
  (with-open [rdr (clojure.java.io/reader input-file)]
    (count (filter true? (mapv validate-2 (line-seq rdr))))))
