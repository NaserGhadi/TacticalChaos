package com.company;

import com.company.Game.Game;
import com.company.groups.Group;

import java.io.Serializable;
import java.util.ArrayList;

public class GroupFilter {
    public static Hero.Attribute[] filterGroupList(Hero.Attribute[] heroAttr){
        ArrayList<Hero.Attribute>temp = new ArrayList<>();
        Hero.Attribute[] heroes = new Hero.Attribute[5];
        if (Game.existingGroups!=null){
            for (int i=0;i<heroAttr.length;i++){
                Boolean isExist1 = false;
                for (int j=0;j<Game.existingGroups.size();j++){
                    for (int k=0;k<heroAttr[i].getGroupTypes().size();k++){
                        if (heroAttr[i].getGroupTypes().get(k).getID()==Game.existingGroups.get(j).getID()){
                            isExist1 = true;
                        }
                    }
                }
                if (isExist1)
                    temp.add(heroAttr[i]);
            }
        }else {
            ReadHero readHero = new ReadHero();
            return readHero.getRandomHeros();
        }
        if (temp.size()<=1){
            ReadHero readHero = new ReadHero();
            return readHero.getRandomHeros();
        }
         for (int i=0;i<5;i++)
             heroes[i] = temp.get((int)Math.floor(Math.random()*temp.size()));
         return heroes;
    }
}