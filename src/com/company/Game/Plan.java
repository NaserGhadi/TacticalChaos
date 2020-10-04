package com.company.Game;
import com.company.Players.Player;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Plan {
    protected Player player;
    protected ArrayList<Player> players;
    protected int[] orders = new int[100];
    public ArrayList<Player> getPlayers() {
        return players;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }
    public Plan(Player player, ArrayList<Player> players){
        this.player = player;
        this.players = players;
        for (int i = 0; i < 100; i++) {
            this.orders[i] = -1;
        }
    }
    public abstract void getPlan();
    public abstract void executePlans(Player player) throws IOException;
}