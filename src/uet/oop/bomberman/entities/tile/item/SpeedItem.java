package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.graphics.Sprite;

public class SpeedItem extends Item {


	public SpeedItem(int x, int y, Sprite sprite, int currentLevel) {
		super(x, y, sprite, currentLevel);
	}

	@Override
	public void setAttribute() {
		this.effect_duration = -1; //always has this power in current level
		this.hasPower = true;
		Game.addBomberSpeed(0.1); //Increase 0.1
	}
}
