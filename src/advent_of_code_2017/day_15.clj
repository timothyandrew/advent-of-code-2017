(ns advent-of-code-2017.day-15)

(defn match? [left right]
  (= 0 (bit-and 0xffff (bit-xor left right))))

(defn next-value [prev factor]
  (rem (* prev factor) 2147483647))

(defn next-value-2 [prev factor multiple]
  (reduce
   (fn [prev _]
     (let [result (rem (* prev factor) 2147483647)]
       (if (= 0 (rem result multiple))
         (reduced result)
         result)))
   prev
   (range)))

(defn judge [[a b] n]
  (reduce
   (fn [[left right matches] _]
     (let [left (next-value left 16807)
           right (next-value right 48271)
           matches (if (match? left right) (inc matches) matches)]
       [left right matches]))
   [a b 0]
   (range n)))

(defn judge-2 [[a b] n]
  (reduce
   (fn [[left right matches] i]
     (let [left (next-value-2 left 16807 4)
           right (next-value-2 right 48271 8)
           matches (if (match? left right) (inc matches) matches)]
       [left right matches]))
   [a b 0]
   (range n)))

(defn run [input]
  (last (judge input 40000000)))

(defn run-2 [input]
  (last (judge-2 input 5000000)))
