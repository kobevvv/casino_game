package org.example.casino_game.memory;

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

import java.util.ArrayList;
import java.util.Random;
import org.example.casino_game.Card;
import org.example.casino_game.memory.MemoryCard;
import org.example.casino_game.Suit;

public class MemoryApplication extends Application {

    private final static int SCENE_LENGTH = 900;
    private final static int SCENE_WIDTH = 750;
    private final static int AMOUNT_OF_CARDS = 8;
    private ArrayList<MemoryCard> cards = new ArrayList<>();
    private int selectedCard1 = -1;
    private int selectedCard2 = -1;
    private int tries = 0;
    private int points = 0;
    private boolean wait = false;

    public void start(Stage stage){
        MemoryCard queenClubs1 = new MemoryCard(12, Suit.CLUBS, false, false);
        MemoryCard queenClubs2 = new MemoryCard(12, Suit.CLUBS, false, false);
        MemoryCard kingClubs1 = new MemoryCard(13, Suit.CLUBS, false, false);
        MemoryCard kingClubs2 = new MemoryCard(13, Suit.CLUBS, false, false);
        MemoryCard queenSpade1 = new MemoryCard(12, Suit.SPADE, false, false);
        MemoryCard queenSpade2 = new MemoryCard(12, Suit.SPADE, false, false);
        MemoryCard kingSpade1 = new MemoryCard(13, Suit.SPADE, false, false);
        MemoryCard kingSpade2 = new MemoryCard(13, Suit.SPADE, false, false);

        cards.add(queenClubs1);
        cards.add(queenClubs2);
        cards.add(kingClubs1);
        cards.add(kingClubs2);
        cards.add(queenSpade1);
        cards.add(queenSpade2);
        cards.add(kingSpade1);
        cards.add(kingSpade2);

        scrambleCards();

        renderScene(stage);
    }

    private void renderScene(Stage stage){
        VBox root = new VBox();
        Label label = new Label("You're playing memory!");
        root.getChildren().add(label);
        Label triesLabel = new Label("Tries: " + tries);
        root.getChildren().add(triesLabel);
        Label pointsLabel = new Label("¨Points: " + points);
        root.getChildren().add(pointsLabel);

        HBox hbox = new HBox();
        for (int i = 0; i < AMOUNT_OF_CARDS; i++) {
            hbox.getChildren().add(renderCard(stage, cards.get(i), i));
        }
        root.getChildren().add(hbox);

        root.setStyle("-fx-background-color: pink;");

        stage.setTitle("Casino game - Memory");
        stage.setScene(new Scene(root, SCENE_LENGTH, SCENE_WIDTH));
        stage.show();

        if (selectedCard1 != -1 && selectedCard2 != -1 && cards.get(selectedCard1).equals(cards.get(selectedCard2))){
            wait = false;
        }
        else if (wait){
            wait = false;
            cards.get(selectedCard1).setTurned(false);
            cards.get(selectedCard2).setTurned(false);
            selectedCard1 = -1;
            selectedCard2 = -1;
            try{
                Thread.sleep(2000);
            } catch(InterruptedException e){
                System.out.println("FOUT: DE DELAY IS ONDERBROKEN");
                Thread.currentThread().interrupt();
            }
        }
    }

    private StackPane renderCard(Stage stage, MemoryCard card, int index){
        Image image = new Image(getClass().getResource(cards.get(index).getImagePath()).toExternalForm());

        ImageView imageView = new ImageView(image);

        // setting the fit height and width of the image view
        imageView.setFitHeight(SCENE_LENGTH/8);
        imageView.setFitWidth(SCENE_WIDTH/8);

        // create a clickable area
        Rectangle clickableArea = new Rectangle(imageView.getFitWidth(), imageView.getFitHeight());
        clickableArea.setFill(javafx.scene.paint.Color.TRANSPARENT);
        clickableArea.setOnMouseClicked((event) -> {
            System.out.println("You clicked on " + event.getX() + ", " + event.getY());
            // change the card when clicked
            if (selectedCard1 == -1) {
                selectedCard1 = index;
                card.setTurned(true);
            }
            else if (selectedCard2 == -1) {
                selectedCard2 = index;
                card.setTurned(true);
                tries++;
                if (cards.get(selectedCard1).equals(card)){
                    // paar gevonden
                    cards.get(selectedCard1).found();
                    cards.get(index).found();
                    points++;
                }
                else{
                    // foute combinatie
                    wait = true;
                }
            }
            else {
                if (cards.get(selectedCard1).equals(cards.get(selectedCard2))){
                    wait = false;
                    selectedCard1 = -1;
                    selectedCard2 = -1;
                }
            }
            renderScene(stage);
        });

        // setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);

        StackPane pane = new StackPane();
        pane.getChildren().add(imageView);

        pane.getChildren().add(clickableArea);
        return pane;
    }

    private void scrambleCards(){
        Random rand = new Random();
        ArrayList<MemoryCard> temp = new ArrayList<>();
        for (int i = 0; i < AMOUNT_OF_CARDS; i++) {
            temp.add(cards.get(i));
        }
        cards.clear();
        for (int i = 0; i < AMOUNT_OF_CARDS - 1; i++){
            int ind = rand.nextInt(AMOUNT_OF_CARDS - i - 1);
            cards.add(temp.get(ind));
            temp.remove(ind);
        }
        cards.add(temp.get(0));
        temp.remove(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}

