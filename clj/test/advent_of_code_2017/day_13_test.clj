(ns advent-of-code-2017.day-13-test
  (:require [advent-of-code-2017.day-13 :as sut]
            [clojure.string :as s])
  (:use [clojure.test]))

(defonce short-input
  (with-open [rdr (clojure.java.io/reader "resources/day_13/short.txt")]
    (doall (line-seq rdr))))

(defonce long-input
  (with-open [rdr (clojure.java.io/reader "resources/day_13/long.txt")]
    (doall (line-seq rdr))))

(deftest part-1
  (is (= (sut/run short-input) 24))
  (is (= (sut/run long-input) 2384)))


(deftest part-2
  (is (= (sut/run-2 short-input) 10))
  (is (= (sut/run-2 long-input) 3921270)))
