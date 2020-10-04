package com.company.ability;

import com.company.Game.Game;
import com.company.Hero;

public class PoppyAbility extends Ability {
    public PoppyAbility(){
        this.abilityName = "PoppyAbility";
        this.heroID = 31;
    }
    @Override
    public synchronized Object doTheAbility(String playerName,int heroFrequentNumber) {
        int frequent = 1;
        int xPos = 0, yPos = 0;
        for (int i = 0; i< Game.getPlayers().size(); i++){
            if (Game.getPlayers().get(i).getName().equals(playerName))
                for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++)
                    if (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getID()==this.heroID){
                        if (frequent == heroFrequentNumber){
                            xPos = Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX();
                        }
                        else frequent++;
                    }
        }
        for (int i=0;i<Game.getPlayers().size();i++){
            //for just the enemy
            if (!Game.getPlayers().get(i).getName().equals(playerName)){
                for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++){
                    if ((Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX()==xPos)){
                        Game.getPlayers().get(i).getHeroes().get(j).getAttr().setStunned(true);
                        Game.getPlayers().get(i).getHeroes().get(j).getAttr().setNumberOfRoundStuned(2);
                        System.out.println("Hero: " + Game.getPlayers().get(i).getHeroes().get(j).getAttr()  + " Health incremented successfully");
                    }
                }
            }
        }
        return null;
    }
}
