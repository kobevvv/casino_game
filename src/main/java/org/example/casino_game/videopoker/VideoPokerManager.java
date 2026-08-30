package org.example.casino_game.videopoker;

import java.util.ArrayList;
import java.util.List;

public class VideoPokerManager {

    protected final static int START_CREDIT = 100;
    protected final static int AMOUNT_OF_CARDS = 5;
    protected final static int MAX_BETSIZE = 5;
    private ArrayList<Card> deck = new ArrayList<>();
    private Card[] currentCards = new Card[AMOUNT_OF_CARDS];
    private ArrayList<Integer> selectedCards = new ArrayList<>();
    private int credits;
    private int betSize;

    // state of the game
    private boolean betPlaced = false;
    private boolean cardsSwapped = false;

    public VideoPokerManager() {
        this.credits = START_CREDIT;
        initializeNewRound();
    }

    protected void initializeNewRound() {
        CardDeck cardDeck = new CardDeck();
        this.deck = cardDeck.createDeck();

        // take the first 5 cards of the deck
        for (int i = 0; i < AMOUNT_OF_CARDS; i++) {;
            currentCards[i] = deck.removeFirst();
            selectedCards.add(0);
        }

        this.betPlaced = false;
        this.betSize = 1;
        this.cardsSwapped = false;
    }

    public void setBetSize(int betSize) {
        this.betPlaced = true;
        this.betSize = betSize;
        this.credits -= betSize;
    }

    public int getBetSize() {
        return betSize;
    }

    public boolean isBetPlaced() {
        return this.betPlaced;
    }

    public boolean isCardsSwapped() {
        return this.cardsSwapped;
    }

    public String getCreditsString() {
        return ((Integer) credits).toString();
    }

    private PayTable getCurrentPayTable() {
        DeckEvaluator deckEvaluator = new DeckEvaluator(List.of(currentCards));
        return deckEvaluator.evaluate();
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
        if (cardsSwapped) {
            throw new Exception("You already draw new cards");
        }
        for (int i = 0; i < AMOUNT_OF_CARDS; i++) {
            if (isCardSelected(i)) {
                try {
                    currentCards[i] = deck.removeFirst();
                } catch (Exception e) {
                    throw new IllegalArgumentException("The card deck is somehow empty");
                }
            }
        }

        // remove crosses on screen
        for (int i = 0; i < AMOUNT_OF_CARDS; i++) {
            selectedCards.set(i, 0);
        }

        // finish the round
        this.cardsSwapped = true;
        updateCredit();
    }

    public String getPayTable() {
        return getCurrentPayTable().payTableString();
    }

    protected void updateCredit() {
        int bet = getBetSize();
        int payOut = getCurrentPayTable().multiplier;
        this.credits = this.credits + bet * payOut;
    }

}
