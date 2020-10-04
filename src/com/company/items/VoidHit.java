package com.company.items;

import com.company.Hero;

public class VoidHit extends Item {
    @Override
    public Object doItemAbility(Hero hero) {
        hero.getAttr().setMaxHealth(hero.getAttr().getMaxHealth() + hero.getAttr().getMaxHealth()*5/100);
        System.out.println("MaxHealth has ben incremented by 5%");
        return null;
    }

    @Override
    public Object unDoItemAbility(Hero hero) {
        hero.getAttr().setMaxHealth(hero.getAttr().getMaxHealth() + hero.getAttr().getMaxHealth()*5/100 * -1);
        System.out.println("MaxHealth has ben decremented by 5%");
        return null;
    }

    public VoidHit(){
        this.itemName = "VoidHit";
    }
}
