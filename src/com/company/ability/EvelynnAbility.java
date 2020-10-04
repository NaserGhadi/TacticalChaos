package com.company.ability;

import com.company.Game.Game;
import com.company.Hero;

import java.util.Scanner;

public class EvelynnAbility extends Ability {
    @Override
    public synchronized Object doTheAbility(String playerName,int heroFrequentNumber) {
        Scanner scanner = new Scanner(System.in);
        int frequent = 1;
        int xPos = 0, yPos = 0;
        float attackScope = 0;
        for (int i = 0; i< Game.getPlayers().size(); i++){
            if (Game.getPlayers().get(i).getName().equals(playerName)){
                for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++){
                    if (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getID()==this.heroID){
                        if (frequent == heroFrequentNumber){
                            attackScope = Game.getPlayers().get(i).getHeroes().get(j).getAttr().getAttackScope();
                            xPos = Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX();
                            yPos = Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY();
                        }
                        else frequent++;
                    }
                }
            }
        }
        //start
        String playerNameConf = "";
        int heroIDConf = 0;
        if (checkIfConsolePlayer(playerName)){
            boolean neighbor = false;
            System.out.println("Disscuss Which Hero And Player You Want To Damage");
            for (int i=0;i<Game.getPlayers().size();i++){
                for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++){
                    if ((Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX()>=xPos-attackScope || Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX()<=xPos+30) && (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY()>=yPos-30 || Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY()<=yPos+attackScope)){
                        neighbor = true;
                        System.out.println("ID: " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getID() + "Hero Name " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getName() + "Player Name: " + Game.getPlayers().get(i).getName() + " Level: " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getHeroLevel());
                    }
                }
            }
            if (!neighbor) {
                System.out.println("You are alone in you attack scope");
                return null;
            }
            System.out.println("Player Name: ");
            playerNameConf = scanner.next();
            System.out.println("Hero ID: ");
            heroIDConf = scanner.nextInt();
        }else {
            if (Game.getPlayers().size()>=2){
                while (true){
                    playerNameConf = Game.getPlayers().get((int)Math.floor(Math.random()*Game.getPlayers().size())).getName();
                    System.out.println("Player Name Conf: " + playerNameConf);
                    if (!playerNameConf.equals(playerName)){
                        break;
                    }
                }
            }else {
                System.out.println("You are alone");
                return null;
            }
            for (int i=0;i<Game.getPlayers().size();i++){
                if (playerNameConf.equals(Game.getPlayers().get(i).getName())){
                    if (Game.getPlayers().get(i).getHeroes().size()>=2){
                        heroIDConf = Game.getPlayers().get(i).getHeroes().get((int)Math.floor(Math.random()*Game.getPlayers().get(i).getHeroes().size())).getAttr().getID();
                    }else {
                        heroIDConf = Game.getPlayers().get(i).getHeroes().get(0).getAttr().getID();
                    }
                }
            }
            System.out.println("The random player is : " + playerNameConf + " The Random Hero Is: " + heroIDConf);
        }

        for (int i = 0; i < Game.getPlayers().size(); i++) {
            //for just the enemy
            if (Game.getPlayers().get(i).getName().equals(playerNameConf)) {
                for (int j = 0; j < Game.getPlayers().get(i).getHeroes().size(); j++) {
                    if ((Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX() >= xPos - attackScope || Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX() <= xPos + attackScope) && (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY() >= yPos - attackScope || Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY() <= yPos + attackScope) && Game.getPlayers().get(i).getHeroes().get(j).getAttr().getID() == heroIDConf) {
                        if ((Game.getPlayers().get(i).getHeroes().get(j).getAttr().getHealth()*100)/Game.getPlayers().get(i).getHeroes().get(j).getAttr().getMaxHealth()<40){
                            Game.getPlayers().get(i).getHeroes().get(j).destroyHero();
                            Game.getPlayers().get(i).getHeroes().remove(Game.getPlayers().get(i).getHeroes().indexOf(Game.getPlayers().get(i).getHeroes().get(j)));
                            System.out.println("Hero Killed Successfully");
                        }
                    }
                }
            }
        }
        for (int i = 0; i < Game.getPlayers().size(); i++) {
            if (Game.getPlayers().get(i).getName().equals(playerName)) {
                for (int j = 0; j < Game.getPlayers().get(i).getHeroes().size(); j++) {
                    if (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getID()==this.heroID){
                        if (frequent == heroFrequentNumber) {
                            Game.getPlayers().get(i).getHeroes().get(j).moveDown(20);
                        }
                    }
                }
            }
        }
        if (Game.getPlayers().size()<=1 || checkForLonely()){
            System.out.println("Game Ended");
            return null;
        }
        System.out.println("Hero Moved Down");
        return null;
    }
    public EvelynnAbility(){
        this.abilityName = "EvelynnAbility";
        this.heroID = 10;
    }
}