package com.company.groups;

import com.company.Players.ConsolePlayer;
import com.company.Players.Player;

import java.util.ArrayList;

public class Demon extends Group {
    //Deploying multiple Demons grants them a chance to burn away their target's mana،
    //dealing damage based on the amount of mana burned. This chance increases with more Demons.
    public Demon(){
        this.ID = 4;
        this.name = "Demon";
    }


    @Override
    public void enableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber) {
        if (heroesNumber >= 2 && heroesNumber < 4 && previousHeroesNumber < 2){
            for (int i = 0; i < player.getHeroes().size(); i++){
                for (Group group:player.getHeroes().get(i).getAttr().getGroupTypes()) {
                    if (group.getName().equals(this.name)){
                        player.getHeroes().get(i).getAttr().setBurningMana(20);
                        break;
                    }
                }
            }
        }

        if (heroesNumber >= 4 && previousHeroesNumber < 4){
            for (int i = 0; i < player.getHeroes().size(); i++){
                for (Group group:player.getHeroes().get(i).getAttr().getGroupTypes()) {
                    if (group.getName().equals(this.name)){
                        player.getHeroes().get(i).getAttr().setBurningMana(40);
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
                        player.getHeroes().get(i).getAttr().setBurningMana(0);
                        break;
                    }
                }
            }

        }
        if (heroesNumber < 4 && heroesNumber >= 2 && previousHeroesNumber >= 4){
            for (int i = 0; i < player.getHeroes().size(); i++){
                for (Group group:player.getHeroes().get(i).getAttr().getGroupTypes()) {
                    if (group.getName().equals(this.name)){
                        player.getHeroes().get(i).getAttr().setBurningMana(20);
                        break;
                    }
                }
            }

        }
    }
}