package com.company.ability;

import com.company.Game.Game;
import com.company.Hero;

import java.util.Scanner;

public class KhazixAbility extends Ability {
    @Override
    public synchronized Object doTheAbility(String playerName,int heroFrequentNumber) {
        Scanner scanner = new Scanner(System.in);
        int frequent = 1;
        int xPos = 0, yPos = 0;
        float attackScope = 0;
        float attackerPowerAttack = 150;
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
            System.out.println("Which Enemy You Want To be Killed?");
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




        boolean isLonely = false;
        int xPosForEnemy = 0;
        int yPosForEnemy = 0;
        int indexI=0,indexJ=0;
        for (int i = 0; i < Game.getPlayers().size(); i++) {
            if (Game.getPlayers().get(i).getName().equals(playerNameConf)) {
                for (int j = 0; j < Game.getPlayers().get(i).getHeroes().size(); j++) {
                    if ((Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX() >= xPos - attackScope || Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX() <= xPos + attackScope) && (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY() >= yPos - attackScope || Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY() <= yPos + attackScope)) {
                        if (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getID() == heroIDConf){
                            xPosForEnemy = Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX();
                            yPosForEnemy = Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY();
                            indexI = i;
                            indexJ = j;
                            break;
                        }
                    }

                }
            }
        }

        int x = 0 ;

        for (int i = 0; i < Game.getPlayers().size(); i++) {
            //for just the enemy
            if (Game.getPlayers().get(i).getName().equals(playerNameConf)) {
                for (int j = 0; j < Game.getPlayers().get(i).getHeroes().size(); j++) {
                    if ((Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX() >= xPosForEnemy || Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX() <= xPosForEnemy) && (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY() >= yPosForEnemy || Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY() <= yPosForEnemy)) {
                        x++;
                    }
                }
            }
        }
        if (x<=1){
            isLonely = true;
            System.out.println("The Hero Is Dead");
            Game.getPlayers().get(indexI).getHeroes().get(indexJ).destroyHero();
            Game.getPlayers().get(indexI).getHeroes().remove(Game.getPlayers().get(indexI).getHeroes().get(indexJ));
        }else {
            System.out.println("This hero have ally arround him so you cant kill him");
        }
        return null;
    }
    public KhazixAbility(){
        this.abilityName = "KhazixAbility";
        this.heroID = 21;
    }
}
