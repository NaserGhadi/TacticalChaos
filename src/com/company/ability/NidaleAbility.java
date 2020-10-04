package com.company.ability;

import com.company.Game.Game;
import com.company.Hero;

public class NidaleAbility extends Ability {
    public NidaleAbility(){
        this.abilityName = "NidaleAbility";
        this.heroID = 30;
    }
    @Override
    public synchronized Object doTheAbility(String playerName,int heroFrequentNumber) {
        int x = 0;
        int frequent = 1;
        boolean y = false;
        for (int i = 0; i< Game.getPlayers().size(); i++){
            if (Game.getPlayers().get(i).getName().equals(playerName)){
                for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++){
                    if (this.heroID!=Game.getPlayers().get(i).getHeroes().get(j).getAttr().getID() && !y){
                        Game.getPlayers().get(i).getHeroes().get(j).getAttr().incrementHealth(100);
                        System.out.println("Hero: " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getName()  + " Health incremented successfully");
                        y = true;
                    }

                    if (this.heroID==Game.getPlayers().get(i).getHeroes().get(j).getAttr().getID()){
                        if (frequent == heroFrequentNumber){
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().incrementDamage(Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPowerAttack()*(10/100));
                            System.out.println("Hero: " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getName()  + " Damage incremented successfully");
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