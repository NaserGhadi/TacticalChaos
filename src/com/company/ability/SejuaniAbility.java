package com.company.ability;

import com.company.Game.Game;
import com.company.Hero;

public class SejuaniAbility extends Ability {
    public SejuaniAbility(){
        this.abilityName = "SejuaniAbility";
        this.heroID = 34;
    }
    @Override
    public synchronized Object doTheAbility(String playerName,int heroFrequentNumber) {
        int frequent = 1;
        for (int i = 0; i< Game.getPlayers().size(); i++){
            if (!Game.getPlayers().get(i).getName().equals(playerName)){
                for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++){
                    if (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getID()==this.heroID){
                        if (heroFrequentNumber == frequent){
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().setPreventedAbility(true);
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
