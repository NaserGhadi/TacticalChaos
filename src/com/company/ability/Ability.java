package com.company.ability;
import com.company.AfterLife;
import com.company.Game.Game;
import com.company.Hero;
import com.company.Players.AutoPlayer;
import com.company.Players.ConsolePlayer;
import com.company.ability.interfaces.doTheAbility;

import java.io.Serializable;

public abstract class  Ability  implements doTheAbility {
    protected String abilityName;
    protected int heroID;
    @Override
    public synchronized Object doTheAbility(String playerName,int heroFrequentNumber) {
        return null;
    }
    public synchronized Boolean checkIfConsolePlayer(String playerName){
        if (Game.getPlayers().size() <= 1 || this.checkForLonely()) {
            System.out.println("Game Ended");
            return false;
        }
        for (int i=0;i< Game.getPlayers().size();i++){
            if (Game.getPlayers().get(i).getName().equals(playerName)){
                if (Game.getPlayers().get(i) instanceof ConsolePlayer){
                    return true;
                }else if (Game.getPlayers().get(i) instanceof AutoPlayer) {
                    return false;
                }
            }
        }
        return false;
    }
    public void print(){
        for (int i=0;i< Game.getPlayers().size();i++){
            for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++){
                Game.getPlayers().get(i).getHeroes().get(j).getAttr().displayHeroInfo();
            }
        }
    }
    public boolean checkForLonely() {
        if (Game.getPlayers().size()<=1){
            System.out.println("Game Ended");
            try{
                Game.file.close();
                AfterLife.start();
            }catch (Exception ex){

            }
            return true;
        }
        int numberOfActivePlayers = 0;
        for (int i=0;i<Game.getPlayers().size();){
            boolean oneHeroAtLeast = false;
            for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++){
                if (Game.getPlayers().get(i).getHeroes().size()>=1){
                    oneHeroAtLeast = true;
                }
            }
            if (oneHeroAtLeast){
                numberOfActivePlayers++;
                i++;
            }else {
                System.out.println("Player: " + Game.getPlayers().get(i).getName() + " Is Dead");
                Game.getPlayers().remove(i);
            }
        }
        if (numberOfActivePlayers>=2){
            return false;
        }
        try{
            System.out.println("Game Ended");
            Game.file.close();
            AfterLife.start();
        }catch (Exception ex){

        }
        return true;
    }
    public void autoPlayerTurn(String playerName){
        String playerNameConf = "";
        int choice = 0;
        if (Game.getPlayers().size()>=2){
            while (true){
                playerNameConf = Game.getPlayers().get((int)Math.floor(Math.random()*Game.getPlayers().size())).getName();
                if (!playerNameConf.equals(playerName)){
                    break;
                }
            }
        }else {
            System.out.println("You are alone");
        }
        for (int i=0;i<Game.getPlayers().size();i++){
            if (playerName.equals(Game.getPlayers().get(i).getName())){
                if (Game.getPlayers().get(i).getHeroes().size()>=2){
                    choice = Game.getPlayers().get(i).getHeroes().get((int)Math.floor(Math.random()*Game.getPlayers().get(i).getHeroes().size())).getAttr().getID();
                }else {
                    choice = Game.getPlayers().get(i).getHeroes().get(0).getAttr().getID();
                }
            }
        }
        System.out.println("The random player is : " + playerNameConf + " The Random Hero Is: " + choice);
    }
}