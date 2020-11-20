package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

public class WallPassItem extends Item {

    public WallPassItem(int x, int y, Sprite sprite, int currentLevel) {
        super(x, y, sprite, currentLevel);
    }

    @Override
    public void setAttribute() {
        this.effect_duration = 240; //4s
        this.hasPower = true;
        Game.setBomberPassWall(true);
    }

}
