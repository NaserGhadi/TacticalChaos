package com.company;
import com.company.Game.Game;
import com.company.ability.Ability;
import com.company.documentation.InformationList;
import com.company.groups.Group;
import com.company.items.Item;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

public class Hero {
    private Attribute attr;
    public transient Scanner scanner = new Scanner(System.in);
    public static class Attribute implements Serializable{
        private int ID;
        private int positionX;
        private int positionY;
        private float health;
        private float shield;
        private float goldPrice;
        private float armor;
        private float magicResistance;
        private float speed;
        private float powerAttack;
        private float attackScope;
        private float visionScope;
        private float manaStart;
        private float manaCost;
        private float possibleSeriesAttack;
        private float seriousAttackPower;
        private float mana;
        private float costMana;
        private int heroLevel;
        private float maxHealth;
        private boolean isImmune = false;
        private boolean isStunned = false;
        private boolean isAlive = true;
        private boolean trueDamage = false;
        private int missTheAttack = 0;
        private int decreaseDamage = 0;
        private int doubleMana = 0;
        private int burningMana = 0;
        private int numberOfRoundStuned = 0;
        private int numberOfRoundBlockedDamage = 0;
        private int numberOfRoundPreventedMana = 0;
        private int numberOfRoundPreventedAbility = 0;
        private int numberOfRoundDoubleRangeDoubleDamage = 0;
        private int numberOfRoundFullPossibleSeriesAttack = 0;
        private int numberOfRound400SeriesPowerAttack = 0;
        private float maxMagicResistance = 0;
        private boolean fullPossibleSeriesAttack = false;
        private float maxpossibleSeriesAttack;
        private boolean preventedAbility = false;
        private boolean blockedDamage = false;
        private boolean doubleRangeDoubleDamage = false;
        private boolean isPreventedMana = false;
        private boolean isPreventedAbility = false;
        private boolean isProtectedBasicAttack = true;
        private boolean _400SeriesPowerAttack = false;
        private boolean isGroupStunned = false;
        private float maxSeriesPowerAttack = 0;
        private String name;
        private String owner;
        private Ability ability;
        private ArrayList<Group> groupTypes = new ArrayList<>();
        private Item[] itemsTypes = new Item[3];
        private ArrayList<Integer> groupsLastStatus = new ArrayList<>();
        private ArrayList<Item> existingItems = new ArrayList<>(3);
        private Object heroPosition;
        public Attribute(String ID, String name, String group1,String group2,String group3,String goldPrice,String health,String armor,String magicResistance,String attackScope,String visionScope,String powerAttack, String speed,  String possibleSeriesAttack,  String seriousAttackPower, String manaStart,String manaCost, String ability) {
            this.visionScope = Float.parseFloat(visionScope);
            this.heroLevel = 1;
            this.attackScope = Float.parseFloat(attackScope);
            this.magicResistance = Float.parseFloat(magicResistance);
            this.goldPrice = Float.parseFloat(goldPrice);
            this.armor = Float.parseFloat(armor);
            this.ID = Integer.parseInt(ID);
            this.name = name;
            this.health = Float.parseFloat(health);
            this.maxHealth = this.health;
            this.magicResistance = Float.parseFloat(magicResistance);
            this.speed = Float.parseFloat(speed);
            this.groupTypes.add(MatchName.matchGroupByName(group1));
            this.groupTypes.add(MatchName.matchGroupByName(group2));
            if (!group3.equals("NONE"))
                this.groupTypes.add(MatchName.matchGroupByName(group3));
            this.possibleSeriesAttack = Float.parseFloat(possibleSeriesAttack);
            this.seriousAttackPower = Float.parseFloat(seriousAttackPower);
            this.manaCost = Float.parseFloat(manaCost);
            this.manaStart = Float.parseFloat(manaStart);
            this.mana = this.manaStart;
            this.ability =  MatchName.matchAbilityByID(Integer.parseInt(ability));
            this.powerAttack = Float.parseFloat(powerAttack);
            for (int i = 0; i < this.groupTypes.size(); i++) {
                this.groupsLastStatus.add(1);
            }
            this.maxSeriesPowerAttack = this.seriousAttackPower;
            this.maxMagicResistance = this.magicResistance;
        }

        public ArrayList<Item> getExistingItems() {
            return existingItems;
        }

        public void setExistingItems(ArrayList<Item> existingItems) {
            this.existingItems = existingItems;
        }



