package com.company;
import com.company.Players.AutoPlayer;
import com.company.Players.ConsolePlayer;
import com.company.Players.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Bench extends Store {
    Scanner scanner = new Scanner(System.in);
    private ArrayList<Hero> tempHeros = new ArrayList<>();
    public void buyHero(Player player){
        boolean exit = false;
        Hero[] heros = new Hero[5];
        GroupFilter groupFilter = new GroupFilter();
        ReadHero read = new ReadHero();
        if (this.tempHeros.size()<=7){
            if (player instanceof AutoPlayer){
                boolean canBuy = false;
                while (!canBuy){
                    Hero hero = new Hero(groupFilter.filterGroupList(new ReadHero().getHerosList())[(int)Math.floor(Math.random()*5)]);
                    if (player.getMoney()>=hero.getAttr().getGoldPrice()){
                        hero.getAttr().setOwner(player.getName());
                        hero.getAttr().setHeroLevel(1);
                        tempHeros.add(hero);
                        System.out.println("Auto Player Buys Hero And Put him in the Bench");
                        canBuy = true;
                    }else {
                        if (player.getMoney()<=0)return ;
                        else {
                            canBuy = false;
                        }
                    }
                }
            }
            if (player instanceof ConsolePlayer) {
                boolean isExist = false;
                while (!isExist){
                    for (int i=0;i<5;i++){
                        heros[i] = new Hero(groupFilter.filterGroupList(new ReadHero().getHerosList())[i]);
                        System.out.println("ID: " + heros[i].getAttr().getID() + " Name: " + heros[i].getAttr().getName());
                    }
                    int choice = 0;
                    do {
                        try {
                            System.out.println("Discuss Which Hero You Want To Buy And Add Him in the Bench:");
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
                            tempHeros.add(hero);
                        }else {
                            if (player.getMoney()==0)return ;
                            else isExist = false;
                        }
                    }else {
                        System.out.println("This Hero Is not exist in this list");
                    }
                }
            }
        }else {
            System.out.println("Bench is full now");
        }
    }
    public ArrayList<Hero> getTempHeros() {
        return tempHeros;
    }
    public void replaceInTemporalStore(Player player){
        boolean exit = false;
        int choiceBench = 0, choiceArena = 0;
        boolean isExist = false;
        Hero arenaHero = new Hero();
        Hero benchHero = new Hero();
        if (player instanceof ConsolePlayer){
            while (!isExist) {
                for (int i = 0; i < player.getBench().getTempHeros().size(); i++) {
                    System.out.println("ID: " + player.getBench().getTempHeros().get(i).getAttr().getID() + " Hero: " + player.getBench().getTempHeros().get(i).getAttr().getName() + " Level: " + player.getBench().getTempHeros().get(i).getAttr().getHeroLevel());
                }

                do {
                    try {
                        System.out.println("Hero You Want To Replace in your Bench?");
                        choiceBench = scanner.nextInt();
                        if (choiceBench <= 0) {
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
                for (Hero hero : player.getBench().getTempHeros()) {
                    if (hero.getAttr().getID() == choiceBench) {
                        benchHero = hero;
                        isExist = true;
                    }
                }
            }
            isExist = false;
            for (int i=0;i<player.getHeroes().size();i++){
                System.out.println("ID: " + player.getHeroes().get(i).getAttr().getID() + " Hero: " + player.getHeroes().get(i).getAttr().getName() + " Level: " + player.getHeroes().get(i).getAttr().getHeroLevel());
            }

            do {
                try {
                    System.out.println("Hero You Want To Replace In The Arena?");
                    choiceArena = scanner.nextInt();
                    if (choiceArena <= 0) {
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
            while (!isExist){
                for (Hero hero: player.getHeroes()) {
                    if (hero.getAttr().getID() == choiceArena) {
                        arenaHero = hero;
                        isExist = true;
                    }
                }
            }
        }
        else if (player instanceof AutoPlayer){

            benchHero = player.getBench().getTempHeros().get(0);


            arenaHero = player.getHeroes().get(0);
        }
        else {
            System.out.println("This for GUI!");
            return;
        }
        player.getHeroes().remove(arenaHero);
        player.getHeroes().add(benchHero);
        player.getBench().getTempHeros().remove(benchHero);
        player.getBench().getTempHeros().add(arenaHero);
    }
    public void sellAHero(Player player){
        boolean exit = false;
        Hero benchHero = new Hero();
        int choice = 0;
        boolean isExist = false;
        if (player instanceof ConsolePlayer){
                for (int i=0;i<player.getBench().getTempHeros().size();i++){
                    System.out.println("ID: " + player.getBench().getTempHeros().get(i).getAttr().getID() + " Hero: " + player.getBench().getTempHeros().get(i).getAttr().getName() + " Level: " + player.getBench().getTempHeros().get(i).getAttr().getHeroLevel());
                }
                do {
                    try {
                        System.out.println("Hero You Want To Sell?");
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
                for (Hero hero: player.getBench().getTempHeros()) {
                    if (hero.getAttr().getID() == choice) {
                        benchHero = hero;
                        break;
                    }
                    else
                        System.out.println("This hero is not found in your bench");
                }
                for (Hero hero : player.getBench().getTempHeros()){
                    if (hero == benchHero){
                            player.setMoney(player.getMoney() + (int) (benchHero.getAttr().getGoldPrice() ));
                            player.getBench().getTempHeros().remove(benchHero);
                            System.out.println("You sell: " + benchHero.getAttr().getName() + "and earn: " + benchHero.getAttr().getGoldPrice());
                            break;
                    }
                }
        }
        else if (player instanceof AutoPlayer){
                player.setMoney(player.getMoney() + (int) (player.getBench().getTempHeros().get(0).getAttr().getGoldPrice()));
                player.getBench().getTempHeros().remove(0);


        }
        else {
            System.out.println("This for GUI");
            return;
        }
    }
}