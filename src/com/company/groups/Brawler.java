package com.company.groups;

import com.company.Hero;
import com.company.Players.ConsolePlayer;
import com.company.Players.Player;

import java.util.ArrayList;

public class Brawler extends Group {
    public Brawler(){
        this.ID = 3;
        this.name =  "Brawler";
    }

    @Override
    public void setName(String groupName) {
        this.name = groupName;
    }

    @Override
    public void enableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber) {
            for (int i = 0; i < player.getHeroes().size(); i++) {
                for (int j = 0; j < player.getHeroes().get(i).getAttr().getGroupTypes().size(); j++) {
                    if (player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID() == this.ID) {
                        if (heroesNumber >= 2 && heroesNumber < 4 && previousHeroesNumber < 2){
                            player.getHeroes().get(i).getAttr().setHealth(player.getHeroes().get(i).getAttr().getHealth() + 250);
                            break;
                        }

                        if (heroesNumber >= 4 && heroesNumber < 6 && previousHeroesNumber < 4){
                            player.getHeroes().get(i).getAttr().setHealth(player.getHeroes().get(i).getAttr().getHealth() + 500);
                            break;
                        }

                        if (heroesNumber >= 6 && previousHeroesNumber < 6){
                            player.getHeroes().get(i).getAttr().setHealth(player.getHeroes().get(i).getAttr().getHealth() + 1000);
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
                    if (player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID() == this.ID) {
                        if (heroesNumber < 2 && previousHeroesNumber >= 2){
                            player.getHeroes().get(i).getAttr().setHealth(player.getHeroes().get(i).getAttr().getHealth() - 250);
                            break;
                        }

                        if (heroesNumber < 4 && heroesNumber >= 2 && previousHeroesNumber >= 4){
                            player.getHeroes().get(i).getAttr().setHealth(player.getHeroes().get(i).getAttr().getHealth() - 500);
                             break;
                        }

                        if (heroesNumber < 6 && heroesNumber >= 4 &&  previousHeroesNumber >= 6){
                            player.getHeroes().get(i).getAttr().setHealth(player.getHeroes().get(i).getAttr().getHealth() - 1000);
                             break;
                        }

                    }
                }
            }
    }

    @Override
    public void setID(int heroID) {
        this.ID = heroID;
    }






}
