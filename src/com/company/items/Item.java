package com.company.items;

import com.company.Hero;
import com.company.items.interfaces.IItemAbility;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Item implements IItemAbility {
    protected String itemName;
    public static ArrayList<Item> Items;
    public Item(){

    }
    public String getName(){
        return this.itemName;
    }
    @Override
    public Object doItemAbility(Hero hero) {
        return null;
    }

    @Override
    public Object unDoItemAbility(Hero hero) {
        return null;
    }
}
