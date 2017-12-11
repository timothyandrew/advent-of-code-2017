(ns advent-of-code-2017.day-11-test
  (:require [advent-of-code-2017.day-11 :as sut]
            [clojure.string :as s])
  (:use [clojure.test]))

(defonce long-input
  (with-open [rdr (clojure.java.io/reader "resources/day_11/long.txt")]
    (doall (line-seq rdr))))

(deftest part-1
  (is (= (sut/run "ne,ne,ne") 3.0))
  (is (= (sut/run "ne,ne,sw,sw") 0.0))
  (is (= (sut/run "ne,ne,s,s") 2.0))
  (is (= (sut/run "se,sw,se,sw,sw") 3.0))
  (is (= (sut/run "nw,nw") 2.0))
  (is (= (sut/run "nw,sw,nw,sw") 4.0))
  (is (= (sut/run "sw,sw,n,n,s,s,sw,ne,n,n") 2.0))
  (is (= (sut/run "sw,sw,s") 3.0))
  (is (= (sut/run (first long-input)) 643.0)))


(deftest part-2
  (is (= (sut/run-2 (first long-input)) 1471.0)))
