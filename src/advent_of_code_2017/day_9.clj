(ns advent-of-code-2017.day-9
  (:require [instaparse.core :as insta]
            [clojure.java.io :as io]))

(def stream (insta/parser (io/resource "day_9/stream.bnf")))
(def stream-count (insta/parser (io/resource "day_9/stream_count.bnf")))

(defn score [structure level]
  (reduce
   (fn [sum item]
     (cond
       (= item :group) (+ sum level)
       (vector? item) (+ sum (score item (inc level)))
       :default sum))
   0
   structure))

(defn run [input]
  (let [structure (vec (stream input))]
    (score structure 0)))

(defn run-2 [input]
  (->> (vec (stream-count input))
       flatten
       (filter #{:anything})
       count))
