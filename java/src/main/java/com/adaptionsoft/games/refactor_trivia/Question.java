package com.adaptionsoft.games.refactor_trivia;

public class Question{
    private String category;
    private String content;

    public Question(String category, String content){
        this.category = category;
        this.content = content;
    }

    public String getCategory(){
        return this.category;
    }

    public String getContent(){
        return this.content;
    }

}