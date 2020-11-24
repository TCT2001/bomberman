package uet.oop.bomberman.sound;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


public class Sound {

    private static final String bombExplosive = "res\\sound\\bang.wav";
    private static final String nextLevel = "res\\sound\\next_level.wav";
    private static final String bomberDie = "res\\sound\\die_bomber.wav";
    private static final String item = "res\\sound\\item.wav";
    private static final String lose = "res\\sound\\lose.mid";
    private static final String monsterDie = "res\\sound\\die_monster.wav";
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


    public static void playBroken() {
        Sound.play(broken);
    }

    public static void playBombExplosive() {
        Sound.play(bombExplosive);
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

    public static void playMonsterDie() {
        Sound.play(monsterDie);
    }

    public static void playPlaceNewBomb() {
        Sound.play(newBomb);
    }

    public static void playWin() {
        Sound.play(win);
    }

    public static void playBackGround() {
        Sound.play(backgroundGame);
    }
}
