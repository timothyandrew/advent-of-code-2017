(ns advent-of-code-2017.day-3-test
  (:require [advent-of-code-2017.day-3 :as sut])
  (:use [clojure.test]))

(deftest part-1
  (is (= (sut/run 1) 0))
  (is (= (sut/run 12) 3))
  (is (= (sut/run 23) 2))
  (is (= (sut/run 1024) 31))
  (is (= (sut/run 265149) 438)))

(deftest part-2
  (is (= (sut/run-part-2) 266330)))
