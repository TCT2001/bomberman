package uet.oop.bomberman.entities.tile.item;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.tile.Tile;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.graphics.Sprite;

public abstract class Item extends Tile {

	protected int effect_duration = -2; //Mặc định khởi tạo là -2
	protected boolean hasPower = true;
	protected int currentLevel;

	public Item(int x, int y, Sprite sprite, int currentLevel ) {
		super(x, y, sprite);
		this.currentLevel = currentLevel;
		setAttribute();
	}

	public int getEffect_duration() {
		return effect_duration;
	}

	public void setEffect_duration(int effect_duration) {
		this.effect_duration = effect_duration;
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
			((Bomber) e).addPowerup(this);
			//Sau khi ăn item, item biến mất
			remove();
			return true;
		}
		return false;
	}
}
