package com.company.documentation;
import com.company.Game.Game;
import com.company.Players.AutoPlayer;
import com.company.Players.Player;
import com.company.Style.Style;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
public class ScoreBoard {
    private static PrintWriter writer;
    public static Scanner scanner = new Scanner(System.in);
    public static void writeScoreBoard(Player player,boolean state){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String type = "Human";
        if (player instanceof AutoPlayer)
            type = "Boot";
        try {
            String Winner = "--------------------------------------- New Game ---------------------------------------------------" +
                    "\nGame Start At: " + sdf.format(Game.cal.getTime()) +
                    "\nGame End At: " + sdf.format(cal.getTime()) +
                    "\nLast Round: " + Game.roundIndex +
                    "\nPlayer Winner: " + player.getName()+
                    "\nPlayer Type: " + type + " \nHero Still Alive: \n";
            for (int i=0;i<player.getHeroes().size();i++){
                Winner+= i+1 + "- Hero Name: " + player.getHeroes().get(i).getAttr().getName();
                Winner+= "\nHealth: " + player.getHeroes().get(i).getAttr().getHealth();
                Winner+= "\nItems: ";
                for (int j=0;j<player.getHeroes().get(i).getAttr().getExistingItems().size();j++){
                    Winner+= player.getHeroes().get(i).getAttr().getExistingItems().get(j).getName() + ", ";
                }
                if (player.getHeroes().get(i).getAttr().getExistingItems().size()==0){
                    Winner+=" Empty";
                }
                Winner+= "\nGroups: ";
                for (int j=0;j<player.getHeroes().get(i).getAttr().getGroupTypes().size();j++){
                    Winner+=player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getName() + ", ";
                }
                Winner+= "\n";
            }
            Files.write(Paths.get("WinnersScoreBoard.txt"), Winner.getBytes(), StandardOpenOption.APPEND);
        }catch (IOException e) {
            //exception handling left as an exercise for the reader
        }
    }
    public static void printWinnersScoreBoard(){
        try {
            scanner = new Scanner(new File(".//WinnersScoreBoard.txt"));
            while (scanner.hasNextLine()){
                    System.out.println(Style.ANSI_PURPLE + scanner.nextLine() + Style.ANSI_RESET);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {

        }
    }
    public static void printLosersScoreBoard(){
        try {
            scanner = new Scanner(new File(".//LosersScoreBoard.txt"));
            while (scanner.hasNextLine()){
                System.out.println(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {

        }
    }
}
