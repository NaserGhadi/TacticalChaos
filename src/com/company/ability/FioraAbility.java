package com.company.ability;
import com.company.Hero;
import com.company.Game.Game;


import java.util.Scanner;

public class FioraAbility extends Ability{
    @Override
    public synchronized Object doTheAbility(String playerName,int heroFrequentNumber) {
        Scanner scanner = new Scanner(System.in);
        int frequent = 1;
        int xPos = 0, yPos = 0;
        float attackScope = 0;
        for (int i = 0; i< Game.getPlayers().size(); i++){
            if (Game.getPlayers().get(i).getName().equals(playerName))
                for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++) {
                    if (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getID()==this.heroID){
                        if (frequent == heroFrequentNumber){
                            attackScope = Game.getPlayers().get(i).getHeroes().get(j).getAttr().getAttackScope();
                            xPos = Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX();
                            yPos = Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY();
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().setBlockedDamage(true);
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().setNumberOfRoundBlockedDamage(1);
                        }
                        else frequent++;
                    }
                }
        }
        String playerNameConf = "";
        int heroIDConf = 0;
        if (checkIfConsolePlayer(playerName)){
            boolean neighbor = false;
            System.out.println("Disscuss Which Hero And Player You Want To Damage");
            for (int i=0;i<Game.getPlayers().size();i++){
                for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++){
                    if ((Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX()>=xPos-attackScope || Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX()<=xPos+30) && (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY()>=yPos-30 || Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY()<=yPos+attackScope)){
                        neighbor = true;
                        System.out.println("ID: " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getID() + "Hero Name " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getName() + "Player Name: " + Game.getPlayers().get(i).getName() + " Level: " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getHeroLevel());
                    }
                }
            }
            if (!neighbor) {
                System.out.println("You are alone in you attack scope");
                return null;
            }
            System.out.println("Player Name: ");
            playerNameConf = scanner.next();
            System.out.println("Hero ID: ");
            heroIDConf = scanner.nextInt();
        }else {
            if (Game.getPlayers().size()>=2){
                while (true){
                    playerNameConf = Game.getPlayers().get((int)Math.floor(Math.random()*Game.getPlayers().size())).getName();
                    System.out.println("Player Name Conf: " + playerNameConf);
                    if (!playerNameConf.equals(playerName)){
                        break;
                    }
                }
            }else {
                System.out.println("You are alone");
                return null;
            }
            for (int i=0;i<Game.getPlayers().size();i++){
                if (playerNameConf.equals(Game.getPlayers().get(i).getName())){
                    if (Game.getPlayers().get(i).getHeroes().size()>=2){
                        heroIDConf = Game.getPlayers().get(i).getHeroes().get((int)Math.floor(Math.random()*Game.getPlayers().get(i).getHeroes().size())).getAttr().getID();
                    }else {
                        heroIDConf = Game.getPlayers().get(i).getHeroes().get(0).getAttr().getID();
                    }
                }
            }
            System.out.println("The random player is : " + playerNameConf + " The Random Hero Is: " + heroIDConf);
        }

            for (int i=0;i<Game.getPlayers().size();i++){
                if (Game.getPlayers().get(i).getName().equals(playerNameConf)){
                    for (int j=0;j<Game.getPlayers().get(i).getHeroes().size();j++){
                        if ((Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX()>=xPos-attackScope || Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionX()<=xPos+30) && (Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY()>=yPos-30 || Game.getPlayers().get(i).getHeroes().get(j).getAttr().getPositionY()<=yPos+attackScope) && Game.getPlayers().get(i).getHeroes().get(j).getAttr().getID()==heroIDConf){
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().setStunned(true);
                            Game.getPlayers().get(i).getHeroes().get(j).getAttr().setNumberOfRoundStuned(1);
                            System.out.println("Hero " + Game.getPlayers().get(i).getHeroes().get(j).getAttr().getName() + " Stunned successfully");
                        }
                    }
                }
            }
        if (Game.getPlayers().size()<=1 || checkForLonely()){
            System.out.println("Game Ended");
            return null;
        }
            System.out.println("You Stunned A Hero For One Rounde");
        return null;
    }
    public FioraAbility(){
        this.abilityName = "FioraAbility";
        this.heroID = 11;
    }
}