        public boolean isGroupStunned() {
            return isGroupStunned;
        }
        public void setGroupStunned(boolean groupStunned) {
            isGroupStunned = groupStunned;
        }
        public void setNumberOfRound400SeriesPowerAttack(int numberOfRound400SeriesPowerAttack) {
            this.numberOfRound400SeriesPowerAttack = numberOfRound400SeriesPowerAttack;
        }
        public boolean isBlockedDamage() {
            return blockedDamage;
        }
        public void setBlockedDamage(boolean blockedDamage) {
            this.blockedDamage = blockedDamage;
        }
        public void set_400SeriesPowerAttack(boolean _400SeriesPowerAttack) {
            this._400SeriesPowerAttack = _400SeriesPowerAttack;
        }
        public void setNumberOfRoundFullPossibleSeriesAttack(int numberOfRoundFullPossibleSeriesAttack) {
            this.numberOfRoundFullPossibleSeriesAttack = numberOfRoundFullPossibleSeriesAttack;
        }
        public void setFullPossibleSeriesAttack(boolean fullPossibleSeriesAttack) {
            this.fullPossibleSeriesAttack = fullPossibleSeriesAttack;
        }
        public void setNumberOfRoundDoubleRangeDoubleDamage(int numberOfRoundDoubleRangeDoubleDamage) {
            this.numberOfRoundDoubleRangeDoubleDamage = numberOfRoundDoubleRangeDoubleDamage;
        }
        public void setDoubleRangeDoubleDamage(boolean doubleRangeDoubleDamage) {
            this.doubleRangeDoubleDamage = doubleRangeDoubleDamage;
        }
        public boolean getPreventedAbility(){
            return this.preventedAbility;
        }
        public void decrementNumberOfPreventedAbility(){
            this.numberOfRoundPreventedAbility--;
            if (this.numberOfRoundPreventedAbility<=0){
                this.numberOfRoundPreventedAbility = 0;
                this.preventedAbility = false;
            }
        }
        public void setNumberOfRoundBlockedDamage(int numberOfRoundBlockedDamage) {
            this.numberOfRoundBlockedDamage = numberOfRoundBlockedDamage;
        }
        public void setNumberOfRoundPreventedMana(int counter){
            this.numberOfRoundPreventedMana = counter;
        }
        public void decrementNumberOfRoundBlockedDamage(){
            this.numberOfRoundBlockedDamage--;
            if (this.numberOfRoundBlockedDamage<=0){
                this.numberOfRoundBlockedDamage = 0;
                this.blockedDamage = false;
            }
            else {
                this.blockedDamage = true;
            }
        }
        public void setNumberOfRoundStuned(int numberOfRoundStuned) {
            this.isStunned = true;
            this.numberOfRoundStuned = numberOfRoundStuned;
        }
        public void decrementNumberOfRoundStuned(){
            this.numberOfRoundStuned--;
            if (this.numberOfRoundStuned<=0){
                this.isStunned = false;
                this.numberOfRoundStuned = 0;
            }
        }
        public float getMaxpossibleSeriesAttack() {
            return maxpossibleSeriesAttack;
        }
        public void setProtectedBasicAttack(boolean protectedBasicAttack) { isProtectedBasicAttack = protectedBasicAttack; }
        public void setPreventedAbility(boolean preventedAbility) {
            isPreventedAbility = preventedAbility;
        }
        public void setPreventedMana(boolean preventedMana) {
            isPreventedMana = preventedMana;
        }
        public int getMissTheAttack() {
            return missTheAttack;
        }
        public boolean isTrueDamage() {
            return trueDamage;
        }
        public void setTrueDamage(boolean trueDamage) {
            this.trueDamage = trueDamage;
        }
        public int getDecreaseDamage() {
            return decreaseDamage;
        }
        public void setDecreaseDamage(int decreaseDamage) {
            this.decreaseDamage = decreaseDamage;
        }
        public int getDoubleMana() {
            return doubleMana;
        }
        public void setDoubleMana(int doubleMana) {
            this.doubleMana = doubleMana;
        }
        public int getBurningMana() {
            return burningMana;
        }
        public void setBurningMana(int burningMana) {
            this.burningMana = burningMana;
        }
        public ArrayList<Integer> getGroupsLastStatus() {
            return groupsLastStatus;
        }
        public boolean isStunned() {
            return isStunned;
        }
        public void setStunned(boolean stunned) {
            isStunned = stunned;
        }
        public void incrementArmor(float Counter){
            this.armor+=Counter;
        }
        public float getGoldPrice() {
            return goldPrice;
        }
        public void setMagicResistance(float magicResistance) {
            if (magicResistance<=0)
                this.magicResistance = 0;
            if (magicResistance>=this.maxMagicResistance){
                this.magicResistance = this.maxMagicResistance;
            }
            else this.magicResistance = magicResistance;
        }
        public void setSpeed(float speed) {
            this.speed = speed;
        }
        public void setAttackScope(float attackScope) {
            this.attackScope = attackScope;
        }
        public void setVisionScope(float visionScope) {
            this.visionScope = visionScope;
        }
        public void setSeriousAttackPower(float seriousAttackPower) {
            this.seriousAttackPower = seriousAttackPower;
        }
        public void setMana(float mana) {
            if (this.mana<=0){
                this.mana = 0;
            }
            else this.mana = mana;
        }
        public void setMaxHealth(float maxHealth) {
            this.maxHealth = maxHealth;
        }
        public boolean isImmune() {
            return isImmune;
        }
        public void setImmune(boolean immune) {
            isImmune = immune;
        }
        public void setName(String name) {
            this.name = name;
        }
        public void setAbility(Ability ability) {
            this.ability = ability;
        }
        public void incrementMagicRes(float Counter){
            if (this.magicResistance + Counter <=0){
                this.magicResistance = 0;
            }
            else if (this.maxMagicResistance<=this.magicResistance){
                this.magicResistance = this.maxMagicResistance;
            }
            else if (this.magicResistance + Counter>=this.maxMagicResistance){
                this.magicResistance = this.maxMagicResistance;
            }
            else this.magicResistance+=Counter;
        }
        public void setHeroLevel(int level){this.heroLevel = level;}
        public int getHeroLevel(){return this.heroLevel;}
        public float getArmor() {
            return armor;
        }
        public void setOwner(String owner){
            this.owner = owner;
        }
        public String getOwner(){return this.owner;}
        public void setArmor(float armor) {
            this.armor = armor;
        }
        public void setHealth(float health) {
            if (this.health<=0){
                this.health = 0;
                return ;
            }
            if (this.health>=this.maxHealth){
                this.health = this.maxHealth;
            }
            else this.health = health;
        }
        public ArrayList<Group> getGroupTypes() {
            return groupTypes;
        }
        public float getMaxHealth(){return this.maxHealth;}
        public int getPositionX() {
            return positionX;
        }
        public String getName(){return this.name;}
        public void setPositionX(int positionX) {
            this.positionX = positionX;
        }
        public int getPositionY() {
            return positionY;
        }
        public void setPositionY(int positionY) {
            this.positionY = positionY;
        }
        public synchronized void displayHeroInfo(){
            System.out.println("The Hero's Information Are:");
            System.out.println("Name: " + this.name);
            System.out.println("Level: "+ this.heroLevel);
            System.out.println("Player: " +  this.owner);
            System.out.println("Health: "+ this.health);
            System.out.println("Max Health: " + this.maxHealth);
            System.out.println("Magic Resistance: " + this.magicResistance + "%");
            System.out.println("Attack Scope: " + this.attackScope);
            System.out.println("Vission Scope: "+ this.visionScope);
            System.out.println("Speed: "+ this.speed);
            System.out.println("Possible Series Attack: " + this.possibleSeriesAttack + "%");
            System.out.println("Series Attack Power: "+ this.seriousAttackPower + "%");
            System.out.println("Mana: "+ this.mana);
            System.out.println("Mana Start: " + this.manaStart);
            System.out.println("Ability: "+ this.name + "Ability");
            System.out.println("Power Attack: " + this.powerAttack);
            System.out.println("Mana Price: " + this.manaCost);
            System.out.println("Hero Price: " + this.goldPrice);
            System.out.println("Armor: " + this.armor + "%");
            System.out.println("Groups: ");
            for (int i=0;i<this.groupTypes.size();i++){
                if (this.groupTypes.get(i) == null || this.groupTypes.get(i).getName().equals("NONE"))
                    break;
                System.out.println(i+1 + "- " + this.groupTypes.get(i).getName());
            }
            System.out.println("Hero xPos: " + this.positionX + " , yPos: " + this.positionY);
            if (this.isAlive)System.out.println("Is Alive: YES");
            else System.out.println("Is Alive: DEAD");
            for (int i=0;i<this.existingItems.size();i++){
                System.out.println("Item: " + this.existingItems.get(i).getName());
            }
        }
        public void setHealth(int health){
            if (health<=0){
                this.health = 0;
                return ;
            }
            if (health>=this.maxHealth){
                this.health = this.maxHealth;
            }
            else this.health = health;
        }
        public void setPossibleSeriesAttack(float counter){
            if (this.possibleSeriesAttack + counter <=0){
                this.possibleSeriesAttack = 0;
                return ;
            }
            this.possibleSeriesAttack = counter;
        }
        public float getMagicResistance(){
            if (this.magicResistance<=0)this.magicResistance = 0;
            return this.magicResistance;
        }
        public void setSpeed(int speed){
            this.speed = speed;
        }
        public void incrementSpeed(float counter){
            this.speed+=counter;
        }
        public void incrementDamage(float counter){
            if (this.powerAttack+counter<=0)this.powerAttack = 0;
            else this.powerAttack+=counter;
        }
        public void incrementVisionScope(float counter){
            this.visionScope+=counter;
        }
        public void incrementAttackScope(float counter){
            this.attackScope+=counter;
        }
        public Ability getAbility(){
            return this.ability;
        }
        public float getSpeed(){
            return this.speed;
        }
        public void incrementMana(float counter){
            if (this.mana+counter<=0)
                this.mana = 0;
            else this.mana+=counter;
        }
        public void decrementMagicResist(float counter){
            this.magicResistance-=counter;
            if (this.magicResistance<=0){
                this.magicResistance=0;
            }
        }
        public void incrementHealth(float counter){
            if (this.health + counter <=0){
                this.health = 0;
                return ;
            }else if (this.health + counter >=this.maxHealth){
                this.health = this.maxHealth;
            }
            else this.health+=counter;
        }
        public float getPossibleSeriesAttack() {
            return possibleSeriesAttack;
        }
        public float getSeriousAttackPower() { return seriousAttackPower; }
        public float getMana() {
            if (this.mana<=0)this.mana = 0;
            return mana;
        }
        public int getID() {
            return this.ID;
        }
        public float getHealth() {
            return health;
        }
        public float getPowerAttack() {
            return powerAttack;
        }
        public float getAttackScope() {
            return attackScope;
        }
        public float getVisionScope() {
            return visionScope;
        }
        public void setID(int id) {
            this.ID = id;
        }
        public Boolean IsAlive(){
            return this.isAlive;
        }
        public void killHero(){
            this.isAlive = false;
        }
        public void setPowerAttack(float v) {
           if (v<=0)this.powerAttack = 0;
           else this.powerAttack = v;
        }
    }
    public void addExistingItems(Item newItem) {
        if (this.getAttr().existingItems.size()>=3){
            System.out.println("Hero Can't have " + newItem.getName() + " Because he has enough items");
            return ;
        }
        System.out.println("Before: Damage: " + this.getAttr().getPowerAttack());
        newItem.doItemAbility(this);
        System.out.println("After: Damage: " + this.getAttr().getPowerAttack());
        this.getAttr().existingItems.add(newItem);
    }
    public Hero(Attribute attr) {
        this.attr = attr;
    }
    public Hero(){}
    public Hero.Attribute getAttr(){
        return attr;
    }
    public void destroyHero(){
        System.out.println("Hero " + this.getAttr().getName() + " Is Dead..");
        if (this.getAttr().existingItems.size()>=1){
            for (int i=0;i<this.getAttr().existingItems.size();i++){
                (Game.arenaItems[this.getAttr().getPositionX()][this.getAttr().getPositionY()]).add(this.getAttr().existingItems.get(i));
            }
        }

        this.getAttr().killHero();
        this.attr = null;
        Game.printArenaItems();
    }
    public void attack() {

    }
// Start basic move functions
    public synchronized boolean moveRight(int placesNumber) {
        if (Game.arenaHeight - 1<getAttr().getPositionY() + placesNumber){
            System.out.println("Invalid Place");
            return false;
        }
        boolean isTerran = false;
        if (placesNumber <= getAttr().getSpeed()){
            for (int i = getAttr().getPositionY() + 1;i<=placesNumber+getAttr().getPositionY();i++){
                if (Game.arenaTypes[this.getAttr().getPositionX()][i].equals("T")){
                    System.out.println("You Are Next To Terran Area <" + this.getAttr().getPositionX() + "," + i + "> Type so you can't go throw it");
                    getAttr().setPositionY(i-1);
                    isTerran = true;
                    return true;
                }else if (Game.arenaTypes[this.getAttr().getPositionX()][i].equals("W") && i!=placesNumber+getAttr().getPositionY()){
                    System.out.println("You Are In Water Square So Your movement will be decreased by one");
                    placesNumber--;
                }
            }
            if (!isTerran){
                this.getAttr().setPositionY(getAttr().getPositionY() + placesNumber);
            }
        }

        else{
            boolean isWater = false;
            int isWaterCounter = 0;
            for (int i = getAttr().getPositionY() + 1;i<=getAttr().getPositionY()+getAttr().getSpeed();i++){
                if (Game.arenaTypes[this.getAttr().getPositionX()][i].equals("T")){
                    System.out.println("You Are Next To Terran Area <" + this.getAttr().getPositionX() + "," + i + "> Type so you can't go throw it");
                    getAttr().setPositionY(i-1);
                    isTerran = true;
                    return true;
                }else if (Game.arenaTypes[this.getAttr().getPositionX()][i].equals("W") && i!=getAttr().getPositionY()+getAttr().getSpeed()){
                    System.out.println("You Are In Water Square So Your movement will be decreased by one");
                    isWater = true;
                    isWaterCounter++;
                }
            }
            if (!isTerran && isWater){
                this.getAttr().setPositionY((int) (getAttr().getPositionY() + getAttr().getSpeed() - isWaterCounter));
            }else this.getAttr().setPositionY((int) (getAttr().getPositionY() + getAttr().getSpeed()));
        }

        InformationList.writeInformation(this);
        return true;
    }
    public synchronized boolean moveLeft(int placesNumber) {
        if (getAttr().getPositionY() - placesNumber<0){
            System.out.println("Invalid Place");
            return false;
        }
        else if (placesNumber <= getAttr().getSpeed()){
            boolean isTerran = false;
            for (int i = getAttr().getPositionY()-1;i>=getAttr().getPositionY() - placesNumber;i--){
                System.out.println(getAttr().getPositionY() - placesNumber);
                if (Game.arenaTypes[this.getAttr().getPositionX()][i].equals("T")){
                    System.out.println("You Are Next To Terran Area <" +this.getAttr().getPositionX() + "," + i + "> Type so you can't go throw it");
                    getAttr().setPositionY(i+1);
                    isTerran = true;
                    return true;
                }else if (Game.arenaTypes[this.getAttr().getPositionX()][i].equals("W") && getAttr().getPositionY() - placesNumber!=i){
                    System.out.println("You Are In Water Square So Your movement will be decreased by one");
                    getAttr().setPositionY(getAttr().getPositionY()+1);
                }
            }
            if (!isTerran){
                getAttr().setPositionY(getAttr().getPositionY() - placesNumber);
            }
        }

        else{
            boolean isTerran = false;
            boolean isWater = false;
            int isWaterCounter = 0;
            for (int i = getAttr().getPositionY()-1;i>=getAttr().getPositionY() - getAttr().getSpeed() ;i--){
                System.out.println(i);
                if (Game.arenaTypes[this.getAttr().getPositionX()][i].equals("T")){
                    System.out.println("You Are Next To Terran Area <" +this.getAttr().getPositionX() + "," + i + "> Type so you can't go throw it");
                    getAttr().setPositionY(i+1);
                    isTerran = true;
                    return true;
                }else if (Game.arenaTypes[this.getAttr().getPositionY()][i].equals("W") && i!=getAttr().getPositionY() - getAttr().getSpeed()){
                    System.out.println("You Are In Water Square So Your movement will be decreased by one");
                    isWater = true;
                    getAttr().setPositionY(getAttr().getPositionY()+1);
                    isWaterCounter++;
                }
            }
            if (!isTerran && isWater){
                getAttr().setPositionY((int) (getAttr().getPositionY() - getAttr().getSpeed() + isWaterCounter));
            }else getAttr().setPositionY((int) (getAttr().getPositionY() - getAttr().getSpeed()));

        }
        InformationList.writeInformation(this);
        return true;
    }

