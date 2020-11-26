package uet.oop.bomberman.input;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Controls;
import uet.oop.bomberman.Game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Tiếp nhận và xử lý các sự kiện nhập từ bàn phím
 */
public class Keyboard implements KeyListener {
    private boolean[] keys = new boolean[120]; //120 is enough to this game
    public static boolean up, down, left, right, space;
    private Controls controls;

    public Keyboard(Controls controls) {
        this.controls = controls;
    }

    public void update() {
        up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
        down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
        left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
        space = keys[KeyEvent.VK_SPACE] || keys[KeyEvent.VK_X];
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        keys[e.getKeyCode()] = true;
        //add shortcut direction game
        if (e.getKeyCode() == KeyEvent.VK_N
                && ((e.getModifiers() & KeyEvent.CTRL_MASK) != 0)) {
            //new game
            System.out.println("new game");
            controls.newGame();

        } else if (e.getKeyCode() == KeyEvent.VK_P
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
    public void keyReleased(KeyEvent e) {
        keys[e.getKeyCode()] = false;

    }

}
