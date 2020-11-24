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

        super(x, y, board, Sprite.minvo_dead, Game.getBomberSpeed(), 100);
        _sprite = Sprite.minvo_left1;

        _ai = new AILow();
        _direction = _ai.calculateDirection();
        MAX_STEPS *= 2;
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
    @Override
    public void calculateMove() {
        super.calculateMove();
        _speed *= (random.nextInt(199) / 285.0 + 0.7);//0.7 - 1.5
    }
}
