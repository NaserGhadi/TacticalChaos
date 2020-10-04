package com.company.squareType;

import com.company.Hero;
import com.company.squareType.interfaces.ISquareEffect;

public class Terrain extends SquareTypeEffect {
    @Override
    public Object doSquareTypeEffect(Hero hero) {
        return null;
    }

    @Override
    public Object unDoSquareTypeEffect(Hero hero) {
        return super.unDoSquareTypeEffect(hero);
    }
}