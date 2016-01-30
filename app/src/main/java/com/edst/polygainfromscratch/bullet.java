package com.edst.polygainfromscratch;

/**
 * Created by haroon on 9/15/2015.
 */
public class bullet extends character{
    int width= 30;
    int height=4;
    bullet(int x, int y, int id){
        super(x, y, "bullet", id);
    }
    public String enemyType(){return "player";}
    public void updateX(int x){super.xcoord += x;}
    public int getHeight(){return height;}
    public int getWidth(){return width;}
}

/*
public class GameInitialize {
    /*
    void ChooseCharacter(void) {
        getCharacterPos();
        getEnemyPos();
        countDeaths();
    }

public void mainMethod() {
    character player1;
    int level_number = 1;
    Level level = new Level();
    //bool shoot;
    //shoot = level.shooting();
}
    //start the running animation of player
    //if the player runs into an enemy, restart level
    //if screen pressed is pressed, jump
    //if shoot, display shoot button.
    //if shoot is pressed, fire bullet
    //if bullet hits enemy, delete enemy

}
 */

/*
public class Level {
    character enemies[];
    obstacles Obstacles[];
    //background Background;
    //foreground Foreground;
    int deaths = 0;
    double checkpoints;
    double gravity;

    //level constructor

    Level(int level_number) {
        switch(level_number){
            case 1:
                //level1
            case 2:
                //level2
        }
    }


    // bool shooting();
    //bool goalCompleted(){}
}

 */

/*
public class movement  {
    //playerForward();
    //playerJump();
    //enemyMovement();
    //playerShootsEnemy();
    //enemyHitsPlayer();
}
 */