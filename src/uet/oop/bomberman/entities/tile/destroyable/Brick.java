package uet.oop.bomberman.entities.tile.destroyable;

import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.level.Coordinates;
import uet.oop.bomberman.sound.Sound;

public class Brick extends DestroyableTile {
	public int isUpdate = 1;
	public Brick(int x, int y, Sprite sprite) {
		super(x, y, sprite);
	}
	
	@Override
	public void update() {
		super.update();
	}
	
	@Override
	public void render(Screen screen) {
		int x = Coordinates.tileToPixel(_x);
		int y = Coordinates.tileToPixel(_y);

		//Nếu bị nổ, hiện cảnh gạch bị nổ dùng sprite, sau đó render cái entity ở dưới cái gạch bị nổ
		if(this._destroyed) {
			_sprite = movingSprite(Sprite.brick_exploded, Sprite.brick_exploded1, Sprite.brick_exploded2);
			screen.renderEntityWithBelowSprite(x, y, this, _belowSprite);
			Sound.playBroken();
		}
		else
			screen.renderEntity( x, y, this);
	}
}
