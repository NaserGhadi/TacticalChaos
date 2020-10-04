package com.company;

import com.company.Game.Game;
import com.company.Hero;

import java.util.concurrent.ThreadLocalRandom;

public class DamageCalculator {
    public synchronized static Hero getDamagedHero(Hero hero1, Hero hero2){
        Hero newHero = hero2;
        int randomNum = ThreadLocalRandom.current().nextInt(1, 101);
        if (randomNum <= hero1.getAttr().getMissTheAttack())
            hero1.getAttr().setMana(hero1.getAttr().getMana() + hero2.getAttr().getMana());
        newHero.getAttr().setMana(newHero.getAttr().getMana() - hero1.getAttr().getBurningMana());

        if (hero1.getAttr().isStunned() || hero1.getAttr().isGroupStunned() || newHero.getAttr().isImmune()){
            System.out.println(hero1.getAttr().getName() + " mybe is stunned or his group are stunned or " + newHero.getAttr().getName() + " Is immune");
            return newHero;
        }

        float attackPower;
        // Apply Series Attack
        if (randomNum <= hero1.getAttr().getPossibleSeriesAttack())
            attackPower = hero1.getAttr().getSeriousAttackPower();
        // Apply Normal Attack
        else
            attackPower = hero1.getAttr().getPowerAttack();


        attackPower = attackPower - (attackPower * (hero2.getAttr().getDecreaseDamage() / 100));

        if ((newHero.getAttr().getArmor() > 0) && (!hero1.getAttr().isTrueDamage()) ){
            float a = newHero.getAttr().getArmor() - attackPower;
            newHero.getAttr().setArmor(newHero.getAttr().getArmor() - attackPower);
            if (newHero.getAttr().getArmor() <= 0){
                System.out.println("Armor Damaged By: " + attackPower);
                attackPower = -newHero.getAttr().getArmor();
                System.out.println("Helath Damaged by: " + attackPower);
                newHero.getAttr().incrementHealth(newHero.getAttr().getArmor());
                newHero.getAttr().setArmor(0);
            }
            else
                attackPower = 0;
        }else if (newHero.getAttr().getArmor()<=0){
            System.out.println("Helath Damaged by: " + attackPower);
            newHero.getAttr().incrementHealth(attackPower * -1);
        }

        System.out.println(hero1.getAttr().getName() + " attack " + hero2.getAttr().getName() + " with damage: " + attackPower);
        if (newHero.getAttr().getHealth()<=0){
            for (int i=0;i< Game.getPlayers().size();i++){
                for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++){
                    if (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getHealth()<=0 && Game.getPlayers().get(i).getHeroes().get(j).getAttr().getID()==newHero.getAttr().getID()){
                        Game.getPlayers().get(i).getHeroes().get(j).destroyHero();
                        Game.getPlayers().get(i).getHeroes().remove(j);
                        return null;
                    }
                }
            }
        }
        return newHero;
    }
}