package Main.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {



    String direction;

    public boolean upDirection,downDirection,leftDirection,rightDirection;

    public boolean upPressed, downPressed, leftPressed, rightPressed;



    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {


        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {



                upPressed = true;
                downPressed = leftPressed = rightPressed = false;



        }
        if (code == KeyEvent.VK_DOWN) {




              downPressed = true;
              upPressed = leftPressed = rightPressed = false;


            }

        if (code == KeyEvent.VK_LEFT) {


              leftPressed = true;
              upPressed = downPressed =  rightPressed = false;





        }
        if (code == KeyEvent.VK_RIGHT) {


            rightPressed = true;
            upPressed = downPressed = leftPressed = false;



        }

        if (code == KeyEvent.VK_ENTER){
            upPressed = downPressed = leftPressed = rightPressed = false;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
