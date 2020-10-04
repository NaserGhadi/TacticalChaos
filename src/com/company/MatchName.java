package com.company;

import com.company.ability.*;
import com.company.groups.*;
import com.company.groups.Void;

import java.io.Serializable;

public class MatchName {
    public static Group matchGroupByName(String name){
                 if (name .equals("Demon"))return new Demon();
            else if (name .equals ("BladeMaster"))return new BladeMaster();
            else if (name .equals ("Wild"))return new Wild();
            else if (name .equals ("Sorcerer"))return new Sorcerer();
            else if (name .equals ("Assassin"))return new Assassin();
            else if (name .equals ("Ninja"))return new Ninja();
            else if (name .equals ("Imperial"))return new Imperial();
            else if (name .equals ("Dragons"))return new Dragons();
            else if (name .equals ("Glacial"))return new Glacial();
            else if (name .equals ("Elementalist"))return new Elementalist();
            else if (name .equals ("Ranger"))return new Ranger();
            else if (name .equals ("Void"))return new Void();
            else if (name .equals ("Brawler"))return new Brawler();
            else if (name .equals ("Knight"))return new Knight();
            else if (name .equals ("Noble"))return new Noble();
            else if (name .equals ("Pirate"))return new Pirate();
            else if (name .equals ("Gunslinger"))return new Gunslinger();
            else if (name .equals ("Yordle"))return new Yordle();
            else if (name .equals ("Shapeshifter"))return new Shapeshifter();
            else System.out.println("Invalid NONE Group");
            return null;
    }
    public static Ability matchAbilityByID(int ID){
             if (ID==60)return new AatroxAbility();
        else if (ID==61)return new AhriAbility();
        else if (ID==62)return new Akaliability();
        else if (ID==63)return new AniviaAbility();
        else if (ID==64)return new AsheAbility();
        else if (ID==65)return new BrandAbility();
        else if (ID==66)return new ChogathAbility();
        else if (ID==67)return new DariusAbility();
        else if (ID==68)return new DravenAbility();
        else if (ID==69)return new EvelynnAbility();
        else if (ID==70)return new FioraAbility();
        else if (ID==71)return new GankplankAbility();
        else if (ID==72)return new GarenAbility();
        else if (ID==73)return new GnarAbility();
        else if (ID==74)return new GravesAbility();
        else if (ID==75)return new KarthusAbility();
        else if (ID==76)return new KassadinAbility();
        else if (ID==77)return new KatarinaAbility();
        else if (ID==78)return new KayleAbility();
        else if (ID==79)return new KennenAbility();
        else if (ID==80)return new KhazixAbility();
        else if (ID==81)return new KindredAbility();
        else if (ID==82)return new LeonaAbility();
        else if (ID==83)return new LissandraAbility();
        else if (ID==84)return new LucianAbility();
        else if (ID==85)return new LuluAbility();
        else if (ID==86)return new MissFortuneAbility();
        else if (ID==87)return new MordekaiserAbility();
        else if (ID==88)return new MorganaAbility();
        else if (ID==89)return new NidaleAbility();
        else if (ID==90)return new PoppyAbility();
        else if (ID==91)return new PykeAbility();
        else if (ID==92)return new RangerAbility();
        else if (ID==93)return new SejuaniAbility();
        else if (ID==94)return new ShenAbility();
        else if (ID==95)return new ShyvanaAbility();
        else if (ID==96)return new SolAbility();
        else if (ID==97)return new SwainAbility();
        else if (ID==98)return new TalonAbility();
        else if (ID==99)return new TristanaAbility();
        else if (ID==100)return new VarusAbility();
        else if (ID==101)return new VayneAbility();
        else if (ID==102)return new VeigerAbility();
        else if (ID==103)return new VolibearAbility();
        else if (ID==104)return new WarwickAbility();
        else if (ID==105)return new YasuoAbility();
        else if (ID==106)return new ZedAbility();
        else if (ID==107)return new AzirAbility();
        System.out.println("Invalid Ability ID");
        return null;
    }
}