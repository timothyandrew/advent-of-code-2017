(ns advent-of-code-2017.day-18-test
  (:require [advent-of-code-2017.day-18 :as sut]
            [clojure.string :as s])
  (:use [clojure.test]))

(def short-input
  (with-open [rdr (clojure.java.io/reader "resources/day_18/short.txt")]
    (doall (line-seq rdr))))

(def long-input
  (with-open [rdr (clojure.java.io/reader "resources/day_18/long.txt")]
    (doall (line-seq rdr))))

(deftest part-2
  (is (= (sut/run-1 long-input) 7493)))