    public synchronized boolean moveDown(int placesNumber) {
        if (Game.arenaWidth - 1 < getAttr().getPositionX() + placesNumber){
            System.out.println("Invalid Place");
            return false;
        }
            boolean isTerran = false;
            if (placesNumber <= getAttr().getSpeed()){
                for (int i = getAttr().getPositionX()+1;i<=getAttr().getPositionX() + placesNumber;i++){
                    System.out.println("<" + i + "," + this.getAttr().getPositionY()  + ">");
                    if (Game.arenaTypes[i][this.getAttr().getPositionY()].equals("T")){
                        System.out.println("You Are Next To Terran Area <" + i + "," + this.getAttr().getPositionY()  + "> Type so you can't go throw it");
                        getAttr().setPositionX(i-1);
                        isTerran = true;
                        return true;
                    }else if (Game.arenaTypes[i][this.getAttr().getPositionY()].equals("W") && i!=getAttr().getPositionX() + placesNumber){
                        System.out.println("You Are In Water Square So Your movement will be decreased by one");
                        getAttr().setPositionX(getAttr().getPositionX()-1);
                    }
                }
                if (!isTerran)
                     getAttr().setPositionX(getAttr().getPositionX() + placesNumber);
            }

        else{
            boolean isWater = false;
            int isWaterCounter = 0;
                for (int i = getAttr().getPositionX()+1;i<=getAttr().getSpeed() + getAttr().getPositionX();i++){
                    if (Game.arenaTypes[i][this.getAttr().getPositionY()].equals("T")){
                        System.out.println("You Are Next To Terran Area <" + i + "," + this.getAttr().getPositionY() + "> Type so you can't go throw it");
                        getAttr().setPositionX(i-1);
                        isTerran = true;
                        return true;
                    }else if (Game.arenaTypes[i][this.getAttr().getPositionY()].equals("W") && i!=getAttr().getSpeed() + getAttr().getPositionX()){
                        System.out.println("You Are In Water Square So Your movement will be decreased by one");
                        isWaterCounter++;
                        isWater = true;
                    }
                }
                if (!isTerran && isWater)
                    getAttr().setPositionX((int) (getAttr().getPositionX() + getAttr().getSpeed() - isWaterCounter));
            }

        InformationList.writeInformation(this);
        return true;
    }

