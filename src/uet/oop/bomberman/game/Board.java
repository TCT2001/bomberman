package uet.oop.bomberman.game;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Message;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.bomb.FlameSegment;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.graphics.IRender;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.level.FileLevelLoader;
import uet.oop.bomberman.level.LevelLoader;
import uet.oop.bomberman.sound.Sound;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Quản lý thao tác điều khiển, load level, render các màn hình của game
 */
public class Board implements IRender {
    protected LevelLoader _levelLoader;
    protected Game _game;
    protected Screen _screen;

    public Entity[] _entities;
    public List<Character> _characters = new ArrayList<>();
    protected List<Bomb> _bombs = new ArrayList<>();
    private List<Message> _messages = new ArrayList<>();

    private int _screenToShow = -1; //1:endgame, 2:changelevel, 3:paused, 4:menu

    private int _time = Game.TIME;
    private int _points = Game.POINTS;
    public static int _live = Game.lives;

    public Board(Game game) {
        _game = game;
        _screen = Screen.getInstance();
        _time = Game.TIME;
        _points = Game.POINTS;
        _live = Game.lives;
        loadLevel(1); //start in level 1
    }

    @Override
    public void update() {
        if (_game.isPaused()) return;

        updateEntities();
        updateCharacters();
        updateBombs();
        updateMessages();
        detectEndGame();

        for (int i = 0; i < _characters.size(); i++) {
            Character a = _characters.get(i);
            if (a.isRemoved()) _characters.remove(i);
        }
    }

