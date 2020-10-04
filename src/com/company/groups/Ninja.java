package com.company.groups;

import com.company.Hero;
import com.company.Players.ConsolePlayer;
import com.company.Players.Player;

import java.util.ArrayList;

public class Ninja extends Group {
    public Ninja(){
        this.ID = 11;
        this.name = "Ninja";
    }


    @Override
    public void enableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber) {
        for (int i = 0; i < player.getHeroes().size(); i++) {
            for (int j = 0; j < player.getHeroes().get(i).getAttr().getGroupTypes().size(); j++) {
                if(player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID() == this.ID)  {
                    if (heroesNumber == 1 && previousHeroesNumber < 1){
                        player.getHeroes().get(i).getAttr().setPowerAttack(player.getHeroes().get(i).getAttr().getPowerAttack() * 2);
                        player.getHeroes().get(i).getAttr().setPossibleSeriesAttack(player.getHeroes().get(i).getAttr().getSeriousAttackPower() * 1.5f);
                        if (player.getHeroes().get(i).getAttr().getSeriousAttackPower() > 1.0f)
                            player.getHeroes().get(i).getAttr().setPossibleSeriesAttack(1.0f);
                        break;
                    }
                    if (heroesNumber > 1 && previousHeroesNumber <= 1){
                        player.getHeroes().get(i).getAttr().setPowerAttack(player.getHeroes().get(i).getAttr().getPowerAttack() * 3);
                        player.getHeroes().get(i).getAttr().setPossibleSeriesAttack(player.getHeroes().get(i).getAttr().getSeriousAttackPower() * 1.75f);
                        if (player.getHeroes().get(i).getAttr().getSeriousAttackPower() > 1.0f)
                            player.getHeroes().get(i).getAttr().setPossibleSeriesAttack(1.0f);
                        break;
                    }
                }
            }
        }
    }

    @Override
    public void disableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber) {
        for (int i = 0; i < player.getHeroes().size(); i++) {
            for (int j = 0; j < player.getHeroes().get(i).getAttr().getGroupTypes().size(); j++) {
                if(player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID() == this.ID)  {

                    if (heroesNumber == 1 && previousHeroesNumber > 1){
                        player.getHeroes().get(i).getAttr().setPowerAttack(player.getHeroes().get(i).getAttr().getPowerAttack() / 3);
                        player.getHeroes().get(i).getAttr().setPossibleSeriesAttack(player.getHeroes().get(i).getAttr().getSeriousAttackPower() / 1.75f);
                        break;
                    }


                }
            }
        }
    }
}
