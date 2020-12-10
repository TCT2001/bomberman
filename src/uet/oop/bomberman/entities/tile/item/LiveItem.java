package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.game.Board;
import uet.oop.bomberman.graphics.Sprite;

public class LiveItem extends Item {

    public LiveItem(int x, int y, Sprite sprite, int currentLevel) {
        super(x, y, sprite, currentLevel);
    }

    @Override
    public void setAttribute() {
        this.hasPower = true;
        Board.addLive(1); //Tăng 1 mạng
    }
}
