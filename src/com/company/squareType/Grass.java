package com.company.squareType;

import com.company.Hero;

public class Grass extends SquareTypeEffect {
    @Override
    public Object doSquareTypeEffect(Hero hero) {
        hero.getAttr().setVisionScope(hero.getAttr().getVisionScope()/2);
        hero.getAttr().setAttackScope(hero.getAttr().getAttackScope()/2);
        System.out.println("Hero Now Can See Just Half Vission because he is in the grass");
        return null;
    }

    @Override
    public Object unDoSquareTypeEffect(Hero hero) {
        hero.getAttr().setVisionScope(hero.getAttr().getVisionScope()*2);
        hero.getAttr().setAttackScope(hero.getAttr().getAttackScope()*2);
        System.out.println("Hero Now Can See Just Full Vission because he left the grass");
        return null;
    }
}