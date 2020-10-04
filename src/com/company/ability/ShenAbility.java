package com.company.ability;

import com.company.Game.Game;
import com.company.Hero;

public class ShenAbility extends Ability {
    public ShenAbility(){
        this.abilityName = "ShenAbility";
        this.heroID = 35;
    }
    @Override
    public synchronized Object doTheAbility(String playerName,int heroFrequentNumber) {
        int xPos = 0,yPos = 0;
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

        for (int i = 0; i< Game.getPlayers().size(); i++){
            if (Game.getPlayers().get(i).getName().equals(playerName)){
                for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++){
                    if ((Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX()>=xPos-20 || Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX()<=xPos+20) && (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY()>=yPos-20 || Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY()<=yPos+20)) {
                        Game.getPlayers().get(i).getHeroes().get(j).getAttr().setProtectedBasicAttack(true);
                        Game.getPlayers().get(i).getHeroes().get(j).getAttr().setNumberOfRoundBlockedDamage(1);
                        Game.getPlayers().get(i).getHeroes().get(j).getAttr().setBlockedDamage(true);
                        System.out.println("Hero: " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getName() + " got blocked damage for 1 round");
                    }
                }
            }
        }
        return null;
    }
}
