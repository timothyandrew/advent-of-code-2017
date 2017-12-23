(ns advent-of-code-2017.day-19-test
  (:require [advent-of-code-2017.day-19 :as sut]
            [clojure.string :as s])
  (:use [clojure.test]))

(def short-input
  (with-open [rdr (clojure.java.io/reader "resources/day_19/short.txt")]
    (doall (line-seq rdr))))

(def long-input
  (with-open [rdr (clojure.java.io/reader "resources/day_19/long.txt")]
    (doall (line-seq rdr))))

(deftest part-1
  (is (= (sut/run long-input) "AYRPVMEGQ")))
