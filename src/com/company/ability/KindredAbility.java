package com.company.ability;

import com.company.Game.Game;
import com.company.Hero;

import java.util.Scanner;

public class KindredAbility extends Ability {
    public KindredAbility(){
        this.abilityName = "KindredAbility";
        this.heroID = 22;
    }
    @Override
    public synchronized Object doTheAbility(String playerName,int heroFrequentNumber) {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i< Game.getPlayers().size(); i++){
            if (Game.getPlayers().get(i).getName().equals(playerName)){
                for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++){
                    Game.getPlayers().get(i).getHeroes().get(j).getAttr().setPowerAttack(Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPowerAttack() + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPowerAttack()*(10/100));
                    System.out.println("Hero:" + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getName() + " " + " power attack has been incremented 10%");
                }
            }
        }
        //start
        System.out.println("All Allies Power attack incremented successfully");
        return null;
    }
}
