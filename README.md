# DominoGame

This is a simple domino game I created to practice OOP.

## How to Play

1. Each player receives 7 dominoes on opening hand
2. The one with the double `[6|6]` domino starts the game
   1. If no one has it, then the one with `[5|5]` starts. Otherwise, `[4|4]` and onward...
   2. If no one has doubles on the opening hand, then the game starts with the top domino of the deck
3. Each player takes turns by playing dominoes whose numbers matches the leftmost or the rightmost number on the table
   1. User player can choose a domino on by entering its `id` on hand
   2. If a player does not have playable dominoes on its hand, It must draw a new domino (and play it if playable)
      1. If the deck is empty, then it just passes its turn
4. The game finishes when:
   1. A player has no dominoes on hand. In this case, it WINS
   2. If the deck is empty and both players have passed their turns. In this case, the player with less dominoes on hand WINS
      1. Finally, if they have the same amount of dominoes, then the game is a DRAW

## Enjoy! :)

