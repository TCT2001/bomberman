package uet.oop.bomberman.entities.tile.item.durationItem;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.tile.item.Item;
import uet.oop.bomberman.graphics.Sprite;

public class FlamePassItem extends DurationItem {
    public FlamePassItem(int x, int y, Sprite sprite, int currentLevel) {
        super(x, y, sprite, currentLevel);
    }

    @Override
    public void setAttribute() {
        this.duration = 360;
        hasPower = true;
        Game.setBomberPassFlame(true);
    }
}
