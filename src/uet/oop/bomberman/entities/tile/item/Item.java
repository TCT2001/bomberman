package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.tile.Tile;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.sound.Sound;

public abstract class Item extends Tile {

	protected boolean hasPower = false;
	protected int currentLevel;

	public Item(int x, int y, Sprite sprite, int currentLevel ) {
		super(x, y, sprite);
		this.currentLevel = currentLevel;
	}

	public boolean isHasPower() {
		return hasPower;
	}

	public void setHasPower(boolean hasPower) {
		this.hasPower = hasPower;
	}

	public int getCurrentLevel() {
		return currentLevel;
	}

	public void setCurrentLevel(int currentLevel) {
		this.currentLevel = currentLevel;
	}

	public abstract void setAttribute();

	@Override
	public boolean collide(Entity e) {
		// TODO: xử lý Bomber ăn Item
		if (e instanceof Bomber) {
			((Bomber) e).addPower(this);
			//Sau khi ăn item, item biến mất
			Sound.playGetNewItem();
			remove();
			return true;
		}
		return false;
	}
}
