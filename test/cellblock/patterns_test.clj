(ns cellblock.patterns-test
  (:use [clojure.test]
        [cellblock.patterns]))

(deftest cells-to-matrix-test
  (is (= [[1 0 0 0 1 1 1]
          [1 1 1 0 0 1 0]
          [0 1 0 0 0 0 0]]

         (->> (read-pattern "rabbits")
              (cells->matrix)))))
