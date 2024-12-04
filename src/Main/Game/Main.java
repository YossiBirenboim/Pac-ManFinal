package Main.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    static JFrame window;
    static GamePanel gamePanel;

    public static void main(String[] args) {

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Pack-Man");

        JPanel winPanel = new JPanel();
        JButton winButton = new JButton("You wom !!:)");
        winPanel.add(winButton);
        JPanel lossPanel = new JPanel();
        JButton lossButton = new JButton("New Game");
        lossButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                gamePanel = GamePanel.getInstance();
                window.add(gamePanel);
                System.out.println(" wow ");
                window.setLocationRelativeTo(null);
                window.setVisible(true);
            }
        });
        lossPanel.add(lossButton);

        gamePanel = GamePanel.getInstance();
        window.add(gamePanel);


        gamePanel.setListener(new GameActionListener() {
            @Override
            public void onLose() {
                System.out.println("Remove game panel");
                window.remove(gamePanel);
                window.setVisible(false);
                window.add(lossPanel);
                window.invalidate();
                window.setVisible(true);
            }

            @Override
            public void onWon() {
                System.out.println("Remove game panel");
                window.remove(gamePanel);
                window.setVisible(false);
                window.add(winPanel);
                window.invalidate();
                window.setVisible(true);
            }
        });

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameTread();
        gamePanel.soundPlayer();
    }
}

