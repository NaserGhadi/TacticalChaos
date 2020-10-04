package com.company.ability;

import com.company.Game.Game;
import com.company.Hero;

public class LucianAbility extends Ability {
    public LucianAbility(){
        this.abilityName = "LucianAbility";
        this.heroID = 25;
    }
    @Override
    public synchronized Object doTheAbility(String playerName,int heroFrequentNumber) {
        int frequent = 1;
        int x = 0;
        for (int i = 0; i< Game.getPlayers().size(); i++){
            //for just the enemy
            if (Game.getPlayers().get(i).getName().equals(playerName)){
                for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++){
                    if (this.heroID==Game.getPlayers().get(i).getHeroes().get(j).getAttr().getID()) {
                        if (heroFrequentNumber==frequent){
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().incrementVisionScope(10);
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().incrementAttackScope(10);
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().incrementSpeed(10);
                            System.out.println("Hero: Lucian got 10 incremented range and 10 speed");
                        }else {
                            frequent++;
                        }
                    }
                }
            }
        }
        return null;
    }
}
