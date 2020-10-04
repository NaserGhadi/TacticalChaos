package com.company;
import com.company.Game.Game;
import com.company.Game.Round;
import com.company.documentation.InformationList;
import com.company.documentation.ScoreBoard;
import java.io.IOException;
import java.util.Scanner;
public class AfterLife {
    public static Scanner scanner = new Scanner(System.in);
    public static void start() throws IOException {
//        for (int i=0;i<Game.getPlayers().size();i++){
//            Round.multithreads.get(i).interrupt();
//        }
        if (getWinner()){
            System.out.println("Congrats, Winner: " + Game.getPlayers().get(0).getName());
            ScoreBoard.writeScoreBoard(Game.getPlayers().get(0),true);
        }else {
            System.out.println("Game Over, Or Players Killed Each Other");
        }
        while (true){
            int num = 0;
            System.out.println("Choose What Do You Want: ");
            System.out.println("1- Display all Logged Game Or from specific round.");
            System.out.println("2- Display Score Board.");
            System.out.println("3- Display Game Movement");
            num = scanner.nextInt();
            if (num==1){
                System.out.println("If You want to display all the game Y/N");
                char choice;
                choice = scanner.next().charAt(0);
                if (Character.toLowerCase(choice)=='y'){
                    InformationList.displayArenaInformation(' ');
                }else {
                    System.out.println("Which round you want to reply?");
                    choice = scanner.next().charAt(0);
                    InformationList.displayArenaInformation(choice);
                }
            }else if (num==2){
                ScoreBoard.printWinnersScoreBoard();
            }else if (num==3){
                InformationList.displayArenaMovement();
            }
        }
    }
    public static boolean getWinner(){
        if (Game.getPlayers().size()>=2)
            return false;
        else if (Game.getPlayers().size()==1)
            return true;
        else return false;
    }
}