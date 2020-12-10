package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.game.Board;
import uet.oop.bomberman.entities.character.enemy.ai.AIMedium;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

//oneal v2
public class Doll extends Enemy {
    private Random random = new Random();

    public Doll(int x, int y, Board board) {
        super(x, y, board, Sprite.doll_dead, 0.75, 200);
        _sprite = Sprite.doll_left1;
        _ai = new AIMedium(_board.getBomber(), this);
        _direction = _ai.calculateDirection();
    }

    @Override
    protected void chooseSprite() {
        switch (_direction) {
            case 0:
            case 1:
                if (_moving)
                    _sprite = Sprite.movingSprite(Sprite.doll_right1, Sprite.doll_right2, Sprite.doll_right3, _animate, 60);
                else
                    _sprite = Sprite.doll_left1;
                break;
            case 2:
            case 3:
                if (_moving)
                    _sprite = Sprite.movingSprite(Sprite.doll_left1, Sprite.doll_left2, Sprite.doll_left3, _animate, 60);
                else
                    _sprite = Sprite.doll_left1;
                break;
        }
    }

    @Override
    public void update() {
        super.update();
    }
}
