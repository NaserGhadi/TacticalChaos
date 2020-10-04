package com.company.groups.interfaces;

import com.company.Players.Player;

import java.util.ArrayList;

public interface IGroupDoAbility {
    public void enableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber);
    public void disableAbility(Player player, ArrayList<Player> players,int heroesNumber, int previousHeroesNumber);
}
