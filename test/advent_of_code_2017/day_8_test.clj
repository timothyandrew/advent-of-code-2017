(ns advent-of-code-2017.day-8-test
  (:require [advent-of-code-2017.day-8 :as sut]
            [clojure.string :as s])
  (:use [clojure.test]))

(defonce short-input
  (with-open [rdr (clojure.java.io/reader "resources/day_8/short.txt")]
    (doall (line-seq rdr))))

(defonce long-input
  (with-open [rdr (clojure.java.io/reader "resources/day_8/long.txt")]
    (doall (line-seq rdr))))

(deftest parse-line
  (is (= (sut/parse-line "b inc 5 if a > 1")
         {:register "b" :op + :value 5 :cond {:register "a" :op > :value 1}}))
  (is (= (sut/parse-line "c dec -20 if c == 10")
         {:register "c" :op - :value -20 :cond {:register "c" :op = :value 10}})))

(deftest part-1
  (is (= (sut/run-1 short-input) 1))
  (is (= (sut/run-1 long-input) 4647)))

(deftest part-2
  (is (= (sut/run-2 short-input) 10))
  (is (= (sut/run-2 long-input) 5590)))
