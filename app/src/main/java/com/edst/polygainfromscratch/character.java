package com.edst.polygainfromscratch;

/**
 * Created by haroon on 8/22/2015.
 */
public class character {
    int xcoord;
    int ycoord;
    String type;
    int IDNumber;
    character(){}
    character(int x, int y, String s, int id){
        this.xcoord = x;
        this.ycoord = y;
        this.type = s;
        this.IDNumber = id++;
    }
    public String enemyType(){return null;}
    public int getHeight(){return 0;}
    public int getWidth(){return 0;}
    public boolean canKillYou() {return false;}
    public boolean willDieByShooting() {return false;}
    public boolean PlayerIsOnPlatform(int playerX, int playerY) {return false;}

}


