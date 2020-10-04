package com.company.ability;

import com.company.Game.Game;
import com.company.Game.SecondaryPlan;
import com.company.Hero;

import java.util.Scanner;

public class AsheAbility extends Ability {
    @Override
    public synchronized Object doTheAbility(String playerName,int heroFrequentNumber) {
        Scanner scanner = new Scanner(System.in);
        int frequent = 1;
        int xPos = 0;
        int yPos = 0;
        float attackerPowerAttack = 200;
        for (int i=0;i< Game.getPlayers().size();i++){
            if (Game.getPlayers().get(i).getName().equals(playerName)){
                for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++){
                    if (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getID()==this.heroID){
                        if (frequent==heroFrequentNumber){
                            xPos = Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX();
                            yPos = Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY();
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().incrementSpeed(Game.getPlayers().get(i).getHeroes().get(j).getAttr().getSpeed()*(10/10));
                        }else {
                            frequent++;
                        }
                    }
                }
            }
        }

        System.out.println("Movement Speed incremented 10% successfully");
        if (SecondaryPlan.checkForLonely()){
            return null;
        }
        String playerNameConf = "";
        int choice = 0;
        if (checkIfConsolePlayer(playerName)){
            System.out.println("Disscuss Which Hero And Player You Want To Stun");
            for (int i=0;i<Game.getPlayers().size();i++){
                for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++){
                    System.out.println("ID: " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getID() + "Hero Name " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getName() + "Player Name: " + Game.getPlayers().get(i).getName() + " Level: " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getHeroLevel());
                }
            }
            System.out.println("Player Name: ");
            playerNameConf = scanner.next();
            System.out.println("Hero ID: ");
            choice = scanner.nextInt();
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
                        choice = Game.getPlayers().get(i).getHeroes().get((int)Math.floor(Math.random()*Game.getPlayers().get(i).getHeroes().size())).getAttr().getID();
                    }else {
                        choice = Game.getPlayers().get(i).getHeroes().get(0).getAttr().getID();
                    }
                }
            }
            System.out.println("The random player is : " + playerNameConf + " The Random Hero Is: " + choice);
        }
            for (int i=0;i<Game.getPlayers().size();i++){
                if (Game.getPlayers().get(i).getName().equals(playerNameConf)){
                    for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++){
                        if (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getID()==choice){
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().setStunned(true);
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().setNumberOfRoundStuned(2);
                                //damage start here
                                if ((Game.getPlayers().get(i).getHeroes().get(j).getAttr().getMagicResistance() - attackerPowerAttack)<=0){
                                    attackerPowerAttack-=Game.getPlayers().get(i).getHeroes().get(j).getAttr().getMagicResistance();
                                    Game.getPlayers().get(i).getHeroes().get(j).getAttr().setMagicResistance(0);
                                    System.out.println("Player " + playerName + " Destroy Hero " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getName() + " Of Player " + Game.getPlayers().get(i).getName() + " Magic Resistance Totally");
                                    if ((Game.getPlayers().get(i).getHeroes().get(j).getAttr().getHealth() - attackerPowerAttack) <=0){
                                        Game.getPlayers().get(i).getHeroes().get(j).getAttr().setHealth(0);
                                        System.out.println("Player " + playerName + " Killed Hero " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getName() + " Of Player " + Game.getPlayers().get(i).getName());
                                        Game.getPlayers().get(i).getHeroes().get(j).destroyHero();
                                        Game.getPlayers().get(i).getHeroes().remove(Game.getPlayers().get(i).getHeroes().indexOf(Game.getPlayers().get(i).getHeroes().get(j)));
                                    }else {
                                        Game.getPlayers().get(i).getHeroes().get(j).getAttr().setHealth(Game.getPlayers().get(i).getHeroes().get(j).getAttr().getHealth() - attackerPowerAttack);
                                        System.out.println("Player " + playerName + " Destroy Hero " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getName() + " Of Player " + Game.getPlayers().get(i).getName() + " In his health By " + attackerPowerAttack);
                                    }
                                }else {
                                    Game.getPlayers().get(i).getHeroes().get(j).getAttr().setMagicResistance(Game.getPlayers().get(i).getHeroes().get(j).getAttr().getMagicResistance() - attackerPowerAttack);
                                    System.out.println("Player " + playerName + " Destroy Hero " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getName() + " Of Player " + Game.getPlayers().get(i).getName() + " In his Magic Ressitance By " + attackerPowerAttack);
                                }
                                //end here
                        }
                        attackerPowerAttack = 200;
                    }
                }
            }
            System.out.println("You Stunned A Hero For One Rounde");
     //   }
        return null;
    }
    public AsheAbility(){
        this.abilityName = "AsheAbility";
        this.heroID = 5;
    }
}
