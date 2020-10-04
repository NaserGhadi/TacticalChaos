package com.company.ability;

import com.company.Game.Game;
import com.company.Hero;

public class VarusAbility extends Ability {
    public VarusAbility(){
        this.abilityName = "VarusAbility";
        this.heroID = 41;
    }
    @Override
    public synchronized Object doTheAbility(String playerName,int heroFrequentNumber) {
        int frequent = 1;
        for (int i = 0; i< Game.getPlayers().size(); i++){
            if (Game.getPlayers().get(i).getName().equals(playerName))
                for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++)
                    if (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getID()==this.heroID){
                        if (frequent == heroFrequentNumber){
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().set_400SeriesPowerAttack(true);
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().setNumberOfRound400SeriesPowerAttack(4);
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().setSeriousAttackPower(Game.getPlayers().get(i).getHeroes().get(j).getAttr().getSeriousAttackPower() + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getSeriousAttackPower()*(400/100));
                            System.out.println("Hero: " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getName() + " got 400 series in 4 rounds");
                        }
                        else frequent++;
                    }
        }
        return null;
    }
}
