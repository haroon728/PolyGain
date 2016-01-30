package com.edst.polygainfromscratch;

/**
 * Created by haroon on 8/22/2015.
 */
public class obstacles extends character{
        String enemyType;
        int height;
        int width;
        obstacles(String s2, int id){
            super(480,400,"obstacle", id);
            this.enemyType = s2;
            if(s2.equals("tree")){
                height=162;
                width = 100;
            }
        }
        public String enemyType(){return enemyType;}
        public int getHeight() {return height;}
        public int getWidth() {return width;}
        public boolean canKillYou() {return true;}
        public boolean willDieByShooting() {return false;}

/*
    class mold{};
    class blocks{};
    class spikes{};
    void placeObstacles(){}
    void playerHitsObstacle(){}
    */
}
