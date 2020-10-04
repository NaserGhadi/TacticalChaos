package com.company.Game;
import com.company.AfterLife;
import com.company.Multithread;
import com.company.Players.Player;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Round {
    private ArrayList<Player> players;
    private Plan[] plans = new Plan[8];
    public static ArrayList<Multithread> multithreads = new ArrayList<>();
    public synchronized static boolean checkForLonely(){
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
        System.out.println("Game Ended");
        try{
            Game.file.close();
            AfterLife.start();
        }catch (Exception ex){

        }
        return true;
    }
    public Round(ArrayList<Player> players, boolean isPrimaryRound) throws FileNotFoundException {
        this.players = players;
        for (int i = 0; i < players.size(); i++) {
            if (isPrimaryRound)
                this.plans[i] = new PrimaryPlan(players.get(i), players);
            else
                this.plans[i] = new SecondaryPlan(players.get(i), players);
        }
    }
    public Round readPlans() throws IOException {
        for (int i1=0;i1<Game.numberOfChosenActions;i1++){
            if (Game.roundIndex>=9){
                System.out.println("Arena Squares Types:\n"); Game.printArenaTypes();
            }
            System.out.println("Arena Items");
            Game.printArenaItems();
            for (int i = 0; i < players.size(); i++) {
                if (Game.roundIndex>=11 && (Game.getPlayers().size()<=1 || checkForLonely())){
                    System.out.println("Game Ended");
                    return null;
                }
                if (i1==1 && plans[i] instanceof PrimaryPlan){
                    break;
                }
                Player actualPlayer = players.get(i);
                actualPlayer.setMoney(actualPlayer.getMoney() + 2);
                if (actualPlayer.getPiratesMoney())
                    actualPlayer.setMoney(actualPlayer.getMoney() + 2);
                plans[i].setPlayer(this.players.get(i));
                plans[i].getPlan();
            }
            int random = ThreadLocalRandom.current().nextInt(1, 3);
            if (random == 1){
                for (int i = 0; i < players.size(); i++) {
                    if (plans[i] instanceof SecondaryPlan){
                        plans[i].executePlans(this.players.get(i));
//                        Multithread thread = new Multithread(i,plans,this.players.get(i));
//                        multithreads.add(thread);
//                        thread.start();
//                        Multithread r = new Multithread(i,plans,this.players.get(i));
//                        new Thread(r).start();
                    }
                }
            }
            if (random == 2){
                for (int i = this.players.size() - 1; i >= 0; i--) {
                    if (plans[i] instanceof SecondaryPlan){
//                        plans[i].executePlans(this.players.get(i));
                        plans[i].executePlans(this.players.get(i));
//                        Multithread thread = new Multithread(i,plans,this.players.get(i));
//                        multithreads.add(thread);
//                        thread.start();
//                        Multithread r = new Multithread(i,plans,this.players.get(i));
//                        new Thread(r).start();
                    }
                }
            }
            for (int i=0;i<this.multithreads.size();i++){
                this.multithreads.remove(i);
            }
        }
        return this;
    }
}
