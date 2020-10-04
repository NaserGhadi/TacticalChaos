package com.company.ability;

import com.company.Game.Game;
import com.company.Hero;

public class TalonAbility extends  Ability{
    public TalonAbility(){
        this.abilityName = "TalonAbility";
        this.heroID = 39;
    }
    @Override
    public synchronized Object doTheAbility(String playerName,int heroFrequentNumber) {
        int frequent = 1;
        for (int i = 0; i< Game.getPlayers().size(); i++){
            if (Game.getPlayers().get(i).getName().equals(playerName))
                for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++)
                    if (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getID()==this.heroID){
                        if (frequent == heroFrequentNumber){
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().setFullPossibleSeriesAttack(true);
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().setNumberOfRoundFullPossibleSeriesAttack(4);
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().setPossibleSeriesAttack(Game.getPlayers().get(i).getHeroes().get(j).getAttr().getMaxpossibleSeriesAttack());
                            System.out.println("Hero: " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getName() + " got full chance in 4 rounds");
                        }
                        else frequent++;
                    }
        }
        return null;
    }
}
