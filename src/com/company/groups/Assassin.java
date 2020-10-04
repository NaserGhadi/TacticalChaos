package com.company.groups;

import com.company.Hero;
import com.company.Players.ConsolePlayer;
import com.company.Players.Player;

import java.util.ArrayList;

public class Assassin extends Group {
    public Assassin(){
        this.ID = 1;
        this.name = "Assassin";
    }


    @Override
    public void enableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber) {
            for (int i = 0; i < player.getHeroes().size(); i++) {
                for (int j = 0; j < player.getHeroes().get(i).getAttr().getGroupTypes().size(); j++) {
                    if(player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID() == this.ID)  {
                        if (heroesNumber >= 2 && heroesNumber < 4 && previousHeroesNumber < 2){
                            player.getHeroes().get(i).getAttr().setPowerAttack(player.getHeroes().get(i).getAttr().getPowerAttack() + 30);
                            player.getHeroes().get(i).getAttr().setArmor(player.getHeroes().get(i).getAttr().getArmor() + ( player.getHeroes().get(i).getAttr().getArmor() * 0.1f ) );
                        }

                        if (heroesNumber >= 4 && previousHeroesNumber < 4){
                            player.getHeroes().get(i).getAttr().setPowerAttack(player.getHeroes().get(i).getAttr().getPowerAttack() + 60);
                            player.getHeroes().get(i).getAttr().setArmor(player.getHeroes().get(i).getAttr().getArmor() + ( player.getHeroes().get(i).getAttr().getArmor() * 0.2f ) );
                        }
                        break;
                    }
                }
            }

    }

    @Override
    public void disableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber) {
        for (int i = 0; i < player.getHeroes().size(); i++) {
            for (int j = 0; j < player.getHeroes().get(i).getAttr().getGroupTypes().size(); j++) {
                if (player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID() == this.ID) {
                    if (heroesNumber < 2 && previousHeroesNumber >= 2) {
                        player.getHeroes().get(i).getAttr().setPowerAttack(player.getHeroes().get(i).getAttr().getPowerAttack() - 30);
                        player.getHeroes().get(i).getAttr().setArmor(player.getHeroes().get(i).getAttr().getArmor() - (player.getHeroes().get(i).getAttr().getArmor() * 0.1f));
                    }

                    if (heroesNumber < 4 && previousHeroesNumber >= 4) {
                        player.getHeroes().get(i).getAttr().setPowerAttack(player.getHeroes().get(i).getAttr().getPowerAttack() - 60);
                        player.getHeroes().get(i).getAttr().setArmor(player.getHeroes().get(i).getAttr().getArmor() - (player.getHeroes().get(i).getAttr().getArmor() * 0.2f));
                    }
                    break;
                }
            }
        }


    }


}
