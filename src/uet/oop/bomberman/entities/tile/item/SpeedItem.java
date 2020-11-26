package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

public class SpeedItem extends Item {


	public SpeedItem(int x, int y, Sprite sprite, int currentLevel) {
		super(x, y, sprite, currentLevel);
	}

	@Override
	public void setAttribute() {
		this.hasPower = true;
		Game.addBomberSpeed(0.2); //Increase 0.2
	}
}