    public synchronized boolean moveUp(int placesNumber) {
        if ((getAttr().getPositionX() - placesNumber)<0){
            System.out.println("Invalid Place");
            return false;
        }
        boolean isTerran = false;
        if (placesNumber <= getAttr().getSpeed()){
            for (int i = getAttr().getPositionX()-1;i>=getAttr().getPositionX()-placesNumber;i--){
                System.out.println(i);
                System.out.println(getAttr().getPositionX()-placesNumber);
                if (Game.arenaTypes[i][this.getAttr().getPositionY()].equals("T")){
                    System.out.println("You Are Next To Terran Area <" + i + "," +this.getAttr().getPositionY() + "> Type so you can't go throw it");
                    getAttr().setPositionX(i+1);
                    isTerran = true;
                    return true;
                }else if (Game.arenaTypes[i][this.getAttr().getPositionY()].equals("W") && i!=getAttr().getPositionX()-placesNumber){
                    System.out.println("You Are In Water Square So Your movement will be decreased by one");
                    getAttr().setPositionX(getAttr().getPositionX()+1);
                }
            }
            if (!isTerran)
                getAttr().setPositionX(getAttr().getPositionX() - placesNumber);
        }
        else{
            boolean isWater = false;
            int isWaterCounter = 0;
            for (int i = getAttr().getPositionX()-1;i>=getAttr().getPositionX()-getAttr().getSpeed();i--){
                System.out.println(i);
                if (Game.arenaTypes[i][this.getAttr().getPositionY()].equals("T")){
                    System.out.println("You Are Next To Terran Area Type so you can't go throw it");
                    getAttr().setPositionX(i+1);
                    isTerran = true;
                    return true;
                }else if (Game.arenaTypes[i][this.getAttr().getPositionY()].equals("W") && getAttr().getPositionX()-getAttr().getSpeed()!=i){
                    isWater = true;
                    isWaterCounter++;
                    System.out.println("You Are In Water Square So Your movement will be decreased by one");
                    getAttr().setPositionX(getAttr().getPositionX()+1);
                }
            }
            if (!isTerran && isWater)
                getAttr().setPositionX((int)(getAttr().getPositionX() - getAttr().getSpeed() + isWaterCounter));
            else getAttr().setPositionX((int)(getAttr().getPositionX() - getAttr().getSpeed()));
        }
        InformationList.writeInformation(this);
        return true;
    }

