package com.company;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.Scanner;
public class ReadHero {
    public transient Scanner scanner;
    String Word[] = new String[90000];
    String Lines[] = new String[49];
    static String line;
    public Hero.Attribute getHeroFromID(int ID){
        int co = 0;
        try{
            scanner = new Scanner(new File(".//NewHeros.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        line = scanner.nextLine();
        while (scanner.hasNextLine()){
            Word[0] = "";
            co = 0;
            line = scanner.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) == '%')continue;
                    if (line.charAt(i) == ' ') {
                        co++;
                        Word[co] = "";
                    } else {
                        Word[co] += line.charAt(i);
                    }
                }
                if (Integer.parseInt(Word[0])==ID){
                      Hero.Attribute attr  = new Hero.Attribute(Word[0],Word[1],Word[2],Word[3],Word[4],Word[5],Word[6],Word[7],Word[8],Word[9],Word[10],Word[11],Word[12],Word[13],Word[14],Word[15],Word[16],Word[17]);
                      return attr;
                }
        }
        System.out.println("The ID Is Not Valid");
        return null;
    }
    public Hero.Attribute[] getRandomHeros(){
        int co = 0;
        int i=0;
        try{
            scanner = new Scanner(new File(".//NewHeros.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        line = scanner.nextLine();
        while (scanner.hasNextLine()){
            line = scanner.nextLine();
            Lines[i] = line;
            i++;
        }
        Hero.Attribute attr[] = new Hero.Attribute[5];
        for (i=0;i<5;i++){
            co = 0;
            Word[0] = "";
           String singleHero = Lines[(int)Math.floor(Math.random()*48)];
            for (int j = 0; j < singleHero.length(); j++) {
                if (singleHero.charAt(j) == '%')continue;
                if (singleHero.charAt(j) == ' ') {
                    co++;
                    Word[co] = "";
                } else{
                    Word[co] += singleHero.charAt(j);
                }
            }
           attr[i] = new Hero.Attribute(Word[0],Word[1],Word[2],Word[3],Word[4],Word[5],Word[6],Word[7],Word[8],Word[9],Word[10],Word[11],Word[12],Word[13],Word[14],Word[15],Word[16],Word[17]);
        }
        return attr;
    }
    public Hero.Attribute[] getHerosList(){
        int co = 0;
        int i=0;
        try{
            scanner = new Scanner(new File(".//NewHeros.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        line = scanner.nextLine();
        while (scanner.hasNextLine()){
            line = scanner.nextLine();
            Lines[i] = line;
            i++;
        }
        Hero.Attribute attr[] = new Hero.Attribute[48];
        for (i=0;i<48;i++){
            co = 0;
            Word[0] = "";
            String singleHero = Lines[i];
            for (int j = 0; j < singleHero.length(); j++) {
                if (singleHero.charAt(j) == '%')continue;
                if (singleHero.charAt(j) == ' ') {
                    co++;
                    Word[co] = "";
                } else{
                    Word[co] += singleHero.charAt(j);
                }
            }
             attr[i]  = new Hero.Attribute(Word[0],Word[1],Word[2],Word[3],Word[4],Word[5],Word[6],Word[7],Word[8],Word[9],Word[10],Word[11],Word[12],Word[13],Word[14],Word[15],Word[16],Word[17]);
        }
        return attr;
    }
}