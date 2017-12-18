(ns advent-of-code-2017.day-18)

(defn blank [instructions]
  {:registers {}
   :sound nil
   :stack instructions
   :current 0})

(defn get
  "If register, get value from register, otherwise return value."
  [state maybe-register]
  (if (integer? maybe-register)
    maybe-register
    (or (get-in state [:registers maybe-register]) 0)))

(defn forward [state]
  (update state :current inc))

(defn apply-instruction [state _]
  (let [[instruction x y] (get-in state [:stack (:current state)])]

    (case instruction
      "snd" (forward (assoc state :sound (get state x)))
      "set" (forward (assoc-in state [:registers x] (get state y)))
      "add" (forward (update-in state [:registers x] #(+ (or % 0) (get state y))))
      "mul" (forward (update-in state [:registers x] #(* (or % 0) (get state y))))
      "mod" (forward (update-in state [:registers x] #(rem (or % 0) (get state y))))
      "jgz" (if (> (get state x) 0)
              (update state :current #(+ % (get state y)))
              (forward state))
      "rcv" (if (= 0 (get state x)) (forward state) (reduced state)))
    ))

(defn parse-line [line]
  (let [[_ instruction x y] (re-matches #"(\w+)\s([\-\w]+)\s?([\-\w]+)?" line)
        x (if (re-matches #"-?\d+" x) (Integer/parseInt x) x)
        y (if (and y (re-matches #"-?\d+" y)) (Integer/parseInt y) y)]
    [instruction x y]))

(defn run-1 [lines]
  (let [instructions (mapv parse-line lines)]
    (:sound (reduce apply-instruction (blank instructions) (range)))))
