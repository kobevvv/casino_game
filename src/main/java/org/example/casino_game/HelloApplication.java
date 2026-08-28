package org.example.casino_game;

import java.util.List;
import java.util.LinkedList;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;

public class HelloApplication extends Application {
    // windowbreedte en hoogte
    static final int BREEDTE = 800;
    static final int HOOGTE = 600;

    // memory state
    LinkedList<Boolean> memoryState = new LinkedList<>();
    static int aantalOmgedraaid = 0;

    // initialisatie
    Stage st = new Stage();
    Pane root = new Pane();

    // achterkant van kaarten
    Image memoryBackBlauw = new Image("file:images/cards/card backs/blue_back_suits_dark.png", 180, -1, true, true);
    Image testKaart = new Image("file:images/cards/card_clubs_1.png", 180, -1, true, true);


    // rectangle click memory subroutine
    private void kaartGeklikt(int kaartnr) {
        System.out.println("Er is geklikt!");
        if (aantalOmgedraaid < 2){
            aantalOmgedraaid++;
            memoryState.set(kaartnr, true);
            if (aantalOmgedraaid == 2){
                // ... even wachten
            }
            renderMemoryGame();
        }
    }



    @Override
    public void start(Stage stage) {
        /*String javaVersion = System.getProperty("java.version");
        String javafxVersion = System.getProperty("javafx.version");
        Label l = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        Scene scene = new Scene(new StackPane(l), 640, 480);
        stage.setScene(scene);
        stage.show();*/


        root.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, null, null)));
        Scene sceneMainMenu = new Scene(root, BREEDTE, HOOGTE, Color.GREENYELLOW);


        // initiele memory state
        for (int i = 0; i < 8; i++) memoryState.add(false);


        // rectangles kaarten
        Rectangle kaartRect0 = new Rectangle(180, 243);
        kaartRect0.setX(10); kaartRect0.setY(10);
        kaartRect0.setOnMouseClicked(event -> {kaartGeklikt(0);});
        Rectangle kaartRect1 = new Rectangle(180, 243);
        kaartRect1.setX(210); kaartRect1.setY(10);
        kaartRect1.setOnMouseClicked(event -> {kaartGeklikt(1);});
        Rectangle kaartRect2 = new Rectangle(180, 243);
        kaartRect2.setX(410); kaartRect2.setY(10);
        kaartRect2.setOnMouseClicked(event -> {kaartGeklikt(2);});
        Rectangle kaartRect3 = new Rectangle(180, 243);
        kaartRect3.setX(610); kaartRect3.setY(10);
        kaartRect3.setOnMouseClicked(event -> {kaartGeklikt(3);});
        Rectangle kaartRect4 = new Rectangle(180, 243);
        kaartRect4.setX(10); kaartRect4.setY(270);
        kaartRect4.setOnMouseClicked(event -> {kaartGeklikt(4);});
        Rectangle kaartRect5 = new Rectangle(180, 243);
        kaartRect5.setX(210); kaartRect5.setY(270);
        kaartRect5.setOnMouseClicked(event -> {kaartGeklikt(5);});
        Rectangle kaartRect6 = new Rectangle(180, 243);
        kaartRect6.setX(410); kaartRect6.setY(270);
        kaartRect6.setOnMouseClicked(event -> {kaartGeklikt(6);});
        Rectangle kaartRect7 = new Rectangle(180, 243);
        kaartRect7.setX(610); kaartRect7.setY(270);
        kaartRect7.setOnMouseClicked(event -> {kaartGeklikt(7);});

        // achterkanten van kaarten
        ImageView memBackBlauwViewer0 = new ImageView(memoryBackBlauw);
        memBackBlauwViewer0.relocate(10, 10);
        ImageView memBackBlauwViewer1 = new ImageView(memoryBackBlauw);
        memBackBlauwViewer1.relocate(210, 10);
        ImageView memBackBlauwViewer2 = new ImageView(memoryBackBlauw);
        memBackBlauwViewer2.relocate(410, 10);
        ImageView memBackBlauwViewer3 = new ImageView(memoryBackBlauw);
        memBackBlauwViewer3.relocate(610, 10);
        ImageView memBackBlauwViewer4 = new ImageView(memoryBackBlauw);
        memBackBlauwViewer4.relocate(10, 270);
        ImageView memBackBlauwViewer5 = new ImageView(memoryBackBlauw);
        memBackBlauwViewer5.relocate(210, 270);
        ImageView memBackBlauwViewer6 = new ImageView(memoryBackBlauw);
        memBackBlauwViewer6.relocate(410, 270);
        ImageView memBackBlauwViewer7 = new ImageView(memoryBackBlauw);
        memBackBlauwViewer7.relocate(610, 270);


        Button memoryButton = new Button("Memory");
        root.getChildren().add(memoryButton);

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                root.getChildren().clear();

                root.getChildren().add(memBackBlauwViewer0);
                root.getChildren().add(memBackBlauwViewer1);
                root.getChildren().add(memBackBlauwViewer2);
                root.getChildren().add(memBackBlauwViewer3);
                root.getChildren().add(memBackBlauwViewer4);
                root.getChildren().add(memBackBlauwViewer5);
                root.getChildren().add(memBackBlauwViewer6);
                root.getChildren().add(memBackBlauwViewer7);

                root.getChildren().add(kaartRect0);
                root.getChildren().add(kaartRect1);
                root.getChildren().add(kaartRect2);
                root.getChildren().add(kaartRect3);
                root.getChildren().add(kaartRect4);
                root.getChildren().add(kaartRect5);
                root.getChildren().add(kaartRect6);
                root.getChildren().add(kaartRect7);

            }
        };
        memoryButton.setOnAction(event);


        st.setScene(sceneMainMenu);
        st.setTitle("CASINO GAME (v1.0)");
        st.show();
    }

    public void renderMemoryGame(){
        // achterkanten van kaarten
        ImageView memBackBlauwViewer0 = new ImageView(memoryBackBlauw);
        memBackBlauwViewer0.relocate(10, 10);
        ImageView memBackBlauwViewer1 = new ImageView(memoryBackBlauw);
        memBackBlauwViewer1.relocate(210, 10);
        ImageView memBackBlauwViewer2 = new ImageView(memoryBackBlauw);
        memBackBlauwViewer2.relocate(410, 10);
        ImageView memBackBlauwViewer3 = new ImageView(memoryBackBlauw);
        memBackBlauwViewer3.relocate(610, 10);
        ImageView memBackBlauwViewer4 = new ImageView(memoryBackBlauw);
        memBackBlauwViewer4.relocate(10, 270);
        ImageView memBackBlauwViewer5 = new ImageView(memoryBackBlauw);
        memBackBlauwViewer5.relocate(210, 270);
        ImageView memBackBlauwViewer6 = new ImageView(memoryBackBlauw);
        memBackBlauwViewer6.relocate(410, 270);
        ImageView memBackBlauwViewer7 = new ImageView(memoryBackBlauw);
        memBackBlauwViewer7.relocate(610, 270);

        // rectangles kaarten
        Rectangle kaartRect0 = new Rectangle(180, 243);
        kaartRect0.setX(10); kaartRect0.setY(10);
        kaartRect0.setOnMouseClicked(event -> {kaartGeklikt(0);});
        Rectangle kaartRect1 = new Rectangle(180, 243);
        kaartRect1.setX(210); kaartRect1.setY(10);
        kaartRect1.setOnMouseClicked(event -> {kaartGeklikt(1);});
        Rectangle kaartRect2 = new Rectangle(180, 243);
        kaartRect2.setX(410); kaartRect2.setY(10);
        kaartRect2.setOnMouseClicked(event -> {kaartGeklikt(2);});
        Rectangle kaartRect3 = new Rectangle(180, 243);
        kaartRect3.setX(610); kaartRect3.setY(10);
        kaartRect3.setOnMouseClicked(event -> {kaartGeklikt(3);});
        Rectangle kaartRect4 = new Rectangle(180, 243);
        kaartRect4.setX(10); kaartRect4.setY(270);
        kaartRect4.setOnMouseClicked(event -> {kaartGeklikt(4);});
        Rectangle kaartRect5 = new Rectangle(180, 243);
        kaartRect5.setX(210); kaartRect5.setY(270);
        kaartRect5.setOnMouseClicked(event -> {kaartGeklikt(5);});
        Rectangle kaartRect6 = new Rectangle(180, 243);
        kaartRect6.setX(410); kaartRect6.setY(270);
        kaartRect6.setOnMouseClicked(event -> {kaartGeklikt(6);});
        Rectangle kaartRect7 = new Rectangle(180, 243);
        kaartRect7.setX(610); kaartRect7.setY(270);
        kaartRect7.setOnMouseClicked(event -> {kaartGeklikt(7);});

        //TEST
        ImageView testje = new ImageView(testKaart);
        testje.relocate(50,50);

        root.getChildren().clear();
        root.getChildren().add(memBackBlauwViewer0);
        root.getChildren().add(memBackBlauwViewer1);
        root.getChildren().add(memBackBlauwViewer2);
        root.getChildren().add(memBackBlauwViewer3);
        root.getChildren().add(memBackBlauwViewer4);
        root.getChildren().add(memBackBlauwViewer5);
        root.getChildren().add(memBackBlauwViewer6);
        root.getChildren().add(memBackBlauwViewer7);

        root.getChildren().add(kaartRect0);
        root.getChildren().add(kaartRect1);
        root.getChildren().add(kaartRect2);
        root.getChildren().add(kaartRect3);
        root.getChildren().add(kaartRect4);
        root.getChildren().add(kaartRect5);
        root.getChildren().add(kaartRect6);
        root.getChildren().add(kaartRect7);

        for (int i = 0; i < memoryState.size(); i++){
            if (memoryState.get(i) == true){
                System.out.println("JA");
                if (i < 4){
                    root.getChildren().add(testje);
                }
                else{
                    root.getChildren().add(testje);
                }
            }
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
