package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.game.Game;
import uet.oop.bomberman.graphics.Sprite;

public class FlamePassItem extends Item {
    public FlamePassItem(int x, int y, Sprite sprite, int currentLevel) {
        super(x, y, sprite, currentLevel);
    }

    @Override
    public void setAttribute() {
        hasPower = true;
        Game.setBomberPassFlame(true);
    }
}
