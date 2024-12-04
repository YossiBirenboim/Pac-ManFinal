package Main.Game;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class GamePanel extends JPanel implements Runnable {

    private GameActionListener listener;


    public void setListener(GameActionListener listener) {
        this.listener = listener;
    }

    private GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        ghosts[0] = ghost1;
        ghosts[1] = ghost2;
        ghosts[2] = ghost3;
        ghosts[3] = ghost4;
    }

    public static synchronized GamePanel getInstance() {
        if (gamePanel == null) {
            gamePanel = new GamePanel();
        }
        return gamePanel;
    }

    static GamePanel gamePanel;
    Ghost ghost1 = new Ghost();
    Ghost ghost2 = new Ghost();
    Ghost ghost3 = new Ghost();
    Ghost ghost4  = new Ghost();
    KeyHandler keyH = new KeyHandler();

    Player player = new Player(keyH, this);
    int failuresCount = 0;
    Thread gameThread = new Thread();

    final int maxScreenCol = 21;
    final int maxScreenRow = 19;
    final int screenWidth = player.getPlayerSize() * maxScreenCol; // 525
    final int screenHeight = player.getPlayerSize() * maxScreenRow; // 475

    String direction = "up";
    Ghost[] ghosts = new Ghost[4];
    static Graphics2D g5;
    static Graphics2D g6;
    private int timeForSleeping = 8;
    SoundManager soundManager = new SoundManager();


    public void startGameTread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        while (gameThread != null) {
            upDate();
            repaint();

            try {
                Thread.sleep(timeForSleeping);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void upDate() {

        chaseCalculator();

        keyActions();

        objectsMeeting();

        setResult();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Graphics2D g3 = (Graphics2D) g;
        Graphics2D g4 = (Graphics2D) g;
        g5 = (Graphics2D) g;
        g6 = (Graphics2D) g;

        g5.setColor(Color.green);
        g5.setFont(new Font("Ariel", Font.BOLD, 25));
        g5.drawString("SCORE = " + player.getPoints(), 12 * 25, 17 * 25);

        g6.setColor(Color.red);
        g6.setFont(new Font("Ariel", Font.BOLD, 25));
        g6.drawString(" NUM OF LIFE = " + (3 - failuresCount), 30, 17 * 25);

        if (direction.equals("up")) {
            ImageIcon imageIcon = new ImageIcon("src/Main/Game/Graphics/up_2.png");
            g2.drawImage(imageIcon.getImage(), player.locationInX, player.getLocationInY(), player.getPlayerSize(), player.getPlayerSize(), null);
        } else if (direction.equals("down")) {
            ImageIcon imageIcon = new ImageIcon("src/Main/Game/Graphics/down_2.png");
            g2.drawImage(imageIcon.getImage(), player.locationInX, player.getLocationInY(), player.getPlayerSize(), player.getPlayerSize(), null);
        } else if (direction.equals("left")) {
            ImageIcon imageIcon = new ImageIcon("src/Main/Game/Graphics/left_2.png");
            g2.drawImage(imageIcon.getImage(), player.locationInX, player.getLocationInY(), player.getPlayerSize(), player.getPlayerSize(), null);
        } else if (direction.equals("right")) {
            ImageIcon imageIcon = new ImageIcon("src/Main/Game/Graphics/right_2.png");
            g2.drawImage(imageIcon.getImage(), player.locationInX, player.getLocationInY(), player.getPlayerSize(), player.getPlayerSize(), null);
        }

        int[][] arr = GameObjects.arrOfIndex;
//

        for (int y = 0; y < arr.length; y++) {
            for (int x = 0; x < arr[y].length; x++) {
                if (arr[y][x] == 0) {
                    ImageIcon icon = new ImageIcon("src/Main/Game/Graphics/wall.png");
                    g3.drawImage(icon.getImage(), x * 25, y * 25, 25, 25, null);
                } else if (arr[y][x] == 2) {
                    ImageIcon icon = new ImageIcon("src/Main/Game/Graphics/currency.png");
                    g3.drawImage(icon.getImage(), x * 25 + 7, y * 25 + 7, 11, 11, null);
                } else if (arr[y][x] == 4) {
                    ImageIcon icon = new ImageIcon("src/Main/Game/Graphics/Ghost Killer.png");
                    g3.drawImage(icon.getImage(), x * 25 + 5, y * 25 + 5, 15, 15, null);
                } else if (arr[y][x] == 5) {
                    ImageIcon icon = new ImageIcon("src/Main/Game/Graphics/chary.png");
                    g3.drawImage(icon.getImage(), x * 25 - 3, y * 25 - 3, 31, 31, null);
                }
            }
        }

        if (player.ghostKillerEaten) {
            ImageIcon icon = new ImageIcon("src/Main/Game/Graphics/escape ghost.png");
            g4.drawImage(icon.getImage(), ghost1.getLocationInX(), ghost1.getLocationInY(), ghost1.getPlayerSize(), ghost1.getPlayerSize(), null);
        } else if (ghost1.movements[0]) {
            ImageIcon icon = new ImageIcon("src/Main/Game/Graphics/blue_up.png");
            g4.drawImage(icon.getImage(), ghost1.getLocationInX(), ghost1.getLocationInY(), ghost1.getPlayerSize(), ghost1.getPlayerSize(), null);
        } else if (ghost1.movements[1]) {
            ImageIcon icon = new ImageIcon("src/Main/Game/Graphics/blue_down.png");
            g4.drawImage(icon.getImage(), ghost1.getLocationInX(), ghost1.getLocationInY(), ghost1.getPlayerSize(), ghost1.getPlayerSize(), null);
        } else if (ghost1.movements[2]) {
            ImageIcon icon = new ImageIcon("src/Main/Game/Graphics/blue_left.png");
            g4.drawImage(icon.getImage(), ghost1.getLocationInX(), ghost1.getLocationInY(), ghost1.getPlayerSize(), ghost1.getPlayerSize(), null);
        } else if (ghost1.movements[3]) {
            ImageIcon icon = new ImageIcon("src/Main/Game/Graphics/blue_right.png");
            g4.drawImage(icon.getImage(), ghost1.getLocationInX(), ghost1.getLocationInY(), ghost1.getPlayerSize(), ghost1.getPlayerSize(), null);
        } else {
            ImageIcon icon = new ImageIcon("src/Main/Game/Graphics/blue_up.png");
            g4.drawImage(icon.getImage(), ghost1.getLocationInX(), ghost1.getLocationInY(), ghost1.getPlayerSize(), ghost1.getPlayerSize(), null);
        }

        if (player.ghostKillerEaten) {
            ImageIcon icon = new ImageIcon("src/Main/Game/Graphics/escape ghost.png");
            g4.drawImage(icon.getImage(), ghost2.getLocationInX(), ghost2.getLocationInY(), ghost2.getPlayerSize(), ghost2.getPlayerSize(), null);
        } else if (ghost2.movements[0]) {
            ImageIcon icon = new ImageIcon("src/Main/Game/Graphics/pink_up.png");
            g4.drawImage(icon.getImage(), ghost2.getLocationInX(), ghost2.getLocationInY(), ghost2.getPlayerSize(), ghost2.getPlayerSize(), null);
        } else if (ghost2.movements[1]) {
            ImageIcon icon = new ImageIcon("src/Main/Game/Graphics/pink_down.png");
            g4.drawImage(icon.getImage(), ghost2.getLocationInX(), ghost2.getLocationInY(), ghost2.getPlayerSize(), ghost2.getPlayerSize(), null);
        } else if (ghost2.movements[2]) {
            ImageIcon icon = new ImageIcon("src/Main/Game/Graphics/pink_left.png");
            g4.drawImage(icon.getImage(), ghost2.getLocationInX(), ghost2.getLocationInY(), ghost2.getPlayerSize(), ghost2.getPlayerSize(), null);
        } else if (ghost2.movements[3]) {
            ImageIcon icon = new ImageIcon("src/Main/Game/Graphics/pink_right.png");
            g4.drawImage(icon.getImage(), ghost2.getLocationInX(), ghost2.getLocationInY(), ghost2.getPlayerSize(), ghost2.getPlayerSize(), null);
        } else {
            ImageIcon icon = new ImageIcon("src/Main/Game/Graphics/pink_up.png");
            g4.drawImage(icon.getImage(), ghost2.getLocationInX(), ghost2.getLocationInY(), ghost2.getPlayerSize(), ghost2.getPlayerSize(), null);
        }


        if (player.ghostKillerEaten) {
            ImageIcon icon = new ImageIcon("src/Main/Game/Graphics/escape ghost.png");
            g4.drawImage(icon.getImage(), ghost3.getLocationInX(), ghost3.getLocationInY(), ghost3.getPlayerSize(), ghost3.getPlayerSize(), null);
        } else if (ghost3.movements[0]) {
            ImageIcon icon = new ImageIcon("src/Main/Game/Graphics/red_up.png");
            g4.drawImage(icon.getImage(), ghost3.getLocationInX(), ghost3.getLocationInY(), ghost3.getPlayerSize(), ghost3.getPlayerSize(), null);
        } else if (ghost3.movements[1]) {
            ImageIcon icon = new ImageIcon("src/Main/Game/Graphics/red_down.png");
            g4.drawImage(icon.getImage(), ghost3.getLocationInX(), ghost3.getLocationInY(), ghost3.getPlayerSize(), ghost3.getPlayerSize(), null);
        } else if (ghost3.movements[2]) {
            ImageIcon icon = new ImageIcon("src/Main/Game/Graphics/red_left.png");
            g4.drawImage(icon.getImage(), ghost3.getLocationInX(), ghost3.getLocationInY(), ghost3.getPlayerSize(), ghost3.getPlayerSize(), null);
        } else if (ghost3.movements[3]) {
            ImageIcon icon = new ImageIcon("src/Main/Game/Graphics/red_right.png");
            g4.drawImage(icon.getImage(), ghost3.getLocationInX(), ghost3.getLocationInY(), ghost3.getPlayerSize(), ghost3.getPlayerSize(), null);
        } else {
            ImageIcon icon = new ImageIcon("src/Main/Game/Graphics/escape ghost.png");
            g4.drawImage(icon.getImage(), ghost3.getLocationInX(), ghost3.getLocationInY(), ghost3.getPlayerSize(), ghost3.getPlayerSize(), null);
        }


        if (player.ghostKillerEaten) {
            ImageIcon icon = new ImageIcon("src/Main/Game/Graphics/escape ghost.png");
            g4.drawImage(icon.getImage(), ghost4.getLocationInX(), ghost4.getLocationInY(), ghost4.getPlayerSize(), ghost4.getPlayerSize(), null);
        } else if (ghost4.movements[0]) {
            ImageIcon icon = new ImageIcon("src/Main/Game/Graphics/orange_up.png");
            g4.drawImage(icon.getImage(), ghost4.getLocationInX(), ghost4.getLocationInY(), ghost4.getPlayerSize(), ghost4.getPlayerSize(), null);
        } else if (ghost3.movements[1]) {
            ImageIcon icon = new ImageIcon("src/Main/Game/Graphics/orange_down.png");
            g4.drawImage(icon.getImage(), ghost4.getLocationInX(), ghost4.getLocationInY(), ghost4.getPlayerSize(), ghost4.getPlayerSize(), null);
        } else if (ghost3.movements[2]) {
            ImageIcon icon = new ImageIcon("src/Main/Game/Graphics/orange_left.png");
            g4.drawImage(icon.getImage(), ghost4.getLocationInX(), ghost4.getLocationInY(), ghost4.getPlayerSize(), ghost4.getPlayerSize(), null);
        } else if (ghost3.movements[3]) {
            ImageIcon icon = new ImageIcon("src/Main/Game/Graphics/orange_right.png");
            g4.drawImage(icon.getImage(), ghost4.getLocationInX(), ghost4.getLocationInY(), ghost4.getPlayerSize(), ghost4.getPlayerSize(), null);
        } else {
            ImageIcon icon = new ImageIcon("src/Main/Game/Graphics/orange_up.png");
            g4.drawImage(icon.getImage(), ghost4.getLocationInX(), ghost4.getLocationInY(), ghost4.getPlayerSize(), ghost4.getPlayerSize(), null);
        }
    }


    public void chaseCalculator() {
        // this method manages the path of ghosts game

        if (player.ghostKillerEaten) {
            for (int i = 0; i < ghosts.length; i++) {
                ghosts[i].escape(player);
                ghosts[i].isAfterScapeOrChase = true;
            }
        } else {
            chasePosition();
            for (int i = 0; i < ghosts.length; i++) {
                if (ghosts[i].chase) {
                    makeChase(ghosts[i]);
                    ghosts[i].isAfterScapeOrChase = true;

                } else {
                    ghosts[i].randomSteps();
                }
            }
        }
    }


    public void chasePosition() {
        // this method selects ghost for chase, and put the others for random steps

        int num;
        Random ran = new Random();
        num = ran.nextInt(500);
        if (num < 4) {
            for (int i = 0; i < ghosts.length; i++) {
                if (i == num) {
                    ghosts[i].chase = true;
                } else ghosts[i].chase = false;
            }
        }
    }

    public void makeChase(Ghost ghost) {
        //this method knows how make chase after the player

        int num;
        Random ran = new Random();
        if (player.getLocationInY() <= ghost.getLocationInY() && GameObjects.canMoveUp(ghost.getLocationInY() - ghost.getPlayerSpeed(), ghost.getLocationInX())) {
            ghost.setLocationInY(ghost.getLocationInY() - ghost.getPlayerSpeed());
            num = 0;
            Ghost.setMovement(num, ghost);
        } else if (player.getLocationInY() >= ghost.getLocationInY() && GameObjects.canMoveDown(ghost.getLocationInY() + ghost.getPlayerSpeed(), ghost.getLocationInX())) {
            ghost.setLocationInY(ghost.getLocationInY() + ghost.getPlayerSpeed());
            num = 1;
            Ghost.setMovement(num, ghost);
        }
        if (player.getLocationInX() <= ghost.getLocationInX() && GameObjects.canMoveLeft(ghost.getLocationInY(), ghost.getLocationInX() - ghost.getPlayerSpeed())) {
            ghost.setLocationInX(ghost.getLocationInX() - ghost.getPlayerSpeed());
            num = 2;
            Ghost.setMovement(num, ghost);
        } else if (player.getLocationInX() >= ghost.getLocationInX() && GameObjects.canMoveRight(ghost.getLocationInY(), ghost.getLocationInX() + ghost.getPlayerSpeed())) {
            ghost.setLocationInX(ghost.getLocationInX() + ghost.getPlayerSpeed());
            num = 3;
            Ghost.setMovement(num, ghost);
        }
    }

    public void keyActions() {
        boolean pressed = keyH.upPressed && !GameObjects.canMoveUp(player.getLocationInY() - 1, player.getLocationInX()) ||
                keyH.downPressed && !GameObjects.canMoveDown(player.getLocationInY() + 1, player.getLocationInX()) ||
                keyH.leftPressed && !GameObjects.canMoveLeft(player.getLocationInY(), player.getLocationInX() - 1) ||
                keyH.rightPressed && !GameObjects.canMoveRight(player.getLocationInY(), player.getLocationInX() + 1);

        if ((keyH.upPressed || pressed && direction.equals("up")) && GameObjects.canMoveUp(player.getLocationInY() - player.getPlayerSpeed(), player.getLocationInX())) {

            direction = "up";

            player.setLocationInY(player.getLocationInY() - player.getPlayerSpeed());
            player.setPoints();
            GameObjects.isEaten(player.getLocationInY() / 25, player.getLocationInX() / 25);
        } else if ((keyH.downPressed || pressed && direction.equals("down")) && GameObjects.canMoveDown(player.getLocationInY() + player.getPlayerSpeed(), player.getLocationInX())) {

            direction = "down";

            player.setLocationInY(player.getLocationInY() + player.getPlayerSpeed());
            player.setPoints();
            GameObjects.isEaten(player.getLocationInY() / 25, player.getLocationInX() / 25);
        } else if ((keyH.leftPressed || pressed && direction.equals("left")) && GameObjects.canMoveLeft(player.getLocationInY(), player.getLocationInX() - player.getPlayerSpeed())) {

            direction = "left";

            player.setLocationInX(player.getLocationInX() - player.getPlayerSpeed());
            player.setPoints();
            GameObjects.isEaten(player.getLocationInY() / 25, player.getLocationInX() / 25);

        } else if ((keyH.rightPressed || pressed && direction.equals("right")) && GameObjects.canMoveRight(player.getLocationInY(), player.getLocationInX() + player.getPlayerSpeed())) {

            direction = "right";


            player.setLocationInX(player.getLocationInX() + player.getPlayerSpeed());
            player.setPoints();
            GameObjects.isEaten(player.getLocationInY() / 25, player.getLocationInX() / 25);

        }
        if (GameObjects.arrOfIndex[(player.getLocationInY() + 2) / 25][(player.getLocationInX() + 2) / 25] == 4 || GameObjects.arrOfIndex[(player.getLocationInY()) / 25][(player.getLocationInX() - 1) / 25] == 4) {
            player.ghostKillerEaten = true;
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    player.ghostKillerEaten = false;
                }
            }, 10 * 1000);
        }
    }

    public void objectsMeeting() {
        // this method manages meeting between the player and the ghost

        int sameArea = 10;
        for (Ghost value : ghosts) {
            if (player.getLocationInY() / sameArea == value.getLocationInY() / sameArea && player.getLocationInX() / sameArea == value.getLocationInX() / sameArea) {
                if (player.ghostKillerEaten) {
                    value.setLocationInY(75);
                    value.setLocationInX(250);
                    player.addPoints();
                } else {
                    player.setLocationInY(25);
                    player.setLocationInX(25);

                    for (Ghost ghost : ghosts) {
                        ghost.setLocationInY(75);
                        ghost.setLocationInX(250);
                    }
                    failuresCount++;
                }
            }
        }
    }

    public void setResult() {
        //this method checks everyTime if does player win or loss and manages the game

        if (failuresCount == 3) {
            gameThread = null;
            player.setLoss(true);
            if (listener != null) {
                listener.onLose();
            }
            System.out.println("The game it's over loser :( ");
        }
        player.setWin(GameObjects.arrOfIndex);
        if (player.isWin()) {
            GameObjects.arrOfIndex = GameObjects.level_2;
            timeForSleeping = 6;
            player.ghostKillerEaten = false;

//            if (listener != null) {
//                listener.onWon();
//            }
//            System.out.println("You won !!! :)");
//            gameThread = null;
        }
    }
    public void soundPlayer(){
         soundManager.playSound( "src/Main/Game/Sounds/regolar.wav");
    }
}





