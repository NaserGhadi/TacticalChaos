package com.company.documentation;
import com.company.Hero;
import com.company.Style.Style;

import java.io.*;
import java.util.Scanner;

public class InformationList {
    private static PrintWriter writer;
    static {
        try {
            writer = new PrintWriter("HeroInformation.txt", "UTF-8");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static Scanner scanner = new Scanner(System.in);

    public static void displayArenaInformation(char RoundNumber) {
        try {
            scanner = new Scanner(new File(".//test.txt"));
            boolean founded = false;
            if (RoundNumber==' '){
                founded = true;
            }
            while (scanner.hasNextLine()){
                String Line = scanner.nextLine();
                if (Line.contains("Round #" + RoundNumber)){
                    founded = true;
                }
                if (founded){
                    System.out.println(Line);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {

        }
    }

    public static void writeInformation(Hero hero) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(".//HeroInformation.txt", true));
            if (writer != null)
                writer.newLine();
            writer.write("Player: " + hero.getAttr().getOwner() + ", Hero: " + hero.getAttr().getName() + ", Move To X: " + hero.getAttr().getPositionX() + ", Y: " + hero.getAttr().getPositionY());
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {

        }
    }

    public static void displayArenaMovement() {
        try {
            scanner = new Scanner(new File(".//HeroInformation.txt"));
            while (scanner.hasNextLine()){
                String Line = scanner.nextLine();
                    System.out.println(Style.ANSI_GREEN  + Line + Style.ANSI_RESET);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e);
        } finally {

        }
    }
}