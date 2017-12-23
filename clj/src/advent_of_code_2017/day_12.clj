(ns advent-of-code-2017.day-12
  (:require [clojure.string :as s]
            [clojure.set :as set]
            [clojure.java.io :as io]
            [instaparse.core :as insta]))

(def adj-list (insta/parser (io/resource "day_12/adj.bnf")))

(defn parse-line [line]
  (let [[source & targets] (map #(Integer/parseInt (second %)) (adj-list line))]
    [source targets]))

(defn build-lookup [lookup [source targets]]
  (assoc lookup source targets))

(defn build-group [start group lookup]
  (when-not (contains? group start)
    (let [group (conj group start)]
      (set/union group (set (mapcat #(build-group % group lookup) (lookup start)))))))

(defn run [input]
  (->> input
       (map parse-line)
       (reduce build-lookup {})
       (build-group 0 #{})
       count))

(defn run-2 [input]
  (let [lookup (->> input
                    (map parse-line)
                    (reduce build-lookup {}))]
    (count (set (map #(build-group % #{} lookup) (keys lookup))))))