    @Override
    public void render(Screen screen) {
        if (_game.isPaused()) return;

        //only render the visible part of screen
        int x0 = Screen.xOffset >> 4; //tile precision, -> left X
        int x1 = (Screen.xOffset + screen.getWidth() + Game.TILES_SIZE) / Game.TILES_SIZE; // -> right X
        int y0 = Screen.yOffset >> 4;
        int y1 = (Screen.yOffset + screen.getHeight()) / Game.TILES_SIZE; //render one tile plus to fix black margins

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++) {
                _entities[x + y * _levelLoader.getWidth()].render(screen);
            }
        }

        renderBombs(screen);
        renderCharacter(screen);

    }

    public void nextLevel() {
        if (_levelLoader.getLevel() == 5) { //win ? {
            winGame();
            return;
        }
        Sound.playNextLevel();
        loadLevel(_levelLoader.getLevel() + 1);
    }

    public void loadLevel(int level) {
        _time = Game.TIME;
        _screenToShow = 2;
        _game.resetScreenDelay();
        _game.pause();
        _characters.clear();
        _bombs.clear();
        _messages.clear();
        _game.defaultValue();

        _levelLoader = new FileLevelLoader(this, level);
        _entities = new Entity[_levelLoader.getHeight() * _levelLoader.getWidth()];
        _levelLoader.createEntities();
    }

    protected void detectEndGame() {
        if (_time <= 0) {
            endGame();
        }
    }

    public void endGame() {
        Sound.playLose();
        _screenToShow = 1;
        _game.stop();
    }

    public void winGame() {
        Sound.playWin();
        _screenToShow = 4;
        _game.stop();
    }

    public boolean detectNoEnemies() {
        int total = 0;
        for (int i = 0; i < _characters.size(); i++) {
            if (_characters.get(i) instanceof Bomber == false)
                ++total;
        }

        return total == 0;
    }

    public void drawScreen(Graphics g) {
        switch (_screenToShow) {
            case 1:
                _screen.drawEndGame(g, _points, "GAME OVER");
                break;

            case 2:
                _screen.drawChangeLevel(g, _levelLoader.getLevel());
                break;
            case 3:
                _screen.drawPaused(g);
                break;
            case 4:
                _screen.drawEndGame(g, _points, "YOU WIN !");
                break;
        }
    }

    public Entity getEntity(double x, double y, Character m) {
        Entity res = null;

        res = getFlameSegmentAt((int) x, (int) y);
        if (res != null) {
            return res;
        }

        res = getBombAt(x, y);
        if (res != null) {
            return res;
        }

        res = getCharacterAtExcluding((int) x, (int) y, m);
        if (res != null) {
            //TODO
            return res;
        }

        res = getEntityAt((int) x, (int) y);

        return res;
    }

    public List<Bomb> getBombs() {
        return _bombs;
    }

    public Bomb getBombAt(double x, double y) {
        Iterator<Bomb> bs = _bombs.iterator();
        Bomb b;
        while (bs.hasNext()) {
            b = bs.next();
            if (b.getX() == (int) x && b.getY() == (int) y)
                return b;
        }

        return null;
    }

    public Bomber getBomber() {
        Iterator<Character> itr = _characters.iterator();

        Character cur;
        while (itr.hasNext()) {
            cur = itr.next();

            if (cur instanceof Bomber)
                return (Bomber) cur;
        }

        return null;
    }

    public Character getCharacterAtExcluding(int x, int y, Character a) {
        Iterator<Character> itr = _characters.iterator();

        Character cur;
        while (itr.hasNext()) {
            cur = itr.next();
            if (cur == a) {
                continue;
            }

            if (cur.getXTile() == x && cur.getYTile() == y) {
                return cur;
            }

        }

        return null;
    }

    public Character getCharacterAt(double x, double y) {
        Iterator<Character> itr = _characters.iterator();

        Character cur;
        while (itr.hasNext()) {
            cur = itr.next();
            if (cur.getXTile() == x && cur.getYTile() == y) {
                return cur;
            }
        }
        return null;
    }


    public FlameSegment getFlameSegmentAt(int x, int y) {
        Iterator<Bomb> bs = _bombs.iterator();
        Bomb b;
        while (bs.hasNext()) {
            b = bs.next();

            FlameSegment e = b.flameAt(x, y);
            if (e != null) {
                return e;
            }
        }

        return null;
    }

    public int get_live() {
        return _live;
    }

    public static void addLive(int i) {
        _live += i;
    }

    public Entity getEntityAt(double x, double y) {
        try {
            return _entities[(int) x + (int) y * _levelLoader.getWidth()];
        } catch (Exception e) {
            return null;
        }
    }

    public void addEntity(int pos, Entity e) {
        _entities[pos] = e;
    }

    public void addCharacter(Character e) {
        _characters.add(e);
    }

    public void addBomb(Bomb e) {
        _bombs.add(e);
    }

    public void addMessage(Message e) {
        _messages.add(e);
    }

    protected void renderCharacter(Screen screen) {
        Iterator<Character> itr = _characters.iterator();

        while (itr.hasNext())
            itr.next().render(screen);
    }

    protected void renderBombs(Screen screen) {
        Iterator<Bomb> itr = _bombs.iterator();

        while (itr.hasNext())
            itr.next().render(screen);
    }

    public void renderMessages(Graphics g) {
        Message m;
        for (int i = 0; i < _messages.size(); i++) {
            m = _messages.get(i);

            g.setFont(new Font("Arial", Font.PLAIN, m.getSize()));
            g.setColor(m.getColor());
            g.drawString(m.getMessage(), (int) m.getX() - Screen.xOffset * Game.SCALE, (int) m.getY());
        }
    }

    protected void updateEntities() {
        if (_game.isPaused()) return;
        for (int i = 0; i < _entities.length; i++) {
            _entities[i].update();
        }
    }

    protected void updateCharacters() {
        if (_game.isPaused()) return;

        for (int i = 0; i < _characters.size(); i++) {
            Character m = _characters.get(i);
            m.update();
        }
    }

    protected void updateBombs() {
        if (_game.isPaused()) return;
        Iterator<Bomb> itr = _bombs.iterator();

        while (itr.hasNext())
            itr.next().update();
    }

    protected void updateMessages() {
        if (_game.isPaused()) return;
        Message m;
        int left;
        for (int i = 0; i < _messages.size(); i++) {
            m = _messages.get(i);
            left = m.getDuration();

            if (left > 0)
                m.setDuration(--left);
            else
                _messages.remove(i);
        }
    }

    public int subtractTime() {
        if (_game.isPaused())
            return this._time;
        else
            return this._time--;
    }


    public LevelLoader getLevel() {
        return _levelLoader;
    }

    public Game getGame() {
        return _game;
    }

    public int getShow() {
        return _screenToShow;
    }

    public void setShow(int i) {
        _screenToShow = i;
    }

    public int getTime() {
        return _time;
    }

    public int getPoints() {
        return _points;
    }

    public void addPoints(int points) {
        this._points += points;
    }

    public int getWidth() {
        return _levelLoader.getWidth();
    }

    public int getHeight() {
        return _levelLoader.getHeight();
    }

    public void gamePause() {
        _game.resetScreenDelay();
        if (_screenToShow <= 0)
            _screenToShow = 3;
        _game.pause();
    }

    public void gameResume() {
        _game.resetScreenDelay();
        _screenToShow = -1;
        _game.run();
    }

    public void speedUpGame() {
        _game.addBomberSpeed(0.2);
    }

    public void quitGame() {
        System.exit(0);
    }

    public void immortalGame() {
        getBomber().immortal();
    }

    public void addBombSize() {
        _game.addBombRate(999);
    }

}
