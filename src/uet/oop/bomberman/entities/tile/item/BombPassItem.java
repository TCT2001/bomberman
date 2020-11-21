package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

public class BombPassItem extends Item {
    public BombPassItem(int x, int y, Sprite sprite, int currentLevel) {
        super(x, y, sprite, currentLevel);
    }

    @Override
    public void setAttribute() {
        this.hasPower = true;
        Game.setBomberPassBomb(true);
    }
}
