(ns advent-of-code-2017.day-16-test
  (:require [advent-of-code-2017.day-16 :as sut]
            [clojure.string :as s])
  (:use [clojure.test]))

(defonce long-input
  (with-open [rdr (clojure.java.io/reader "resources/day_16/long.txt")]
    (first (doall (line-seq rdr)))))

(deftest runtest
  (is (= (sut/run [\a \e] ["s1" "x3/4" "pe/b"]) "baedc"))
  (is (= (sut/run [\a \p] (s/split long-input #",")) "kgdchlfniambejop"))
  (is (= (sut/run-2 [\a \p] (s/split long-input #",")) "fjpmholcibdgeakn")))
