package com.edst.polygainfromscratch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CountDownTimer;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by haroon on 8/29/2015.
 */
public class LevelClass extends View{
    Bitmap backgroungImage1;
    Bitmap playerImage;
    Bitmap snakes;
    Bitmap scorpions;
    Bitmap hyenas;
    Bitmap platform;
    Bitmap projectile;
    Bitmap tree;
    Timer timer1;
    ArrayList<character> characterList = new ArrayList<>();
    player mainPlayer = new player(1);
    //enemy snake1 = new enemy("snake");
    //enemy scorpion1 = new enemy("scorpion");
    //enemy hyena1 = new enemy("hyena");
    Paint red = new Paint();
    boolean jump = false;
    boolean firstDraw = true;
    boolean ItIsTimeToSched = true;
    int counter=1;
    int gravity = 3;
    int yspeed = -50;
    int platformNumber =-1;
    int score =0;

    public LevelClass(Context context) {
        super(context);
        backgroungImage1 = BitmapFactory.decodeResource(getResources(), R.drawable.level1background);
        playerImage = BitmapFactory.decodeResource(getResources(), R.drawable.level1player);

        snakes = BitmapFactory.decodeResource(getResources(), R.drawable.level1snake);
        scorpions = BitmapFactory.decodeResource(getResources(), R.drawable.level1scorpion);
        hyenas = BitmapFactory.decodeResource(getResources(), R.drawable.level1hyena);
        platform = BitmapFactory.decodeResource(getResources(), R.drawable.level1platform);
        projectile = BitmapFactory.decodeResource(getResources(), R.drawable.bullet);
        tree = BitmapFactory.decodeResource(getResources(), R.drawable.level1tree);
        characterList.add(mainPlayer);
    }
    public void myTimer(){
        new CountDownTimer(1000,1000){
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                mainPlayer.ycoord+=100;
                System.out.println("ycoord 3"+mainPlayer.ycoord);
                //canvas.drawBitmap(playerImage, mainPlayer.xcoord, mainPlayer.ycoord, null);
              this.start();
            }
        }.start();
}

    public void restart(){
        timer1.cancel();
        firstDraw = true;
        score =0;
        for(int i = 0; i<characterList.size();i++) {
            if(!characterList.get(i).type.equals("player")){
                characterList.remove(i);
            }
        }
        ItIsTimeToSched=true;
        this.invalidate();
    }

    protected void reDraw(){
        ArrayList removeOrNot = new ArrayList();
        boolean isOnPlatform = false;
        for(int i = 0; i<characterList.size();i++) {
            removeOrNot.add(0);
        }
        /*
        for(int i =1; i<characterList.size(); i++) {
            if(characterList.get(i).PlayerIsOnPlatform(mainPlayer.xcoord, mainPlayer.ycoord)) {
                System.out.println("whaj");
                isOnPlatform = true;
                platformNumber = characterList.get(i).IDNumber;
            }
        }
        if(isOnPlatform) {
            jump = false;
        }

        for(int i =1; i<characterList.size(); i++){
            if(platformNumber>0&&characterList.get(i).IDNumber == characterList.get(platformNumber).IDNumber){
               if(!characterList.get(i).PlayerIsOnPlatform(mainPlayer.xcoord, mainPlayer.ycoord)){
                   System.out.println("whaj2");
                   jump = true;
                   isOnPlatform = false;
               }
            }
        }
        */
        //if the user tapped the jump button, change the y coordinates of the
        //mainPlayer object

        if (jump) {
            mainPlayer.ycoord +=yspeed;
            yspeed = yspeed+gravity;
            /*
            while(mainPlayer.ycoord<400||counter<2){
                System.out.println("yahoo "+mainPlayer.ycoord);
                mainPlayer.ycoord +=yspeed;
                yspeed = yspeed+gravity;
                counter++;
                this.invalidate();
            }
            counter =1;
            yspeed = -50;
            */
            //when the player is coming back down, stop the
            // change in y if the player hits the ground
            if(mainPlayer.ycoord>= 400) {
                jump = false;
                yspeed = -50;
            }
        }
        for(int i = 0; i<characterList.size();i++) {
            if(characterList.get(i).canKillYou()|| characterList.get(i).type.equals("platform")){
                characterList.get(i).xcoord-=10;
            }
            else if(characterList.get(i).type.equals("bullet")) {
                characterList.get(i).xcoord+=15;
            }
        }
        for(int i = 0; i<characterList.size();i++) {
            if(characterList.get(i).xcoord==-10) score++;

            if(characterList.get(i).canKillYou()){
                boolean yIsInPos = false;
                boolean xIsInPos = false;
                if((mainPlayer.ycoord+mainPlayer.getHeight())>characterList.get(i).ycoord &&
                        (mainPlayer.ycoord+mainPlayer.getHeight())<(characterList.get(i).ycoord+characterList.get(i).getHeight())){
                    yIsInPos = true;
                }
                if((mainPlayer.ycoord>characterList.get(i).ycoord)&&
                        (mainPlayer.ycoord<(characterList.get(i).ycoord+characterList.get(i).getHeight()))){
                    yIsInPos = true;
                }
                if((mainPlayer.xcoord+mainPlayer.getWidth())>characterList.get(i).xcoord &&
                        (mainPlayer.xcoord+mainPlayer.getWidth())<(characterList.get(i).xcoord+characterList.get(i).getWidth())){
                    xIsInPos = true;
                }
                if((mainPlayer.xcoord>characterList.get(i).xcoord)&&
                        (mainPlayer.xcoord<(characterList.get(i).xcoord+characterList.get(i).getWidth()))){
                    xIsInPos = true;
                }
                if(xIsInPos&&yIsInPos){
                    System.out.println("you dead");
                    restart();
                }
            }
            else if(characterList.get(i).type.equals("bullet")) {
                for(int j = 0; j<characterList.size();j++) {
                    if (characterList.get(j).canKillYou()&&
                            characterList.get(i).xcoord+characterList.get(i).getWidth()>characterList.get(j).xcoord &&
                            characterList.get(i).xcoord+characterList.get(i).getWidth()<characterList.get(j).xcoord +characterList.get(j).getWidth()) {

                       // characterList.remove(i);
                        removeOrNot.set(i, 1);
                        if(characterList.get(j).willDieByShooting()) {
                            removeOrNot.set(j, 1);
                            score++;
                        }
                            //characterList.remove(j-1);
                        break;
                    }
                }
            }
        }
        int counter = 0;
        for(int i =0; i<removeOrNot.size(); i++) {
            if(removeOrNot.get(i)==1) {
                characterList.remove(i-counter);
                counter++;
            }
        }
        this.invalidate();
        System.out.println("ycoord2 "+mainPlayer.ycoord);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        //if the jump button was tapped, set the boolean jump to true
        //then draw again.
        if(event.getRawX()<100 && event.getRawY()<100) {
            jump = true;
            reDraw();
        }
        //if the shoot button was tapped, set the boolean shoot to true
        //then draw again.
        if(event.getRawX()<480 && event.getRawX()>380 && event.getRawY()<100) {
            characterList.add(new bullet(mainPlayer.xcoord+100, mainPlayer.ycoord, characterList.get(characterList.size()-1).IDNumber));
            reDraw();
        }
        return super.dispatchTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int time = 0;
        //draw background
        canvas.drawBitmap(backgroungImage1, 0, 0, null);
        red.setColor(Color.RED);
        //draw the jump button
        canvas.drawRect(0, 0, 100, 100, red);
        red.setColor(Color.BLUE);
        //draw the shoot button
        canvas.drawRect(100, 100, 100, 100, red);
        //draw the player
        canvas.drawBitmap(playerImage, mainPlayer.xcoord, mainPlayer.ycoord, null);
        //if its the first time, schedule all of the enemies and obstacles
        //for the rest of the level
        //draw score as text
        red.setColor(Color.BLACK);
        red.setTextSize(20);
        canvas.drawText(Integer.toString(score), 100, 100, red);
        if(firstDraw){
            System.out.println("what?");
            timer1 = new Timer();
            //timer1.schedule(new DisplayTask("enemy", "snake"), 5000);
            //timer1.schedule(new DisplayTask("enemy", "scorpion"), 7000);
            //timer1.schedule(new DisplayTask("obstacle", "tree"), 8000);
            //timer1.schedule(new DisplayTask("platform", "tree"), 2030);
            //timer1.schedule(new DisplayTask("enemy", "hyena"), 10000);
            firstDraw = false;
        }
        if(ItIsTimeToSched) {
            timer1.schedule(new ScheduleTask(), 1500);
            ItIsTimeToSched = false;
            System.out.println("New enemy scheduled");
        }
            for(int i = 0; i<characterList.size();i++) {
                if(characterList.get(i).type.equals("player")){
                    canvas.drawBitmap(playerImage, mainPlayer.xcoord, mainPlayer.ycoord, null);
                }
                else if(characterList.get(i).type.equals("bullet")){
                    canvas.drawBitmap(projectile, characterList.get(i).xcoord, characterList.get(i).ycoord, null);
                }
                else if(characterList.get(i).type.equals("obstacle")){
                    canvas.drawBitmap(tree, characterList.get(i).xcoord, characterList.get(i).ycoord, null);
                }
                else if(characterList.get(i).type.equals("platform")){
                    canvas.drawBitmap(platform, characterList.get(i).xcoord, characterList.get(i).ycoord, null);
                }

                else{
                    if(characterList.get(i).enemyType().equals("snake")) {
                        canvas.drawBitmap(snakes, characterList.get(i).xcoord, characterList.get(i).ycoord, null);
                    }
                    else if(characterList.get(i).enemyType().equals("scorpion")) {
                        canvas.drawBitmap(scorpions, characterList.get(i).xcoord, characterList.get(i).ycoord, null);
                    }
                    else if(characterList.get(i).enemyType().equals("hyena")) {
                        canvas.drawBitmap(hyenas, characterList.get(i).xcoord, characterList.get(i).ycoord, null);
                    }
                }
            }
            /*

            if(time ==5){
                enemy snake1 = new enemy("snake");
                characterList.add(snake1);
            }
            else if(time ==10){
                enemy scorpion1 = new enemy("scorpion");
                characterList.add(scorpion1);
            }
            else if(time ==15){
                enemy hyena1 = new enemy("hyena");
                characterList.add(hyena1);
            }
            */
            time++;
            for(int j = 0; j<characterList.size(); ++j) {
                if (characterList.get(j).type.equals("enemy")) {
                    //move the enemies left
                    //characterList.get(j).xcoord--;
                }
            }
           System.out.println("ycoord1 " + mainPlayer.ycoord);
           //update all the xcoords and ycoords of all the characters
           //with a call to redraw
           reDraw();
        }

    class DisplayTask extends TimerTask {
        String typeToDisplay;
        String enemyOrObstac;
        public DisplayTask(String t, String s){typeToDisplay = s;enemyOrObstac =t;}
        @Override
        public void run() {
            System.out.println("Ichigo");
            if(enemyOrObstac.equals("obstacle")) {
                if(typeToDisplay.equals("tree")){
                    characterList.add(new obstacles("tree", characterList.get(characterList.size()-1).IDNumber));
                }
            }
            else if(enemyOrObstac.equals("platform")){
                characterList.add(new platform(characterList.get(characterList.size()-1).IDNumber));
            }
            else {
                if (typeToDisplay.equals("snake")) {
                    characterList.add(new enemy("snake", characterList.get(characterList.size()-1).IDNumber));
                    System.out.println("foo: character list has " + characterList.size());
                } else if (typeToDisplay.equals("scorpion")) {
                    characterList.add(new enemy("scorpion", characterList.get(characterList.size()-1).IDNumber));
                    System.out.println("bar: character list has " + characterList.size());
                } else if (typeToDisplay.equals("hyena")) {
                    characterList.add(new enemy("hyena", characterList.get(characterList.size()-1).IDNumber));
                    System.out.println("baz: character list has " + characterList.size());
                }
            }

        }
    }

    class ScheduleTask extends TimerTask {
        @Override
        public void run() {
            ItIsTimeToSched = true;
            int type = 1+(int)(Math.random()*4);
            switch(type){
                case 1:
                    timer1.schedule(new DisplayTask("enemy", "snake"), (int)(Math.random() * ((500) + 1)));
                    break;
                case 2:
                    timer1.schedule(new DisplayTask("enemy", "scorpion"), (int)(Math.random() * ((500) + 1)));
                    break;
                case 3:
                    timer1.schedule(new DisplayTask("obstacle", "tree"), (int)(Math.random() * ((500) + 1)));
                    break;
                case 4:
                    timer1.schedule(new DisplayTask("enemy", "hyena"), (int)(Math.random() * ((500) + 1)));
                    break;
            }
            int timeBefore1 = (int)(Math.random() * ((8000) + 1));
            int timeBefore2 = (int)(Math.random() * ((8000) + 1));
            int timeBefore3 = (int)(Math.random() * ((8000) + 1));
            int timeBefore4 = (int)(Math.random() * ((8000) + 1));
            boolean firstAndSec;
            boolean firstAndThird;
            boolean firstAndFourth;
            boolean SecAndThird;
            boolean SecAndFourth;
            boolean ThirdAndFourth;
            if(timeBefore2-timeBefore1<=50&& timeBefore2-timeBefore1>=-50){firstAndSec = true;}
            if(timeBefore3-timeBefore1<=50&& timeBefore3-timeBefore1>=-50){firstAndThird = true;}
            if(timeBefore4-timeBefore1<=50&& timeBefore4-timeBefore1>=-50){firstAndFourth = true;}

            if(timeBefore3-timeBefore2<=50&& timeBefore3-timeBefore2>=-50){SecAndThird = true;}
            if(timeBefore4-timeBefore2<=50&& timeBefore4-timeBefore2>=-50){SecAndFourth = true;}

            if(timeBefore4-timeBefore3<=50&& timeBefore4-timeBefore3>=-50){ThirdAndFourth = true;}

           // timer1.schedule(new DisplayTask("enemy", "snake"), (int)(Math.random() * ((8000) + 1)));
           // timer1.schedule(new DisplayTask("enemy", "scorpion"), (int)(Math.random() * ((8000) + 1)));
           // timer1.schedule(new DisplayTask("obstacle", "tree"), (int)(Math.random() * ((8000) + 1)));
           // timer1.schedule(new DisplayTask("enemy", "hyena"), (int)(Math.random() * ((8000) + 1)));
            System.out.println("is this working?");
        }
    }

}

