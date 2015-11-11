(ns cellblock.view
  (:require [quil.core :as qc]
            [cellblock.life :as life]
            [cellblock.patterns :as p]))

(def blue [0 34 190])
(def white [255 255 255])
(def size 100)

(defn draw-point [[x y] alive scale]
  (apply qc/fill (if (= alive 1) blue white))
  (qc/rect (* scale y) (* scale x) scale scale))

(defn draw-squares [grid size scale coords]
  (dorun (map #(draw-point % (get-in grid %) scale) coords)))

(defn setup []
  (qc/background 255)
  (qc/frame-rate 100))

(defn rate [started]
  (/ (qc/frame-count)
     (/ (- (System/currentTimeMillis) started) 1000.0)))

(def gen (atom nil))

(defn reload! [name]
  (reset! gen (p/pattern-game size name)))

(defn random! []
  (reset! gen (p/random-game size)))

(defn run-sim [game]
  (reset! gen game)
  (let [scale 10
        started (System/currentTimeMillis)
        points (range size)
        addresses (life/address-lookup-table size)
        coords (for [x points y points] [x y])]
    (qc/defsketch conway
      :title "Game of Life"
      :setup setup
      :size [(* scale size) (* scale size)]
      :draw (fn drawfn []
              (draw-squares @gen size scale coords)
              ;; framerate will print to *nrepl-server* buffer
              (prn (str (qc/frame-count) ": " (rate started) " fps"))
              (swap! gen life/tick addresses points)))))

(defn -main [& args]
  (run-sim (first args)))

;; switch to this namespace, then live-code away...

;; let's start with a still-life
(-main (p/pattern-game size "pond"))

;; let's add some animation...
;;
(reload! "glider")

;; that runs forever...
;;(reload! "gosperglidergun")

;; let's see some emergent complexity from simple initial conditions...
;; R-pentomino - 1103 generations with a population of 116.
;;
(reload! "rpentomino")

;; what could 9 little cells do?
;;(reload! "rabbits")

;; what if we simplify the initial conditions slightly?
;; neutering helps control animal populations...
;; (reload! "neutered")

;; random initial conditions
;; (random!)

;; and now, the delighter...
