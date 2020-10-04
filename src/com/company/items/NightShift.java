package com.company.items;

import com.company.Hero;

public class NightShift extends Item {
    @Override
    public Object doItemAbility(Hero hero) {
        System.out.println("HI: " + hero.getAttr().getPowerAttack());
        System.out.println(hero.getAttr().getPowerAttack()*20/100);
        hero.getAttr().incrementDamage(hero.getAttr().getPowerAttack()*20/100);
        System.out.println("Damage has ben incremented by 20%");
        return null;
    }

    @Override
    public Object unDoItemAbility(Hero hero) {
        hero.getAttr().incrementDamage(hero.getAttr().getPowerAttack()*20/100 * -1);
        System.out.println("Damage has ben deccremented by 20%");
        return null;
    }

    public NightShift(){
        this.itemName = "NightShift";
    }
}