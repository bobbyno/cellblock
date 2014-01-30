(ns cellblock.life-test
  (:use [clojure.test]
        [cellblock.life]))

(deftest about-cell-life
  (testing "Any live cell with fewer than two live neighbours dies, as if caused by under-population."
    (is (= 0 (cell [nil nil 0 0, 0 0 0 1] 1))))

  (testing "Any live cell with two or three live neighbours lives on to the next generation."
    (is (= 1 (cell [0 0 0 0 0 0 1 1] 1)))
    (is (= 1 (cell [0 0 0 0 0 1 1 1] 1))))

  (testing "Any live cell with more than three live neighbours dies, as if by overcrowding."
      (is (= 0 (cell [0 0 0 0 1 1 1 1] 1))))

  (testing "Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction."
      (is (= 1 (cell [0 0 0 0 0 1 1 1] 0)))))


(deftest computing-the-next-generation
  (is (=
       (next-gen [[0 0 0 0 0 0]
                  [0 1 1 0 0 0]
                  [0 1 1 0 0 0]
                  [0 0 0 1 1 0]
                  [0 0 0 1 1 0]
                  [0 0 0 0 0 0]])
       [[0 0 0 0 0 0]
        [0 1 1 0 0 0]
        [0 1 0 0 0 0]
        [0 0 0 0 1 0]
        [0 0 0 1 1 0]
        [0 0 0 0 0 0]]))

  ;; a blinking bar
  (is (=
       (next-gen [[0 0 0 0 0]
                  [0 0 0 0 0]
                  [0 1 1 1 0]
                  [0 0 0 0 0]
                  [0 0 0 0 0]])
       [[0 0 0 0 0]
        [0 0 1 0 0]
        [0 0 1 0 0]
        [0 0 1 0 0]
        [0 0 0 0 0]]))

  (is (=
       (next-gen [[0 0 0 0 0 0]
                  [0 0 0 0 0 0]
                  [0 0 1 1 1 0]
                  [0 1 1 1 0 0]
                  [0 0 0 0 0 0]
                  [0 0 0 0 0 0]])
       [[0 0 0 0 0 0]
        [0 0 0 1 0 0]
        [0 1 0 0 1 0]
        [0 1 0 0 1 0]
        [0 0 1 0 0 0]
        [0 0 0 0 0 0]])))

