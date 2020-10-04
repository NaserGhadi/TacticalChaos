package com.company;

import com.company.Game.Game;
import com.company.Players.AutoPlayer;
import com.company.Players.Player;
import com.company.Style.Style;
import com.company.items.Item;
import com.company.squareType.Grass;

import java.io.Serializable;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Store {
    private String[] existingGroups;
    private String[] items = new String[5];
    protected static ReadHero read;
    public Store(){}
    public static Hero.Attribute[] getChampionsList(){
        return read.getHerosList();
    }
    public Boolean startShopping(Player player){
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        Hero[] heros = new Hero[5];
        GroupFilter groupFilter = new GroupFilter();
        ReadHero read = new ReadHero();
        if (player instanceof AutoPlayer){
            Boolean canBuy = false;
            while (!canBuy){
                Hero hero = new Hero(groupFilter.filterGroupList(new ReadHero().getHerosList())[(int)Math.floor(Math.random()*5)]);
                if (hero.getAttr().getGoldPrice()<=player.getMoney()){
                    System.out.println("UUU");
                    hero.getAttr().setOwner(player.getName());
                    int x = ThreadLocalRandom.current().nextInt(1, Game.arenaHeight);
                    int y = ThreadLocalRandom.current().nextInt(1, Game.arenaWidth);
                    hero.getAttr().setPositionX(x);
                    hero.getAttr().setPositionY(y);
                    player.getHeroes().add(hero);
                    player.setMoney((int)(player.getMoney() - hero.getAttr().getGoldPrice()));
                    canBuy = true;
                    if (!Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].isEmpty()){
                        int choice = (int) Math.floor(Math.random()*Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].size());
                        Game.printArenaItems();
                        System.out.println(player.checkItemGroup(hero,choice));
//                        System.out.println("Hero Got " + ((Item) Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].get(choice)).getName());
//                        hero.addExistingItems(((Item)Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].get(choice)));
//                        Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].remove(choice);
                        Game.printArenaItems();
                    }else {
                        System.out.println("Empty Items!");
                    }
                }
                else {
                    if (player.getMoney()<=0)return false;
                    else {
                        canBuy = false;
                    }
                }
            }
            System.out.println("Auto Player Buys Hero");
            return true;
        }
        else {
            boolean isExist = false;
            while(!isExist){
                for (int i=0;i<5;i++){
                    heros[i] = new Hero(groupFilter.filterGroupList(new ReadHero().getHerosList())[i]);
                    System.out.println(Style.ANSI_BLUE + "ID: " + heros[i].getAttr().getID() + " Name: " + heros[i].getAttr().getName() + " Price: " + heros[i].getAttr().getGoldPrice());
                }
                int choice = 0;
                do {
                    try {
                        System.out.println("Discuss Which Hero You Want To Buy:");
                        System.out.println("Your Money is: " + player.getMoney());
                        choice = scanner.nextInt();
                        if (choice <= 0) {
                            System.out.println("Invalid value");
                            continue;
                        }
                        exit = true;
                    } catch (InputMismatchException ex) {
                        System.out.println("Invalid input!\n" + ex);
                        scanner.nextLine();
                    }
                } while (!exit);
                exit = false;
                Hero hero = new Hero(read.getHeroFromID(choice));
                isExist = false;
                for (int i=0;i<5;i++){
                    if (heros[i].getAttr().getID()==choice){isExist = true;break;}
                }
                if (isExist){
                    if (player.getMoney()>=hero.getAttr().getGoldPrice()){
                        hero.getAttr().setOwner(player.getName());
                        hero.getAttr().setHeroLevel(1);
                        int x = -1, y = -1;
                            do {
                                try {
                                    System.out.println("Where is the place of this hero <x y>:");
                                    x = scanner.nextInt();
                                    y = scanner.nextInt();
                                    if ( !( x >= 0 && y >= 0 && x <= (Game.arenaHeight - 1) && y <= (Game.arenaHeight - 1) ) ) {
                                        System.out.println("Invalid value");
                                        continue;
                                    }
                                    exit = true;
                                } catch (InputMismatchException ex) {
                                    System.out.println("Invalid input!\n" + ex);
                                    scanner.nextLine();
                                }
                            } while (!exit);
                            exit = false;
                        hero.getAttr().setPositionX(x);
                        hero.getAttr().setPositionY(y);
                        if (Game.arenaTypes[x][y].equals("T")){
                            boolean notInTerran = false;
                            while (notInTerran){

                            }
                        }
                        else if (Game.arenaTypes[x][y].equals("G")){
                            Grass grass = (Grass) new Grass().doSquareTypeEffect(hero);
                        }else if (Game.arenaTypes[x][y].equals("S")){
                            System.out.println("You Are In The Standard Area"+ Style.ANSI_RESET );
                        }
                        if (!Game.arenaItems[x][y].isEmpty()){
                            choice = 0;
                            System.out.println("Which Item You Want To Collect? Type Index"+ Style.ANSI_RESET );
                            for (int i=0;i<Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].size();i++){
                                System.out.println(i+1 + "- "+ ((Item)Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].get(i)).getName());
                            }
                            choice = scanner.nextInt();
                            player.checkItemGroup(hero,choice-1);
//                            System.out.println("Hero Got " + ((Item) Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].get(choice-1)).getName());
//                            hero.addExistingItems(((Item)Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].get(choice-1)));
//                            Game.arenaItems[hero.getAttr().getPositionX()][hero.getAttr().getPositionY()].remove(choice-1);
                            Game.printArenaItems();
                        }else {
                            System.out.println("Empty Items in this square!"+ Style.ANSI_RESET );
                        }
                        player.getHeroes().add(hero);
                        player.setMoney((int)(player.getMoney() - hero.getAttr().getGoldPrice()));
                    }
                    else {
                        if (player.getMoney()<=0){
                            System.out.println("You Don't Have Any Money to buy any hero"+ Style.ANSI_RESET );
                            return false;
                        }else {
                            System.out.println("You Don't Have Enough money to buy that hero"+ Style.ANSI_RESET );
                            isExist = false;
                        }
                    }
                }
                else System.out.println("This ID Is Not Exist In This List" + Style.ANSI_RESET );
            }
            return true;
        }
    }
}