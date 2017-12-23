(ns advent-of-code-2017.day-10-test
  (:require [advent-of-code-2017.day-10 :as sut]
            [clojure.string :as s])
  (:use [clojure.test]))

(defonce short-input [3 4 1 5])
(defonce long-input [183,0,31,146,254,240,223,150,2,206,161,1,255,232,199,88])
(defonce long-input-str "183,0,31,146,254,240,223,150,2,206,161,1,255,232,199,88")

(deftest reverse-section
  (is (= (sut/reverse-section [1 2 3 4] 0 2) [2 1 3 4]))
  (is (= (sut/reverse-section [1 2 3 4 5 6] 2 3) [1 2 5 4 3 6]))
  (is (= (sut/reverse-section [1 2 3 4 5 6] 4 4) [6 5 3 4 2 1]))
  (is (= (sut/reverse-section [2 1 0 3 4] 3 4) [4 3 0 1 2])))

(deftest to-ascii
  (is (= (sut/to-ascii "1,2,3") [49,44,50,44,51,17,31,73,47,23])))

(deftest part-1
  (is (= (sut/run 5 short-input) 12))
  (is (= (sut/run 256 long-input) 15990)))

(deftest part-2
  (is (= (sut/run-2 "") "a2582a3a0e66e6e86e3812dcb672a272"))
  (is (= (sut/run-2 "AoC 2017") "33efeb34ea91902bb2f59c9920caa6cd"))
  (is (= (sut/run-2 "1,2,3") "3efbe78a8d82f29979031a4aa0b16a9d"))
  (is (= (sut/run-2 "1,2,4") "63960835bcdc130f0b66d7ff4f6a5a8e"))
  (is (= (sut/run-2 long-input-str) "90adb097dd55dea8305c900372258ac6")))