    public synchronized boolean moveDown_Right(int placesNumber) {
        System.out.println(getAttr().getPositionX()  + " Is X");
        System.out.println(getAttr().getPositionY() + " Is Y");
        System.out.println(Game.arenaWidth);
        if (Game.arenaWidth - 1 < getAttr().getPositionX() + placesNumber || Game.arenaHeight - 1<getAttr().getPositionY() + placesNumber){
            System.out.println("Invalid Place");
            return false;
        }
        else if (placesNumber <= getAttr().getSpeed()){
            //for moving Down:
            boolean isTerran = false;
            if (placesNumber <= getAttr().getSpeed()){
                for (int i = getAttr().getPositionX()+1;i<=getAttr().getPositionX() + placesNumber;i++){
                    System.out.println("<" + i + "," + this.getAttr().getPositionY()  + ">");
                    if (Game.arenaTypes[i][this.getAttr().getPositionY()].equals("T")){
                        System.out.println("You Are Next To Terran Area <" + i + "," + this.getAttr().getPositionY()  + "> Type so you can't go throw it");
                        getAttr().setPositionX(i-1);
                        isTerran = true;
                        return true;
                    }else if (Game.arenaTypes[i][this.getAttr().getPositionY()].equals("W") && i!=getAttr().getPositionX() + placesNumber){
                        placesNumber--;
                        System.out.println("You Are In Water Square So Your movement will be decreased by one");
                    }
                }
                if (!isTerran)
                    getAttr().setPositionX(getAttr().getPositionX() + placesNumber);
            }

            //for moving right:
            for (int i = getAttr().getPositionY()+1;i<=getAttr().getPositionY()+placesNumber;i++){
                if (Game.arenaTypes[this.getAttr().getPositionX()][i].equals("T")){
                    System.out.println("You Are Next To Terran Area <" + this.getAttr().getPositionX() + "," + i + "> Type so you can't go throw it");
                    getAttr().setPositionY(i-1);
                    isTerran = true;
                    return true;
                }else if (Game.arenaTypes[this.getAttr().getPositionX()][i].equals("W") && i!=getAttr().getPositionY()+placesNumber){
                    placesNumber--;
                    System.out.println("You Are In Water Square So Your movement will be decreased by one");
                }
            }
            if (!isTerran)
                getAttr().setPositionY(getAttr().getPositionY() + placesNumber);
        }

        else{
            boolean isTerran = false;
            boolean isWater = false;
            int isWaterCounter = 0;
            if (placesNumber <= getAttr().getSpeed()){
                for (int i = getAttr().getPositionX()+1;i<=getAttr().getPositionX() + getAttr().getSpeed();i++){
                    System.out.println("<" + i + "," + this.getAttr().getPositionY()  + ">");
                    if (Game.arenaTypes[i][this.getAttr().getPositionY()].equals("T")){
                        System.out.println("You Are Next To Terran Area <" + i + "," + this.getAttr().getPositionY()  + "> Type so you can't go throw it");
                        getAttr().setPositionX(i-1);
                        isTerran = true;
                        return true;
                    }else if (Game.arenaTypes[i][this.getAttr().getPositionY()].equals("W") && i!=getAttr().getPositionX() + getAttr().getSpeed()){
                        isWaterCounter++;
                        isWater = true;
                        System.out.println("You Are In Water Square So Your movement will be decreased by one");
                    }
                }
                if (!isTerran && isWater)
                    getAttr().setPositionX((int)(getAttr().getPositionX() + getAttr().getSpeed() - isWaterCounter));
                else getAttr().setPositionX((int)(getAttr().getPositionX() + getAttr().getSpeed()));
            }

            //for moving right:
            for (int i = getAttr().getPositionY()+1;i<=getAttr().getPositionY()+getAttr().getSpeed();i++){
                System.out.println(i);
                if (Game.arenaTypes[this.getAttr().getPositionX()][i].equals("T")){
                    System.out.println("You Are Next To Terran Area <" + this.getAttr().getPositionX() + "," + i + "> Type so you can't go throw it");
                    getAttr().setPositionY(i-1);
                    isTerran = true;
                    return true;
                }else if (Game.arenaTypes[this.getAttr().getPositionX()][i].equals("W") && i!=getAttr().getPositionX() + getAttr().getSpeed()){
                    isWaterCounter++;
                    isWater = true;
                    System.out.println("You Are In Water Square So Your movement will be decreased by one");
                }
            }
            if (!isTerran && isWater)
                getAttr().setPositionY((int)(getAttr().getPositionY() + getAttr().getSpeed() - isWaterCounter));
            else getAttr().setPositionY((int)(getAttr().getPositionY() + getAttr().getSpeed()));
        }
        InformationList.writeInformation(this);
        return true;
    }

