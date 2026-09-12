package org.example.casino_game.videopoker;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

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

    public VBox payTableBox() {
        VBox vBox = new VBox(8);
        vBox.setPadding(new Insets(14));
        vBox.setStyle(
                "-fx-background-color: rgba(255, 255, 255, 0.88);" +
                "-fx-border-color: #8b5a2b;" +
                "-fx-border-width: 2;" +
                "-fx-border-radius: 6;" +
                "-fx-background-radius: 6;" +
                "-fx-font-family: 'Roboto';"
        );

        Label title = new Label("Pay Table");
        title.setFont(Font.font("Roboto", FontWeight.BOLD, 22));
        title.setTextFill(Color.DARKSLATEBLUE);

        GridPane table = new GridPane();
        table.setHgap(24);
        table.setVgap(6);
        table.setAlignment(Pos.CENTER_LEFT);

        ColumnConstraints handColumn = new ColumnConstraints();
        handColumn.setMinWidth(220);
        ColumnConstraints payoutColumn = new ColumnConstraints();
        payoutColumn.setMinWidth(80);
        table.getColumnConstraints().addAll(handColumn, payoutColumn);

        Label handHeader = new Label("Hand");
        Label payoutHeader = new Label("Payout");
        handHeader.setFont(Font.font("Roboto", FontWeight.BOLD, 16));
        payoutHeader.setFont(Font.font("Roboto", FontWeight.BOLD, 16));
        handHeader.setTextFill(Color.DARKSLATEGRAY);
        payoutHeader.setTextFill(Color.DARKSLATEGRAY);
        table.add(handHeader, 0, 0);
        table.add(payoutHeader, 1, 0);

        int row = 1;
        for (PayTable payTable : PayTable.values()) {
            boolean isCurrentPayTable = payTable == this;

            Label hand = new Label((isCurrentPayTable ? "> " : "  ") + payTable.name);
            Label payout = new Label(payTable.multiplier + "x");
            FontWeight weight = isCurrentPayTable ? FontWeight.BOLD : FontWeight.NORMAL;

            hand.setFont(Font.font("Roboto", weight, 16));
            payout.setFont(Font.font("Roboto", weight, 16));

            Color textColor = isCurrentPayTable ? Color.FIREBRICK : Color.BLACK;
            hand.setTextFill(textColor);
            payout.setTextFill(textColor);

            table.add(hand, 0, row);
            table.add(payout, 1, row);
            row++;
        }

        vBox.getChildren().addAll(title, table);
        return vBox;
    }
}
