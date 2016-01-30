package com.edst.polygainfromscratch;

/**
 * Created by haroon on 8/22/2015.
 */
public class enemy extends character{
    String enemyType;
    int height;
    int width;
    enemy(String s2, int id){
        super(480,400,"enemy", id);
        this.enemyType = s2;
        if(s2.equals("snake")){
            height=72;
            width = 100;
        }
        else if(s2.equals("scorpion")){
            height=113;
            width = 100;
        }
        else if(s2.equals("hyena")){
            height=72;
            width = 100;
        }
    }
    public String enemyType(){return enemyType;}
    public int getHeight() {return height;}
    public int getWidth() {return width;}
    public boolean canKillYou() {return true;}
    public boolean willDieByShooting() {return true;}

}
