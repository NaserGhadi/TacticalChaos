package com.company.items;

import com.company.Hero;

public class MagicHat extends Item {
    @Override
    public Object doItemAbility(Hero hero) {
        hero.getAttr().incrementDamage(hero.getAttr().getPowerAttack()*20/100);
        return null;
    }

    @Override
    public Object unDoItemAbility(Hero hero) {
        hero.getAttr().incrementDamage(hero.getAttr().getPowerAttack()*20/100 * -1);
        return null;
    }

    public MagicHat(){
        this.itemName = "MagicHat";
    }
}
