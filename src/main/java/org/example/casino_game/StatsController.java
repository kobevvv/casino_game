package org.example.casino_game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class StatsController {

    public static void incrementCoins(int amount){
        writeStats(0, getStats(0) + amount);
    }

    public static void decrementCoins(int amount) throws OutOfCoinsException {
        int newAmount = getStats(0) - amount;
        if (newAmount >= 0) writeStats(0, newAmount);
        else throw new OutOfCoinsException("NIET GENOEG COINS!");
    }

    public static int getCoins() {
        return getStats(0);
    }

    public static void gainTrophies(int amount){
        writeStats(1, getStats(1) + amount);
    }

    public static void loseTrophies(int amount){
        int newAmount = getStats(1) - amount;
        if (newAmount >= 0) writeStats(1, newAmount);
        else writeStats(1, 0);
    }

    public static int getTrophies() {
        return getStats(1);
    }

    public static void gainXP(int amount){
        writeStats(2, getStats(2) + amount);
    }

    public static int getXP() {
        return getStats(2);
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
}
