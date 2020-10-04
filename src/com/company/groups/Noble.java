package com.company.groups;

import com.company.Game.Game;
import com.company.Hero;
import com.company.Players.ConsolePlayer;
import com.company.Players.Player;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Noble extends Group {
    public Noble(){
        this.ID = 12;
        this.name = "Noble";
    }



    @Override
    public void enableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber) {
        if (heroesNumber >= 3 && previousHeroesNumber < 3) {
            for (int i = 0; i < player.getHeroes().size(); i++) {
                player.getHeroes().get(i).getAttr().setArmor(player.getHeroes().get(i).getAttr().getArmor() + 20);
                player.getHeroes().get(i).getAttr().setHealth(player.getHeroes().get(i).getAttr().getHealth() + 40);
                break;
            }

        }

        if (heroesNumber >= 6 && previousHeroesNumber < 6) {
            for (int i = 0 ; i < player.getHeroes().size(); i++) {
                player.getHeroes().get(i).getAttr().setArmor(player.getHeroes().get(i).getAttr().getArmor() + 20);
                player.getHeroes().get(i).getAttr().setHealth(player.getHeroes().get(i).getAttr().getHealth() + 40);
            }

        }
    }

    @Override
    public void disableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber) {
        if (heroesNumber < 3 && previousHeroesNumber >= 3) {
            for (int i = 0; i < player.getHeroes().size(); i++) {
                player.getHeroes().get(i).getAttr().setArmor(player.getHeroes().get(i).getAttr().getArmor() - 20);
                player.getHeroes().get(i).getAttr().setHealth(player.getHeroes().get(i).getAttr().getHealth() - 40);
                break;
            }

        }

        if (heroesNumber < 6 && heroesNumber >= 3 && previousHeroesNumber >= 6) {
            for (int i = 0; i < player.getHeroes().size(); i++) {
                player.getHeroes().get(i).getAttr().setArmor(player.getHeroes().get(i).getAttr().getArmor() - 20);
                player.getHeroes().get(i).getAttr().setHealth(player.getHeroes().get(i).getAttr().getHealth() - 40);
            }

        }
    }
}

