(ns advent-of-code-2017.day-13
  (:require [clojure.string :as s]))

(def cache (atom {}))

(defn packet-index [firewall]
  (:range (first (filter #(:packet %) firewall))))

(defn calc-severity [firewall]
  (apply + (map #(* (:depth %) (:range %)) (filter #(% :caught) firewall))))

(defn move-scanner [depth scanner]
  (when-let [[direction index] scanner]
    (case direction
      :down (if (= index (dec depth)) [:up (dec index)] [:down (inc index)])
      :up (if (= index 0) [:down (inc index)] [:up (dec index)]))))

(defn move-packet-forward [firewall]
  (if-let [index (packet-index firewall)]
    (-> firewall
        (update index #(assoc % :packet false))
        (update (inc index) #(assoc % :packet true)))
    (update firewall 0 #(assoc % :packet true))))

(defn check-if-caught [firewall]
  (let [index (packet-index firewall)]
    (update firewall index #(if (= 0 (last (:scanner %)))
                              (assoc % :caught true)
                              %))))

(defn move-scanners [firewall]
  (map
   (fn [layer]
     (update layer :scanner (partial move-scanner (:depth layer))))
   firewall))

(defn tick
  [firewall wait]
  (if wait
    (-> firewall move-scanners vec)
    (-> firewall move-packet-forward check-if-caught move-scanners vec)))

(defn build-layer [lines index]
  (if-let [[range depth] (first (filter #(= (first %) index) lines))]
    {:depth depth :range range :scanner [:down 0] :packet false :caught false}
    {:depth 0 :range index :scanner nil :packet false :caught false}))

(defn build-firewall [lines]
  (let [layer-count (inc (first (apply max-key first lines)))]
    (-> (map (partial build-layer lines) (range layer-count))
        vec)))

(defn parse-line [line]
  (->> (re-matches #"(\d+)\:\s(\d+)" line)
       next
       (map #(Integer/parseInt %))))

(defn firewall-run [input wait]
  (let [firewall (or (get @cache input)
                     (build-firewall (map parse-line input)))
        wait     (if (get @cache input)
                   1
                   wait)
        waiting (reduce (fn [firewall index] (tick firewall true))
                        firewall
                        (range wait))]

    (swap! cache assoc input waiting)

    (reduce (fn [firewall index] (tick firewall false))
            waiting
            (range (inc (first (apply max-key first (map parse-line input))))))))

(defn run [input]
  (calc-severity (firewall-run input 0)))

(defn run-2 [input]
  (first (filter
          (fn [n]
            (every? false? (pmap :caught (firewall-run input n))))
          (iterate inc 0))))
