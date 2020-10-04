package com.company.items;

import com.company.Hero;

public class WarriorGloves extends Item {
    public WarriorGloves(){
        this.itemName = "WarriorGloves";
    }

    @Override
    public Object unDoItemAbility(Hero hero) {
        hero.getAttr().incrementDamage(hero.getAttr().getPowerAttack()*10/100 * -1);
        System.out.println("Damage has ben decremented by 10%");
        return null;
    }

    @Override
    public Object doItemAbility(Hero hero) {
        hero.getAttr().incrementDamage(hero.getAttr().getPowerAttack()*10/100);
        System.out.println("Damage has ben incremented by 10%");
        return null;
    }
}