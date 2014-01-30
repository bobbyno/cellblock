(ns cellblock.life)

(defn cell [s c]
  (let [n (count (filter #(= % 1) s))
        alive? (= c 1)]
    (cond (and alive? (< n 2)) 0
          (and alive? (= n 2)) 1
          (and alive? (= n 3)) 1
          (and (not alive?) (= n 3)) 1
          :else 0)))

(defn tick [board]
  (let [t [[0 -1] [0 1] [-1 0] [1 0] [-1 -1] [1 1] [-1 1] [1 -1]]
        r (range (count board))]
    (vec (for [x r]
           (vec (for [y r]
                  (let [index (map (fn [[a b]] [(+ x a) (+ y b)]) t)
                        n (map #(get-in board %) index)]
                    (cell n (get-in board [x y])))))))))

(defn next-gen [gen]
  (tick gen))
