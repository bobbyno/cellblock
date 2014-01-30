(ns cellblock.patterns)

(defn random-game [n]
  (vec (for [y (range n)]
         (vec (for [x (range n)]
                (rand-int 2))))))

(defn padded [n pattern]
  (let [width (count (first pattern))
        diff (- n width)
        lhs (/ (if (odd? diff) (dec diff) diff) 2)
        rhs (- diff lhs)]
    (vec
     (map #(vec (concat (vec (repeat lhs 0)) % (vec (repeat rhs 0)))) pattern))))

(defn pad-matrix [n matrix]
  (let [g (padded n matrix)
        diff (- n (count g))
        n-above (/ (if (odd? diff) (dec diff) diff) 2)
        n-below (- diff n-above)
        row (vec (repeat n 0))]
    (vec (concat
          (vec (repeat n-above row))
          g
          (vec (repeat n-below row))))))

(defn read-pattern [name]
  (filter #(not= \! (first %))
   (clojure.string/split
    (slurp (str "./data/" name ".cells")) #"\r\n|\n")))

(defn cells->matrix
  "convert cells format
    O...OOO
    OOO..O
    .O
   to the matrix format used in cellblock."
  [cells]
  (let [sqn (map #(replace {"." 0 "O" 1} (re-seq #"." %)) cells)
        length (apply max (map count sqn))]
    (mapv #(vec (concat % (repeat (- length (count %)) 0))) sqn)))

(defn pattern-game [size name]
  (->> (read-pattern name)
       (cells->matrix)
       (pad-matrix size)))