    public synchronized boolean moveDown_Left(int placesNumber) {
        if ((Game.arenaHeight - 1<getAttr().getPositionX() + placesNumber) || (getAttr().getPositionY() - placesNumber)<0){
            System.out.println("Invalid Place");
            return false;
        }
        else if (placesNumber <= getAttr().getSpeed()){
            //for moving Down:
            boolean isTerran = false;
            for (int i = getAttr().getPositionX()+1;i<=getAttr().getPositionX() + placesNumber;i++){
                System.out.println("<" + i + "," + this.getAttr().getPositionY()  + ">");
                if (Game.arenaTypes[i][this.getAttr().getPositionY()].equals("T")){
                    System.out.println("You Are Next To Terran Area <" + i + "," + this.getAttr().getPositionY()  + "> Type so you can't go throw it");
                    getAttr().setPositionX(i-1);
                    isTerran = true;
                    return true;
                }else if (Game.arenaTypes[i][this.getAttr().getPositionY()].equals("W") && i!=getAttr().getPositionX() + placesNumber){
                    placesNumber--;
                    System.out.println("You Are In Water Square So Your movement will be decreased by one");
                }
            }
            if (!isTerran)
                getAttr().setPositionX(getAttr().getPositionX() + placesNumber);
            //for moving left
            for (int i = getAttr().getPositionY()-1;i>=getAttr().getPositionY()-placesNumber;i--){
                System.out.println(getAttr().getPositionY()-placesNumber);
                if (Game.arenaTypes[this.getAttr().getPositionX()][i].equals("T")){
                    System.out.println("You Are Next To Terran Area <" +this.getAttr().getPositionX() + "," + i + "> Type so you can't go throw it");
                    getAttr().setPositionY(i+1);
                    isTerran = true;
                    return true;
                }else if (Game.arenaTypes[this.getAttr().getPositionX()][i].equals("W") && i!=getAttr().getPositionY() -placesNumber){
                    System.out.println("You Are In Water Square So Your movement will be decreased by one");
                    getAttr().setPositionY(getAttr().getPositionY()+1);
                }
            }
            if (!isTerran){
                getAttr().setPositionY(getAttr().getPositionY() - placesNumber);
            }

        }
        else{
            boolean isTerran = false;
            boolean isWater = false;
            int isWaterCounter = 0;
            for (int i = getAttr().getPositionX()+1;i<=getAttr().getPositionX() + getAttr().getSpeed();i++){
                System.out.println("<" + i + "," + this.getAttr().getPositionY()  + ">");
                if (Game.arenaTypes[i][this.getAttr().getPositionY()].equals("T")){
                    System.out.println("You Are Next To Terran Area <" + i + "," + this.getAttr().getPositionY()  + "> Type so you can't go throw it");
                    getAttr().setPositionX(i-1);
                    isTerran = true;
                    return true;
                }else if (Game.arenaTypes[i][this.getAttr().getPositionY()].equals("W") && i!=getAttr().getPositionX() + getAttr().getSpeed()){
                    isWater = true;
                    isWaterCounter++;
                    System.out.println("You Are In Water Square So Your movement will be decreased by one");
                }
            }
            if (!isTerran && isWater)
                getAttr().setPositionX((int)(getAttr().getPositionX() + getAttr().getSpeed() - isWaterCounter));
            else getAttr().setPositionX((int)(getAttr().getPositionX() + getAttr().getSpeed()));
            //for moving left
            isWater = false;
            isWaterCounter = 0;
            for (int i = getAttr().getPositionY()-1;i>=getAttr().getPositionY()-getAttr().getSpeed();i--){
                if (Game.arenaTypes[this.getAttr().getPositionX()][i].equals("T")){
                    System.out.println("You Are Next To Terran Area <" +this.getAttr().getPositionX() + "," + i + "> Type so you can't go throw it");
                    getAttr().setPositionY(i+1);
                    isTerran = true;
                    return true;
                }else if (Game.arenaTypes[this.getAttr().getPositionX()][i].equals("W") && i!=getAttr().getPositionX() + getAttr().getSpeed()){
                    isWaterCounter++;
                    isWater = true;
                    System.out.println("You Are In Water Square So Your movement will be decreased by one");
                }
            }
            if (!isTerran && isWater){
                getAttr().setPositionY((int)(getAttr().getPositionY() - getAttr().getSpeed() + isWaterCounter));
            }
        }
        InformationList.writeInformation(this);
        return true;
    }

