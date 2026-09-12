package org.example.casino_game.videopoker;

import javafx.scene.layout.VBox;
import org.example.casino_game.Card;

import java.util.ArrayList;
import java.util.List;

import static org.example.casino_game.StatsController.*;

public class VideoPokerManager {

    protected final static int START_CREDIT = 100;
    protected final static int AMOUNT_OF_CARDS = 5;
    protected final static int MAX_BETSIZE = 5;
    private ArrayList<Card> deck = new ArrayList<>();
    private Card[] currentCards = new Card[AMOUNT_OF_CARDS];
    private ArrayList<Integer> selectedCards = new ArrayList<>();
    private int betSize;

    // state of the game
    private boolean betPlaced = false;
    private boolean roundFinished = false;

    public VideoPokerManager() {
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
        this.roundFinished = false;
    }

    public void setBetSize(int betSize) {
        if (betSize > MAX_BETSIZE) {
            throw new IllegalArgumentException("Bet size can't be greater than " + MAX_BETSIZE);
        }
        if (betSize > getCoins()) {
            throw new IllegalArgumentException("You don't have enough coins, you only have " + getCoins() + " coins");
        }
        this.betPlaced = true;
        this.betSize = betSize;
        decrementCoins(betSize);
    }

    public int getBetSize() {
        return betSize;
    }

    public boolean isBetPlaced() {
        return this.betPlaced;
    }

    public boolean isRoundFinished() {
        return this.roundFinished;
    }

    private PayTable getCurrentPayTable() {
        DeckEvaluator deckEvaluator = new DeckEvaluator(List.of(currentCards));
        return deckEvaluator.evaluate();
    }

    public boolean playerWonRound() {
        return getCurrentPayTable().multiplier > 0;
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
        if (roundFinished) {
            throw new Exception("You already drew new cards");
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
        this.roundFinished = true;
        updateCredit();
    }

    public VBox getPayTable() {
        return getCurrentPayTable().payTableBox();
    }

    protected void updateCredit() {
        int bet = getBetSize();
        int payOut = getCurrentPayTable().multiplier;
        incrementCoins(bet * payOut);
        gainXP(payOut);
    }

    public int getXPGained() {
        return getCurrentPayTable().multiplier;
    }

}
