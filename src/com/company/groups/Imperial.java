package com.company.groups;

import com.company.Hero;
import com.company.Players.ConsolePlayer;
import com.company.Players.Player;

import java.util.ArrayList;

public class Imperial extends Group {
    //grants all imperials double damage.
    public Object doGroupAbility(){
        return null;
    }
    public Imperial(){
        this.ID = 9;
        this.name = "Imperial";
    }


    @Override
    public void enableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber) {
        if (heroesNumber >= 2 && previousHeroesNumber < 2){
            for (int i = 0; i < player.getHeroes().size(); i++) {
                for (int j = 0; j < player.getHeroes().get(i).getAttr().getGroupTypes().size(); j++) {
                    if(player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID() == this.ID)  {
                        player.getHeroes().get(i).getAttr().setPowerAttack(player.getHeroes().get(i).getAttr().getPowerAttack() * 2);
                        break;
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
                        player.getHeroes().get(i).getAttr().setPowerAttack(player.getHeroes().get(i).getAttr().getPowerAttack() / 2);
                        break;
                    }
                }

            }

        }
    }
}
