package uet.oop.bomberman.entities.bomb;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.AnimatedEntitiy;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.level.Coordinates;
import uet.oop.bomberman.sound.Sound;

public class Bomb extends AnimatedEntitiy {

    protected double _timeToExplode = 120; //2 seconds
    public int _timeAfter = 20;

    protected Board _board;
    protected Flame[] _flames = new Flame[0];
    protected boolean _exploded = false;
    protected boolean _allowedToPassBomb = true; //mặc định là đi qua được

    public Bomb(int x, int y, Board board) {
        _x = x;
        _y = y;
        _board = board;
        _sprite = Sprite.bomb;
    }

    @Override
    public void update() {
        if (_timeToExplode > 0)
            _timeToExplode--;
        else {
            if (!_exploded)
                explode();
            else
                updateFlames();

            if (_timeAfter > 0)
                _timeAfter--;
            else
                remove();
        }

        animate();
    }

    @Override
    public void render(Screen screen) {
        if (_exploded) {
            _sprite = Sprite.bomb_exploded2;
            renderFlames(screen);
        } else
            _sprite = Sprite.movingSprite(Sprite.bomb, Sprite.bomb_1, Sprite.bomb_2, _animate, 60);

        int xt = (int) _x << 4;
        int yt = (int) _y << 4;

        screen.renderEntity(xt, yt, this);
    }

    public void renderFlames(Screen screen) {
        for (int i = 0; i < _flames.length; i++) {
            _flames[i].render(screen);
        }
    }

    public void updateFlames() {
        for (int i = 0; i < _flames.length; i++) {
            _flames[i].update();
        }
    }

    /**
     * Xử lý Bomb nổ
     */
    protected void explode() {
        _exploded = true;

        // TODO: xử lý khi Character đứng tại vị trí Bomb
        Character character = _board.getCharacterAt(this._x, this._y);
        if (character != null) {
            character.kill();
        }
        // TODO: tạo các Flame, toả ra 4 hướng
        _flames = new Flame[4];
        for (int i = 0; i < 4; i++) {
            _flames[i] = new Flame((int) _x, (int) _y, i, Game.getBombRadius());
        }
        Sound.playBombExplose();
    }

    public FlameSegment flameAt(int x, int y) {
        if (!_exploded) return null;

        for (int i = 0; i < _flames.length; i++) {
            if (_flames[i] == null) return null;
            FlameSegment e = _flames[i].flameSegmentAt(x, y);
            if (e != null) return e;
        }

        return null;
    }

    @Override
    public boolean collide(Entity e) {
        // TODO: xử lý khi Bomber có thể di xuyên qua bomb nhờ item bombPass
        if (e instanceof Bomber && Game.isBomberPassBomb()) {
            return true;
        }
        // TODO: xử lý khi Bomber đi ra sau khi vừa đặt bom (_allowedToPassBomb)
        if (e instanceof Bomber) {
            double diffX = e.getX() - Coordinates.tileToPixel(this.getX()); //Sự chênh nhau của e với bomb theo trục X
            double diffY = e.getY() - Coordinates.tileToPixel(this.getY()); //Sự chênh nhau của e với bomb theo trục Y

            //Khi đã ra khỏi bom thì không thể quay lại
            if (!(diffX >= -10 && diffX < 16 && diffY >= 1 && diffY <= 28)) {
                _allowedToPassBomb = false;
            }
            return _allowedToPassBomb;
        }
        // TODO: xử lý va chạm với Flame của Bomb khác
        //Nếu bomb chạm với flame thì bomb đó nổ luôn
        if (e instanceof Flame) {
            explode();
            return true;
        }
        return false;
    }
}
