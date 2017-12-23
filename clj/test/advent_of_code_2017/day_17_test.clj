(ns advent-of-code-2017.day-17-test
  (:require [advent-of-code-2017.day-17 :as sut]
            [clojure.string :as s])
  (:use [clojure.test]))

#_(defonce long-input
  (with-open [rdr (clojure.java.io/reader "resources/day_17/long.txt")]
    (first (doall (line-seq rdr)))))

(deftest part-1
  (is (= (sut/run-1 3 2017 2017) 638))
  (is (= (sut/run-1 356 2017 2017) 808)))

(deftest part-2
  (is (= (sut/run-2 3 2017) 1226))
  (is (= (sut/run-2 356 50000000) 47465686)))
