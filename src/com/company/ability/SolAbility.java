package com.company.ability;

import com.company.Game.Game;
import com.company.Hero;

public class SolAbility extends Ability {
    public SolAbility(){
        this.abilityName = "SolAbility";
        this.heroID = 37;
    }
    @Override
    public synchronized Object doTheAbility(String playerName,int heroFrequentNumber) {
        int xPos = 0,yPos = 0;
        int attackerPowerAttack = 100;
        int frequent = 1;
        for (int i = 0; i< Game.getPlayers().size(); i++){
            if (Game.getPlayers().get(i).getName().equals(playerName)){
                for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++){
                    if (this.heroID == Game.getPlayers().get(i).getHeroes().get(j).getAttr().getID()) {
                        if (frequent == heroFrequentNumber){
                            xPos = Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX();
                            yPos = Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY();
                        }else {
                            frequent++;
                        }
                    }
                }
            }
        }
        for (int i=0;i<Game.getPlayers().size();i++){
            //for just the enemy
            if (!Game.getPlayers().get(i).getName().equals(playerName)){
                for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++){
                    if ((Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX()>=xPos-25 || Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX()<=xPos+25) && (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY()>=yPos-25 || Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY()<=yPos+25)){
                        if ((Game.getPlayers().get(i).getHeroes().get(j).getAttr().getMagicResistance() - attackerPowerAttack)<=0){
                            Game.getPlayers().get(i).getHeroes().get(j).moveRight(30);
                            attackerPowerAttack-=Game.getPlayers().get(i).getHeroes().get(j).getAttr().getMagicResistance();
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().setMagicResistance(0);
                            System.out.println("Player " + playerName + " Destroy Hero " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getName() + " Of Player " + Game.getPlayers().get(i).getName() + " Magic Resistance Totally");
                            if ((Game.getPlayers().get(i).getHeroes().get(j).getAttr().getHealth() - attackerPowerAttack) <=0){
                                Game.getPlayers().get(i).getHeroes().get(j).getAttr().setHealth(0);
                                System.out.println("Player " + playerName + " Killed Hero " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getName() + " Of Player " + Game.getPlayers().get(i).getName());
                                Game.getPlayers().get(i).getHeroes().get(j).destroyHero();
                                Game.getPlayers().get(i).getHeroes().remove(Game.getPlayers().get(i).getHeroes().indexOf(Game.getPlayers().get(i).getHeroes().get(j)));
                            }else {
                                Game.getPlayers().get(i).getHeroes().get(j).getAttr().setHealth(Game.getPlayers().get(i).getHeroes().get(j).getAttr().getHealth() - attackerPowerAttack);
                                System.out.println("Player " + playerName + " Destroy Hero " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getName() + " Of Player " + Game.getPlayers().get(i).getName() + " In his health By " + attackerPowerAttack);
                            }
                        }else {
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().setMagicResistance(Game.getPlayers().get(i).getHeroes().get(j).getAttr().getMagicResistance() - attackerPowerAttack);
                            System.out.println("Player " + playerName + " Destroy Hero " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getName() + " Of Player " + Game.getPlayers().get(i).getName() + " In his Magic Ressitance By " + attackerPowerAttack);
                        }
                    }
                    attackerPowerAttack = 100;
                }
            }
        }
        return null;
    }
}