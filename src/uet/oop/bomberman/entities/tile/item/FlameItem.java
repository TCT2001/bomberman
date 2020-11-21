package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Sprite;

public class FlameItem extends Item {


	public FlameItem(int x, int y, Sprite sprite, int currentLevel) {
		super(x, y, sprite, currentLevel);
	}

	@Override
	public void setAttribute() {
		this.hasPower = true;
		Game.addBombRadius(1); //Increase 1
	}
}
