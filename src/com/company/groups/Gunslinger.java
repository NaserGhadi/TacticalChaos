//??
package com.company.groups;

import com.company.Players.ConsolePlayer;
import com.company.Players.Player;

import java.util.ArrayList;

public class Gunslinger extends Group {
    public Gunslinger(){
        this.ID = 8;
        this.name = "Gunslinger";
    }


    @Override
    public void enableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber) {
        for (int i = 0; i  < player.getHeroes().size(); i++) {
            for (int j = 0; j < player.getHeroes().get(i).getAttr().getGroupTypes().size(); j++) {
                if (player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID() == this.ID) {
                    if (heroesNumber >= 3 && heroesNumber < 6 && previousHeroesNumber < 3){
                        player.getHeroes().get(i).getAttr().setPowerAttack(player.getHeroes().get(i).getAttr().getPowerAttack() + 40);
                        break;
                    }

                    if (heroesNumber >= 6 && heroesNumber < 9 && previousHeroesNumber < 6){
                        player.getHeroes().get(i).getAttr().setPowerAttack(player.getHeroes().get(i).getAttr().getPowerAttack() + 80);
                        break;
                    }

                    if (heroesNumber >= 9 && previousHeroesNumber < 9){
                        player.getHeroes().get(i).getAttr().setPowerAttack(player.getHeroes().get(i).getAttr().getPowerAttack() + 160);
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
                if (player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID() == this.ID) {
                    if (heroesNumber < 3 && previousHeroesNumber >= 3){
                        player.getHeroes().get(i).getAttr().setPowerAttack(player.getHeroes().get(i).getAttr().getPowerAttack() - 40);
                        break;
                    }

                    if (heroesNumber < 6 && previousHeroesNumber >= 6){
                        player.getHeroes().get(i).getAttr().setPowerAttack(player.getHeroes().get(i).getAttr().getPowerAttack() - 80);
                        break;
                    }

                    if (heroesNumber < 9 && previousHeroesNumber >= 9){
                        player.getHeroes().get(i).getAttr().setPowerAttack(player.getHeroes().get(i).getAttr().getPowerAttack() -160);
                        break;
                    }
                }
            }
        }
    }
}
