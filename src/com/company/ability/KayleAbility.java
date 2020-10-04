package com.company.ability;

import com.company.Game.Game;
import com.company.Hero;

public class KayleAbility extends Ability {
    @Override
    public synchronized Object doTheAbility(String playerName,int heroFrequentNumber) {
        int frequent = 1;
        for (int i = 0; i< Game.getPlayers().size(); i++){
            if (Game.getPlayers().get(i).getName().equals(playerName)){
                for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++){
                    if (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getID()==this.heroID){
                        if (frequent == heroFrequentNumber){
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().setBlockedDamage(true);
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().setNumberOfRoundBlockedDamage(1);
                            if (Game.getPlayers().get(i).getHeroes().size()>2){
                                int firstRandomNumber = (int)Math.floor(Math.random()*Game.getPlayers().get(i).getHeroes().size());
                                Game.getPlayers().get(i).getHeroes().get(firstRandomNumber).getAttr().setBlockedDamage(true);
                                Game.getPlayers().get(i).getHeroes().get(firstRandomNumber).getAttr().setNumberOfRoundBlockedDamage(1);
                                int secondRandomNumber = (int)Math.floor(Math.random()*Game.getPlayers().get(i).getHeroes().size());
                                Game.getPlayers().get(i).getHeroes().get(secondRandomNumber).getAttr().setBlockedDamage(true);
                                Game.getPlayers().get(i).getHeroes().get(secondRandomNumber).getAttr().setNumberOfRoundBlockedDamage(1);
                                System.out.println("Hero: Kayle, " + Game.getPlayers().get(i).getHeroes().get(firstRandomNumber).getAttr().getName() + " , and " + Game.getPlayers().get(i).getHeroes().get(secondRandomNumber).getAttr().getName() + " has been blocked damage for 1 round");
                            }else if (Game.getPlayers().get(i).getHeroes().size()==2) {
                                Game.getPlayers().get(i).getHeroes().get(0).getAttr().setBlockedDamage(true);
                                Game.getPlayers().get(i).getHeroes().get(1).getAttr().setBlockedDamage(true);
                                Game.getPlayers().get(i).getHeroes().get(0).getAttr().setNumberOfRoundBlockedDamage(1);
                                Game.getPlayers().get(i).getHeroes().get(1).getAttr().setNumberOfRoundBlockedDamage(1);
                                System.out.println("Hero: Kayle, " + Game.getPlayers().get(i).getHeroes().get(0).getAttr().getName() + " , and " + Game.getPlayers().get(i).getHeroes().get(1).getAttr().getName() + " has been blocked damage for 1 round");
                            }else {
                                System.out.println("You Dont Have Any Allies");
                            }
                        }
                        else frequent++;
                    }
                }
            }
        }
        for (int i=0;i<Game.getPlayers().size();i++){
            for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++){
                System.out.println("Name " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getName() + " Is Blocked Damage " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().isBlockedDamage());
            }
        }
        return null;
    }
    public KayleAbility(){
        this.abilityName = "KayleAbility";
        this.heroID = 19;
    }
}
