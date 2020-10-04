package com.company.groups;

import com.company.Hero;
import com.company.Players.ConsolePlayer;
import com.company.Players.Player;

import java.util.ArrayList;

public class Shapeshifter extends Group {
    public Shapeshifter(){
        this.ID = 15;
        this.name = "Shapeshifter";
    }



    @Override
    public void enableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber) {
        for (int i = 0; i < player.getHeroes().size(); i++) {
            for (int j = 0; j < player.getHeroes().get(i).getAttr().getGroupTypes().size(); j++) {
                if(player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID() == this.ID)  {
                    if (heroesNumber >= 3 && previousHeroesNumber < 3){
                        player.getHeroes().get(i).getAttr().setHealth(player.getHeroes().get(i).getAttr().getHealth() * 1.4f);
                        break;
                    }

                    if (heroesNumber >= 6 && previousHeroesNumber > 6){
                        player.getHeroes().get(i).getAttr().setHealth(player.getHeroes().get(i).getAttr().getHealth() * 2);
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
                if(player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID() == this.ID)  {
                    if (heroesNumber < 3 && previousHeroesNumber >= 3){
                        player.getHeroes().get(i).getAttr().setHealth(player.getHeroes().get(i).getAttr().getHealth() / 1.4f);
                        break;
                    }

                    if (heroesNumber < 6 && heroesNumber >= 3 && previousHeroesNumber >= 6){
                        player.getHeroes().get(i).getAttr().setHealth(player.getHeroes().get(i).getAttr().getHealth() / 2);
                        break;
                    }

                }
            }
        }
    }
}
