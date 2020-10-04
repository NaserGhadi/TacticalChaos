package com.company.groups;

import com.company.Hero;
import com.company.Players.ConsolePlayer;
import com.company.Players.Player;

import java.util.ArrayList;

public class Glacial extends Group{
    public Glacial(){
        this.ID = 7;
        this.name = "Glacial";
    }


    @Override
    public void enableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber) {
        if (heroesNumber >= 2 && previousHeroesNumber < 2){
            for ( int i = 0; i < player.getHeroes().size(); i++ ) {
                player.getHeroes().get(i).getAttr().setGroupStunned(true);
            }

        }
    }

    @Override
    public void disableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber) {
        if (heroesNumber < 2 && previousHeroesNumber >= 2){
            for ( int i = 0; i < player.getHeroes().size(); i++ ) {
                player.getHeroes().get(i).getAttr().setGroupStunned(false);
            }

        }
    }
}
