package com.edst.polygainfromscratch;

/**
 * Created by haroon on 9/18/2015.
 */
public class platform extends character{
    int height = 10;
    int width = 78;
    platform(int id){
        super(480, 400, "platform", id);
    }
    public String enemyType(){return "platform";}
    public int getHeight(){return height;}
    public int getWidth(){return width;}
    public boolean PlayerIsOnPlatform(int playerX, int playerY) {
        boolean playerXLinesUp =true;
        boolean playerYLinesUp = true;
        if(this.xcoord<100&& this.xcoord>-78) {
            System.out.println("so:1");
            playerXLinesUp = true;
        }
        else{
            playerXLinesUp = false;
        }
        if(playerY-this.ycoord<=100 && playerY>this.ycoord){
            System.out.println("so:2");
            playerYLinesUp = true;
        }
        else {
            playerYLinesUp = false;
        }
        if(playerXLinesUp && playerYLinesUp){
            return true;
        }
        else{
            return false;
        }
    }
}
