//??
package com.company.groups;

import com.company.Players.ConsolePlayer;
import com.company.Players.Player;

import java.util.ArrayList;

public class Void extends Group {
    public Void (){
        this.ID = 17;
        this.name = "Void";
    }


    @Override
    public void enableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber) {
        if (heroesNumber >= 2 && heroesNumber < 4 && previousHeroesNumber < 2) {
            for (int i = 0; i < player.getHeroes().size(); i++) {
                boolean enableForAHero = false;
                for (int j = 0; j < player.getHeroes().get(i).getAttr().getGroupTypes().size(); j++) {
                    if (player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID() == this.ID) {
                        player.getHeroes().get(i).getAttr().setTrueDamage(true);
                        enableForAHero = true;
                       break;
                    }
                }
                if (enableForAHero)
                    break;
            }

        }

        if (heroesNumber >= 4 && previousHeroesNumber < 4) {
            for (int i = 0; i < player.getHeroes().size(); i++) {
                for (int j = 0; j < player.getHeroes().get(i).getAttr().getGroupTypes().size(); j++) {
                    if (player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID() == this.ID) {
                        player.getHeroes().get(i).getAttr().setTrueDamage(true);
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
                boolean enableForAHero = false;
                for (int j = 0; j < player.getHeroes().get(i).getAttr().getGroupTypes().size(); j++) {
                    if(player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID() == this.ID)  {
                        player.getHeroes().get(i).getAttr().setTrueDamage(true);
                        enableForAHero = true;
                       break;
                    }
                }
                if (enableForAHero)
                    break;
            }

        }

        if (heroesNumber < 4 && heroesNumber >= 2 && previousHeroesNumber >= 4){
            for (int i = 0; i < player.getHeroes().size(); i++) {
                for (int j = 0; j < player.getHeroes().get(i).getAttr().getGroupTypes().size(); j++) {
                    if(player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID() == this.ID)  {
                        player.getHeroes().get(i).getAttr().setTrueDamage(true);
                        break;
                    }
                }
            }

        }
    }
}
