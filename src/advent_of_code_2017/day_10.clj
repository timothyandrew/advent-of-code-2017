(ns advent-of-code-2017.day-10)

(defn reverse-section
  "Reverse 'length' items of list, starting at current (index)"
  [list current length]
  (let [n (count list)
        list (concat list list)
        [left list] (split-at current list)
        [rev right] (split-at length list)
        list (concat left (reverse rev) right)]
    (if (> (+ current length) n)
      (take n (concat (take current (drop n list)) (reverse rev)))
      (take n list))))

(defn knot
  "Apply the given length to the list"
  [{list :list skip :skip current :current} length]
  {:list (reverse-section list current length)
   :current (rem (+ current length skip) (count list))
   :skip (inc skip)})

(defn knot-round [input list]
  (reduce knot list input))

(defn knot-hash [n rounds input]
  (let [list {:list (range n) :skip 0 :current 0}]
    (reduce (fn [list _] (knot-round input list))
            list
            (range rounds))))

(defn to-ascii [input]
  (concat (map int input) [17 31 73 47 23]))

(defn build-dense-hash [{list :list}]
  (map #(apply bit-xor %) (partition 16 list)))

(defn build-hex [dense]
  (apply str (map #(format "%02x" %) dense)))

(defn run [n input]
  (->> (knot-hash n 1 input)
       :list
       (take 2)
       (apply *)))

(defn run-2 [input]
  (->> input
       to-ascii
       (knot-hash 256 64)
       build-dense-hash
       build-hex))
