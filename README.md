## What is this?
This project is a simulator for the card game Uno.
Users can start a pass-and-play game with any number of players and any number of starting cards,
then play using an infinite randomized deck.

## Why?
I love playing Uno, but sometimes I don't have a deck to use or a place to put cards!
Plenty of Uno simulators already exist to solve this problem, but they are all web-based and don't work offline.
Hopefully, this project can be used as a substitute for a playing space when on a car, plane, or bus.
I also want to gain experience designing programs that run games.

## User stories
- I want to start a game with a given number of players.
- I want to add multiple starting cards to each player's hand.
- I want to be able to view the cards in my hand.
- I want to play a valid card onto the discard pile.
- I want to add a card to my hand if I don't have any valid cards to play.
- I want the game to end when a player runs out of cards.
- I want to have the option to save the current game at the beginning of my turn.
- I want to have the option to load an existing game instead of starting a new one.

## Stretch goals (beyond minimum product)
- Power cards: +2, +4, Wild, Skip, Reverse
- Sorted cards: automatically sort the cards in the player's hand as they draw them
- Support 10 to 1: start with ten cards each and play games down to one card each
- Choose infinite draw or single draw
- Optional +2 and +4 stacking rule

## Reflection
Overall, I am happy with my design. I was able to get a single point of entry from the UI
package into the model package through the Game class, which is a good example of low coupling.
I was also able to use the Card interface for the fields in Game and Player, another example of low coupling.
However, my cohesion is not the best, especially in the Player class. Currently, all of the code to generate new 
random cards is within the Player class, which is not really something that Player should be responsible for.
To refactor this, I would create a new Deck class which handles card generation. This could be a utility class with
static methods, or a Singleton if I want to keep track of state in the deck in the future (for example, to keep track
of what cards have been played to more accurately simulate Uno card distribution).
Then, my methods in Player can call the Deck class's methods to draw new random cards.
This way, I can easily change card generation later without affecting the Player class at all.
