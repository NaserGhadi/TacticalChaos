package com.company.squareType;

import com.company.Hero;
import com.company.squareType.interfaces.ISquareEffect;

import java.io.Serializable;

public abstract class SquareTypeEffect implements ISquareEffect{
    @Override
    public Object doSquareTypeEffect(Hero hero)  {
        return null;
    }

    @Override
    public Object unDoSquareTypeEffect(Hero hero) {
        return null;
    }
}