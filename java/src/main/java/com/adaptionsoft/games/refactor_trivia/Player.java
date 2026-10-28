package com.adaptionsoft.games.refactor_trivia;

public class Player{
    private String name;
    private boolean inPenaltyBox;
    private int place;
    private int purse;

    
    public Player (String name){
        this.name= name;
        this.inPenaltyBox = false;
        this.place = 0;
        this.purse = 0;
    }


    public String getName() { 
        return name; 
    }

    public int getPlace() { 
        return place; 
    }
    public int getPurse() { 
        return purse; 
    }
    public boolean isInPenaltyBox() { 
        return inPenaltyBox; 
    }

    public void sendToPenaltyBox(){
        this.inPenaltyBox = true;
    }

    public void addCoin(){
        this.purse++;
    }

    public void setPlace(int place){
        this.place = place;
    }
}


