(ns advent-of-code-2017.day-7-test
  (:require [advent-of-code-2017.day-7 :as sut]
            [clojure.string :as s])
  (:use [clojure.test]))

(def part-1-test-input
  (with-open [rdr (clojure.java.io/reader "resources/day_7/part1test.txt")]
    (doall (line-seq rdr))))

(def part-1-actual-input
  (with-open [rdr (clojure.java.io/reader "resources/day_7/part1actual.txt")]
    (doall (line-seq rdr))))

(deftest part-1
  (is (= (sut/parse-line "pbga (66)") ["pbga" 66]))
  (is (= (sut/parse-line "fwft (72) -> ktlj, cntj, xhth") ["fwft" 72 ["ktlj" "cntj" "xhth"]]))
  (is (= (sut/run part-1-test-input) "tknk"))
  (is (= (sut/run part-1-actual-input) "ykpsek")))
