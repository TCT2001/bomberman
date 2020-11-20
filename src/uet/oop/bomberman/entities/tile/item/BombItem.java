package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class BombItem extends Item {


	public BombItem(int x, int y, Sprite sprite, int currentLevel) {
		super(x, y, sprite, currentLevel);
	}

	@Override
	public void setAttribute() {
		this.effect_duration = -1; //always has this power in current level
		this.hasPower = true;
		Game.addBombRate(1); //Increase 1
	}
}
