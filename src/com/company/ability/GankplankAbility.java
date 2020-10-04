package com.company.ability;
import com.company.Game.Game;
import com.company.Hero;

import java.util.Scanner;

public class GankplankAbility extends Ability {
    @Override
    public synchronized Object doTheAbility(String playerName,int heroFrequentNumber) {
        Scanner scanner = new Scanner(System.in);
        int frequent = 1;
        int xPos = 0, yPos = 0;
        float attackScope = 0;
        float attackerPowerAttack = 250;
        for (int i = 0; i< Game.getPlayers().size(); i++){
            if (Game.getPlayers().get(i).getName().equals(playerName))
                for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++)
                    if (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getID()==this.heroID){
                        if (frequent == heroFrequentNumber){
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().setNumberOfRoundDoubleRangeDoubleDamage(1);
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().setDoubleRangeDoubleDamage(true);
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().setVisionScope(Game.getPlayers().get(i).getHeroes().get(j).getAttr().getVisionScope()*2);
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().setAttackScope(Game.getPlayers().get(i).getHeroes().get(j).getAttr().getAttackScope()*2);
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().setPowerAttack(Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPowerAttack()*2);
                            System.out.println("Hero: Gankplank got double range scope and double attack scope and he is blocked damage for 1 round");
                        }
                        else frequent++;    
                    }
        }
        if (Game.getPlayers().size()<=1 || checkForLonely()){
            System.out.println("Game Ended");
            return null;
        }
        return null;
    }
    public GankplankAbility(){
        this.abilityName = "GankplankAbility";
        this.heroID = 12;
    }
}