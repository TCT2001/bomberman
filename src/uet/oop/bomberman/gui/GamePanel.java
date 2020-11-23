package uet.oop.bomberman.gui;

import uet.oop.bomberman.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Swing Panel chứa cảnh game
 */
public class GamePanel extends JPanel {

    private Game _game;
    public boolean canVisible = false;

    public GamePanel(Frame frame) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(Game.WIDTH * Game.SCALE, Game.HEIGHT * Game.SCALE));

        _game = new Game(frame);
        add(_game);
        _game.setVisible(canVisible);

        setVisible(canVisible);
        setFocusable(true);
    }

    public Game getGame() {
        return _game;
    }

}
