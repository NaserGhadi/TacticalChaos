package com.company.groups;

import com.company.Hero;
import com.company.Players.ConsolePlayer;
import com.company.Players.Player;

import java.util.ArrayList;

public class Sorcerer extends Group {
    public Sorcerer(){
        this.ID = 16;
        this.name = "Sorcerer";
    }


    @Override
    public void enableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber) {
        for (int i = 0; i < player.getHeroes().size(); i++) {
            for (int j = 0; j < player.getHeroes().get(i).getAttr().getGroupTypes().size(); j++) {
                if(player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID() == this.ID)  {
                    if (heroesNumber >= 3 && heroesNumber < 6 && previousHeroesNumber < 3){
                        player.getHeroes().get(i).getAttr().setSeriousAttackPower(player.getHeroes().get(i).getAttr().getSeriousAttackPower() * 1.4f);
                        break;
                    }

                    if (heroesNumber >= 6 && heroesNumber < 9 && previousHeroesNumber < 6){
                        player.getHeroes().get(i).getAttr().setSeriousAttackPower(player.getHeroes().get(i).getAttr().getSeriousAttackPower() * 2);
                        break;
                    }

                    if (heroesNumber >= 9 && previousHeroesNumber < 9){
                        player.getHeroes().get(i).getAttr().setSeriousAttackPower(player.getHeroes().get(i).getAttr().getSeriousAttackPower() * 2.7f);
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
                    if (heroesNumber < 3 && previousHeroesNumber >= 3){
                        player.getHeroes().get(i).getAttr().setSeriousAttackPower(player.getHeroes().get(i).getAttr().getSeriousAttackPower() / 1.4f);
                        break;
                    }

                    if (heroesNumber < 6 && heroesNumber >= 3 && previousHeroesNumber >= 6){
                        player.getHeroes().get(i).getAttr().setSeriousAttackPower(player.getHeroes().get(i).getAttr().getSeriousAttackPower() / 2);
                        break;
                    }

                    if (heroesNumber < 9 && heroesNumber >= 6 && previousHeroesNumber >= 9){
                        player.getHeroes().get(i).getAttr().setSeriousAttackPower(player.getHeroes().get(i).getAttr().getSeriousAttackPower() / 2.7f);
                        break;
                    }

                }
            }
        }
    }
}
