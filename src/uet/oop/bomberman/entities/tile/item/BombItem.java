package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.game.Game;
import uet.oop.bomberman.graphics.Sprite;

public class BombItem extends Item {


	public BombItem(int x, int y, Sprite sprite, int currentLevel) {
		super(x, y, sprite, currentLevel);
	}

	@Override
	public void setAttribute() {
		this.hasPower = true;
		Game.addBombRate(1); //Increase 1
	}
}
