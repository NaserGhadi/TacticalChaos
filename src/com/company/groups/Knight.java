//??
package com.company.groups;

import com.company.Players.ConsolePlayer;
import com.company.Players.Player;

import java.util.ArrayList;

public class Knight extends Group {
    public Knight(){
        this.ID = 10;
        this.name = "Knight";
    }

    @Override
    public void enableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber) {
        if (heroesNumber >= 2 && heroesNumber < 4 && previousHeroesNumber < 2){
            for (int i = 0; i < player.getHeroes().size(); i++){
                for (Group group:player.getHeroes().get(i).getAttr().getGroupTypes()) {
                    if (group.getName().equals(this.name)){
                        player.getHeroes().get(i).getAttr().setDecreaseDamage(5);
                        break;
                    }
                }
            }
        }

        if (heroesNumber >= 4 && heroesNumber < 6 && previousHeroesNumber < 4){
            for (int i = 0; i < player.getHeroes().size(); i++){
                for (Group group:player.getHeroes().get(i).getAttr().getGroupTypes()) {
                    if (group.getName().equals(this.name)){
                        player.getHeroes().get(i).getAttr().setDecreaseDamage(8);
                         break;
                    }
                }
            }

        }

        if (heroesNumber >= 6 && previousHeroesNumber < 6){
            for (int i = 0; i < player.getHeroes().size(); i++){
                for (Group group:player.getHeroes().get(i).getAttr().getGroupTypes()) {
                    if (group.getName().equals(this.name)){
                        player.getHeroes().get(i).getAttr().setDecreaseDamage(12);
                        break;
                    }
                }
            }

        }
    }

    @Override
    public void disableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber) {
        if (heroesNumber < 2 && previousHeroesNumber >= 2){
            for (int i = 0; i < player.getHeroes().size(); i++){
                for (Group group:player.getHeroes().get(i).getAttr().getGroupTypes()) {
                    if (group.getName().equals(this.name)){
                        player.getHeroes().get(i).getAttr().setDecreaseDamage(0);
                        break;
                    }
                }
            }

        }

        if (heroesNumber < 4 && heroesNumber >= 2 && previousHeroesNumber >= 4){
            for (int i = 0; i < player.getHeroes().size(); i++){
                for (Group group:player.getHeroes().get(i).getAttr().getGroupTypes()) {
                    if (group.getName().equals(this.name)){
                        player.getHeroes().get(i).getAttr().setDecreaseDamage(5);
                        break;
                    }
                }
            }

        }

        if (heroesNumber < 6 && previousHeroesNumber >= 6){
            for (int i = 0; i < player.getHeroes().size(); i++){
                for (Group group:player.getHeroes().get(i).getAttr().getGroupTypes()) {
                    if (group.getName().equals(this.name)){
                        player.getHeroes().get(i).getAttr().setDecreaseDamage(8);
                        break;
                    }
                }
            }

        }
    }
}
