package com.company.ability;

import com.company.Game.Game;
import com.company.Hero;

public class SwainAbility extends Ability {
    public SwainAbility(){
        this.abilityName = "SwainAbility";
        this.heroID = 38;
    }
    @Override
    public synchronized Object doTheAbility(String playerName,int heroFrequentNumber) {
        int frequent = 1;
        int xPos = 0, yPos = 0;
        float attackScope = 0;
        float attackerPowerAttack = 50;
        for (int i = 0; i< Game.getPlayers().size(); i++){
            if (Game.getPlayers().get(i).getName().equals(playerName))
                for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++)
                    if (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getID()==this.heroID){
                        if (frequent == heroFrequentNumber){
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().incrementHealth(50);
                            xPos = Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX();
                            yPos = Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY();
                        }
                        else frequent++;
                    }
        }
        for (int i=0;i<Game.getPlayers().size();i++){
            //for just the enemy
            if (!Game.getPlayers().get(i).getName().equals(playerName)){
                for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++){
                    if ((Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX()>=xPos-15 || Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX()<=xPos+15) && (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY()>=yPos-15 || Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY()<=yPos+15)){
                        if ((Game.getPlayers().get(i).getHeroes().get(j).getAttr().getMagicResistance() - attackerPowerAttack)<=0){
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
                    attackerPowerAttack = 50;
                }
            }
        }
        return null;
    }
}
