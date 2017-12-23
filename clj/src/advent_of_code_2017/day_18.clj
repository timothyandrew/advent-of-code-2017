(ns advent-of-code-2017.day-18)

(defn blank [instructions]
  [{:registers {"p" 0}
    :queue []
    :stack instructions
    :sends 0
    :blocked false
    :current 0}
   {:registers {"p" 1}
    :queue []
    :stack instructions
    :sends 0
    :blocked false
    :current 0}])

(defn other-pid [pid]
  (if (= pid 0) 1 0))

(defn get
  "If register, get value from register, otherwise return value."
  [state pid maybe-register]
  (if (integer? maybe-register)
    maybe-register
    (or (get-in state [pid :registers maybe-register]) 0)))

(defn forward [pid state]
  (update-in state [pid :current] inc))

(defn rcv [state pid [instruction x y]]
  (let [message (first (get-in state [pid :queue]))]
    (cond
      message (forward pid (-> state
                               (assoc-in [pid :registers x] message)
                               (assoc-in [pid :blocked] false)
                               (update-in [pid :queue] #(vec (drop 1 %)))))
      (every? true? (map :blocked state)) (reduced state)
      :default (assoc-in state [pid :blocked] true))))

(defn snd [state pid [instruction x]]
  (forward pid (-> state
                   (update-in [(other-pid pid) :queue] conj (get state pid x))
                   (update-in [pid :sends] inc))))

(defn apply-instruction [state pid]
  (let [ip (get-in state [pid :current])
        [instruction x y] (get-in state [pid :stack ip])]

    (case instruction
      "snd" (snd state pid [instruction x])
      "set" (forward pid (assoc-in state [pid :registers x] (get state pid y)))
      "add" (forward pid (update-in state [pid :registers x] #(+ (or % 0) (get state pid y))))
      "mul" (forward pid (update-in state [pid :registers x] #(* (or % 0) (get state pid y))))
      "mod" (forward pid (update-in state [pid :registers x] #(rem (or % 0) (get state pid y))))
      "jgz" (if (> (get state pid x) 0)
              (update-in state [pid :current] #(+ % (get state pid y)))
              (forward pid state))
      "rcv" (rcv state pid [instruction x y]))
    ))

(defn parse-line [line]
  (let [[_ instruction x y] (re-matches #"(\w+)\s([\-\w]+)\s?([\-\w]+)?" line)
        x (if (re-matches #"-?\d+" x) (Integer/parseInt x) x)
        y (if (and y (re-matches #"-?\d+" y)) (Integer/parseInt y) y)]
    [instruction x y]))

(defn run-1 [lines]
  (let [instructions (mapv parse-line lines)]
    (get-in
     (reduce apply-instruction (blank instructions)  (cycle [0 1]))
     [1 :sends])))
