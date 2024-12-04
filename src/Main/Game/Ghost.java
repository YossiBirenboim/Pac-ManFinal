package Main.Game;

import java.util.Random;

public class Ghost extends GameObjects {

    final private int playerSpeed = 1;
    boolean eaten;

    boolean node;
    boolean[] movements = new boolean[4];
    boolean[] options = new boolean[4];
    boolean afterScape;

    boolean chase;
    boolean isAfterScapeOrChase;


    public Ghost() {
        setLocationInY(75);
        setLocationInX(250);
    }


    public int getPlayerSpeed() {
        return playerSpeed;
    }

    public void setEaten() {
        boolean eaten = true;
    }

    public void calculator() {
        options[0] = GameObjects.canMoveUp(this.locationInY - this.playerSpeed , this.locationInX);
        options[1] = GameObjects.canMoveDown(this.locationInY + this.playerSpeed , this.locationInX);
        options[2] = GameObjects.canMoveLeft(this.locationInY, this.locationInX - this.playerSpeed );
        options[3] = GameObjects.canMoveRight(this.locationInY, this.locationInX + this.playerSpeed );
        node = (options[0] && options[2] || options[0] && options[3]) || (options[1] && options[2] || options[1] && options[3] );
    }

    public void nodeManager() {

        Random ran = new Random();
        int num;

        while (true) {
            num = ran.nextInt(0,4);

            switch (num) {
                case 0:
                    if (options[0]) {
                         setMovement(0,this);
                         node = false;
                        return;
                    }
                    break;
                case 1:
                    if (options[1]) {
                         setMovement(1,this);
                         node = false;
                    }
                    break;
                case 2:
                    if (options[2]) {
                        setMovement(2, this);
                        node = false;
                        return;
                    }
                    break;
                case 3:
                    if (options[3]) {
                         setMovement(0,this);
                        return;
                    }
                    break;
            }
        }
    }

    public void randomSteps() {
        calculator();
        if (node) {
            nodeManager();
        }
        if (movements[0]) {
            if (options[0]) {
                this.locationInY -= this.playerSpeed;
            } else {
                this.locationInY += this.playerSpeed;
                movements[1] = true;
                movements[0] = false;
            }

        } else if (movements[1]) {
            if (options[1]){
            this.locationInY += this.playerSpeed;
            }else {
                this.locationInY -= this.playerSpeed;
                movements[0] = true;
                movements[1] = false;
            }

        } else if (movements[2]) {
            if (options[2]){
            this.locationInX -= this.playerSpeed;
            }
            else {
                this.locationInX += this.playerSpeed;
                movements[2] = false;
                movements[3] = true;
            }

        } else if (movements[3]) {
            if (options[3]){
            this.locationInX += this.playerSpeed;
            }
            else {
                this.locationInX -= this.playerSpeed;
                movements[3] = false;
                movements[2] = true;
            }
        }
    }
    public void escape (Player player){
        int num;
         if (player.getLocationInY() >= this.getLocationInY() && GameObjects.canMoveUp(this.getLocationInY() - this.getPlayerSpeed(),this.getLocationInX())){
            this.setLocationInY(this.getLocationInY() - this.getPlayerSpeed());
            num = 0;
            Ghost.setMovement(num,this);
        }
        else if (player.getLocationInY() <= this.getLocationInY()  && GameObjects.canMoveDown(this.getLocationInY() + this.getPlayerSpeed(),this.getLocationInX())){
            this.setLocationInY(this.getLocationInY() + this.getPlayerSpeed());
            num = 1;
            Ghost.setMovement(num,this);
        }
         if (player.getLocationInX() >= this.getLocationInX()  && GameObjects.canMoveLeft(this.getLocationInY(),this.getLocationInX() - this.getPlayerSpeed())){
            this.setLocationInX(this.getLocationInX() - this.getPlayerSpeed());
            num = 2;
            Ghost.setMovement(num,this);
        }
        else if (player.getLocationInX() <= this.getLocationInX() && GameObjects.canMoveRight(this.getLocationInY(),this.getLocationInX() + this.getPlayerSpeed())){
            this.setLocationInX(this.getLocationInX() + this.getPlayerSpeed());
            num = 3;
            Ghost.setMovement(num,this);
        }
    }
    public static void setMovement(int num,Ghost ghost){
        for (int i = 0; i < 4; i++) {
            if (i == num){
                ghost.movements[i] = true;
            }
            else ghost.movements[i] = false;
        }
    }
    public void makeChase(Player player){
    }
}

