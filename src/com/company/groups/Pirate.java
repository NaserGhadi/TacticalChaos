package com.company.groups;

import com.company.Players.ConsolePlayer;
import com.company.Players.Player;

import java.util.ArrayList;

public class Pirate extends Group {
    public Pirate(){
        this.ID = 13;
        this.name = "Pirate";
    }


    @Override
    public void enableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber) {
        if (heroesNumber >= 3 && previousHeroesNumber < 3){
            if (player.getPiratesMoney() == false){
                player.setPiratesMoney(true);
            }

        }
    }

    @Override
    public void disableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber) {
        if (heroesNumber < 3 && previousHeroesNumber >= 3){
            if (player.getPiratesMoney() == true){
                player.setPiratesMoney(false);
            }

        }
    }
}
