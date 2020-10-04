package com.company.groups;

import com.company.Players.Player;

import java.util.ArrayList;

public class NONE extends Group {
    public NONE(){
        this.ID = 0;
        this.name = "NONE";
    }

    @Override
    public void enableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber) {
        // Do nothing
    }

    @Override
    public void disableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber) {
        // Do nothing
    }
}
