(ns advent-of-code-2017.day-14-test
  (:require [advent-of-code-2017.day-14 :as sut]
            [clojure.string :as s])
  (:use [clojure.test]))

(defonce short-input
  (with-open [rdr (clojure.java.io/reader "resources/day_14/short.txt")]
    (first (doall (line-seq rdr)))))

(defonce long-input
  (with-open [rdr (clojure.java.io/reader "resources/day_14/long.txt")]
    (first (doall (line-seq rdr)))))

(deftest part-1
  (is (= (sut/run short-input) 8108))
  (is (= (sut/run long-input) 8316)))

(deftest part-2
  (is (= (sut/run-2 short-input) 1242))
  (is (= (sut/run-2 long-input) 1074)))
