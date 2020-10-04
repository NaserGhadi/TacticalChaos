package com.company.ability;

import com.company.Game.Game;
import com.company.Hero;

import java.util.Scanner;

public class LeonaAbility extends Ability {
    public LeonaAbility(){
        this.abilityName = "LeonaAbility";
        this.heroID = 23;
    }
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
        boolean neighbor = false;
        for (int i=0;i<Game.getPlayers().size();i++){
            for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++){
                if ((Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX()>=xPos-attackScope || Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX()<=xPos+30) && (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY()>=yPos-30 || Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY()<=yPos+attackScope)){
                    neighbor = true;
                }
            }
        }
        if (!neighbor) {
            System.out.println("You are alone in you attack scope");
            return null;
        }
        for (int i=0;i<Game.getPlayers().size();i++){
            //for just the enemy
            if (!Game.getPlayers().get(i).getName().equals(playerName)){
                for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++){
                    if ((Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX()>=xPos-attackScope || Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX()<=xPos+attackScope) && (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY()>=yPos-attackScope || Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY()<=yPos+attackScope)){
                        Game.getPlayers().get(i).getHeroes().get(j).getAttr().setStunned(true);
                        Game.getPlayers().get(i).getHeroes().get(j).getAttr().setNumberOfRoundStuned(1);
                        System.out.println("Hero:" + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getName() +  " has been stunned for 1 round");

                    }
                }
            }
        }
        return null;
    }
}



