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

import org.example.casino_game.shop.ShopApplication;
import org.example.casino_game.videopoker.VideoPokerApplication;
import org.example.casino_game.memory.MemoryApplication;
import org.example.casino_game.OutOfCoinsException;

public class MainMenu extends Application {

    public void start(Stage s) {

        VBox root = new VBox();

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

    public static void incrementCoins(int amount){
        writeStats(0, getStats(0) + amount);
    }
    public static void decrementCoins(int amount) throws OutOfCoinsException {
        int newAmount = getStats(0) - amount;
        if (newAmount >= 0) writeStats(0, newAmount);
        else throw new OutOfCoinsException("NIET GENOEG COINS!");
    }
    public static void gainTrophies(int amount){
        writeStats(1, getStats(1) + amount);
    }
    public static void loseTrophies(int amount){
        int newAmount = getStats(1) - amount;
        if (newAmount >= 0) writeStats(1, newAmount);
        else writeStats(1, 0);
    }
    public static void gainXP(int amount){
        writeStats(2, getStats(2) + amount);
    }

    public static int getStats(int line){
        int ret = -1;
        Path path = Path.of("src/main/java/org/example/casino_game/stats.txt");
        try{
            List regels = Files.readAllLines(path);
            String retString = (String) regels.get(line);
            if (retString != null){
                ret = Integer.parseInt(retString);
            }
            else{
                System.out.println("String die je wilt ophalen is null");
            }
        } catch (IOException e){
            System.out.println("IOEXCEPTION FOUT (in getStats)");
            e.printStackTrace();
        }
        return ret;
    }
    private static void writeStats(int line, int value){
        Path path = Path.of("src/main/java/org/example/casino_game/stats.txt");
        try{
            List regels = Files.readAllLines(path);
            List aangepasteRegels = new ArrayList<>();
            for (int i = 0; i < regels.size(); i++){
                if (i == line){
                    // regel die moet aangepast worden
                    aangepasteRegels.add(String.valueOf(value));
                }
                else{
                    aangepasteRegels.add(regels.get(i));
                }
            }
            Files.write(path, aangepasteRegels);
        } catch (IOException e){
            System.out.println("IOEXCEPTION FOUT (in writeStats)");
            e.printStackTrace();
        }
    }


    public static void main(String args[]) {
        launch(args);
    }
}
