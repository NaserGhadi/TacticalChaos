package com.company.ability;

import com.company.Game.Game;
import com.company.Hero;

public class GarenAbility extends Ability {
    @Override
    public synchronized Object doTheAbility(String playerName,int heroFrequentNumber) {
        int frequent = 1;
        int xPos = 0, yPos = 0;
        for (int i = 0; i< Game.getPlayers().size(); i++){
            if (Game.getPlayers().get(i).getName().equals(playerName))
                for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++)
                    if (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getID()==this.heroID){
                        if (frequent == heroFrequentNumber){
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
                    if ((Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX()>=xPos-25 || Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX()<=xPos+25) && (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY()>=yPos-25 || Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY()<=yPos+25)){
                        Game.getPlayers().get(i).getHeroes().get(j).getAttr().decrementMagicResist(100);
                        System.out.println("Hero: "  + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getName() + " magic resistance decremented by 100 and become" + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getMagicResistance());
                    }
                }
            }
        }
        if (Game.getPlayers().size()<=1 || checkForLonely()){
            System.out.println("Game Ended");
            return null;
        }
        return null;
    }
    public GarenAbility(){
        this.abilityName = "GarenAbility";
        this.heroID = 13;
    }
}
