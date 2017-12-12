(ns advent-of-code-2017.day-12-test
  (:require [advent-of-code-2017.day-12 :as sut]
            [clojure.string :as s])
  (:use [clojure.test]))

(defonce short-input
  (with-open [rdr (clojure.java.io/reader "resources/day_12/short.txt")]
    (doall (line-seq rdr))))

(defonce long-input
  (with-open [rdr (clojure.java.io/reader "resources/day_12/long.txt")]
    (doall (line-seq rdr))))

(deftest part-1
  (is (= (sut/run short-input) 6))
  (is (= (sut/run long-input) 141)))

(deftest part-2
  (is (= (sut/run-2 short-input) 2))
  (is (= (sut/run-2 long-input) 171)))
