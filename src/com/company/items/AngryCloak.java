package com.company.items;

import com.company.Hero;

public class AngryCloak extends Item {
    @Override
    public Object doItemAbility(Hero hero) {
        hero.getAttr().setSeriousAttackPower(hero.getAttr().getSeriousAttackPower() + hero.getAttr().getSeriousAttackPower()*20/100);
        System.out.println("Series Power Attack Has been incremented 20% successfully");
        return null;
    }

    @Override
    public Object unDoItemAbility(Hero hero) {
        hero.getAttr().setSeriousAttackPower(hero.getAttr().getSeriousAttackPower() - hero.getAttr().getSeriousAttackPower()*20/100);
        System.out.println("Series Power Attack Has been decremented 20% successfully");
        return null;
    }

    public AngryCloak(){
        this.itemName = "AngryCloak";
    }
}
