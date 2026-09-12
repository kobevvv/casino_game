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

    public String payTableString() {
        int maxNameLength = 0;
        for (PayTable payTable : PayTable.values()) {
            maxNameLength = Math.max(maxNameLength, payTable.name.length());
        }

        StringBuilder builder = new StringBuilder();
        builder.append(String.format("| %-" + maxNameLength + "s\t| Multiplier |%n", "Hand"));
        builder.append(String.format("| %-" + maxNameLength + "s\t| --------- |%n", "---"));

        for (PayTable payTable : PayTable.values()) {
            String handName = payTable == this ? "\t > \t" + payTable.name : payTable.name;
            builder.append(String.format("| %-" + maxNameLength + "s\t| %d |%n", handName, payTable.multiplier));
        }

        return builder.toString();
    }

    public String getName() {
        return name;
    }
}
