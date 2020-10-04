package com.company.groups;

import com.company.Hero;
import com.company.Players.Player;
import com.company.groups.interfaces.IGroupDoAbility;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class  Group implements IGroupDoAbility {
    protected int ID;
    protected String name;

    public void setID(int groupID){
        this.ID = groupID;
    }
    public void setName(String groupName){
        this.name = groupName;
    }
    public int getID(){
        return this.ID;
    }
    public String getName(){
        return this.name;
    }
    @Override
    public abstract void enableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber);

    @Override
    public abstract void disableAbility(Player player, ArrayList<Player> players, int heroesNumber, int previousHeroesNumber);
}