package uet.oop.bomberman.entities.tile;

import uet.oop.bomberman.game.Board;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

public class Portal extends Tile {

    private Board board;

    public Portal(int x, int y, Sprite sprite, Board board) {
        super(x, y, sprite);
        this.board = board;
    }

    @Override
    public boolean collide(Entity e) {
        // TODO: xử lý khi Bomber đi vào
        if (e instanceof Bomber) {

            if (!board.detectNoEnemies()) {
                //TODO: FOR TEST //return false;
                return false;
            }

            if (e.getXTile() == getX() && e.getYTile() == getY()) {
                Sound.playWin();
                board.nextLevel();
            }
            return true;
        }
        return true;
    }

}
