package com.company.documentation;
import com.company.Game.Game;
import com.company.Main;
import com.company.Save;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GameStatePresent {

    private static final String filepath = ".//GameState";
    public void WriteObjectToFile() {
        System.out.println("SAVE!!");
        try {
            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(Save.getGame());
            objectOut.close();
            System.out.println("The Object  was successfully written to a file");
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.print(ex);
        }
    }

    public Object ReadObjectFromFile() {
        System.out.println("LOAAAAD!!");
        try {
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            System.out.println("The Object has been read from the file");
            Game obj = (Game) objectIn.readObject();
            System.out.println(obj.getMaxRounds());
            objectIn.close();

            return obj;
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.print(ex);
            return null;
        }
    }
}