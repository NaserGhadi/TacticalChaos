package com.company.items;

import com.company.Hero;

public class UniverseCore extends Item {
    @Override
    public Object doItemAbility(Hero hero) {
        hero.getAttr().incrementMagicRes(hero.getAttr().getMagicResistance()*20/100);
        System.out.println("Magic Res has ben incremented by 20%");
        return null;
    }

    @Override
    public Object unDoItemAbility(Hero hero) {
        hero.getAttr().incrementMagicRes(hero.getAttr().getMagicResistance()*20/100 * -1);
        System.out.println("Magic Res has ben decremented by 20%");
        return null;
    }

    public UniverseCore(){
        this.itemName = "UniverseCore";
    }
}
