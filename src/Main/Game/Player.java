package Main.Game;

import java.util.ArrayList;

public class Player extends GameObjects{

    final String kind = "player";
    final int playerSpeed = 1;
    private int failuresCounter = 0;
    private boolean loss;
    private boolean win;
    boolean ghostKillerEaten;
    ArrayList<Ghost> ghosts = new ArrayList<>();

    public boolean isLoss() {
        return loss;
    }

    public void setLoss(boolean loss) {
        this.loss = loss;
    }

    public boolean isWin() {
        return win;
    }
    public void setWon(){
        this.win = false;
    }
    public void checkWin(int [][] arr){
        for (int y = 0; y < arr.length; y++) {
            for (int x = 0; x < arr[y].length ; x++) {
                if (arr[y][x] == 2){
                    return;
                }
            }
        }
            this.win = true;
    }

    public int getFailuresCounter() {
        return failuresCounter;
    }

    public void setFailuresCounter(int failuresCounter) {
        this.failuresCounter = failuresCounter;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints() {
        if (GameObjects.arrOfIndex[this.getLocationInY() / 25][this.getLocationInX() / 25] == 2) {
            this.points += 10;
        }
        else if (GameObjects.arrOfIndex[this.getLocationInY() / 25][this.getLocationInX() / 25] == 4){
            this.points += 50;
        }
        else if (GameObjects.arrOfIndex[this.getLocationInY() / 25][this.getLocationInX() / 25] == 5){
            this.points += 100;
        }
    }
    public void addPoints(){
        this.points += 250;
    }
    public void keepPoint(int point){
        this.points = point;
    }

    private int points;

    KeyHandler keyH;

    GamePanel gp;

    String direction;

    public Player(KeyHandler keyH, GamePanel gp) {
        this.keyH = keyH;
        this.gp = gp;
    }




    public int getPlayerSpeed() {
        return this.playerSpeed;
    }

    public String getKind() {
        return kind;
    }

    private int lifeNum = 3;

    public int getLifeNum() {
        return lifeNum;
    }

    public void setLifeNum(int lifeNum) {
        this.lifeNum = lifeNum;
    }
}