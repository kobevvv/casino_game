package org.example.casino_game.videopoker;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import org.example.casino_game.Card;
import org.example.casino_game.Suit;

import java.util.ArrayList;

public class VideoPokerApplication extends Application {

    private final static int SCENE_LENGTH = 900;
    private final static int SCENE_WIDTH = 750;
    private final static int AMOUNT_OF_CARDS = 5;
    private ArrayList<Card> cards = new ArrayList<>();
    private ArrayList<Integer> selectedCards = new ArrayList<>();
    private Image cross = new Image(getClass().getResource("/images/red_cross.png").toExternalForm());

    @Override
    public void start(Stage stage) {

        // insert game logic

        // give some initial cards
        Card card = new Card(9, Suit.HEART);
        Card card2 = new Card(10, Suit.SPADE);
        Card card3 = new Card(8, Suit.DIAMOND);
        Card card4 = new Card(13, Suit.CLUBS);
        Card card5 = new Card(12, Suit.SPADE);

        // save the cards
        cards = new ArrayList<>();
        cards.add(card);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        cards.add(card5);

        // start with no cards selected
        for (int i = 0; i < 5; i++) {
            selectedCards.add(0);
        }

        renderScene(stage);
    }

    private void renderScene(Stage stage) {
        VBox root = new VBox();

        // display current combination + score
        Label label = new Label("You're playing video poker!");
        root.getChildren().add(label);

        // display cards
        HBox cards = new HBox();
        for (int i = 0; i < AMOUNT_OF_CARDS; i++) {
            cards.getChildren().add(renderCard(stage, i));
        }
        root.getChildren().add(cards);

        // info button
        Button b1 = new Button("Confirm delete");
        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                System.out.println("Confirm delete");
            }
        };
        b1.setOnAction(event1);
        root.getChildren().add(b1);

        // confirm button
        Button b2 = new Button("Paytable info");
        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                System.out.println("Paytable info");
            }
        };
        b2.setOnAction(event2);
        root.getChildren().add(b2);

        root.setStyle("-fx-background-color: pink;");

        stage.setTitle("Casino game - Video Poker");
        stage.setScene(new Scene(root, SCENE_LENGTH, SCENE_WIDTH));
        stage.show();
    }

    private StackPane renderCard(Stage stage, int index) {
        Image image = new Image(getClass().getResource(cards.get(index).getImagePath()).toExternalForm());

        ImageView imageView = new ImageView(image);

        // setting the fit height and width of the image view
        imageView.setFitHeight(SCENE_LENGTH/5);
        imageView.setFitWidth(SCENE_WIDTH/5);

        // create a clickable area
        Rectangle clickableArea = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
        clickableArea.setFill(javafx.scene.paint.Color.TRANSPARENT);
        clickableArea.setOnMouseClicked((event) -> {
            System.out.println("You clicked on " + event.getX() + ", " + event.getY());
            // change the card when clicked
            selectedCards.set(index, selectedCards.get(index) + 1);
            renderScene(stage);
        });

        // setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);

        StackPane pane = new StackPane();
        pane.getChildren().add(imageView);

        if (selectedCards.get(index) % 2 == 1) {
            ImageView crossView = new ImageView(cross);
            crossView.setFitHeight(SCENE_LENGTH/10);
            crossView.setFitWidth(SCENE_WIDTH/10);
            pane.getChildren().add(crossView);
        }

        pane.getChildren().add(clickableArea);
        return pane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
