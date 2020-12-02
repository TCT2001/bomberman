package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.character.enemy.ai.AILow;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

//balloon v2
public class Minvo extends Enemy {
    private Random random = new Random();
    public Minvo(int x, int y, Board board) {

        super(x, y, board, Sprite.minvo_dead, 0.75, 100);
        _sprite = Sprite.minvo_left1;

        _ai = new AILow();
        _direction = _ai.calculateDirection();
        MAX_STEPS *= 2;
    }


    @Override
    public void move(double xa, double ya) {
        double speed = _speed*(random.nextInt(199) / 199.0 + 0.3);//0.3 - 1.3
        if (!_alive) return;
        _y += ya * speed;
        _x += xa * speed;
    }

    @Override
    protected void chooseSprite() {
        switch(_direction) {
            case 0:
            case 1:
                _sprite = Sprite.movingSprite(Sprite.minvo_right1, Sprite.minvo_right2, Sprite.minvo_right3, _animate, 60);
                break;
            case 2:
            case 3:
                _sprite = Sprite.movingSprite(Sprite.minvo_left1, Sprite.minvo_left2, Sprite.minvo_left3, _animate, 60);
                break;
        }
    }
}
