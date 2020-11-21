package uet.oop.bomberman.entities.tile.item.durationItem;

import uet.oop.bomberman.entities.tile.item.Item;
import uet.oop.bomberman.graphics.Sprite;

public abstract class DurationItem extends Item {

    protected int duration;

    public DurationItem(int x, int y, Sprite sprite, int currentLevel) {
        super(x, y, sprite, currentLevel);
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
