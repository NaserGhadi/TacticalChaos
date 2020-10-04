package com.company.groups;

import com.company.Hero;
import com.company.Players.ConsolePlayer;
import com.company.Players.Player;

import java.util.ArrayList;

public class Ranger extends Group {
    public Ranger(){
        this.ID = 14;
        this.name = "Ranger";
    }

    @Override
    public void enableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber) {
        if (heroesNumber >= 3 && previousHeroesNumber < 3){
            for (int i = 0; i < player.getHeroes().size(); i++) {
                for (int j = 0; j < player.getHeroes().get(i).getAttr().getGroupTypes().size(); j++) {
                    if(player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID() == this.ID)  {
                        player.getHeroes().get(i).getAttr().setAttackScope(player.getHeroes().get(i).getAttr().getAttackScope() + 1);
                        player.getHeroes().get(i).getAttr().setVisionScope(player.getHeroes().get(i).getAttr().getVisionScope() + 1);
                        break;
                    }
                }
            }

        }
    }

    @Override
    public void disableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber) {
        if (heroesNumber < 3 && previousHeroesNumber >= 3){
            for (int i = 0; i < player.getHeroes().size(); i++) {
                for (int j = 0; j < player.getHeroes().get(i).getAttr().getGroupTypes().size(); j++) {
                    if(player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID() == this.ID)  {
                        player.getHeroes().get(i).getAttr().setAttackScope(player.getHeroes().get(i).getAttr().getAttackScope() - 1);
                        player.getHeroes().get(i).getAttr().setVisionScope(player.getHeroes().get(i).getAttr().getVisionScope() - 1);
                        break;
                    }
                }
            }

        }
    }
}
