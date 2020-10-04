package com.company.items.interfaces;

import com.company.Hero;

public interface IItemAbility {
    public abstract Object doItemAbility(Hero hero);
    public abstract Object unDoItemAbility(Hero hero);
}