package org.example.casino_game;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.stage.Stage;

import org.example.casino_game.videopoker.VideoPokerApplication;
import org.example.casino_game.memory.MemoryApplication;

public class MainMenu extends Application {

    public void start(Stage s) {

        VBox root = new VBox();

        Button button1 = new Button("Video Poker");
        Button button2 = new Button("Memory");

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
        button1.setOnAction(event1);
        button2.setOnAction(event2);

        // add button
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
