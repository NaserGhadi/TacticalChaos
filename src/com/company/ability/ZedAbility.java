package com.company.ability;
import com.company.Game.Game;
import com.company.Hero;
public class ZedAbility extends Ability {
    public ZedAbility(){
        this.abilityName = "ZedAbility";
        this.heroID = 47;
    }
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
                for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();){
                    if ((Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX()>=xPos-25 || Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX()<=xPos+25) && (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY()>=yPos-25 || Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY()<=xPos+25)){
                        Game.getPlayers().get(i).getHeroes().get(j).getAttr().incrementHealth(-250);
                        if (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getHealth()<=0){
                            Game.getPlayers().get(i).getHeroes().get(j).destroyHero();
                            Game.getPlayers().get(i).getHeroes().remove(j);
                        }else j++;
                    }
                }
            }
        }
        return null;
    }
}