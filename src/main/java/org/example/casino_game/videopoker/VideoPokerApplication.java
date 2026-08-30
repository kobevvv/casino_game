package org.example.casino_game.videopoker;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import static org.example.casino_game.videopoker.VideoPokerManager.AMOUNT_OF_CARDS;

public class VideoPokerApplication extends Application {

    private final static int SCENE_LENGTH = 900;
    private final static int SCENE_WIDTH = 750;
    private final Image cross = new Image(getClass().getResource("/images/red_cross.png").toExternalForm());
    private Image backOfCard = new Image(getClass().getResource("/images/cards/card backs/blue_back_suits_dark.png").toExternalForm());
    private VideoPokerManager manager = new VideoPokerManager();

    @Override
    public void start(Stage stage) {
        manager = new VideoPokerManager();

        renderScene(stage);
    }

    // TODO simplify this function
    private void renderScene(Stage stage) {
        VBox root = new VBox();

        // display paytable
        Label paytableLabel = new Label(manager.getPayTable());
        root.getChildren().add(paytableLabel);

        // display cards
        HBox cards = new HBox();
        for (int i = 0; i < AMOUNT_OF_CARDS; i++) {
            cards.getChildren().add(renderCard(stage, i));
        }
        root.getChildren().add(cards);

        if (manager.isBetPlaced() && !manager.isCardsSwapped()) {
            // confirm button
            Button b1 = new Button("Confirm delete");
            EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e)
                {
                    System.out.println("Confirm delete");
                    try {
                        manager.removeSelectedCards();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    renderScene(stage);
                }
            };
            b1.setOnAction(event1);
            root.getChildren().add(b1);
        }

        if (manager.isCardsSwapped()) {
            // next round button
            Button b1 = new Button("Play again");
            EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e)
                {
                    System.out.println("New round started");
                    manager.initializeNewRound();
                    renderScene(stage);
                }
            };
            b1.setOnAction(event1);
            root.getChildren().add(b1);
        }

        // create a menu to choose bet
        MenuButton m = new MenuButton("Choose bet size");

        MenuItem m1 = new MenuItem("1");
        MenuItem m2 = new MenuItem("2");
        MenuItem m3 = new MenuItem("3");
        MenuItem m4 = new MenuItem("4");
        MenuItem m5 = new MenuItem("5");

        m.getItems().add(m1);
        m.getItems().add(m2);
        m.getItems().add(m3);
        m.getItems().add(m4);
        m.getItems().add(m5);

        // create events for menu items
        // action event
        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                System.out.println("Choose bet size " + ((MenuItem)e.getSource()).getText());
                manager.setBetSize(Integer.parseInt(((MenuItem)e.getSource()).getText()));
                renderScene(stage);
            }
        };

        m1.setOnAction(event);
        m2.setOnAction(event);
        m3.setOnAction(event);
        m4.setOnAction(event);
        m5.setOnAction(event);

        root.getChildren().add(m);

        // display current bet
        Label bet = new Label("Current bet: " + manager.getBetSize());
        root.getChildren().add(bet);

        // display amount of credits
        Label score = new Label("Credits: " + manager.getCreditsString());
        root.getChildren().add(score);

        root.setStyle("-fx-background-color: pink;");

        stage.setTitle("Casino game - Video Poker");
        stage.setScene(new Scene(root, SCENE_LENGTH, SCENE_WIDTH));
        stage.show();
    }

    private StackPane renderBackOfCard() {
        ImageView imageView = new ImageView(backOfCard);
        StackPane root = new StackPane();
        imageView.setFitHeight(SCENE_LENGTH/5);
        imageView.setFitWidth(SCENE_WIDTH/5);
        imageView.setPreserveRatio(true);
        root.getChildren().add(imageView);
        return root;
    }

    private StackPane renderCard(Stage stage, int index) {
        if (!manager.isBetPlaced()) {
            return renderBackOfCard();
        }

        Image image = new Image(getClass().getResource(manager.getCurrentCards().get(index).getImagePath()).toExternalForm());

        ImageView imageView = new ImageView(image);

        // setting the fit height and width of the image view
        imageView.setFitHeight(SCENE_LENGTH/5);
        imageView.setFitWidth(SCENE_WIDTH/5);

        // setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);

        StackPane pane = new StackPane();
        pane.getChildren().add(imageView);

        if (manager.isCardSelected(index)) {
            ImageView crossView = new ImageView(cross);
            crossView.setFitHeight(SCENE_LENGTH/10);
            crossView.setFitWidth(SCENE_WIDTH/10);
            pane.getChildren().add(crossView);
        }

        if (!manager.isCardsSwapped()) {
            // create a clickable area
            Rectangle clickableArea = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
            clickableArea.setFill(javafx.scene.paint.Color.TRANSPARENT);
            clickableArea.setOnMouseClicked((event) -> {
                System.out.println("You clicked on card " + (index + 1));
                manager.selectCard(index);
                renderScene(stage);
            });
            pane.getChildren().add(clickableArea);
        }

        return pane;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
