package com.company.ability;

import com.company.Game.Game;
import com.company.Hero;

public class LuluAbility extends Ability {
    public LuluAbility(){
        this.abilityName = "LuluAbility";
        this.heroID = 26;
    }
    @Override
    public synchronized Object doTheAbility(String playerName,int heroFrequentNumber) {
        int frequent = 1;
        int x = 0;
        for (int i = 0; i< Game.getPlayers().size(); i++){
            if (Game.getPlayers().get(i).getName().equals(playerName)){
                for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++){
                    if (x<2){
                        Game.getPlayers().get(i).getHeroes().get(j).getAttr().incrementHealth(150);
                        System.out.println("Hero: " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getName() + " health incremented 150");
                        x++;
                    }
                    if (this.heroID==Game.getPlayers().get(i).getHeroes().get(j).getAttr().getID()){
                        if (heroFrequentNumber == frequent){
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().incrementHealth(150);
                            System.out.println("Hero: " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getName() + " health incremented 150");
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
