
package com.company.groups;
import com.company.Players.Player;

import java.util.ArrayList;

    public class Yordle extends Group {
    public Yordle(){
        this.ID = 19;
        this.name = "Yordle";
    }

    @Override
    public void enableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber) {
        if (heroesNumber >= 2 && heroesNumber < 4 && previousHeroesNumber < 2){
            for (int i = 0; i < player.getHeroes().size(); i++){
                for (Group group:player.getHeroes().get(i).getAttr().getGroupTypes()) {
                    if (group.getName().equals(this.name)){
                        player.getHeroes().get(i).getAttr().setDecreaseDamage(15);
                       break;
                    }
                }
            }

        }

        if (heroesNumber >= 4 && heroesNumber < 6 && previousHeroesNumber < 4){
            for (int i = 0; i < player.getHeroes().size(); i++){
                for (Group group:player.getHeroes().get(i).getAttr().getGroupTypes()) {
                    if (group.getName().equals(this.name)){
                        player.getHeroes().get(i).getAttr().setDecreaseDamage(30);
                       break;
                    }
                }
            }

        }

        if (heroesNumber >= 6 && previousHeroesNumber < 6){
            for (int i = 0; i < player.getHeroes().size(); i++){
                for (Group group:player.getHeroes().get(i).getAttr().getGroupTypes()) {
                    if (group.getName().equals(this.name)){
                        player.getHeroes().get(i).getAttr().setDecreaseDamage(50);
                        break;
                    }
                }
            }

        }
    }

    @Override
    public void disableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber) {
        if (heroesNumber < 2 && previousHeroesNumber >= 2){
            for (int i = 0; i < player.getHeroes().size(); i++){
                for (Group group:player.getHeroes().get(i).getAttr().getGroupTypes()) {
                    if (group.getName().equals(this.name)){
                        player.getHeroes().get(i).getAttr().setDecreaseDamage(0);
                        break;
                    }
                }
            }

        }

        if (heroesNumber < 4 && heroesNumber >= 2 && previousHeroesNumber >= 4){
            for (int i = 0; i < player.getHeroes().size(); i++){
                for (Group group:player.getHeroes().get(i).getAttr().getGroupTypes()) {
                    if (group.getName().equals(this.name)){
                        player.getHeroes().get(i).getAttr().setDecreaseDamage(15);
                        break;
                    }
                }
            }

        }

        if (heroesNumber < 6 && previousHeroesNumber >= 6){
            for (int i = 0; i < player.getHeroes().size(); i++){
                for (Group group:player.getHeroes().get(i).getAttr().getGroupTypes()) {
                    if (group.getName().equals(this.name)){
                        player.getHeroes().get(i).getAttr().setDecreaseDamage(30);
                       break;
                    }
                }
            }

        }
    }
}
