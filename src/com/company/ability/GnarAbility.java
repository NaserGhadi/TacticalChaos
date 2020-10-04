package com.company.ability;
import com.company.Game.Game;
import com.company.Hero;
public class GnarAbility extends Ability {
    @Override
    public synchronized Object doTheAbility(String playerName,int heroFrequentNumber) {
        int frequent = 1;
        for (int i=0;i<Game.getPlayers().size();i++){
            //for just the enemy
            if (!Game.getPlayers().get(i).getName().equals(playerName)){
                for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++){
                    Game.getPlayers().get(i).getHeroes().get(j).getAttr().decrementMagicResist(100);
                    System.out.println("Hero: "  + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getName() + " Magic resitance decremented by 100 and become" + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getMagicResistance());

                }
            }
        }
        if (Game.getPlayers().size()<=1 || checkForLonely()){
            System.out.println("Game Ended");
            return null;
        }
        return null;
    }
    public GnarAbility(){
        this.abilityName = "GnarAbility";
        this.heroID = 14;
    }
}