package com.company.groups;

import com.company.Hero;
import com.company.Players.ConsolePlayer;
import com.company.Players.Player;

import java.util.ArrayList;

public class Dragons extends Group {
    //Deploying multiple Glacial grants their attacks a chance to stun their target، increasing with more Glacial.
    public Dragons(){
        this.ID = 5;
        this.name = "Dragons";
    }


    @Override
    public void enableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber) {
        if (heroesNumber >= 2 && previousHeroesNumber < 2){
            for (int i = 0; i < player.getHeroes().size(); i++) {
                for (int j = 0; j < player.getHeroes().get(i).getAttr().getGroupTypes().size(); j++) {
                    if(player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID() == this.ID)  {
                        player.getHeroes().get(i).getAttr().setImmune(true);
                    }
                }
            }

        }
    }

    @Override
    public void disableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber) {
        if (heroesNumber < 2 && previousHeroesNumber >= 2){
            for (int i = 0; i < player.getHeroes().size(); i++) {
                for (int j = 0; j < player.getHeroes().get(i).getAttr().getGroupTypes().size(); j++) {
                    if(player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID() == this.ID)  {
                        player.getHeroes().get(i).getAttr().setImmune(false);
                    }
                }
            }
        }

    }
}
