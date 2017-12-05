(ns advent-of-code-2017.day-5-test
  (:require [advent-of-code-2017.day-5 :as sut]
            [clojure.string :as s])
  (:use [clojure.test]))

(def input
  (with-open [rdr (clojure.java.io/reader "resources/day_5_input.txt")]
    (mapv #(Integer/parseInt %) (line-seq rdr))))

(deftest part-1
  (is (= (sut/run [0 3 0 1 -3]) 5))
  (is (= (sut/run input) 315613)))

(deftest part-2
  (is (= (sut/run-2 [0 3 0 1 -3]) 10))
  (is (= (sut/run-2 input) 22570529)))
