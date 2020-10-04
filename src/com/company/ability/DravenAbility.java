package com.company.ability;

import com.company.Game.Game;
import com.company.Hero;

public class DravenAbility extends Ability {
    @Override
    public synchronized Object doTheAbility(String playerName,int heroFrequentNumber) {
        int frequent = 1;
        for (int i=0;i<Game.getPlayers().size();i++){
            //for just the enemy
            if (!Game.getPlayers().get(i).getName().equals(playerName)){
                for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();){
                        Game.getPlayers().get(i).getHeroes().get(j).getAttr().incrementHealth(-1*Game.getPlayers().get(i).getHeroes().get(j).getAttr().getHealth()*30/100);
                        if (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getHealth()<=0){
                            Game.getPlayers().get(i).getHeroes().remove(j);
                        }else j++;
                }
            }
        }
        if (Game.getPlayers().size()<=1 || checkForLonely()){
            System.out.println("Game Ended");
            return null;
        }
        System.out.println("Decremented 30% health from the enemies");
        return null;
    }
    public DravenAbility(){
        this.abilityName = "DravenAbility";
        this.heroID = 9;
    }
}