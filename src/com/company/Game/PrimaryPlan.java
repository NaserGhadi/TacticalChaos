package com.company.Game;
import com.company.Players.AutoPlayer;
import com.company.Players.ConsolePlayer;
import com.company.Players.Player;
import com.company.Style.Style;
import com.company.groups.Group;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class PrimaryPlan extends Plan {
    public PrimaryPlan(Player player, ArrayList<Player> players){
        super(player, players);
    }
    @Override
    public void getPlan() {
        Scanner scnr = new Scanner(System.in);
        boolean exit = false;
        char answer = 'x';
        System.out.println(this.player.getName() + ": it is your turn.");
        System.out.println("Your Arena: ");
        player.printArena(this.players);
        if (player instanceof ConsolePlayer) {
            do {
                try {
                    System.out.println( Style.ANSI_BLUE + "Do you want to buy a hero? <y/n>" + Style.ANSI_RESET);
                    answer = scnr.next().charAt(0);
                    if (answer != 'y' && answer != 'Y' && answer != 'n' && answer != 'N') {
                        System.out.println("Invalid value");
                        continue;
                    }
                    exit = true;
                } catch (InputMismatchException ex) {
                    System.out.println("Invalid input!\n" + ex);
                    scnr.nextLine();
                }
            } while (!exit);
            exit = false;
        } else if (player instanceof AutoPlayer) {
            System.out.println(this.player.getName() + " is playing now...");
            int randomNum = ThreadLocalRandom.current().nextInt(1, 3);
            if (randomNum == 1) answer = 'y';
            if (randomNum == 2) answer = 'n';
        } else {
            answer = scnr.next().charAt(0);
        }
        if (answer == 'y' || answer == 'Y'){
            this.player.buyAHero();
            // Start Check for enabling / disabling the group ability
            for (int i = 0; i < this.player.getHeroes().size(); i++) {
                for (int j = 0; j < this.player.getHeroes().get(i).getAttr().getGroupTypes().size(); j++) {
                    this.player.getHeroes().get(i).getAttr().getGroupsLastStatus().set(j, this.player.getGroupsStatus().get(this.player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID()));
                }
                for (int j = 1; j <= 19; j++) {
                    this.player.getGroupsStatus().set(i, 0);
                }

                for (Group group : this.player.getHeroes().get(i).getAttr().getGroupTypes()) {
                    if (group==null)break;
                    this.player.getGroupsStatus().set(group.getID(), this.player.getGroupsStatus().get(group.getID()) + 1);
                }
            }

            for (int i = 0; i < this.player.getHeroes().size(); i++) {
                for (int j = 0; j < this.player.getHeroes().get(i).getAttr().getGroupTypes().size(); j++) {
                    if (this.player.getHeroes().get(i).getAttr().getGroupsLastStatus().get(j) == this.player.getGroupsStatus().get(this.player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID()))
                        continue;
                    if (this.player.getHeroes().get(i).getAttr().getGroupsLastStatus().get(j) < this.player.getGroupsStatus().get(this.player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID())) {
                        this.player.getHeroes().get(i).getAttr().getGroupTypes().get(j).enableAbility(this.player, this.players, this.player.getGroupsStatus().get(this.player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID()), this.player.getHeroes().get(i).getAttr().getGroupsLastStatus().get(j));
                    }
                    if (this.player.getHeroes().get(i).getAttr().getGroupsLastStatus().get(j) > this.player.getGroupsStatus().get(this.player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID())) {
                        this.player.getHeroes().get(i).getAttr().getGroupTypes().get(j).disableAbility(this.player, this.players, this.player.getGroupsStatus().get(this.player.getHeroes().get(i).getAttr().getGroupTypes().get(j).getID()), this.player.getHeroes().get(i).getAttr().getGroupsLastStatus().get(j));
                    }
                }
            }
            // End Check for enabling / disabling the group ability
        }
    }

    @Override
    public void executePlans(Player player) {

    }
}
