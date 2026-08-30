package org.example.casino_game.videopoker;

import java.util.List;
import java.util.function.Predicate;

/**
 * Paytable for video poker using the Jacks or Better variant
 */
public enum PayTable {
    ROYAL_FLUSH(800, DeckEvaluator::isRoyalFlush, "Royal Flush"),
    STRAIGHT_FLUSH(50, DeckEvaluator::isStraightFlush, "Straight Flush"),
    FOUR_OF_A_KIND(25, DeckEvaluator::isFourOfAKind, "Four Of A Kind"),
    FULL_HOUSE(9,  DeckEvaluator::isFullHouse, "Full House"),
    FLUSH(6, DeckEvaluator::isFlush, "Flush"),
    STRAIGHT(4, DeckEvaluator::isStraight, "Straight"),
    THREE_OF_KIND(3, DeckEvaluator::isThreeOfAKind, "Three Of A Kind"),
    TWO_PAIR(2, DeckEvaluator::isTwoPair, "Two Pair"),
    PAIR_AT_LEAST_JACKS(1, DeckEvaluator::isPairAtLeastJacks, "Pair at Least Jacks"),
    NONE(0, DeckEvaluator::isNone, "no combination (You need at least a pair of jacks)");

    public final int multiplier;
    public final Predicate<DeckEvaluator> predicate;
    public final String name;

    PayTable(int multiplier, Predicate<DeckEvaluator> predicate, String name) {
        this.multiplier = multiplier;
        this.predicate = predicate;
        this.name = name;
    }

    public static String payTableString() {
        StringBuilder builder = new StringBuilder();
        List<PayTable> payTables = List.of(PayTable.values());
        for (PayTable payTable : payTables) {
            builder.append(payTable.name());
            builder.append(" x");
            builder.append(payTable.multiplier);
            builder.append("\n");
        }
        return builder.toString();
    }

    public String getName() {
        return name;
    }
}
