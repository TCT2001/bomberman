package uet.oop.bomberman.entities.character.enemy;

import uet.oop.bomberman.game.Board;
import uet.oop.bomberman.game.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.Message;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.entities.character.Character;
import uet.oop.bomberman.entities.character.enemy.ai.AI;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;

import java.awt.*;

public abstract class Enemy extends Character {

    protected int _points;

    protected double _speed;
    protected AI _ai;

    protected double MAX_STEPS;
    protected final double rest;
    protected double _steps;

    protected int _finalAnimation = 30;
    protected Sprite _deadSprite;

    public Enemy(int x, int y, Board board, Sprite dead, double speed, int points) {
        super(x, y, board);

        _points = points;
        _speed = speed;

        MAX_STEPS = Game.TILES_SIZE / _speed;
        rest = (MAX_STEPS - (int) MAX_STEPS) / MAX_STEPS;
        _steps = MAX_STEPS;

        _timeAfter = 20;
        _deadSprite = dead;
    }

    @Override
    public void update() {
        animate();

        if (!_alive) {
            afterKill();
            return;
        }

        if (_alive)
            calculateMove();
    }

    @Override
    public void render(Screen screen) {

        if (_alive)
            chooseSprite();
        else {
            if (_timeAfter > 0) {
                _sprite = _deadSprite;
                _animate = 0;
            } else {
                _sprite = Sprite.movingSprite(Sprite.mob_dead1, Sprite.mob_dead2, Sprite.mob_dead3, _animate, 60);
            }

        }

        screen.renderEntity((int) _x, (int) _y - _sprite.SIZE, this);
    }

    //0 1 2 3 up right down left
    @Override
    public void calculateMove() {
        // TODO: Tính toán hướng đi và di chuyển Enemy theo _ai và cập nhật giá trị cho _direction
        // TODO: sử dụng canMove() để kiểm tra xem có thể di chuyển tới điểm đã tính toán hay không
        // TODO: sử dụng move() để di chuyển
        // TODO: nhớ cập nhật lại giá trị cờ _moving khi thay đổi trạng thái di chuyển
        if (_steps <= 0) {
            _direction = _ai.calculateDirection();
            _steps = MAX_STEPS;
        }

        _steps -= 1;
        int x = 0;
        int y = 0;
        switch (_direction) {
            case 0:
                y--;
                break;
            case 1:
                x++;
                break;
            case 2:
                y++;
                break;
            case 3:
                x--;
                break;
            default:
                x--;
        }

        if (canMove(x*_speed + _x, y*_speed + _y)) {

            move(x, y);
            _moving = true;
        } else {
            _steps -= 1;
            _moving = false;
        }


    }

    @Override
    public void move(double xa, double ya) {
        if (!_alive) return;
        _y += ya * _speed;
        _x += xa * _speed;
    }

    @Override
    public boolean canMove(double x, double y) {
        //run test =>

        //top   ., v, v,left
        Entity e = _board.getEntity((x) / 16, (y - 15) / 16, this);
        if (e != null && !e.collide(this)) {
            return false;
        }
//        check top right
        e = _board.getEntity((x + 15) / 16, (y - 15) / 16, this);
        if (e != null && !e.collide(this)) {
            return false;
        }
//
        //check bot left
        e = _board.getEntity((x) / 16, (y - 1) / 16, this);
        if (e != null && !e.collide(this)) {
            return false;
        }
//
        //check bot right
        e = _board.getEntity((x + 15) / 16, (y - 1) / 16, this);
        if (e != null && !e.collide(this)) {
            return false;
        }


        return true;
    }

    @Override
    public boolean collide(Entity e) {

        // TODO: xử lý va chạm với Flame
        if (e instanceof Flame) {
            kill();
            return false;
        }

        // TODO: xử lý va chạm với Bomber
        if (e instanceof Bomber) {
            ((Bomber) e).kill();
            return false;
        }
        return true;
    }

    @Override
    public void kill() {
        if (!_alive) return;
        _alive = false;

        _board.addPoints(_points);

        Message msg = new Message("+" + _points, getXMessage(), getYMessage(), 2, Color.white, 14);
        _board.addMessage(msg);
    }


    @Override
    protected void afterKill() {
        if (_timeAfter > 0) --_timeAfter;
        else {
            if (_finalAnimation > 0) --_finalAnimation;
            else
                remove();
        }
    }

    protected abstract void chooseSprite();
}
