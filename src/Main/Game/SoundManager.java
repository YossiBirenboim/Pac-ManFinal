package Main.Game;

import javax.sound.sampled .*;
import java.io.File;
import java.io.IOException;
public class SoundManager {

    // שיטה להאזנה לסאונד
    public static void playSound(String soundFilePath) {
        try {
            // טוען את הקובץ
            File soundFile = new File(soundFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();  // מתחיל את הסאונד
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // קורא לפונקציה לשמיעת סאונד
        playSound("src/Main/Game/Sounds/eating fruits.wav");
        System.out.println("cccc");
    }
    public static void playBackgroundMusic(String musicFilePath) {
        try {
            File musicFile = new File(musicFilePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);  // השמעה בלולאה אינסופית
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


