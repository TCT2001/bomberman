package uet.oop.bomberman.gui;

import uet.oop.bomberman.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Swing Panel hiển thị thông tin thời gian, điểm mà người chơi đạt được
 */
public class InfoPanel extends JPanel {

    // nhãn
    private JLabel timeLabel;
    private JLabel pointsLabel;
    private JLabel livesLabel;

    public InfoPanel() {
        setLayout(new GridLayout());

        timeLabel = new JLabel("Times: " + Game.getBoard().getTime());
        timeLabel.setForeground(Color.LIGHT_GRAY);
        timeLabel.setHorizontalAlignment(JLabel.CENTER);

        pointsLabel = new JLabel("Points: " + Game.getBoard().getPoints());
        pointsLabel.setForeground(Color.CYAN);
        pointsLabel.setHorizontalAlignment(JLabel.CENTER);

        livesLabel = new JLabel("Lives: " + Game.getBoard().get_live());
        livesLabel.setForeground(Color.ORANGE);
        livesLabel.setHorizontalAlignment(JLabel.CENTER);


        add(timeLabel);
        add(pointsLabel);
        add(livesLabel);

        setBackground(Color.black);
        setPreferredSize(new Dimension(0, 40));
    }
    // hiển thị thời gian
    public void setTime(int t) {
        timeLabel.setText("Time: " + t);
    }
    // hiển thị điểm
    public void setPoints(int t) {
        pointsLabel.setText("Score: " + t);
    }
    // hiển thị mạng
    public void setLives(int t) {
        livesLabel.setText("Lives: " + t);
    }

}
