package com.company.Game;
import com.company.*;
import com.company.Players.AutoPlayer;
import com.company.Players.ConsolePlayer;
import com.company.Players.Player;
import com.company.Style.Style;
import com.company.documentation.GameStatePresent;
import com.company.documentation.ScoreBoard;
import com.company.groups.Group;
import com.company.items.Item;
import com.company.squareType.Grass;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class SecondaryPlan extends com.company.Game.Plan {
    public int frequentHeroes(int ID) {
        int frequent = 0;
        for (int i = 0; i < this.player.getHeroes().size(); i++) {
            if (this.player.getHeroes().get(i).getAttr().getID() == ID) frequent++;
        }
        return frequent;
    }
    public SecondaryPlan(Player player, ArrayList<Player> players) throws FileNotFoundException {
        super(player, players);
    }
    public synchronized static boolean checkForLonely(){
        if (Game.getPlayers().size()<=1){
            System.out.println("Game Ended");
            try{
                Game.file.close();
                AfterLife.start();
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
            Game.file.close();
            AfterLife.start();
        }catch (Exception ex){

        }
        return true;
    }
    @Override
    public  synchronized void getPlan() {
        Scanner scnr = new Scanner(System.in);
        for (int i=0;i<this.player.getHeroes().size();i++){
            this.player.getHeroes().get(i).getAttr().displayHeroInfo();
        }
        boolean exit = false;
        String answer = "x";
        System.out.println(this.player.getName() + ": it is your turn.");
        System.out.println("Your Arena: ");
        if (Game.roundIndex>=11 && (Game.getPlayers().size()<=1 || checkForLonely())){
            System.out.println("Game Ended");
            return ;
        }
        player.printArena(players);
        // Start get place info
        if (player instanceof ConsolePlayer) {
            System.out.println( Style.ANSI_BLUE + "Do you want to get a place information ? <y/n>");
            answer = scnr.next();
            if (answer.equals("y") || answer .equals("Y")) {
                boolean canSee = false;
                for (int i = 0; i < Game.arenaHeight; i++) {
                    for (int j = 0; j < Game.arenaWidth; j++) {
                        player.getExplore()[i][j] = false;
                    }
                }
                for (Hero hero : player.getHeroes()) {
                    float heroVisionScope = hero.getAttr().getVisionScope();
                    float leftLimit, rightLimit, upLimit, downLimit;

                    if (hero.getAttr().getPositionX() - heroVisionScope > 0)
                        leftLimit = hero.getAttr().getPositionX() - heroVisionScope;
                    else
                        leftLimit = 0;

                    if (hero.getAttr().getPositionY() - heroVisionScope > 0)
                        upLimit = hero.getAttr().getPositionX() - heroVisionScope;
                    else
                        upLimit = 0;

                    if (hero.getAttr().getPositionX() + heroVisionScope < Game.arenaWidth)
                        rightLimit = hero.getAttr().getPositionX() + heroVisionScope;
                    else
                        rightLimit = Game.arenaWidth - 1;

                    if (hero.getAttr().getPositionY() + heroVisionScope < Game.arenaHeight)
                        downLimit = hero.getAttr().getPositionY() + heroVisionScope;
                    else
                        downLimit = Game.arenaHeight - 1;
                    for (int i = 0; i < Game.arenaHeight; i++) {
                        for (int j = 0; j < Game.arenaWidth; j++) {
                            if (i >= leftLimit && i <= rightLimit && j >= upLimit && j <= downLimit)
                                player.getExplore()[i][j] = true;
                        }
                    }
                    int x = -1, y = -1;
                    while (!canSee) {
                        System.out.println("Enter the place : <x/y>");
                        x = scnr.nextInt();
                        y = scnr.nextInt();
                        if (player.getExplore()[x][y])
                            canSee = true;
                        else
                            System.out.println("You can not see this place because it out of your vision range.");
                    }
                    if (canSee) {
                        for (int i = 0; i < players.size(); i++) {
                            for (Hero hero1 : players.get(i).getHeroes()) {
                                if (hero1.getAttr().getPositionX() == x && hero1.getAttr().getPositionY() == y) {
                                    hero1.getAttr().displayHeroInfo();
                                    System.out.println("_______________________________________________");
                                }
                            }
                        }
                        if (Game.arenaItems[x][y]!=null){
                            for (int k=0;k<Game.arenaItems[x][y].size();k++){
                                System.out.println("Item In This Square: " + ((Item)Game.arenaItems[x][y].get(k)).getName());
                            }

                        }else {
                            System.out.println("this square has no item");
                        }
                        System.out.println("Square Type: " + Game.arenaTypes[x][y]);
                    }
                }
            }
            if (player.getBench().getTempHeros().size()>=1){
                System.out.println(Style.ANSI_RESET + "Your bench is:");
                for (Hero hero : player.getBench().getTempHeros()) {
                    hero.getAttr().displayHeroInfo();
                }
            }
        }
        // End get place info
        if (player instanceof ConsolePlayer) {
            do {
                try {
                    System.out.println(Style.ANSI_BLUE +"Do you want to buy a hero? <y/n>" + Style.ANSI_RESET);
                    answer = scnr.next();
                    if (!answer.equals("y") && !answer.equals("Y") && !answer.equals("n") && !answer.equals("N")) {
                        System.out.println("Invalid value");
                        continue;
                    }
                    exit = true;
                } catch (InputMismatchException ex) {
                    System.out.println("Invalid input!\n" + ex);
                    scnr.nextLine();
                }
            } while (!exit);
        }
        else if (player instanceof AutoPlayer) {
            if (Game.roundIndex>=11 && (Game.getPlayers().size()<=1 || checkForLonely())){
                System.out.println("Game Ended");
                return ;
            }
            System.out.println(this.player.getName() + " is playing now...");
            int randomNum = ThreadLocalRandom.current().nextInt(1, 3);
            if (randomNum == 1) answer = "y";
            if (randomNum == 2) answer = "n";
        }
        else {
            System.out.println("This is for GUI");
        }
        if (answer.equals("y") || answer.equals("Y")) {

            this.player.getBench().buyHero(player);
        }
        for (int replaceCounter = 0; replaceCounter < Game.maxChanges; replaceCounter++) {
            if (player instanceof ConsolePlayer) {
                do {
                    try {
                        System.out.println(Style.ANSI_BLUE + "Do you want to replace with the bench a hero? <y/n>" + Style.ANSI_RESET);
                        answer = scnr.next();
                        if (!answer.equals("y") && !answer.equals("Y") && !answer.equals("n") && !answer.equals("N")) {
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
            }
            else if (player instanceof AutoPlayer) {
                if (Game.roundIndex>=11 && (Game.getPlayers().size()<=1 || checkForLonely())){
                    System.out.println("Game Ended");
                    return ;
                }
                System.out.println(this.player.getName() + " is playing now...");
                int randomNum = ThreadLocalRandom.current().nextInt(1, 3);
                if (randomNum == 1) answer = "y";
                if (randomNum == 2) answer = "n";
            }
            else {
                System.out.println("This for GUI");
            }
            if (answer.equals("Y") || answer.equals("y")) {
                if (player.getBench().getTempHeros().size() == 0 || player.getHeroes().size() == 0){
                    if (player instanceof ConsolePlayer)
                        System.out.println("You don't have any hero in your bench or arena!");
                }
                else
                    this.player.getBench().replaceInTemporalStore(player);
               // Start Check for enabling / disabling the group ability
                for (int i = 0; i < this.player.getHeroes().size(); i++) {
                    for (int j = 0; j < this.player.getHeroes().get(i).getAttr().getGroupTypes().size(); j++) {
                        this.player.getHeroes().get(i).getAttr().getGroupsLastStatus()
                                        .set(j, this.player.getGroupsStatus()
                                        .get(this.player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID()));
                    }
                    for (int j = 1; j <= 19; j++) {
                        this.player.getGroupsStatus().set(i, 0);
                    }

                    for (Group group : this.player.getHeroes().get(i).getAttr().getGroupTypes()) {
                        this.player.getGroupsStatus()
                                .set(group.getID(), this.player.getGroupsStatus().get(group.getID()) + 1);
                    }
                }
                for (int i = 0; i < this.player.getHeroes().size(); i++) {
                    for (int j = 0; j < this.player.getHeroes().get(i).getAttr().getGroupTypes().size(); j++) {
                        if (this.player.getHeroes().get(i).getAttr().getGroupsLastStatus().get(j) == this.player.getGroupsStatus().get(this.player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID()))
                            continue;
                        if (this.player.getHeroes().get(i).getAttr().getGroupsLastStatus().get(j) < this.player.getGroupsStatus().get(this.player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID())) {
                            this.player.getHeroes().get(i).getAttr().getGroupTypes().get(j).enableAbility(this.player, this.players, this.player.getGroupsStatus().get(this.player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID()), this.player.getHeroes().get(i).getAttr().getGroupsLastStatus().get(j));
                        }
                        if (this.player.getHeroes().get(i).getAttr().getGroupsLastStatus().get(j) > this.player.getGroupsStatus().get(this.player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID())) {
                            this.player.getHeroes().get(i).getAttr().getGroupTypes().get(j).disableAbility(this.player, this.players, this.player.getGroupsStatus().get(this.player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID()), this.player.getHeroes().get(i).getAttr().getGroupsLastStatus().get(j));
                        }
                    }

                }
                // End Check for enabling / disabling the group ability
            }
        }
        if (player instanceof ConsolePlayer) {
            do {
                try {
                    System.out.println(Style.ANSI_BLUE +"Do you want to sell an item? <y/n>");
                    answer = scnr.next();
                    if (!answer.equals("y") && !answer.equals("Y") && !answer.equals("n") && !answer.equals("N"))  {
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
            if ((answer).equals("y")){
                System.out.println("Discuss which hero item you want to sell");
                for (int i=0;i<this.player.getHeroes().size();i++){
                    System.out.println("Hero ID: " + this.player.getHeroes().get(i).getAttr().getID() + " Name: " + this.player.getHeroes().get(i).getAttr().getName() + " Level: " + this.player.getHeroes().get(i).getAttr().getHeroLevel());
                    for (int j=0;j<this.player.getHeroes().get(i).getAttr().getExistingItems().size();j++){
                        System.out.println(" Item NO." + j + ": " + this.player.getHeroes().get(i).getAttr().getExistingItems().get(j).getName());
                    }
                }
                System.out.println("Hero ID: ");
                int choice = 0;
                int frequent = 0;
                choice = scnr.nextInt();
                Hero selledHero = new Hero();
                for (int i=0;i<this.player.getHeroes().size();i++){
                    if (this.player.getHeroes().get(i).getAttr().getID()==choice){
                        selledHero = this.player.getHeroes().get(i);
                        frequent++;
                    }
                }
                if (frequent>1){
                    frequent = 1;
                    System.out.println("Which one?");
                    int frequentNum;
                    frequentNum = scnr.nextInt();
                    for (int i=0;i<this.player.getHeroes().size();i++){
                        if (this.player.getHeroes().get(i).getAttr().getID()==choice){
                            System.out.println(frequent + " " + frequentNum);
                            if (frequent==frequentNum){
                                System.out.println(this.player.getHeroes().get(i).getAttr().getName());
                                System.out.println(this.player.getHeroes().get(i).getAttr().getExistingItems().get(0));
                                this.player.sellItem(this.player.getHeroes().get(i));
                                break;
                            }else frequent++;
                        }
                    }
                }else {
                    this.player.sellItem(selledHero);
                }
            }
            System.out.println(Style.ANSI_RESET + "");
        }
        if (player instanceof ConsolePlayer && Game.roundIndex>=9) {
            if (Game.roundIndex>=11 && (Game.getPlayers().size()<=1 || checkForLonely())){
                System.out.println("Game Ended");
                return ;
            }
            do {
                try {
                    System.out.println(Style.ANSI_BLUE +"Display place item? <y/n>");
                    answer = scnr.next();
                    if (!answer.equals("y") && !answer.equals("Y") && !answer.equals("n") && !answer.equals("N"))  {
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
            if ((answer).equals("y")){
                int x=0,y=0;
                System.out.println("Type Your <x,y>");
                x = scnr.nextInt();
                y = scnr.nextInt();
                if (x>=Game.arenaHeight || y>=Game.arenaHeight || x<0 || y<0){
                    System.out.println("Invalid Input");
                }else {
                    if (Game.arenaItems[x][y]!=null && Game.arenaItems[x][y].size()!=0){
                        for (int i=0;i<Game.arenaItems[x][y].size();i++){
                            System.out.println(i+1 + " " + ((Item)Game.arenaItems[x][y].get(i)).getName());
                        }
                    }else {
                        System.out.println("Empty Items");
                    }
                }
            }
        }
        if (player instanceof ConsolePlayer) {
            if (Game.getPlayers().size()<=1 || checkForLonely()){
                System.out.println("Game Ended");
                return ;
            }
            do {
                try {
                    System.out.println(Style.ANSI_RESET + "Do you want to sell a hero? <y/n>");
                    answer = scnr.next();
                    if (!answer.equals("y") && !answer.equals("y") && !answer.equals("n") && !answer.equals("N")) {
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
        }
        else if (player instanceof AutoPlayer) {
            if (Game.roundIndex>=11 && (Game.getPlayers().size()<=1 || checkForLonely())){
                System.out.println("Game Ended");
                return ;
            }
            System.out.println(this.player.getName() + " is playing now...");
            int randomNum = ThreadLocalRandom.current().nextInt(1, 3);
            if (randomNum == 1) answer = "y";
            if (randomNum == 2) answer = "n";
        }
        else {
            answer = scnr.next();
        }
        if (answer.equals("Y") || answer.equals("y")) {
            if (player.getBench().getTempHeros().size() == 0){
                if (player instanceof ConsolePlayer)
                    System.out.println(Style.ANSI_BLUE +"You don't have any hero in your bench!");
            }
            else
                this.player.getBench().sellAHero(player);
        }
        for (int i = 0; i < this.player.getHeroes().size(); i++) {
            if (this.player.getHeroes().get(i).getAttr().isBlockedDamage()) {
                this.player.getHeroes().get(i).getAttr().decrementNumberOfRoundBlockedDamage();
                if (!this.player.getHeroes().get(i).getAttr().isBlockedDamage())
                    this.player.getHeroes().get(i).getAttr().setImmune(false);
                this.player.getHeroes().get(i).getAttr().setImmune(true);
            }
            if (this.player.getHeroes().get(i).getAttr().isStunned()) {
                System.out.println("Sorry ! This Hero Is Stunned !");
                this.player.getHeroes().get(i).getAttr().decrementNumberOfRoundStuned();
                continue;
            }
            if (player instanceof ConsolePlayer) {
                System.out.println(
                        Style.ANSI_BLUE + "Order #" + (i + 1) + "for Hero: " + player.getHeroes().get(i).getAttr().getName() + ".");
                do {
                    try {
                        System.out.println("" +
                                "your plan:\n" +
                                "1)- Move a champion.\n" +
                                "2)- Attack an enemy.\n" +
                                "3)- Activate special ability for a champion.\n"+
                                "4- Collect Item In The Square.\n"+
                                "5- Save The Game.\n" +
                                "6- Load The Game.\n" +
                                Style.ANSI_RESET
                        );
                        this.orders[i] = scnr.nextInt();
                        if (this.orders[i] != 1 && this.orders[i] != 2 && this.orders[i] != 3 && this.orders[i] != 4 && this.orders[i] != 5 && this.orders[i] != 6) {
                            System.out.println("The available choices is (1) or (2) or (3) or 4 .");
                            continue;
                        }
                        exit = true;
                    } catch (InputMismatchException ex) {
                        System.out.println("Invalid input!\n" + ex);
                        scnr.nextLine();
                    }
                } while (!exit);
                exit = false;
            } else if (player instanceof AutoPlayer) {
                if (Game.roundIndex>=11 && (Game.getPlayers().size()<=1 || checkForLonely())){
                    System.out.println("Game Ended");
                    return ;
                }
                int randomNum = ThreadLocalRandom.current().nextInt(1, 4);
                this.orders[i] = randomNum;
            } else {
                System.out.println("This for GUI");
            }
        }
    }
    public void sleep(Player player){
//        for (int i=0;i<Round.multithreads.size();i++){
//            try{
//                if (!player.getName().equals(Round.multithreads.get(i).getPlayer().getName()))
//                    Round.multithreads.get(i).sleep(1000);
//            }catch (Exception ex){
//
//            }
//        }
    }
    @Override
    public void executePlans(Player player) throws IOException {
        sleep(player);
        Scanner scnr = new Scanner(System.in);
        if (Game.getPlayers().size() <= 1 || checkForLonely()) {
            System.out.println("Game Ended");
            return ;
        }
        boolean exit = false;
        for (int i = 0; i < this.player.getHeroes().size(); i++) {
            // Start execute basic move plan
            if (orders[i]==5){
                GameStatePresent objectIO = new GameStatePresent();
                objectIO.WriteObjectToFile();
                return ;
            }
            if (orders[i]==6){
                GameStatePresent objectIO = new GameStatePresent();
                objectIO.ReadObjectFromFile();
                return ;
            }
            if (orders[i] == 1) {
                if (Game.getPlayers().size()<=1 || checkForLonely()){
                    System.out.println("Game Ended");
                    return ;
                }
                int moveAnswer = 0, speedAnswer = 0;
                Hero newHero = new Hero();
                if (this.player instanceof ConsolePlayer) {
                    do {
                        try {
                            System.out.println(Style.ANSI_BLUE +
                                            "Move for Hero: " + player.getHeroes().get(i).getAttr().getName()
                                            + " which in place <" + player.getHeroes().get(i).getAttr().getPositionX()
                                            + ", " + player.getHeroes().get(i).getAttr().getPositionY() + ">:");
                            System.out.println("Chose the direction:\n" +
                                    "1)- Up.\n" +
                                    "2)- Down.\n" +
                                    "3)- Left.\n" +
                                    "4)- Right.\n" +
                                    "5)- Up-Right.\n" +
                                    "6)- Up-Left.\n" +
                                    "7)- Down-Right.\n" +
                                    "8)- Down-Left.\n" +Style.ANSI_RESET);
                            moveAnswer = scnr.nextInt();
                            if (moveAnswer <= 0 || moveAnswer >= 9) {
                                System.out.println("Invalid value!");
                                continue;
                            }
                            exit = true;
                        } catch (InputMismatchException ex) {
                            System.out.println("Invalid input!\n" + ex);
                            scnr.nextLine();
                        }
                    } while (!exit);
                    exit = false;
                    do {
                        try {
                            System.out.println(Style.ANSI_BLUE +"Chose the number of moves:" +Style.ANSI_RESET );
                            speedAnswer = scnr.nextInt();
                            if (speedAnswer <= 0) {
                                System.out.println("Invalid Value");
                                continue;
                            }
                            exit = true;
                        } catch (InputMismatchException ex) {
                            System.out.println("Invalid input!\n" + ex);
                            scnr.nextLine();
                        }
                    } while (!exit);
                    exit = false;
                }
                else if (this.player instanceof AutoPlayer) {
                    if (Game.getPlayers().size()<=1 || checkForLonely()){
                        System.out.println("Game Ended");
                        return ;
                    }
                    moveAnswer = (int) Math.floor(Math.random() * 8);
                    speedAnswer = (int) Math.floor(Math.random() * 8);
                    System.out.println("Speed Answer: " + speedAnswer + " Move Answer: " + moveAnswer + " X: " +this.player.getHeroes().get(i).getAttr().getPositionX() + " Y: " + this.player.getHeroes().get(i).getAttr().getPositionY() );
                }
                int oldX = this.player.getHeroes().get(i).getAttr().getPositionX();
                int oldY = this.player.getHeroes().get(i).getAttr().getPositionY();
                if (moveAnswer == 1) {
                    if (this.player.getHeroes().get(i).moveUp(speedAnswer)){
                        this.player.checkForItemExistinsy(this.player.getHeroes().get(i));
                    }
                } else if (moveAnswer == 2) {
                    if (this.player.getHeroes().get(i).moveDown(speedAnswer)){
                        this.player.checkForItemExistinsy(this.player.getHeroes().get(i));
                    }
                } else if (moveAnswer == 3) {
                    if (this.player.getHeroes().get(i).moveLeft(speedAnswer)){
                        this.player.checkForItemExistinsy(this.player.getHeroes().get(i));
                    }
                } else if (moveAnswer == 4) {
                    if (this.player.getHeroes().get(i).moveRight(speedAnswer)){
                        this.player.checkForItemExistinsy(this.player.getHeroes().get(i));
                    }
                } else if (moveAnswer == 5) {
                    if (this.player.getHeroes().get(i).moveUp_Right(speedAnswer)){
                        this.player.checkForItemExistinsy(this.player.getHeroes().get(i));
                    }
                } else if (moveAnswer == 6) {
                    if (this.player.getHeroes().get(i).moveUp_Left(speedAnswer)){
                        this.player.checkForItemExistinsy(this.player.getHeroes().get(i));
                    }
                } else if (moveAnswer == 7) {
                    if (this.player.getHeroes().get(i).moveDown_Right(speedAnswer)){
                        this.player.checkForItemExistinsy(this.player.getHeroes().get(i));
                    }
                } else if (moveAnswer == 8) {
                    if (this.player.getHeroes().get(i).moveDown_Left(speedAnswer)){
                        this.player.checkForItemExistinsy(this.player.getHeroes().get(i));
                    }
                }
                System.out.println("OLD MOVE X: " + oldX + " OLD MOVE Y: " + oldY);
                System.out.println("new MOVE X: " + this.player.getHeroes().get(i).getAttr().getPositionX() + " new MOVE Y: " + this.player.getHeroes().get(i).getAttr().getPositionY());
                    if (this.player.getHeroes().get(i).getAttr().getPositionX()==oldX && this.player.getHeroes().get(i).getAttr().getPositionY()==oldY){
                        System.out.println("You Stayed In your last pos");
                    }else {
                        if (Game.arenaTypes[oldX][oldY].equals("G") && Game.arenaTypes[this.player.getHeroes().get(i).getAttr().getPositionX()][this.player.getHeroes().get(i).getAttr().getPositionY()].equals("G")){
                            System.out.println("You Are In Grass Area So Your Vision Scope Will be Half");
                        }else {
                            if (Game.arenaTypes[oldX][oldY].equals("G") && !Game.arenaTypes[this.player.getHeroes().get(i).getAttr().getPositionX()][this.player.getHeroes().get(i).getAttr().getPositionY()].equals("G")){
                                Grass grass = new Grass();
                                grass.unDoSquareTypeEffect(this.player.getHeroes().get(i));
                            }else if (!Game.arenaTypes[oldX][oldY].equals("G") && Game.arenaTypes[this.player.getHeroes().get(i).getAttr().getPositionX()][this.player.getHeroes().get(i).getAttr().getPositionY()].equals("G")){
                                Grass grass = new Grass();
                                grass.doSquareTypeEffect(this.player.getHeroes().get(i));
                            }
                        }
                    }
            }
            // End execute basic move plan
            // Start execute basic attack plan
            if (orders[i] == 2) {
                if (Game.getPlayers().size()<=1 || checkForLonely()){
                    System.out.println("Game Ended");
                    return ;
                }
                int attackX = 0;
                int attackY = 0;
                boolean accept = false;
                System.out.println(
                        "Attack for Hero: " + player.getHeroes().get(i).getAttr().getName()
                        + " which in place <" + player.getHeroes().get(i).getAttr().getPositionX()
                        + ", " + player.getHeroes().get(i).getAttr().getPositionY() + ">:");
                if (player instanceof ConsolePlayer) {
                    do {
                        try {
                            System.out.println(Style.ANSI_BLUE +"what is place that you want to attack ( The destination between your hero and the target place <x y> )" + Style.ANSI_RESET);
                            attackX = scnr.nextInt();
                            attackY = scnr.nextInt();
                            if ( !(Math.abs(attackX - this.player.getHeroes().get(i).getAttr().getPositionX()) <= this.player.getHeroes().get(i).getAttr().getAttackScope() && Math.abs(attackY - this.player.getHeroes().get(i).getAttr().getPositionY()) <= this.player.getHeroes().get(i).getAttr().getAttackScope()) ) {
                                System.out.println("This place is out of your attack scope!");
                                continue;
                            }
                            exit = true;
                        } catch (InputMismatchException ex) {
                            System.out.println("Invalid input!\n" + ex);
                            scnr.nextLine();
                        }
                    } while (!exit);
                    exit = false;
                }
                if (player instanceof AutoPlayer) {
                    if (Game.getPlayers().size()<=1 || checkForLonely()){
                        System.out.println("Game Ended");
                        return ;
                    }
                    while (!accept) {
                        attackX = ThreadLocalRandom.current().nextInt(0, Game.arenaHeight);
                        attackY = ThreadLocalRandom.current().nextInt(0, Game.arenaWidth);
                        if (
                                Math.abs(attackX - this.player.getHeroes().get(i).getAttr().getPositionX()) <= this.player.getHeroes().get(i).getAttr().getAttackScope()
                                && Math.abs(attackY - this.player.getHeroes().get(i).getAttr().getPositionY()) <= this.player.getHeroes().get(i).getAttr().getAttackScope()
                        )
                            accept = true;
                    }
                }
                for (int j = 0; j < this.players.size(); j++) {
                    if (!this.players.get(j).getName().equals(this.player.getName())) {
                        for (int k = 0; k < this.getPlayers().get(j).getHeroes().size(); k++) {

                            if (
                                    this.getPlayers().get(j).getHeroes().get(k).getAttr().getPositionX() == attackX
                                    && this.getPlayers().get(j).getHeroes().get(k).getAttr().getPositionY() == attackY
                            ) {
                                Hero damagedHero;
                                damagedHero = DamageCalculator.getDamagedHero(this.player.getHeroes().get(i), this.getPlayers().get(j).getHeroes().get(k));
                                if (damagedHero!=null)
                                    this.getPlayers().get(j).getHeroes().set(k, damagedHero);
                            }
                        }
                    }
                }
                this.player.getHeroes().get(i).getAttr()
                        .setMana(this.player.getHeroes().get(i).getAttr().getMana() + 1 + this.player.getHeroes().get(i).getAttr().getDoubleMana());
            }
            //end execute basic attack plan
            // Start execute ability plan
            if (orders[i] == 3) {
                if (Game.getPlayers().size()<=1 || checkForLonely()){
                    System.out.println("Game Ended");
                    return ;
                }
               executeRound(player.getHeroes().get(i).getAttr().getID());
            }
            if (orders[i]==4){
                if (Game.getPlayers().size()<=1 || checkForLonely()){
                    System.out.println("Game Ended");
                    return ;
                }
                this.player.checkForItemExistinsy(player.getHeroes().get(i));
            }
            // end execute ability plan
        }
    }
    public void executeRound(int id) throws IOException {
        Scanner scnr = new Scanner(System.in);
        if (Game.getPlayers().size()<=1 || checkForLonely()){
            System.out.println("Game Ended");
            return ;
        }
        boolean exit = false;
        Hero tempHero;
        ReadHero readHero = new ReadHero();
        tempHero = new Hero(readHero.getHeroFromID(id));
        System.out.println(
                "Ability for Hero: " + tempHero.getAttr().getName()
                + " which in place <" + tempHero.getAttr().getPositionX() + ", " + tempHero.getAttr().getPositionY()
                + ">:");
        int frequent = 1;
        for (int j = 0; j < player.getHeroes().size(); j++) {
            if (Game.getPlayers().size()<=1 || checkForLonely()){
                System.out.println("Game Ended");
                return ;
            }
            System.out.println("ID: " + id + " ID " + player.getHeroes().get(j).getAttr().getID());
            if (player.getHeroes().get(j).getAttr().getID() == id) {
                //checking for frequent hero
                if (this.player.getHeroes().get(j).getAttr().getPreventedAbility()) {
                    System.out.println("Sorry You Are Prevented To Enable any ability in this round");
                        this.player.getHeroes().get(j).getAttr().decrementNumberOfPreventedAbility();
                    }
                    if (this.player instanceof AutoPlayer) {
                        if (frequentHeroes(player.getHeroes().get(j).getAttr().getID()) > 1) {
                            int x = frequentHeroes(player.getHeroes().get(j).getAttr().getID());
                            while (true) {
                                frequent = (int) Math.floor(Math.random() * x + 1);
                                if (frequent != 0) break;
                            }

                        }
                    }
                    else if (this.player instanceof ConsolePlayer) {
                        if (frequentHeroes(player.getHeroes().get(j).getAttr().getID()) > 1) {
                            do {
                                try {
                                    System.out.println(Style.ANSI_BLUE + "Which One You Want To Select?, Enter A Number" + Style.ANSI_RESET);
                                    frequent = scnr.nextInt();
                                    exit = true;
                                } catch (InputMismatchException ex) {
                                    System.out.println("Invalid input!\n" + ex);
                                    scnr.nextLine();
                                }
                            } while (!exit);
                        }
                    }
                    try {
                        if (Game.getPlayers().size()<=1 || checkForLonely()){
                            System.out.println("Game Ended");
                            return ;
                        }
                        player.getHeroes().get(j).getAttr().getAbility().doTheAbility(player.getName(), frequent);
                       // player.getHeroes().get(j).getAttr().displayHeroInfo();
                    } catch (Exception ex) {

                    } finally {
                        System.out.println("Ability Has Been Done Successfully");
                        if (Game.getPlayers().size() <= 1 || checkForLonely()) {
                            System.out.println("Game Ended");
                            return ;
                        }
                        System.out.println("YAAAAY");
                    }
                    break;
                }
            }
        }
    }