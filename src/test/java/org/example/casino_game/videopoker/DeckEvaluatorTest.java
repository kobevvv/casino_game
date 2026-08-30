package org.example.casino_game.videopoker;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DeckEvaluatorTest {

    @Test
    void detectsRoyalFlush() {
        DeckEvaluator evaluator = new DeckEvaluator(hand(
                new Card(14, Suit.HEART),
                new Card(13, Suit.HEART),
                new Card(12, Suit.HEART),
                new Card(11, Suit.HEART),
                new Card(10, Suit.HEART)
        ));

        assertTrue(evaluator.isRoyalFlush());
    }

    @Test
    void detectsFourOfAKind() {
        DeckEvaluator evaluator = new DeckEvaluator(hand(
                new Card(9, Suit.CLUBS),
                new Card(9, Suit.HEART),
                new Card(9, Suit.SPADE),
                new Card(9, Suit.DIAMOND),
                new Card(14, Suit.CLUBS)
        ));

        assertTrue(evaluator.isFourOfAKind());
    }

    @Test
    void detectsTwoPair() {
        DeckEvaluator evaluator = new DeckEvaluator(hand(
                new Card(3, Suit.CLUBS),
                new Card(3, Suit.HEART),
                new Card(12, Suit.SPADE),
                new Card(12, Suit.DIAMOND),
                new Card(7, Suit.CLUBS)
        ));

        assertTrue(evaluator.isTwoPair());
    }

    @Test
    void pairAtLeastJacksIgnoresLowPair() {
        DeckEvaluator evaluator = new DeckEvaluator(hand(
                new Card(10, Suit.CLUBS),
                new Card(10, Suit.HEART),
                new Card(2, Suit.SPADE),
                new Card(5, Suit.DIAMOND),
                new Card(8, Suit.CLUBS)
        ));

        assertFalse(evaluator.isPairAtLeastJacks());
    }

    private static ArrayList<Card> hand(Card... cards) {
        return new ArrayList<>(List.of(cards));
    }
}
