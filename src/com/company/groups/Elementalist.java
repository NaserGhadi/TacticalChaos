package com.company.groups;

import com.company.Players.ConsolePlayer;
import com.company.Players.Player;

import java.util.ArrayList;

public class Elementalist extends Group {
    public Elementalist(){
        this.ID = 6;
        this.name = "Elemntalist";

    }

    @Override
    public void enableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber) {
        if (heroesNumber >= 2 && heroesNumber < 4 && previousHeroesNumber < 2){
            for (int i = 0; i < player.getHeroes().size(); i++){
                for (Group group:player.getHeroes().get(i).getAttr().getGroupTypes()) {
                    if (group.getName().equals(this.name)){
                        player.getHeroes().get(i).getAttr().setDoubleMana(2);
                        break;
                    }
                }
            }

        }

        if (heroesNumber >= 4 && previousHeroesNumber < 4){
            for (int i = 0; i < player.getHeroes().size(); i++){
                for (Group group:player.getHeroes().get(i).getAttr().getGroupTypes()) {
                    if (group.getName().equals(this.name)){
                        player.getHeroes().get(i).getAttr().setDoubleMana(4);
                        player.setExtraHeroes(player.getExtraHeroes() + 1);
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
                        player.getHeroes().get(i).getAttr().setDoubleMana(0);
                        break;
                    }
                }
            }

        }
        if (heroesNumber < 4 && heroesNumber >= 2 && previousHeroesNumber >= 4){
            for (int i = 0; i < player.getHeroes().size(); i++){
                for (Group group:player.getHeroes().get(i).getAttr().getGroupTypes()) {
                    if (group.getName().equals(this.name)){
                        player.getHeroes().get(i).getAttr().setDoubleMana(2);
                        if (player.getExtraHeroes() > 0)
                            player.setExtraHeroes(player.getExtraHeroes() - 1);
                        break;
                    }
                }
            }

        }
    }
}
