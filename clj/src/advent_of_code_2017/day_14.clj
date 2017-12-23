(ns advent-of-code-2017.day-14
  (:require [advent-of-code-2017.day-10 :as kh]
            [clojure.string :as s]))

(defonce cache (atom nil))

(defn ->binary-str [hex]
  (format "%04d" (Integer/parseInt (Integer/toBinaryString hex))))

(defn gen-uuid [] (str (java.util.UUID/randomUUID)))

(defn str->row [s]
  (->> s
       kh/str->kh
       seq
       (map #(str "0x" %))
       (map read-string)
       (map ->binary-str)
       s/join))

(defn build-grid [input]
  (pmap
   #(str->row (str input "-" %))
   (range 128)))

(defn annotate-cell [row-idx col-idx cell]
  {:cell cell :row row-idx :col col-idx})

(defn annotate-grid [input]
  (vec (map-indexed
        (fn [i n]
          (->> (str input "-" n)
               str->row
               seq
               (map-indexed (partial annotate-cell i))
               vec))
        (range 128))))

(defn count-used [grid]
  (apply + (map
            (fn [row]
              (count (filter #(= % \1) (seq row))))
            grid)))

(defn add-cell-to-region [cell region]
  (swap! cache update-in [(:row cell) (:col cell)] #(assoc % :region region)))

(defn get-cell [row col]
  (when (and (>= row 0) (>= col 0))
    (get-in @cache [row col])))

(defn merge-regions [l r]
  (doseq [cell (filter #(= (:region %) r) (flatten @cache))]
    (add-cell-to-region cell l)))

(defn adjacent-cells [cell]
  (remove (fn [cell]
            (or (nil? cell)
                (= \0 (:cell cell))))
          [(get-cell (dec (:row cell)) (:col cell))
                (get-cell (inc (:row cell)) (:col cell))
                (get-cell (:row cell) (dec (:col cell)))
                (get-cell (:row cell) (inc (:col cell)))]))

(defn build-regions [grid]
  (reset! cache grid)

  (doseq [row grid
          cell row]
    (when (and (nil? (:region cell))
               (= \1 (:cell cell)))
      (let [region (gen-uuid)
            adj (adjacent-cells cell)]
        (add-cell-to-region cell region)
        (doseq [adj-cell adj]
          (if-let [adj-region (:region adj-cell)]
            (merge-regions region adj-region)
            (add-cell-to-region adj-cell region))))))

  @cache)

(defn count-regions [grid]
  (count (distinct (remove nil? (map :region (flatten grid))))))

(defn run [input]
  (-> input build-grid count-used))

(defn run-2 [input]
  (-> input annotate-grid build-regions count-regions))
