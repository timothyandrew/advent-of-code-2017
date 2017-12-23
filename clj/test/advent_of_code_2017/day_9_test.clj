(ns advent-of-code-2017.day-9-test
  (:require [advent-of-code-2017.day-9 :as sut]
            [clojure.string :as s])
  (:use [clojure.test]))

(defonce long-input
  (with-open [rdr (clojure.java.io/reader "resources/day_9/long.txt")]
    (doall (line-seq rdr))))

(deftest part-1
  (is (= (sut/run "{}") 1))
  (is (= (sut/run "{{{}}}") 6))
  (is (= (sut/run "{{},{}}") 5))
  (is (= (sut/run "{{{},{},{{}}}}") 16))
  (is (= (sut/run "{<a>,<a>,<a>,<a>}") 1))
  (is (= (sut/run "{{<ab>},{<ab>},{<ab>},{<ab>}}") 9))
  (is (= (sut/run "{{<!!>},{<!!>},{<!!>},{<!!>}}") 9))
  (is (= (sut/run "{{<a!>},{<a!>},{<a!>},{<ab>}}") 3))
  (is (= (sut/run (first long-input)) 16869)))

(deftest part-2
  (is (= (sut/run-2 "{<>}") 0))
  (is (= (sut/run-2 "{<random characters>}") 17))
  (is (= (sut/run-2 "{<<<<>}") 3))
  (is (= (sut/run-2 "{<{!>}>}") 2))
  (is (= (sut/run-2 "{<!!>}") 0))
  (is (= (sut/run-2 "{<!!!>>}") 0))
  (is (= (sut/run-2 "{<{o\"i!a,<{i<a>}") 10))
  (is (= (sut/run-2 (first long-input)) 7284)))
