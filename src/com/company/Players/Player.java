package com.company.Players;
import com.company.*;

import com.company.Game.Game;
import com.company.Hero;
import com.company.Style.Style;
import com.company.groups.Sorcerer;
import com.company.items.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;
public abstract class Player {
    protected int ID;
    protected int realHeroesSize = 0;
    protected String name;
    protected boolean[][] explore = new boolean[Game.arenaWidth][Game.arenaHeight];
    protected Bench Bench = new Bench();
    protected ArrayList<Hero> heroes = new ArrayList<>();
    protected int money;
    protected Store store = new Store();
    protected int extraHeroes = 0;
    protected int numberHeroesInTheFirstLevel  = 0;
    protected int numberHeroesInTheSecondLevel = 0;
    protected int numberHeroesInTheThirdLevel  = 0;
    protected ArrayList<Integer> groupsStatus = new ArrayList<>();
    protected Boolean piratesMoney = false;
    public synchronized static boolean checkForLonely(){
        if (Game.getPlayers().size()<=1){
            System.out.println("Game Ended");
            try{
                Game.file.close();
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
            try{
                Game.file.close();
            }catch (Exception ex){

            }
        }catch (Exception ex){

        }
        return true;
    }
    Player(){
        for (int i = 0; i <= 19; i++) {
            this.groupsStatus.add(0);
        }
        for (int i = 0; i < Game.arenaHeight; i++) {
            for (int j = 0; j < Game.arenaWidth; j++) {
                this.explore[i][j] = false;
            }
        }
    }

    public int getExtraHeroes() {
        return extraHeroes;
    }
    public void setExtraHeroes(int extraHeroes) {
        this.extraHeroes = extraHeroes;
    }
    public Boolean getPiratesMoney() {
        return piratesMoney;
    }
    public ArrayList<Integer> getGroupsStatus() {
        return groupsStatus;
    }
    public void setPiratesMoney(Boolean piratesMoney) {
        this.piratesMoney = piratesMoney;
    }
    public int getMoney() {return money;}
    public void setMoney(int money) {
        this.money = money;
    }
    public ArrayList<Hero> getHeroes() {
        return heroes;
    }
    public boolean[][] getExplore() {
        return explore;
    }
    public Player buyAHero(){
        if (this.realHeroesSize < Game.maxHeroes + this.extraHeroes){
            if (store.startShopping(this)){
                System.out.println(this.name + " Buys " + this.getHeroes().get(this.getHeroes().size()-1).getAttr().getName());
                this.realHeroesSize++;
                this.checkHeroLevelty(this.getHeroes().get(this.getHeroes().size()-1));
            }
        }
        return this;
    }
    public synchronized void printArena(ArrayList<Player> players){
        if (Game.roundIndex>=11 && (Game.getPlayers().size()<=1 || checkForLonely())){
            System.out.println("Game Ended");
            return ;
        }
        for (int i = 0; i < Game.arenaHeight; i++) {
            for (int j = 0; j < Game.arenaWidth; j++) {
                explore[i][j] = false;
            }
        }
        for (Hero hero:heroes) {
            if (hero==null)continue;
            float heroVisionScope = hero.getAttr().getVisionScope();
            float leftLimit, rightLimit, upLimit, downLimit;
            if (hero.getAttr().getPositionX() - heroVisionScope > 0)
                leftLimit = hero.getAttr().getPositionX() - heroVisionScope;
            else leftLimit = 0;

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
                        explore[i][j] = true;
                }
            }
        }
        char symbol;
        if (Game.roundIndex>=11 && (Game.getPlayers().size()<=1 || checkForLonely())){
            System.out.println("Game Ended");
            return ;
        }
        for (int j = 0; j < Game.arenaHeight; j++) {
            for (int i = 0; i < Game.arenaWidth; i++) {
                symbol = '#';
                for (Hero hero:heroes) {
                    if (hero!=null && hero.getAttr().getPositionX() == j && hero.getAttr().getPositionY() == i){
                        symbol = '*';
                    }
                }
                if (explore[i][j]){
                    for (int k = 0; k < players.size(); k++) {
                        if (players.get(k) == this || players.get(k)==null)
                            continue;
                        for (Hero hero:players.get(k).getHeroes()) {
                            if (hero!=null && hero.getAttr().getPositionX() == j && hero.getAttr().getPositionY() == i){
                                    if (symbol == '*')
                                        symbol = 'C';
                                    else
                                        symbol = 'E';
                            }
                        }
                    }
                }
                System.out.print(" " + symbol + " ");
                }
            System.out.println();
            }
        }
    public com.company.Bench getBench() {
        return Bench;
    }
    public void checkHeroLevelty(Hero hero){
        int numCounter = 0;
        numberHeroesInTheFirstLevel = numberHeroesInTheSecondLevel = numberHeroesInTheThirdLevel = 0;
        for (int i=0;i<this.getHeroes().size();i++) {
            if (this.getHeroes().get(i).getAttr().getHeroLevel()==1 && this.getHeroes().get(i).getAttr().getName().equals(hero.getAttr().getName())){
                this.numberHeroesInTheFirstLevel++;
            }
            else if (this.getHeroes().get(i).getAttr().getHeroLevel()==2 && this.getHeroes().get(i).getAttr().getName().equals(hero.getAttr().getName())){
                this.numberHeroesInTheSecondLevel++;
            }else if (this.getHeroes().get(i).getAttr().getHeroLevel()==3 && this.getHeroes().get(i).getAttr().getName().equals(hero.getAttr().getName())){
                this.numberHeroesInTheThirdLevel++;
            }
        }
        System.out.println(numberHeroesInTheFirstLevel + " " + numberHeroesInTheSecondLevel + " " + numberHeroesInTheThirdLevel);
        while (this.numberHeroesInTheFirstLevel>=3){
            numCounter=0;
            for (int i=0;i<this.getHeroes().size();){
                if (this.getHeroes().get(i).getAttr().getHeroLevel()==1 && hero.getAttr().getName().equals(this.getHeroes().get(i).getAttr().getName())){
                    this.getHeroes().get(i).getAttr().killHero();
                    this.getHeroes().remove(this.getHeroes().indexOf(this.getHeroes().get(i)));
                    this.numberHeroesInTheFirstLevel--;
                    numCounter++;
                    System.out.println("Number Counter IS: " + numCounter);
                }else i++;
                if (numCounter==3){
                    ReadHero readHero = new ReadHero();
                    Hero advancedHero = new Hero(readHero.getHeroFromID(hero.getAttr().getID()));
                    advancedHero.getAttr().setHeroLevel(2);
                    advancedHero.getAttr().incrementDamage(advancedHero.getAttr().getPowerAttack()*(10/100));
                    advancedHero.getAttr().incrementArmor(advancedHero.getAttr().getArmor()*(20/100));
                    advancedHero.getAttr().incrementMagicRes(advancedHero.getAttr().getMagicResistance()*(20/100));
                    this.getHeroes().add(advancedHero);
                    System.out.println(advancedHero.getAttr().getName() + " | " + advancedHero.getAttr().getHeroLevel());
                    this.numberHeroesInTheSecondLevel++;
                    numCounter-=3;
                }
            }
        }
        while (this.numberHeroesInTheSecondLevel>=3){
            numCounter=0;
            for (int i=0;i<this.getHeroes().size();){
                if (this.getHeroes().get(i).getAttr().getHeroLevel()==2 && hero.getAttr().getName().equals(this.getHeroes().get(i).getAttr().getName())){
                    this.getHeroes().get(i).getAttr().killHero();
                    this.getHeroes().remove(this.getHeroes().get(i));
                    this.numberHeroesInTheSecondLevel--;
                    numCounter++;
                }else i++;
                if (numCounter==3){
                    ReadHero readHero = new ReadHero();
                    Hero advancedHero = new Hero(readHero.getHeroFromID(hero.getAttr().getID()));
                    advancedHero.getAttr().setHeroLevel(3);
                    advancedHero.getAttr().incrementDamage(advancedHero.getAttr().getPowerAttack()*15/100);
                    advancedHero.getAttr().incrementArmor(advancedHero.getAttr().getArmor()*25/100);
                    advancedHero.getAttr().incrementMagicRes(advancedHero.getAttr().getMagicResistance()*25/100);
                    this.getHeroes().add(advancedHero);
                    this.numberHeroesInTheThirdLevel++;
                    numCounter-=3;
                }
            }
        }
                for (int i=0;i<this.getHeroes().size();i++){
                    System.out.println(this.getHeroes().get(i).getAttr().getName()  + " || " + this.getHeroes().get(i).getAttr().getHeroLevel());
        }
    }
    public boolean checkItemGroup(Hero hero,int choice){
        System.out.println(((Item)Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].get(choice)).getName() + " And The Wearer Is Also "  + " Added Successfully");
        hero.addExistingItems(((Item)Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].get(choice)));
        ((Item)Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].get(choice)).doItemAbility(hero);
        String groupRequiredName = "";
        if (((Item)Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].get(choice)).getName().equals("MagicHat")){
            groupRequiredName = "Sorcerer";
        }else if (((Item)Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].get(choice)).getName().equals("WarriorGloves")) {
            groupRequiredName = "BladeMaster";
        }else if (((Item)Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].get(choice)).getName().equals("KnightArmor")) {
            groupRequiredName = "Knight";
        }else if (((Item)Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].get(choice)).getName().equals("AngryCloak")) {
            groupRequiredName = "Yordle";
        }else if (((Item)Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].get(choice)).getName().equals("NightShift")) {
            groupRequiredName = "Assassin";
        }else if (((Item)Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].get(choice)).getName().equals("VoidHit")) {
            groupRequiredName = "Void";
        }else if (((Item)Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].get(choice)).getName().equals("UniverseCore")) {
            groupRequiredName = "Elementalist";
        }else {
            System.out.println(groupRequiredName);
            System.out.println("Empty Area");
        }

        boolean isExist = false;
        for (int i=0;i<hero.getAttr().getGroupTypes().size();i++){
                if (hero.getAttr().getGroupTypes().get(i).getName().equals(groupRequiredName)){
                    System.out.println("This Group is already exist");
                    isExist = true;
                    break;
                }
        }
        if (!isExist){
            hero.getAttr().getGroupTypes().add(MatchName.matchGroupByName(groupRequiredName));
            hero.getAttr().getGroupsLastStatus().add(1);
            Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].remove(choice);
        }else {
            Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].remove(choice);
        }

        System.out.println("Hero Now Have Groups: ");
        for (int i=0;i<hero.getAttr().getGroupTypes().size();i++){
            System.out.println( i+1 + "- " + hero.getAttr().getGroupTypes().get(i).getName());
        }
        return true;
    }
    public synchronized void checkForItemExistinsy(Hero hero){
        Scanner scanner = new Scanner(System.in);
        if (Game.roundIndex>=11 && (Game.getPlayers().size()<=1 || checkForLonely())){
            System.out.println("Game Ended");
            return ;
        }
        int choice;
        System.out.println(hero.getAttr().getPositionX() + " " + hero.getAttr().getPositionY());
        if (!Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].isEmpty()){
            if (this instanceof ConsolePlayer){
                System.out.println(Style.ANSI_BLUE + "Which Item You Want To get From The Sqaure? Type The Index");
                int k = 0;
                for (k = 0;k<Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].size();k++){
                    System.out.println( k+1 + "- " + ((Item)Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].get(k)).getName());
                }
                choice = scanner.nextInt();
                if (choice>=1 && choice<=k){
                    if (hero.getAttr().getExistingItems().size()>=3){
                        System.out.println("You have have enough items to catch !" + Style.ANSI_RESET);
                    }else {
                        checkItemGroup(hero,choice-1);
                    }
                }else {
                    System.out.println("No Item Selected" + Style.ANSI_RESET);
                }
            }
            else {
                int a = (int) Math.floor(Math.random()*1 + 1);
                if (a==1){
                    if (hero.getAttr().getExistingItems().size()>=3){
                        System.out.println("You have have enough items to catch !");
                    }else {
                        int k = (int)Math.floor(Math.random()*Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].size());
//                        System.out.println("Boot has selected " + ((Item)Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].get(k)).getName()+ " as an item");
//                        hero.addExistingItems(((Item)Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].get(k)));
//                        ((Item)Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].get(k)).doItemAbility(hero);
                        checkItemGroup(hero,k);
                    }
                    System.out.println(" Added Successfully");

//                    Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].remove(k);
                }else {
                    System.out.println("Boot Refused to catch the item");
                }
            }
        }else {
            System.out.println("There is no item in your place to catch !");
        }
        Game.printArenaItems();
    }
    public void sellItem(Hero hero){
        Scanner scanner = new Scanner(System.in);
        String Item;
        if (this instanceof ConsolePlayer){
            System.out.println( Style.ANSI_BLUE + "Which item you want to sell");
                    for (int j=0;j<hero.getAttr().getExistingItems().size();j++){
                        System.out.println("Item: " + hero.getAttr().getExistingItems().get(j).getName());
                    }
            System.out.println("Item: ");
            Item = scanner.next();
            if (hero.getAttr().getExistingItems().size()>0){
                for (int i=0;i<hero.getAttr().getExistingItems().size();i++){
                    if (hero.getAttr().getExistingItems().get(i).getName().equals(Item)){
                        //for group
                        String groupRequiredName = "";
                        if (((Item)Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].get(i)).getName().equals("MagicHat")){
                            groupRequiredName = "Sorcerer";
                        }else if (((Item)Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].get(i)).getName().equals("WarriorGloves")) {
                            groupRequiredName = "BladeMaster";
                        }else if (((Item)Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].get(i)).getName().equals("KnightArmor")) {
                            groupRequiredName = "Knight";
                        }else if (((Item)Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].get(i)).getName().equals("AngryCloak")) {
                            groupRequiredName = "Yordle";
                        }else if (((Item)Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].get(i)).getName().equals("NightShift")) {
                            groupRequiredName = "Assassin";
                        }else if (((Item)Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].get(i)).getName().equals("VoidHit")) {
                            groupRequiredName = "Void";
                        }else if (((Item)Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].get(i)).getName().equals("UniverseCore")) {
                            groupRequiredName = "Elementalist";
                        }
                        if (hero.getAttr().getGroupTypes().size()>=3){
                            for (int i1=0;i1<hero.getAttr().getGroupTypes().size();i1++){
                                if (hero.getAttr().getGroupTypes().get(i1).getName().equals(groupRequiredName)){
                                    hero.getAttr().getGroupTypes().remove(i1);
                                    System.out.println("Group Disabled Successfully");
                                    break;
                                }
                            }
                        }
                        //for item
                        Item itemObject = hero.getAttr().getExistingItems().get(i);
                        hero.getAttr().getExistingItems().get(i).unDoItemAbility(hero);
                        hero.getAttr().getExistingItems().remove(hero.getAttr().getExistingItems().get(i));
                        this.setMoney(this.getMoney() + 2);
                        Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].add(itemObject);
                        System.out.println("The Item has been now in the ground in place: <" + hero.getAttr().getPositionX() + "," + hero.getAttr().getPositionY() + ">");
                        System.out.println("Has been sell successfully" + Style.ANSI_RESET);
                        return ;
                    }
                }
            }else {
                System.out.println("You Dont have any item !" + Style.ANSI_RESET);
            }
        }else {
            if (hero.getAttr().getExistingItems().size()>0){
                            com.company.items.Item itemObject = hero.getAttr().getExistingItems().get(0);
                            hero.getAttr().getExistingItems().remove(0);
                            this.setMoney(this.getMoney() + 2);
                            Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].add(itemObject);
                            System.out.println("The Item has been put in place <" + hero.getAttr().getPositionX() + "," + hero.getAttr().getPositionY() + ">");
                            System.out.println("Has been sell successfully");
            }else {
                System.out.println("Boot player does not have any items");
            }
        }
    }
    public int getID() {
        return ID;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}