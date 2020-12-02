package uet.oop.bomberman.input;

import uet.oop.bomberman.Controls;
import uet.oop.bomberman.file.FileUltis;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Tiếp nhận và xử lý các sự kiện nhập từ bàn phím
 */
public class Keyboard implements KeyListener {
    private boolean[] keys = new boolean[120]; //120 is enough to this game
    public static boolean up, down, left, right, boom;
    public static String sUp, sDown, sLeft, sRight, sBoom;
    private Controls controls;

    public Keyboard(Controls controls) {
        this.controls = controls;
        updateKey();
    }

    public static void updateKey() {
        String s[] = FileUltis.getKeyNav();
        sUp = s[0];
        sLeft = s[1];
        sDown = s[2];
        sRight = s[3];
        sBoom = s[4];
    }

    private void setKey(KeyEvent e, Boolean bool) {
        if (getkeyText(e).equals(sUp)) {
            up = bool;
        }
        if (getkeyText(e).equals(sDown)) {
            down = bool;
        }
        if (getkeyText(e).equals(sLeft)) {
            left = bool;
        }
        if (getkeyText(e).equals(sRight)) {
            right = bool;
        }
        if (getkeyText(e).equals(sBoom)) {
            boom = bool;
        }
    }

    private void addNav(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_P
                && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            //pause
            controls.pause();
            System.out.println("pause");
        } else if (e.getKeyCode() == KeyEvent.VK_R
                && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            //resume
            controls.resume();
            System.out.println("resume");
        } else if (e.getKeyCode() == KeyEvent.VK_Q
                && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            controls.quitGame();
            System.out.println("quit");
        } else if (e.getKeyCode() == KeyEvent.VK_H
                && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {

            //help
        } else if (e.getKeyCode() == KeyEvent.VK_U
                && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            controls.levelUpGame();
            System.out.println("level up ");
        }
        //cheap game
        else if (e.getKeyCode() == KeyEvent.VK_D
                && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            controls.immortalGame();
            System.out.println("can't die");
        } else if (e.getKeyCode() == KeyEvent.VK_S
                && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {

            controls.upSpeedGame();
            System.out.println("hack speed");
        } else if (e.getKeyCode() == KeyEvent.VK_T
                && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            //time ?
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        setKey(e, true);
        addNav(e);
    }

    private String getkeyText(KeyEvent e) {
        return e.paramString().split(",")[2].split("=")[1].toUpperCase();
    }


    @Override
    public void keyReleased(KeyEvent e) {
        setKey(e, false);
    }

}
