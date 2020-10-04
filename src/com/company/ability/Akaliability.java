package com.company.ability;

import com.company.Game.Game;
import com.company.Hero;

public class Akaliability extends Ability {
    public Akaliability() {
        this.abilityName = "Akaliability";
        this.heroID = 3;
    }

    @Override
    public synchronized Object doTheAbility(String playerName, int heroFrequentNumber) {
        int frequent = 1;
        int xPos = 0, yPos = 0;
        float attackScope = 0;
        for (int i = 0; i < Game.getPlayers().size(); i++) {
            if (Game.getPlayers().get(i).getName().equals(playerName))
                for (int j = 0; j < Game.getPlayers().get(i).getHeroes().size(); j++)
                    if (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getID() == this.heroID) {
                        if (frequent == heroFrequentNumber) {
                            attackScope = Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPowerAttack();
                            xPos = Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX();
                            yPos = Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY();
                        } else frequent++;
                    }
        }
        for (int i = 0; i < Game.getPlayers().size(); i++) {
            //for just the enemy
            if (!Game.getPlayers().get(i).getName().equals(playerName)) {
                for (int j = 0; j < Game.getPlayers().get(i).getHeroes().size(); j++) {
                    if ((Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX() >= xPos - attackScope || Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX() <= xPos + attackScope) && (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY() >= yPos - attackScope || Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY() <= yPos + attackScope)) {
                        Game.getPlayers().get(i).getHeroes().get(j).getAttr().setMaxHealth(Game.getPlayers().get(i).getHeroes().get(j).getAttr().getMaxHealth() * Game.getPlayers().get(i).getHeroes().get(j).getAttr().getMaxHealth() * (10 / 100));
                        if (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getMaxHealth() <= 0) {
                            System.out.println("Player " + playerName + " Destroy Hero " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getName() + " Of Player " + Game.getPlayers().get(i).getName() + " Totally");
                            Game.getPlayers().get(i).getHeroes().get(j).destroyHero();
                            Game.getPlayers().get(i).getHeroes().remove(Game.getPlayers().get(i).getHeroes().get(j));
                        } else {
                            System.out.println("Player " + playerName + " Destroy Hero " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getName() + " Of Player " + Game.getPlayers().get(i).getName() + " Max Health By 10% and become " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getMaxHealth());
                        }
                    }
                }
            }
        }
        if (Game.getPlayers().size()<=1 || checkForLonely()){
            System.out.println("Game Ended");
            return null;
        }
        for (int i = 0; i < Game.getPlayers().size(); i++) {
            for (int j = 0; j < Game.getPlayers().get(i).getHeroes().size(); j++) {
                System.out.println("Player: " + Game.getPlayers().get(i).getName());
                System.out.println("Heros: " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getName() + " Magic Resistance: " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getMagicResistance());
            }
        }
        return null;
    }
}