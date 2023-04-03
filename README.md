#  ***UnoSim*** - UBC CPSC 210 term project

## What is this?
This project will be a simulator for the card game Uno.
Users will be able to start a pass-and-play game with any number of players and any number of starting cards,
then play using an infinite randomized deck.

## Why?
I love playing Uno, but sometimes I don't have a deck to use or a place to put cards!
Plenty of Uno simulators already exist to solve this problem, but they are all web-based and don't work offline.
Hopefully, this project can be used as a substitute for a playing space when on a car, plane, or bus.
I also want to gain experience designing programs that run games.

## User stories
- As a user, I want to start a game with a given number of players.
- As a user, I want to add multiple starting cards to each player's hand.
- As a user, I want to be able to view the cards in my hand.
- As a user, I want to play a valid card onto the discard pile.
- As a user, I want to add a card to my hand if I don't have any valid cards to play.
- As a user, I want the game to end when a player runs out of cards.
- As a user, I want to have the option to save the current game at the beginning of my turn.
- As a user, I want to have the option to load an existing game instead of starting a new one.

## Stretch goals (beyond minimum product)
- Power cards: +2, +4, Wild, Skip, Reverse
- Sorted cards: automatically sort the cards in the player's hand as they draw them
- Support 10 to 1: start with ten cards each and play games down to one card each
- Choose infinite draw or single draw
- Optional +2 and +4 stacking rule

## Instructions for grader
- My visual component is the Uno logo on the first screen, as well as the cards in the main game.
- You can generate the first action by pressing "Start Game", then choosing any number of players
and any number of cards to add to their hands.
- You can generate the second action by clicking on the deck to draw a card, or clicking on a valid card to play it.
- You can save the state of the application using the menu in the top left of the game screen.
- You can load the state of the application using the menu or the "Load Game" button on the first screen.

## Phase 4: Task 2
Mon Apr 03 15:22:37 PDT 2023 \
Starting new game with 3 players and 2 starting cards... \
Mon Apr 03 15:22:37 PDT 2023 \
Player 1 drew a Red Reverse \
Mon Apr 03 15:22:37 PDT 2023 \
Player 1 drew a Red 8 \
Mon Apr 03 15:22:37 PDT 2023 \
Player 2 drew a Yellow 5 \
Mon Apr 03 15:22:37 PDT 2023 \
Player 2 drew a Blue 7 \
Mon Apr 03 15:22:37 PDT 2023 \
Player 3 drew a Blue 2 \
Mon Apr 03 15:22:37 PDT 2023 \
Player 3 drew a Green 2 \
Mon Apr 03 15:22:37 PDT 2023 \
Starting game! \
\
Mon Apr 03 15:22:39 PDT 2023 \
Player 1 played a Red 8 \
Mon Apr 03 15:22:40 PDT 2023 \
Player 2 drew a Green 6 \
Mon Apr 03 15:22:41 PDT 2023 \
Player 3 drew a +4 \
Mon Apr 03 15:22:42 PDT 2023 \
Player 1 played a Red Reverse \
Mon Apr 03 15:22:42 PDT 2023 \
Player 1 wins!