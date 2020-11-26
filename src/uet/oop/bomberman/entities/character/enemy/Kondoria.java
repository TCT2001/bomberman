package uet.oop.bomberman.entities.character.enemy;
//enemy create enemy

import uet.oop.bomberman.Board;
import uet.oop.bomberman.Game;
import uet.oop.bomberman.entities.character.enemy.ai.AILow;
import uet.oop.bomberman.graphics.Sprite;

import java.util.Random;

public class Kondoria extends Enemy {
    private Random random = new Random();
    private boolean added = false;


    public Kondoria(int x, int y, Board board) {
        super(x, y, board, Sprite.kondoria_dead, 0.7, 200);
        _sprite = Sprite.kondoria_left1;
        _ai = new AILow();
        _direction = _ai.calculateDirection();
    }

    @Override
    public void update() {
        super.update();
        if (_board.getTime() % 60 == 0) {
            if (added) {
                return;
            }
            added = true;
            int n = random.nextInt(4) + 1;
            switch (n) {
                case 1:
                    System.out.println("case 1");
                    _board.addCharacter(new Balloon((int) _x, (int) _y, _board));
                    break;
                case 2:
                    System.out.println("case 2");
                    _board.addCharacter(new Minvo((int) _x, (int) _y, _board));
                    break;
                case 3:
                    System.out.println("case 3");
                    _board.addCharacter(new Oneal((int) _x, (int) _y, _board));
                    break;
                case 4:
                    System.out.println("case 4");
                    _board.addCharacter(new Doll((int) _x, (int) _y, _board));
                    break;
            }
        } else {
            added = false;
        }
    }

    @Override
    protected void chooseSprite() {
        switch (_direction) {
            case 0:
            case 1:
                _sprite = Sprite.movingSprite(Sprite.kondoria_right1,
                        Sprite.kondoria_right2,
                        Sprite.kondoria_right3, _animate, 60);
                break;
            case 2:
            case 3:
                _sprite = Sprite.movingSprite(Sprite.kondoria_left1,
                        Sprite.kondoria_left2,
                        Sprite.kondoria_left3, _animate, 60);
                break;
        }
    }

}
