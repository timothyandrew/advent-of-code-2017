(ns advent-of-code-2017.day-17-test
  (:require [advent-of-code-2017.day-17 :as sut]
            [clojure.string :as s])
  (:use [clojure.test]))

#_(defonce long-input
  (with-open [rdr (clojure.java.io/reader "resources/day_17/long.txt")]
    (first (doall (line-seq rdr)))))

(deftest runtest
  (is (= (sut/run-1 3 2017 2017) 638))
  (is (= (sut/run-1 356 2017 2017) 808)))
