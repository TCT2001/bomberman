package uet.oop.bomberman.sound;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


public class Sound {

    private static final String bombExplose = "res\\sound\\bang.wav";
    private static final String nextLevel = "res\\sound\\next_level.wav";
    private static final String bomberDie = "res\\sound\\die_bomber.wav";
    private static final String item = "res\\sound\\item.wav";
    private static final String lose = "res\\sound\\lose.mid";
    private static final String mosterDie = "res\\sound\\die_monster.wav";
    private static final String newBomb = "res\\sound\\new_bomb.wav";
    private static final String win = "res\\sound\\win.wav";
    private static final String backgroundGame = "res\\sound\\back_ground.wav";
    private static final String broken = "res\\sound\\broken.wav";

    public Sound() {

    }


    public static void play(String filePath) {
        InputStream music;
        try {
            music = new FileInputStream(new File(filePath));
            AudioStream audio = new AudioStream(music);
            AudioPlayer.player.start(audio);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * các hàm gọi trong các trường hợp trong game
     */


    public static void playBroken() {
        Sound.play(broken);
    }

    public static void playBombExplose() {
        Sound.play(bombExplose);
    }

    public static void playNextLevel() {
        Sound.play(nextLevel);
    }

    public static void playBomberDie() {
        Sound.play(bomberDie);
    }

    public static void playGetNewItem() {
        Sound.play(item);
    }

    public static void playLose() {
        Sound.play(lose);
    }

    public static void playMosterDie() {
        Sound.play(mosterDie);
    }

    public static void playPlaceNewBomb() {
        //for ( int i = 0 ; i < 10000 ; i++)
        Sound.play(newBomb);
        // Sound.playBombExplose();
    }

    public static void playWin() {
        Sound.play(win);
    }

    public static void playBackGround() {
        Sound.play(backgroundGame);
    }
}
