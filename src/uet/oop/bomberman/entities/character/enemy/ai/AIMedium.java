package uet.oop.bomberman.entities.character.enemy.ai;

import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.enemy.Enemy;

public class AIMedium extends AI {
    Bomber _bomber;
    Enemy _e;

    public AIMedium(Bomber bomber, Enemy e) {
        _bomber = bomber;
        _e = e;
    }

    //0 1 2 3 up right down left
    @Override
    public int calculateDirection() {
        // TODO: cài đặt thuật toán tìm đường đi
        //can bang vi tri enemy voi bomber
        //move can bang ko dc thi move de di chuyen
        double deltaX = _bomber.getX() - _e.getX() > 0 ? 1 : -1;
        double deltaY = _bomber.getY() - _e.getY() > 0 ? 1 : -1;

        //can bang vi tri bomber
        if (_e.canMove(deltaX + _e.getX(), _e.getY())) {
            return deltaX < 0 ? 3 : 1;
        }
        if (_e.canMove(_e.getX(), _e.getY() + deltaY)) {
            return deltaY < 0 ? 0 : 2;
        }

        //move dc thi di
        if (_e.canMove(-deltaX + _e.getX(), _e.getY())) {
            return deltaX > 0 ? 3 : 1;
        }
        if (_e.canMove(_e.getX(), _e.getY() - deltaY)) {
            return deltaY > 0 ? 0 : 2;
        }
        //default
        return 1;
    }

}
