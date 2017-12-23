(ns advent-of-code-2017.day-15-test
  (:require [advent-of-code-2017.day-15 :as sut]
            [clojure.string :as s])
  (:use [clojure.test]))

(defonce short-input
  [65 8921])

(defonce long-input
  [516 190])

(deftest part-1
  (is (= (sut/run short-input) 588))
  (is (= (sut/run long-input) 597)))


(deftest part-2
  (is (= (sut/run-2 short-input) 309))
  (is (= (sut/run-2 long-input) 303)))
