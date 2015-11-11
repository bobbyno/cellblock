(ns cellblock.life
  (:require [cellblock.patterns :as p]))

(defn address-lookup-table
  "Builds a lookup table of the Moore neighborhood for each coordinate in the square matrix of dimension 'size'."
  [size]
  (let [pairs (for [x (range size) y (range size)] [x y])
        indexes [[0 -1] [0 1] [-1 0] [1 0] [-1 -1] [1 1] [-1 1] [1 -1]]]
    (reduce (fn [acc [x y]]
              (assoc acc [x y] (map (fn [[a b]] [(+ x a) (+ y b)]) indexes)))
         {} pairs)))

(defn cell [living-neighbors agent]
  (cond (< living-neighbors 2) 0
        (= living-neighbors 3) 1
        (and (= agent 1) (= living-neighbors 2)) 1
        :else 0))

(defn tick [board addresses points]
  (vec (for [x points]
         (vec (for [y points]
                (let [agent (get-in board [x y])
                      living-neighbors (reduce (fn [acc address] (+ acc (get-in board address 0)))
                                                 0 (addresses [x y]))]
                  (cell living-neighbors agent)))))))

;; This should take < 1s to hit a 20 fps goal
;; (time (count (take 20 (iterate #(tick % (address-lookup-table 100) (range 100)) (p/random-game 100)))))
