package uet.oop.bomberman;

import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.gui.Frame;
import uet.oop.bomberman.input.Keyboard;
import uet.oop.bomberman.sound.Sound;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

/**
 * Tạo vòng lặp cho game, lưu trữ một vài tham số cấu hình toàn cục,
 * Gọi phương thức render(), update() cho tất cả các entity
 */
public class Game extends Canvas {

    public static final int TILES_SIZE = 16,
            WIDTH = TILES_SIZE * (31 / 2),
            HEIGHT = 13 * TILES_SIZE;

    public static int SCALE = 3;

    public static final String TITLE = "BombermanGame";

    private static final int BOMBRATE = 1;
    private static final int BOMBRADIUS = 1;
    private static final double BOMBERSPEED = 1.0;
    public static final int LIVES = 1; // mang

    public static final int TIME = 200;
    public static final int POINTS = 0;

    protected static int SCREENDELAY = 3;

    protected static int bombRate = BOMBRATE;
    protected static int bombRadius = BOMBRADIUS;
    protected static double bomberSpeed = BOMBERSPEED;
    protected static boolean bomberPassWall = false;
    protected static boolean bomberPassBomb = false;
    protected static boolean bomberPassFlame = false;
    protected static int lives = LIVES;


    protected int _screenDelay = SCREENDELAY;

    private Keyboard _input;
    private boolean _running = false;
    private boolean _paused = true;

    private static Board _board;
    private Screen screen;
    private Frame _frame;

    private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public Game(Frame frame) {
        _frame = frame;
        _frame.setTitle(TITLE);
        _board = new Board(this);
        screen = Screen.getInstance();
        _input = new Keyboard(new Controls(_board));


        addKeyListener(_input);
    }

    private void renderGame() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();

        _board.render(screen);

        for (int i = 0; i < pixels.length; i++) {
            pixels[i] = screen._pixels[i];
        }

        Graphics g = bs.getDrawGraphics();

        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        _board.renderMessages(g);

        g.dispose();
        bs.show();
    }

    private void renderScreen() {
        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();

        Graphics g = bs.getDrawGraphics();

        _board.drawScreen(g);

        g.dispose();
        bs.show();
    }

    private void update() {
        _input.update();
        _board.update();
    }

    private void defaultValue() {
        bombRate = BOMBRATE;
        bombRadius = BOMBRADIUS;
        bomberSpeed = BOMBERSPEED;
        bomberPassWall = false;
        bomberPassBomb = false;
        bomberPassFlame = false;
        lives = LIVES;
    }

    public void start() {

        defaultValue();
        _running = true;

        long lastTime = System.nanoTime();
        long timer = System.currentTimeMillis();
        final double ns = 1000000000.0 / 60.0; //nanosecond, 60 frames per second
        double delta = 0;
        int frames = 0;
        int updates = 0;
        requestFocus();
        Sound.playBackGround();
        while (_running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }

            if (_paused) {
                if (_screenDelay <= 0) {
                    _board.setShow(-1);
                    _paused = false;
                }
                //TODO : Là cái trước show level trước khi vào game
                renderScreen();
            } else {
                renderGame();
            }


            frames++;
            if (System.currentTimeMillis() - timer > 1000) {
                _frame.setTime(_board.subtractTime());
                _frame.setPoints(_board.getPoints());
                _frame.setLives(_board.get_live());
                timer += 1000;
                _frame.setTitle(TITLE + " | " + updates + " rate, " + frames + " fps");
                updates = 0;
                frames = 0;

                if (_board.getShow() == 2)
                    --_screenDelay;
            }
        }
    }

    public static double getBomberSpeed() {
        return bomberSpeed;
    }

    public static int getBombRate() {
        return bombRate;
    }

    public static int getBombRadius() {
        return bombRadius;
    }

    public static void addBomberSpeed(double i) {
        bomberSpeed += i;
    }

    public static void addBombRadius(int i) {
        bombRadius += i;
    }

    public static void addBombRate(int i) {
        bombRate += i;
    }

    public static boolean isBomberPassWall() {
        return bomberPassWall;
    }

    public static void setBomberPassWall(boolean bomberPassWall) {
        Game.bomberPassWall = bomberPassWall;
    }

    public static boolean isBomberPassBomb() {
        return bomberPassBomb;
    }

    public static void setBomberPassBomb(boolean bomberPassBomb) {
        Game.bomberPassBomb = bomberPassBomb;
    }

    public static boolean isBomberPassFlame() {
        return bomberPassFlame;
    }

    public static void setBomberPassFlame(boolean bomberPassFlame) {
        Game.bomberPassFlame = bomberPassFlame;
    }

    public void resetScreenDelay() {
        _screenDelay = SCREENDELAY;
    }

    public static Board getBoard() {
        return _board;
    }

    public boolean isPaused() {
        return _paused;
    }

    public void pause() {
        _paused = true;
    }

    public void stop() {
        _running = false;
        renderScreen();
        _board.setShow(1);
    }

    public void run() {
        _running = true;
        _paused = false;
    }

}
