package com.company;

import com.company.Game.Game;

import java.io.Serializable;

public class Save implements Serializable {
    private static Game game;

    public static void setGame(Game game2){
        game = game2;
    }
    public static Game getGame(){
        return game;
    }
}
