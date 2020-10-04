package com.company.items;

import com.company.Hero;
import com.company.groups.Knight;

public class KnightArmor extends Item {
    @Override
    public Object doItemAbility(Hero hero) {
        hero.getAttr().incrementArmor(hero.getAttr().getArmor()*15/100);
        System.out.println("Armor has ben incremented by 15%");
        return null;
    }

    @Override
    public Object unDoItemAbility(Hero hero) {
        hero.getAttr().incrementArmor(hero.getAttr().getArmor()*15/100* -1);
        System.out.println("Armor has ben decremented by 15%");
        return null;
    }

    public KnightArmor(){
        this.itemName = "KnightArmor";
    }
}
