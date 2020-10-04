package com.company.ability;

import com.company.Game.Game;
import com.company.Hero;

public class ShyvanaAbility extends Ability {
    public ShyvanaAbility(){
        this.abilityName = "ShyvanaAbility";
        this.heroID = 36;
    }
    @Override
    public synchronized Object doTheAbility(String playerName,int heroFrequentNumber) {
        int frequent = 1;
        for (int i = 0; i< Game.getPlayers().size(); i++){
            if (Game.getPlayers().get(i).getName().equals(playerName)){
                for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++){
                    if (this.heroID == Game.getPlayers().get(i).getHeroes().get(j).getAttr().getID()) {
                        if (frequent == heroFrequentNumber){
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().incrementHealth(Game.getPlayers().get(i).getHeroes().get(j).getAttr().getHealth()*10/100);
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().incrementAttackScope(5);
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().incrementVisionScope(5);
                            System.out.println("Hero: " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getName() + " range and health has been incremented successfully");
                        }
                        else {
                            frequent++;
                        }
                    }
                }
            }
        }
        return null;
    }
}
