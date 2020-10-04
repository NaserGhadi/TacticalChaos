package com.company;
import com.company.Game.Game;
import com.company.Game.SecondaryPlan;
import com.company.Players.ConsolePlayer;
import com.company.Players.Player;
import com.company.Style.Style;
import com.company.ability.AatroxAbility;
import com.company.documentation.GameStatePresent;
import com.company.groups.*;

import javax.sound.sampled.Line;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Main implements Serializable {
    public Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        GameStatePresent o = new GameStatePresent();
//        o.ReadObjectFromFile();8
        Game game;
        game = new Game();
        game.startGame();
        Save.setGame(game);
    }

    public static void save(){
        try {
            FileOutputStream fileOut = new FileOutputStream(".//GameState");
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
           // objectOut.writeObject();
            objectOut.close();
            System.out.println("The Object  was successfully written to a file");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.print(ex);
        }
    }
    public void ReadObjectFromFile() {
        try {
            FileInputStream fileIn = new FileInputStream(".//GameState");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            System.out.println("The Object has been read from the file");
            Game obj = (Game) objectIn.readObject();
            objectIn.close();
//            for (int i=0;i<obj.getPlayers2().size();i++)
//                System.out.println(obj.getPlayers2().get(i).getName());
//            System.out.println(obj.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.print(ex);
        }
    }
}