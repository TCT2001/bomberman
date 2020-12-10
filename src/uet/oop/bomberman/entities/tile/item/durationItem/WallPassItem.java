package uet.oop.bomberman.entities.tile.item.durationItem;

import uet.oop.bomberman.game.Game;
import uet.oop.bomberman.graphics.Sprite;

public class WallPassItem extends DurationItem {

    public WallPassItem(int x, int y, Sprite sprite, int currentLevel) {
        super(x, y, sprite, currentLevel);
    }

    @Override
    public void setAttribute() {
        this.duration = 360;
        this.hasPower = true;
        Game.setBomberPassWall(true);
    }

}
