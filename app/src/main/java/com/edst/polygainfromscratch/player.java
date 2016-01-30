package com.edst.polygainfromscratch;

/**
 * Created by haroon on 8/22/2015.
 */
public class player extends character{
    int height = 50;
    int width = 50;
    player(int id){
        super(0, 400, "player", id);
    }
    public String enemyType(){return "player";}
    public void updateX(int x){super.xcoord += x;}
    public void updateY(int y){super.ycoord += y;}
    public int getHeight(){return height;}
    public int getWidth(){return width;}
}
