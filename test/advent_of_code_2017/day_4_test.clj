(ns advent-of-code-2017.day-4-test
  (:require [advent-of-code-2017.day-4 :as sut])
  (:use [clojure.test]))

(deftest part-1
  (is (= (sut/validate "aa bb cc dd ee") true))
  (is (= (sut/validate "aa bb cc dd aaa") true))
  (is (= (sut/validate "aa bb cc dd aa") false))
  (is (= (sut/run "resources/day_4_input.txt") 386)))

(deftest part-2
  (is (= (sut/run-2 "resources/day_4_input.txt") 208)))
