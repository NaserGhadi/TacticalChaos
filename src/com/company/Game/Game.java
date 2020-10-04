package com.company.Game;

import com.company.AfterLife;
import com.company.Style.Style;
import com.company.documentation.GameStatePresent;
import com.company.documentation.InformationList;
import com.company.MatchName;
import com.company.Players.AutoPlayer;
import com.company.Players.ConsolePlayer;
import com.company.Players.Player;
import com.company.documentation.ScoreBoard;
import com.company.documentation.TeePrintStream;
import com.company.groups.Group;
import com.company.items.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {
    private int playersNumber;
    public static Calendar cal;
    private static ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Round> rounds = new ArrayList<>();
    public static ArrayList[][] arenaItems = new ArrayList[100][100];
    public static int roundIndex = 0;
    public static int numberOfChosenActions = 0;
    public static String[][] arenaTypes = new String[100][100];
    public static String[] types = new String[4];
    private boolean isRunning;
    private int maxRounds;
    public static int arenaHeight;
    public static int arenaWidth;
    public static int maxHeroes;
    public static int maxChanges;
    public static FileOutputStream file;

    static {
        try {
            file = new FileOutputStream("test.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static TeePrintStream tee = new TeePrintStream(file, System.out);

    public Game() throws FileNotFoundException {

    }

    public int getMaxRounds(){return 0;}
    public static ArrayList<Group> existingGroups = new ArrayList<>();
    public static ArrayList<Item> existingItems = new ArrayList<>();
    public static ArrayList<Player> getPlayers() {
        return players;
    }
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
    public synchronized static boolean checkForLonely(){
        if (Game.getPlayers().size()<=1){
            System.out.println("Game Ended");
            try{
                file.close();
            }catch (Exception ex){

            }
            return true;
        }
        int numberOfActivePlayers = 0;
        for (int i=0;i<Game.getPlayers().size();){
            boolean oneHeroAtLeast = false;
            for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++){
                if (Game.getPlayers().get(i).getHeroes().size()>=1){
                    oneHeroAtLeast = true;
                }
            }
            if (oneHeroAtLeast){
                numberOfActivePlayers++;
                i++;
            }else {
                System.out.println("Player: " + Game.getPlayers().get(i).getName() + " Is Dead");
                Game.getPlayers().remove(i);
            }
        }
        if (numberOfActivePlayers>=2){
            return false;
        }
        System.out.println("Game Ended");
        try{
            file.close();
        }catch (Exception ex){

        }
        return true;
    }
    public synchronized static void printArenaItems() {
        if (Game.getPlayers().size() <= 1) {
            System.out.println("Game Ended");
            try{
                Game.file.close();
                AfterLife.start();
            }catch (Exception ex){

            }
            return;
        }
        for (int i=0;i<arenaHeight;i++){
            for (int j=0;j<arenaWidth;j++){
                if (!arenaItems[i][j].isEmpty() && arenaItems[i][j]!=null && arenaItems[i][j].size()!=0){
                    if (arenaItems[i][j].size()==1){
                        if (((Item)arenaItems[i][j].get(0)).getName().charAt(0)=='A')
                            System.out.print( Style.ANSI_RED +  " A " + Style.ANSI_RESET);
                        else if (((Item)arenaItems[i][j].get(0)).getName().charAt(0)=='U')
                            System.out.print( Style.ANSI_YELLOW +  " U " + Style.ANSI_RESET);
                        else if (((Item)arenaItems[i][j].get(0)).getName().charAt(0)=='N')
                            System.out.print( Style.ANSI_BLUE +  " N " + Style.ANSI_RESET);
                        else if (((Item)arenaItems[i][j].get(0)).getName().charAt(0)=='K')
                            System.out.print( Style.ANSI_PURPLE +  " K " + Style.ANSI_RESET);
                        else if (((Item)arenaItems[i][j].get(0)).getName().charAt(0)=='M')
                            System.out.print( Style.ANSI_GREEN +  " M " + Style.ANSI_RESET);
                        else if (((Item)arenaItems[i][j].get(0)).getName().charAt(0)=='W')
                            System.out.print( Style.ANSI_CYAN +  " W " + Style.ANSI_RESET);
                        else if (((Item)arenaItems[i][j].get(0)).getName().charAt(0)=='V')
                            System.out.print( Style.ANSI_PURPLE +  " V " + Style.ANSI_RESET);
                    }else {
                        System.out.print( Style.ANSI_RED +  " C " + Style.ANSI_RESET);
                    }
                }else {
                    System.out.print( Style.ANSI_CYAN +  " E " + Style.ANSI_RESET);
                }
            }
            System.out.println();
        }
    }
    public synchronized static void printArenaTypes(){
        for (int i=0;i<arenaHeight;i++){
            for (int j=0;j<arenaWidth;j++){
                if (arenaItems[i][j]!=null){
                    if (arenaTypes[i][j].equals("G"))
                       System.out.print(" " + Style.ANSI_GREEN + arenaTypes[i][j] + Style.ANSI_RESET + " ");
                    else if (arenaTypes[i][j].equals("T"))
                        System.out.print(" " + Style.ANSI_RED + arenaTypes[i][j] + Style.ANSI_RESET + " ");
                    else if (arenaTypes[i][j].equals("W"))
                        System.out.print(" " + Style.ANSI_BLUE + arenaTypes[i][j] + Style.ANSI_RESET + " ");
                    else if (arenaTypes[i][j].equals("S") )
                        System.out.print(" " + arenaTypes[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
    public void startGame() throws IOException {
        cal = Calendar.getInstance();
        Scanner scnr = new Scanner(System.in);
        existingItems.add(new AngryCloak());
        existingItems.add(new KnightArmor());
        existingItems.add(new MagicHat());
        existingItems.add(new NightShift());
        existingItems.add(new UniverseCore());
        existingItems.add(new VoidHit());
        existingItems.add(new WarriorGloves());
        types[0] = "S";
        types[1] = "G";
        types[2] = "T";
        types[3] = "W";
        System.setOut(tee);
        boolean exit = false;
        System.out.println(Style.ANSI_RED + "-------------------------------{ Welcome to Tactical chaos }------------------------------- " + Style.ANSI_RESET);
        System.out.println(Style.ANSI_YELLOW + "We hope you enjoy the game\n\n" + Style.ANSI_RESET);
        System.out.println("First of all: Game Rules: ");
        int size = 0;
        do {
            try {
                System.out.println("What is the arena size ( Square ):");
                size = scnr.nextInt();
                if (size <= 0) {
                    System.out.println("Invalid value");
                    continue;
                }
                exit = true;
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input!\n" + ex);
                scnr.nextLine();
            }
        } while (!exit);
        System.out.println("Exit");
        exit = false;
        this.arenaHeight = size;
        this.arenaWidth  = size;

        for (int i=0;i<size;i++){
            for (int j=0;j<size;j++){
                arenaItems[i][j] = new ArrayList<Item>();
                arenaItems[i][j].add(existingItems.get((int)Math.floor(Math.random()*7)));
                arenaItems[i][j].add(existingItems.get((int)Math.floor(Math.random()*7)));
                arenaItems[i][j].add(existingItems.get((int)Math.floor(Math.random()*7)));
                arenaTypes[i][j] = types[(int)Math.floor(Math.random()*4)];
            }
        }
        int maxHeroes = 0;
        do {
            try {
                System.out.println("What is the maximum number of champions that every player can have: ");
                maxHeroes = scnr.nextInt();
                if (maxHeroes <= 0) {
                    System.out.println("Invalid value");
                    continue;
                }
                exit = true;
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input!\n" + ex);
                scnr.nextLine();
            }
        } while (!exit);
        exit = false;
        this.maxHeroes = maxHeroes;
        int maxRounds = 0;
        do {
            try {
                System.out.println("What is the maximum number of rounds ( Greater than 10 ) : ");
                maxRounds = scnr.nextInt();
                if (maxRounds <= 10) {
                    System.out.println("Invalid value");
                    continue;
                }
                exit = true;
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input!\n" + ex);
                scnr.nextLine();
            }
        } while (!exit);
        exit = false;
        this.maxRounds = maxRounds;
        int maxChanges = 0;
        do {
            try {
                System.out.println("What is the maximum number of changes that every player can do in each round: ");
                maxChanges = scnr.nextInt();
                if (maxChanges <= 0) {
                    System.out.println("Invalid value");
                    continue;
                }
                exit = true;
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input!\n" + ex);
                scnr.nextLine();
            }
        } while (!exit);
        exit = false;
        numberOfChosenActions = 0;
        do {
            try {
                System.out.println("What is the number of Actions that every player can do in each round: ");
                numberOfChosenActions = scnr.nextInt();
                if (numberOfChosenActions <= 0) {
                    System.out.println("Invalid value");
                    continue;
                }
                exit = true;
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input!\n" + ex);
                scnr.nextLine();
            }
        } while (!exit);
        exit = false;
        this.maxChanges = maxChanges;
        char answer = 'x';
        do {
            try {
                System.out.println("Do You Want To Exist Specific Groups In The Game ? Y/N");
                answer = scnr.next().charAt(0);
                if (answer != 'y' && answer != 'Y' && answer != 'n' && answer != 'N') {
                    System.out.println("Invalid value");
                    continue;
                }
                exit = true;
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input!\n" + ex);
                scnr.nextLine();
            }
        } while (!exit);
        exit = false;

        if (Character.toLowerCase(answer)=='y'){
            int number = 0;
            do {
                try {
                    System.out.println("What is the groups that you want to be exist in the game: ");
                    System.out.println("Number of groups");
                    number = scnr.nextInt();
                    if (number < 3) {
                        System.out.println("Invalid value");
                        continue;
                    }
                    exit = true;
                } catch (InputMismatchException ex) {
                    System.out.println("Invalid input!\n" + ex);
                    scnr.nextLine();
                }
            } while (!exit);
            exit = false;
            if (number<=1)
                System.out.println("Invalid Number Groups");
            for (int i = 0; i < number; i++) {
                System.out.println("group #" + (i + 1) + " : ");
                String name = scnr.next();
                Group group = MatchName.matchGroupByName(name);
                if (group==null){
                    System.out.println("Invalid Group Name, There's No Kind Of Group Like That In This Game");
                }
                else this.existingGroups.add(group);
            }
        }
        else{
            this.existingGroups = null;
        }
        this.isRunning = true;

        do {
            try {
                System.out.print("Number Of Players: ");
                this.playersNumber = scnr.nextInt();
                if (this.playersNumber < 2) {
                    System.out.println("Invalid value");
                    continue;
                }
                exit = true;
            } catch (InputMismatchException ex) {
                System.out.println("Invalid input!\n" + ex);
                scnr.nextLine();
            }
        } while (!exit);
        exit = false;
        for (int i = 0; i < playersNumber; i++) {
            String name = "";
            do {
                try {
                    System.out.print("Player #" + (i + 1) + " name: ");
                    name = scnr.next();
                    exit = true;
                } catch (InputMismatchException ex) {
                    System.out.println("Invalid input!\n" + ex);
                    scnr.nextLine();
                }
            } while (!exit);
            exit = false;

            int choice = 0;
            do {
                try {
                    System.out.println("This player is:");
                    System.out.println("1)- Console.");
                    System.out.println("2)- Auto(Bot).");
                    choice = scnr.nextInt();
                    if (choice != 1 && choice != 2) {
                        System.out.println("Invalid value");
                        continue;
                    }
                    exit = true;
                } catch (InputMismatchException ex) {
                    System.out.println("Invalid input!\n" + ex);
                    scnr.nextLine();
                }
            } while (!exit);
            exit = false;

            if (choice == 1)
                this.players.add(new ConsolePlayer(i, name));
            else
                this.players.add(new AutoPlayer(i, name));
            System.out.println();
        }
        System.out.println("We done with the rules...\nSo let's get started");
        roundIndex = 1;
        while (isRunning){
            System.out.println("------------(Round #" + (roundIndex) + ")------------");
            if (roundIndex>=11 && (Game.getPlayers().size()<=1 || checkForLonely())){
                System.out.println("Game Ended");
                return ;
            }
            boolean isPrimaryRound;
            if (roundIndex <= 9)
                isPrimaryRound = true;
            else
                isPrimaryRound = false;
            Round round = new Round(this.getPlayers(), isPrimaryRound);
            round.readPlans();
            this.rounds.add(round);
            if (!isPrimaryRound){
                for (int i=0;i<getPlayers().size();){
                    if (getPlayers().get(i).getHeroes().size()<=0){
                        System.out.println("Player: " + getPlayers().get(i).getName() + " Is Dead");
                        getPlayers().remove(i);
                    }else i++;
                }
                int runningPlayers = 0;
                for (int i=0;i<getPlayers().size();i++) {
                    if (getPlayers().get(i).getHeroes().size() != 0){
                        runningPlayers++;
                    }else {
                        System.out.println("Player Dead: " + getPlayers().get(i).getName());
                    }
                }
                if (runningPlayers == 1)
                    isRunning = false;
            }
            if (roundIndex == this.maxRounds && isRunning){
                System.out.println("Game over");
                file.close();
                AfterLife.start();
//                System.out.println("If You want to display all the game press Y/N");
//                char choice;
//                choice = scnr.next().charAt(0);
//                if (Character.toLowerCase(choice)=='y'){
//                    InformationList.displayArenaInformation(' ');
//                }else {
//                    System.out.println("Which round you want to reply?");
//                    choice = scnr.next().charAt(0);
//                    InformationList.displayArenaInformation(choice);
//                }
                break;
            }
            roundIndex++;
        }
        if (!isRunning){
            file.close();
            AfterLife.start();
            for (Player player:Game.getPlayers()){
                if (player.getHeroes().size() != 0){
                   // System.out.println("Congrats " + player.getName() + ", You Win the game.");
                    file.close();
                    AfterLife.start();
//                    ScoreBoard.writeScoreBoard(player,true);
//                    System.out.println("If You want to display all the game Y/N");
//                    char choice;
//                    choice = scnr.next().charAt(0);
//                    if (Character.toLowerCase(choice)=='y'){
//                        InformationList.displayArenaInformation(' ');
//                    }else {
//                        System.out.println("Which round you want to reply?");
//                        choice = scnr.next().charAt(0);
//                        InformationList.displayArenaInformation(choice);
//                    }
                }
            }
        }
    }
}