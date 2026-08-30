package org.example.casino_game.videopoker;

import java.util.ArrayList;
import java.util.List;

public class VideoPokerManager {

    protected final static int AMOUNT_OF_CARDS = 5;
    private ArrayList<Card> deck = new ArrayList<>();
    private Card[] currentCards = new Card[AMOUNT_OF_CARDS];
    private ArrayList<Integer> selectedCards = new ArrayList<>();

    public void start() {
        CardDeck cardDeck = new CardDeck();
        deck = cardDeck.createDeck();

        // take the first 5 cards of the deck
        for (int i = 0; i < AMOUNT_OF_CARDS; i++) {;
            currentCards[i] = deck.removeFirst();
            selectedCards.add(0);
        }
    }

    public String getCurrentCombination() {
        DeckEvaluator deckEvaluator = new DeckEvaluator(List.of(currentCards));
        return deckEvaluator.evaluate().getName();
    }

    public List<Card> getCurrentCards() {
        return List.of(currentCards);
    }

    public boolean isCardSelected(int index) {
        return selectedCards.get(index) % 2 == 1;
    }

    public void selectCard(int index) {
        selectedCards.set(index, selectedCards.get(index) + 1);
    }

    public void removeSelectedCards() throws Exception {
        int count = 0;
        for (int i = 0; i < AMOUNT_OF_CARDS; i++) {
            if (isCardSelected(i)) {
                try {
                    currentCards[i] = deck.removeFirst();
                } catch (Exception e) {
                    throw new IllegalArgumentException("The card deck is empty");
                }
                count++;
            }
        }
        if (count == 0) {
            throw new IllegalArgumentException("No cards not selected");
        } else {
            for (int i = 0; i < AMOUNT_OF_CARDS; i++) {
                selectedCards.set(i, 0);
            }
        }
    }

}