    public synchronized boolean moveUp_Right(int placesNumber) {
        if (getAttr().getPositionY() + placesNumber>Game.arenaWidth-1 || 0 > getAttr().getPositionX() - placesNumber){
            System.out.println("Invalid Place");
            return false;
        }
        boolean isTerran = false;
        if (placesNumber <= getAttr().getSpeed()){
            //for moving up:
            for (int i = getAttr().getPositionX()-1;i>=getAttr().getPositionX()-placesNumber;i--){
                System.out.println(getAttr().getPositionX()-placesNumber);
                if (Game.arenaTypes[i][this.getAttr().getPositionY()].equals("T")){
                    System.out.println("You Are Next To Terran Area <" + i + "," +this.getAttr().getPositionY() + "> Type so you can't go throw it");
                    getAttr().setPositionX(i+1);
                    isTerran = true;
                    return true;
                }else if (Game.arenaTypes[i][this.getAttr().getPositionY()].equals("W") && i!=getAttr().getPositionX()-placesNumber){
                    System.out.println("You Are In Water Square So Your movement will be decreased by one");
                    getAttr().setPositionX(getAttr().getPositionX()+1);
                }
            }
            if (!isTerran)
                getAttr().setPositionX(getAttr().getPositionX() - placesNumber);
            //for moving right:
            for (int i = getAttr().getPositionY()+1;i<=getAttr().getPositionY() + placesNumber;i++){
                if (Game.arenaTypes[this.getAttr().getPositionX()][i].equals("T")){
                    System.out.println("You Are Next To Terran Area <" + this.getAttr().getPositionX() + "," + i + "> Type so you can't go throw it");
                    getAttr().setPositionY(i-1);
                    isTerran = true;
                    return true;
                }else if (Game.arenaTypes[this.getAttr().getPositionX()][i].equals("W") && i!=getAttr().getPositionY() + placesNumber){
                    System.out.println("You Are In Water Square So Your movement will be decreased by one");
                    placesNumber--;
                }
            }
            if (!isTerran){
                this.getAttr().setPositionY(getAttr().getPositionY() + placesNumber);
            }

        }
        else{
            boolean isWater = false;
            int isWaterCounter = 0;
            //for moving up
            for (int i = getAttr().getPositionX()-1;i>=getAttr().getPositionX()-getAttr().getSpeed();i--){
                System.out.println(getAttr().getPositionX()-getAttr().getSpeed());
                if (Game.arenaTypes[i][this.getAttr().getPositionY()].equals("T")){
                    System.out.println("You Are Next To Terran Area <" + i + "," +this.getAttr().getPositionY() + "> Type so you can't go throw it");
                    getAttr().setPositionX(i+1);
                    isTerran = true;
                    return true;
                }else if (Game.arenaTypes[i][this.getAttr().getPositionY()].equals("W") && i!=getAttr().getPositionX()-getAttr().getSpeed()){
                    isWater = true;
                    isWaterCounter++;
                    System.out.println("You Are In Water Square So Your movement will be decreased by one");
                }
            }
            if (!isTerran && isWater)
                getAttr().setPositionX((int)(getAttr().getPositionX() - getAttr().getSpeed() + isWaterCounter));
            else getAttr().setPositionX((int)(getAttr().getPositionX() - getAttr().getSpeed()));

            //for moving right:
            isWater = false;
            isWaterCounter = 0;
            for (int i = getAttr().getPositionY()+1;i<=getAttr().getPositionY()+getAttr().getSpeed();i++){
                if (Game.arenaTypes[this.getAttr().getPositionX()][i].equals("T")){
                    System.out.println("You Are Next To Terran Area <" + this.getAttr().getPositionX() + "," + i + "> Type so you can't go throw it");
                    getAttr().setPositionY(i-1);
                    isTerran = true;
                    return true;
                }else if (Game.arenaTypes[this.getAttr().getPositionX()][i].equals("W") && i!=getAttr().getPositionY()+getAttr().getSpeed()){
                    isWater = true;
                    isWaterCounter++;
                    System.out.println("You Are In Water Square So Your movement will be decreased by one");
                }
            }
            if (!isTerran && isWater){
                getAttr().setPositionY((int)(getAttr().getPositionY() + getAttr().getSpeed() - isWaterCounter));
            }else getAttr().setPositionY((int)(getAttr().getPositionY() + getAttr().getSpeed()));
        }
        InformationList.writeInformation(this);
        return true;
    }
    public synchronized boolean moveUp_Left(int placesNumber) {
        if (getAttr().getPositionY() - placesNumber<0 || (getAttr().getPositionX() - placesNumber)<0 ){
            System.out.println("Invalid Place");
            return false;
        }
        boolean isTerran = false;
        if (placesNumber <= getAttr().getSpeed()){
            //for moving left
            for (int i = getAttr().getPositionY()-1;i>=getAttr().getPositionY()-placesNumber;i--){
                System.out.println(getAttr().getPositionY()-placesNumber);
                if (Game.arenaTypes[this.getAttr().getPositionX()][i].equals("T")){
                    System.out.println("You Are Next To Terran Area <" +this.getAttr().getPositionX() + "," + i + "> Type so you can't go throw it");
                    getAttr().setPositionY(i+1);
                    isTerran = true;
                    return true;
                }else if (Game.arenaTypes[this.getAttr().getPositionX()][i].equals("W") && getAttr().getPositionY()-placesNumber!=i){
                    getAttr().setPositionY(getAttr().getPositionY()+1);
                    System.out.println("You Are In Water Square So Your movement will be decreased by one");
                }
            }
            if (!isTerran){
                getAttr().setPositionY(getAttr().getPositionY() - placesNumber);
            }
            //for moving up
            for (int i = getAttr().getPositionX()-1;i>=getAttr().getPositionX()-placesNumber;i--){
                System.out.println(getAttr().getPositionX()-placesNumber);
                if (Game.arenaTypes[i][this.getAttr().getPositionY()].equals("T")){
                    System.out.println("You Are Next To Terran Area <" + i + "," +this.getAttr().getPositionY() + "> Type so you can't go throw it");
                    getAttr().setPositionX(i+1);
                    isTerran = true;
                    return true;
                }else if (Game.arenaTypes[i][this.getAttr().getPositionY()].equals("W") && i!=getAttr().getPositionX()-placesNumber){
                    System.out.println("You Are In Water Square So Your movement will be decreased by one");
                    getAttr().setPositionX(getAttr().getPositionX()+1);
                }
            }
            if (!isTerran)
                getAttr().setPositionX(getAttr().getPositionX() - placesNumber);
        }
        else{
            boolean isWater = false;
            int isWaterCounter = 0;
            //for moving left
            for (int i = getAttr().getPositionY()-1;i>=getAttr().getPositionY() - getAttr().getSpeed();i--){
                if (Game.arenaTypes[this.getAttr().getPositionX()][i].equals("T")){
                    System.out.println("You Are Next To Terran Area <" +this.getAttr().getPositionX() + "," + i +"> Type so you can't go throw it");
                    getAttr().setPositionY(i+1);
                    isTerran = true;
                    return true;
                }else if (Game.arenaTypes[this.getAttr().getPositionX()][i].equals("W") && i!=getAttr().getPositionY() - getAttr().getSpeed()){
                    System.out.println("You Are In Water Square So Your movement will be decreased by one");
                    isWaterCounter++;
                    isWater = true;
                }
            }
            if (!isTerran && isWater){
                getAttr().setPositionY((int)(getAttr().getPositionY() - getAttr().getSpeed() + isWaterCounter));
            }
            //for moving up
            isWater = false;
            isWaterCounter = 0;
            for (int i = getAttr().getPositionX()-1;i>=getAttr().getPositionX()-getAttr().getSpeed();i--){
                if (Game.arenaTypes[i][this.getAttr().getPositionY()].equals("T")){
                    System.out.println("You Are Next To Terran Area <" + i + "," +this.getAttr().getPositionY() + "> Type so you can't go throw it");
                    getAttr().setPositionX(i+1);
                    isTerran = true;
                    return true;
                }else if (Game.arenaTypes[i][this.getAttr().getPositionY()].equals("W")){
                    System.out.println("You Are In Water Square So Your movement will be decreased by one");
                    isWaterCounter++;
                    isWater = true;
                }
            }
            if (!isTerran && isWater)
                getAttr().setPositionX((int)(getAttr().getPositionX() - getAttr().getSpeed() + isWaterCounter));
            else getAttr().setPositionX((int)(getAttr().getPositionX() - getAttr().getSpeed()));
        }
        InformationList.writeInformation(this);
        return true;
    }
    public boolean checkForLonely() {
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