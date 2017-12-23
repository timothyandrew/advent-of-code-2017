(ns advent-of-code-2017.day-8
  (:require [clojure.string :as s]
            [clojure.set :as set]))

(defn cond-op-fn [s]
  (case s
    ">" >
    "<" <
    ">=" >=
    "==" =
    "<=" <=
    "!=" not=))

(defn parse-line [line]
  (let [[_ reg op value cond-reg cond-op cond-value]
        (re-matches #"(\w+)\s(\w+)\s(-?\d+)\sif\s(\w+)\s(\S+)\s(-?\d+)" line)]
    {:register reg
     :op (if (= op "inc") + -)
     :value (Integer/parseInt value)
     :cond {:register cond-reg :op (cond-op-fn cond-op) :value (Integer/parseInt cond-value)}}))

(defn check-condition [registers {register :register op :op value :value}]
  (op (registers register 0) value))

(defn apply-instruction [registers {register :register op :op value :value condition :cond}]
  (if (check-condition registers condition)
    (update registers register #(op (or % 0) value))
    registers))

(defn run-1 [input]
  (let [instructions (map parse-line input)]
    (apply max (vals (reduce apply-instruction {} instructions)))))

(defn run-2 [input]
  (let [instructions (map parse-line input)]
    (->> (reductions apply-instruction {} instructions)
         (map vals)
         flatten
         (remove nil?)
         (apply max))))
