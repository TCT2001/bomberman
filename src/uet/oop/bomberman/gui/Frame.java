package uet.oop.bomberman.gui;

import uet.oop.bomberman.game.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Swing Frame chứa toàn bộ các component
 */
public class Frame extends JFrame {

    private final GamePanel _game_pane;
    private final JPanel _container_pane;
    private final InfoPanel _info_pane;

    private final Game _game;

    public Frame() {

        _container_pane = new JPanel(new BorderLayout());
        _game_pane = new GamePanel(this);
        _info_pane = new InfoPanel();

        _container_pane.add(_info_pane, BorderLayout.PAGE_START);
        _container_pane.add(_game_pane, BorderLayout.PAGE_END);

        _game = _game_pane.getGame();

        add(_container_pane);

        setResizable(false);
        setUndecorated(true);
        getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        _game.start();
    }

    public void setTime(int time) {
        _info_pane.setTime(time);
    }

    public void setPoints(int points) {
        _info_pane.setPoints(points);
    }

    public void setLives(int lives) {
        _info_pane.setLives(lives);
    }

}
