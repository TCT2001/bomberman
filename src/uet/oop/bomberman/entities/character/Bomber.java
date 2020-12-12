package uet.oop.bomberman.entities.character;

import uet.oop.bomberman.entities.tile.item.SpeedItem;
import uet.oop.bomberman.game.Board;
import uet.oop.bomberman.game.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.bomb.Bomb;
import uet.oop.bomberman.entities.bomb.Flame;
import uet.oop.bomberman.entities.character.enemy.Enemy;
import uet.oop.bomberman.entities.tile.Wall;
import uet.oop.bomberman.entities.tile.item.Item;
import uet.oop.bomberman.entities.tile.item.durationItem.DurationItem;
import uet.oop.bomberman.entities.tile.item.durationItem.WallPassItem;
import uet.oop.bomberman.graphics.Screen;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.input.Keyboard;
import uet.oop.bomberman.level.Coordinates;
import uet.oop.bomberman.sound.Sound;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Bomber extends Character {

    private List<Bomb> _bombs;
    private boolean immortal = false;

    public static List<Item> permanentPowerUps = new ArrayList<>();
    public static List<Item> temporaryPowerUps = new ArrayList<>();
    public static List<Integer> duration = new ArrayList<>();

    /**
     * nếu giá trị này < 0 thì cho phép đặt đối tượng Bomb tiếp theo,
     * cứ mỗi lần đặt 1 Bomb mới, giá trị này sẽ được reset về 0 và giảm dần trong mỗi lần update()
     */
    protected int _timeBetweenPutBombs = 0;

    public Bomber(int x, int y, Board board) {
        super(x, y, board);
        _bombs = _board.getBombs();
        _sprite = Sprite.player_right;
        _timeAfter = 80;
    }

    @Override
    public void update() {
        clearBombs();
        if (!_alive) {
            chooseSprite();
            afterKill();
            return;
        }
        //Set lại thời gian giữa 2 lần đặt bom : được cho là 30
        if (_timeBetweenPutBombs < -7500) {
            _timeBetweenPutBombs = 0;
        } else {
            _timeBetweenPutBombs--;
        }
        //Hình ảnh động cho bomber
        animate();

        calculateMove();

        detectPlaceBomb();

        updateDurationOfPower();
    }

    @Override
    public void render(Screen screen) {
        calculateXOffset();

        if (_alive) {
            chooseSprite(); }

        screen.renderEntity((int) _x, (int) _y - _sprite.SIZE, this);
    }

    public void calculateXOffset() {
        int xScroll = Screen.calculateXOffset(_board, this);
        Screen.setOffset(xScroll, 0);
    }

    /**
     * Kiểm tra xem có đặt được bom hay không? nếu có thì đặt bom tại vị trí hiện tại của Bomber
     */
    private void detectPlaceBomb() {
        // TODO: kiểm tra xem phím điều khiển đặt bom có được gõ và giá trị _timeBetweenPutBombs, Game.getBombRate() có thỏa mãn hay không
        if (Keyboard.boom && Game.getBombRate() > 0 && _timeBetweenPutBombs < 0) {
            int xt = Coordinates.pixelToTile(_x + (_sprite.getSize() >> 1));
            int yt = Coordinates.pixelToTile((_y + (_sprite.getSize() >> 1)) - _sprite.getSize()); //subtract half player height and minus 1 y position

            placeBomb(xt, yt);
            //Giảm bombRate đi 1
            Game.addBombRate(-1);
            _timeBetweenPutBombs = 30;
        }
        // TODO:  Game.getBombRate() sẽ trả về số lượng bom có thể đặt liên tiếp tại thời điểm hiện tại
        // TODO: _timeBetweenPutBombs dùng để ngăn chặn Bomber đặt 2 Bomb cùng tại 1 vị trí trong 1 khoảng thời gian quá ngắn
        // TODO: nếu 3 điều kiện trên thỏa mãn thì thực hiện đặt bom bằng placeBomb()
        // TODO: sau khi đặt, nhớ giảm số lượng Bomb Rate và reset _timeBetweenPutBombs về 0
    }

    protected void placeBomb(int x, int y) {
        // TODO: thực hiện tạo đối tượng bom, đặt vào vị trí (x, y) trên board
        this._board.addBomb(new Bomb(x, y, this._board));
        Sound.playPlaceNewBomb();
    }

    private void clearBombs() {
        Iterator<Bomb> bs = _bombs.iterator();

        Bomb b;
        while (bs.hasNext()) {
            b = bs.next();
            if (b.isRemoved()) {
                bs.remove();
                Game.addBombRate(1);
            }
        }

    }

    @Override
    public void kill() {
        if (immortal) return;
        if (_board.get_live() > 1) {
            // Khi vẫn còn mạng, thì giảm mạng
            Board.addLive(-1);
            return;
        } else {
            //Khi mạng = 0 thì endGame
            _alive = false;
        }
        Sound.playBomberDie();
    }

    @Override
    protected void afterKill() {
        System.out.println("?");
        if (_timeAfter > 0) --_timeAfter;
        else {
            System.out.println("??");
            _board.endGame();
        }
    }

    @Override
    protected void calculateMove() {
        // TODO: xử lý nhận tín hiệu điều khiển hướng đi từ _input và gọi move() để thực hiện di chuyển
        // TODO: nhớ cập nhật lại giá trị cờ _moving khi thay đổi trạng thái di chuyển
        int moveX = 0;
        int moveY = 0;
        if (Keyboard.up) moveY--;
        if (Keyboard.down) moveY++;
        if (Keyboard.right) moveX++;
        if (Keyboard.left) moveX--;
        if (moveX != 0 || moveY != 0) {
            _moving = true;
            move(moveX, moveY);
        } else {
            _moving = false;
        }
    }

    @Override
    public boolean canMove(double x, double y) {
        // TODO: kiểm tra có đối tượng tại vị trí chuẩn bị di chuyển đến và có thể di chuyển tới đó hay không

//        check top left
        Entity e = _board.getEntity((x - 0.1) / 16, (y - 8) / 16, this);
        if (!e.collide(this))
            return false;
//        check top right
        e = _board.getEntity((x + 8) / 16, (y - 8) / 16, this);
        if (!e.collide(this))
            return false;

//        check bot left
        e = _board.getEntity((x - 0.1) / 16, (y - 0.1) / 16, this);
        if (!e.collide(this))
            return false;

//        check bot right
        e = _board.getEntity((x + 8) / 16, (y - 0.1) / 16, this);
        return e.collide(this);
    }

    @Override
    public void move(double moveX, double moveY) {
        // TODO: sử dụng canMove() để kiểm tra xem có thể di chuyển tới điểm đã tính toán hay không và thực hiện thay đổi tọa độ _x, _y
        // TODO: nhớ cập nhật giá trị _direction sau khi di chuyển
        if (moveX > 0) _direction = 1;
        if (moveX < 0) _direction = 3;
        if (moveY > 0) _direction = 2;
        if (moveY < 0) _direction = 0;

        double ya = _y + moveY * Game.getBomberSpeed();
        double xa = _x + moveX * Game.getBomberSpeed();
        if (canMove(_x, ya)) {
            _y = ya;
        }

        if (canMove(xa, _y)) {
            _x = xa;
        }
    }

    @Override
    public boolean collide(Entity e) {
        // TODO : xử lý va chạm với Flame khi có power kháng lửa
        if (e instanceof Flame && Game.isBomberPassFlame()) {
            return true;
        }

        // TODO: xử lý va chạm với Flame
        if (e instanceof Flame) {
            kill();
            Sound.playBomberDie();
            return false;
        }

        // TODO: xử lý va chạm với Enemy
        if (e instanceof Enemy) {
            kill();
            Sound.playBomberDie();
            return false;
        }

        return true;
    }

    private void chooseSprite() {
        if (!_alive) {
            _sprite = Sprite.movingSprite(Sprite.player_dead1,Sprite.player_dead2,Sprite.player_dead3,_animate,5);

            return;
        }
        switch (_direction) {
            case 0:
                _sprite = Sprite.player_up;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_up_1, Sprite.player_up_2, _animate, 20);
                }
                break;
            case 1:
                _sprite = Sprite.player_right;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, _animate, 20);
                }
                break;
            case 2:
                _sprite = Sprite.player_down;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_down_1, Sprite.player_down_2, _animate, 20);
                }
                break;
            case 3:
                _sprite = Sprite.player_left;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_left_1, Sprite.player_left_2, _animate, 20);
                }
                break;
            default:
                _sprite = Sprite.player_right;
                if (_moving) {
                    _sprite = Sprite.movingSprite(Sprite.player_right_1, Sprite.player_right_2, _animate, 20);
                }
                break;
        }
    }

    public void addPower(Item item) {
        if (item.isRemoved()) {
            return;
        }
        item.setAttribute();
        if (item instanceof DurationItem) {
            temporaryPowerUps.add(item);
            duration.add(((DurationItem) item).getDuration());
        } else {
            permanentPowerUps.add(item);
        }
    }

    public void updateDurationOfPower() {
        for (int i = 0; i < duration.size(); i++) {
            if (duration.get(i) != -1) {
                int c = duration.get(i);
                duration.set(i, c - 1);
            }
            if (_board.getEntityAt(getXTile(), getYTile()) instanceof Wall) {
                return;
            }
            if (duration.get(i) <= 0 && duration.get(i) != -1) {
                if (temporaryPowerUps.size() >= i - 1) {
                    if (temporaryPowerUps.get(i) instanceof WallPassItem) {
                        Game.setBomberPassWall(false);
                    }
                    temporaryPowerUps.remove(i);
                }
            }
        }
    }

    public void immortal() {
        immortal = !immortal;
    }
}
