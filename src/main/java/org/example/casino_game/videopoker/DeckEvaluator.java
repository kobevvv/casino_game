package org.example.casino_game.videopoker;

import java.util.*;

import static org.example.casino_game.videopoker.PayTable.*;

/**
 * Evaluate a set of 5 cards to the best corresponding poker value
 */
public class DeckEvaluator {

    public final ArrayList<Card> deck;
    public HashMap<Integer, Integer> numbersMap;
    public HashMap<Suit, Integer> suitsMap;

    public DeckEvaluator(List<Card> deck) {
        this.deck = new ArrayList<>(deck);
        this.numbersMap = mapOfNumbers();
        this.suitsMap = mapOfSuits();
        sortByNumber();
    }

    /**
     *     See PayTable
     *     ROYAL_FLUSH(800),
     *     STRAIGHT_FLUSH(50),
     *     FOUR_OF_A_KIND(25),
     *     FULL_HOUSE(9),
     *     FLUSH(6),
     *     STRAIGHT(4),
     *     THREE_OF_KIND(3),
     *     TWO_PAIR(2),
     *     PAIR_AT_LEAST_JACKS(1);
     */
    public PayTable evaluate() {
        for (PayTable rule: PayTable.values()) {
            if (rule.predicate.test(this)) {
                return rule;
            }
        }
        return NONE;
    }

    protected boolean isRoyalFlush() {
        return isFlush() && isStraight() && deck.getLast().hasSameNumber(14);
    }

    protected boolean isStraightFlush() {
        return isFlush() && isStraight();
    }

    protected boolean isFourOfAKind() {
        return maxOfSameNumber() >= 4;
    }

    protected boolean isFullHouse() {
        return maxOfSameNumber() >= 3 && isTwoPair();
    }

    protected boolean isFlush() {
        return maxOfSameSuit() >= 5;
    }

    protected boolean isStraight() {
        int count = 0;
        int max = 0;
        for (Card card : deck) {
            if (count == 0) {
                // first card
                count++;
                max = card.number();
            } else if (card.number() == max + 1) {
                // current card is one more than the previous
                count++;
                max++;
            } else if (card.number() > max + 1) {
                count = 1;
                max = card.number();
            }
        }
        return count >= 5;
    }

    protected boolean isThreeOfAKind() {
        return maxOfSameNumber() >= 3;
    }

    protected boolean isTwoPair() {
       return amountOfPairs() >= 2;
    }

    protected boolean isPairAtLeastJacks() {
        for (Integer number: numbersMap.keySet()) {
            // one pair of at least jacks
            if (numbersMap.get(number) >= 2 &&  number > 10) {
                return true;
            }
        }
        return false;
    }

    protected boolean isNone() {
        return true;
    }

    /**
     * HELP METHODS
     */

    private void sortByNumber() {
        deck.sort(Comparator.comparing(Card::number));
    }

    private int maxOfSameSuit() {
        int max = 0;
        for (Suit suit: suitsMap.keySet()) {
            if (suitsMap.get(suit) > max) {
                max = suitsMap.get(suit);
            }
        }
        return max;
    }

    private int maxOfSameNumber() {
        int max = 0;
        for (Integer number: numbersMap.keySet()) {
            if (numbersMap.get(number) > max) {
                max = numbersMap.get(number);
            }
        }
        return max;
    }

    private int amountOfPairs() {
        int count = 0;
        for (Integer number: numbersMap.keySet()) {
            if (numbersMap.get(number) >= 2) count++;
        }
        return count;
    }

    private HashMap<Integer, Integer> mapOfNumbers() {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (Card card : deck) {
            map.put(card.number(), map.getOrDefault(card.number(), 0) + 1);
        }
        return map;
    }

    private HashMap<Suit, Integer> mapOfSuits() {
        HashMap<Suit, Integer> map = new HashMap<>();
        for (Card card : deck) {
            map.put(card.suit(), map.getOrDefault(card.suit(), 0) + 1);
        }
        return map;
    }
}
