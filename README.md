# Cellblock

Cellblock is an implementation of [Conway's Game of Life](http://en.wikipedia.org/wiki/Conway%27s_Game_of_Life). It illustrates the emergence of complex patterns from interactions among cells following a simple set of rules:

1. Any live cell with fewer than two live neighbors dies, as if caused by under-population.
1. Any live cell with two or three live neighbors lives on to the next generation.
1. Any live cell with more than three live neighbors dies, as if by overcrowding.
1. Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.


## Getting Started

Cellblock was built to be used interactively in talks while live-coding in Emacs.

A typical session might look like this:

Open `cellblock.view`, the entry point to the application.

`C-c M-j: cider-jack-in`

`C-c M-n: cider-repl-set-ns`

`C-c C-k: cider-load-buffer`

The Game will start in a paused state so you can see the intial configuration.

Double-click to start.

Click again to stop and start.

When stopped, press any key to advance the simulation one frame.

Patterns are either random or are defined in one of the files in the `data` directory.

From the `cellblock.view` namespace, you can `reload!` any of the patterns:

`(reload! "rabbits")`

If you `reload!` while stopped, you can press a key to redraw the screen and see the
new pattern. If you're already running, the new pattern will replace the old one automatically.


## Why Game of Life?

> One might have thought – as at first I certainly did – that if the rules for a program were simple then this would mean that its behavior must also be correspondingly simple. For our everyday experience in building things tends to give us the intuition that creating complexity is somehow difficult, and requires rules or plans that are themselves complex. But the pivotal discovery that I made some eighteen years ago is that in the world of programs such intuition is not even close to correct.
...
> For all it takes is that systems in nature operate like typical programs and then it follows that their behavior will often be complex. And the reason that such complexity is not usually seen in human artifacts is just that in building these we tend in effect to use programs that are specially chosen to give only behavior simple enough for us to be able to see that it will achieve the purposes we want.

> Stephen Wolfram, A New Kind of Science


## Why Cellblock?

Cellblock is a simple program that let's us easily experiment with cellular automata in Clojure. The logic to handle the frame iteration is under 30 lines of code. Each game state is represented as a square matrix. 0 indicates the cell is dead, 1 indicates it is alive. `quil` handles the rendering. The patterns come from [LifeWiki](http://www.conwaylife.com/patterns/all.zip), except for PDX, which is original work.
