package org.example.casino_game.videopoker;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Create a new card deck with 52 cards with 13 from each suit.
 */
public class CardDeck {

    /**
     * Create a shuffled deck
     * @return A shuffled deck of 52 cards
     */
    public ArrayList<Card> createDeck() {
        Card[] deck = new Card[52];
        int i = 0;

        for(Suit suit : Suit.values()) {
            for (int j = 2; j < 15; j++) { // represent an ace as 14 (since it is the highest card)
                deck[i] = new Card(j,suit);
                i++;
            }
        }

        shuffleDeck(deck);
        return new ArrayList<>(List.of(deck));
    }

    /**
     * Shuffle the given deck using the fisher yates method
     * @param deck the deck of cards that has to be shuffled
     */
    private void shuffleDeck(Card[] deck){
        Random rnd = new Random();

        for (int i = deck.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            Card temp = deck[index];
            deck[index] = deck[i];
            deck[i] = temp;
        }
    }
}
