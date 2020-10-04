package com.company;

import com.company.Game.Game;
import com.company.Game.Plan;
import com.company.Players.Player;

public class Multithread extends Thread implements Runnable{
    private int i;
    private Plan[] plans;
    private Player player;
    public Multithread getThread(){
        return this;
    }
    public Player getPlayer(){
        return this.player;
    }
    public Multithread(int parameter, Plan[] plans, Player player) {
        this.i = parameter;
        this.plans = plans;
        this.player = player;
    }
    public void run() {
        try {
            if (Game.getPlayers().size() <= 1 || checkForLonely()) {
                System.out.println("Game Ended");
                this.interrupt();
                // this.finalize();
                return;
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        if (!this.isInterrupted()) {
            try {
                this.plans[i].executePlans(player);
            } catch (Exception e) {
                // Throwing an exception
                System.err.print(e);
                System.out.println("Exception is caught");
            }
        }else {

        }
    }
    public boolean checkForLonely() throws Throwable {
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
}