package org.example.casino_game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ArrayList;

import org.example.casino_game.StatsController;
import org.example.casino_game.shop.ShopApplication;
import org.example.casino_game.videopoker.VideoPokerApplication;
import org.example.casino_game.memory.MemoryApplication;
import org.example.casino_game.OutOfCoinsException;

public class MainMenu extends Application {

    public void start(Stage s) {

        VBox root = new VBox();

        Label coinsLabel = new Label("Coins: " + StatsController.getStats(0));
        Label trophiesLabel = new Label("Trophies: " + StatsController.getStats(1));
        Label levelLabel = new Label("Level: " + (1 + (int) StatsController.getStats(2) / 1000));
        Label xpLabel = new Label("Next level xp: " + StatsController.getStats(2) % 1000 + "/1000");
        Button shopButton = new Button("Shop");
        Button button1 = new Button("Video Poker");
        Button button2 = new Button("Memory");

        EventHandler<ActionEvent> shopEvent = new EventHandler<ActionEvent>(){
            public void handle(ActionEvent e){
                System.out.println("Shopping");
                ShopApplication shopApplication = new ShopApplication();
                shopApplication.start(s);
            }
        };

        EventHandler<ActionEvent> event1 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                System.out.println("Playing videopoker");
                VideoPokerApplication videoPokerApplication = new VideoPokerApplication();
                videoPokerApplication.start(s);
            }
        };

        EventHandler<ActionEvent> event2 = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                System.out.println("Playing memory");
                MemoryApplication memoryApplication = new MemoryApplication();
                memoryApplication.start(s);
                // TODO create and start memory application
            }
        };

        // when button is pressed
        shopButton.setOnAction(shopEvent);
        button1.setOnAction(event1);
        button2.setOnAction(event2);

        // add button
        root.getChildren().add(coinsLabel);
        root.getChildren().add(trophiesLabel);
        root.getChildren().add(levelLabel);
        root.getChildren().add(xpLabel);
        root.getChildren().add(shopButton);
        root.getChildren().add(button1);
        root.getChildren().add(button2);

        // create a scene
        Scene sc = new Scene(root, 500, 500);

        // set the scene
        s.setScene(sc);
        s.setTitle("Menu");
        s.show();
    }

    public static void main(String args[]) {
        launch(args);
    }
}
