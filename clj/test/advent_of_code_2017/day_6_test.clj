(ns advent-of-code-2017.day-6-test
  (:require [advent-of-code-2017.day-6 :as sut]
            [clojure.string :as s])
  (:use [clojure.test]))

(defonce input [14 0 15 12 11 11 3 5 1 6 8 4 9 1 8 4])

(deftest part-1
  (is (= (sut/run [0 2 7 0]) 5))
  (is (= (sut/run input) 11137)))

(deftest part-2
  (is (= (sut/run-2 [0 2 7 0]) 4))
  (is (= (sut/run-2 input) 1037)))
