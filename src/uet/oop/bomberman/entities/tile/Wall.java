package uet.oop.bomberman.entities.tile;


import uet.oop.bomberman.game.Game;
import uet.oop.bomberman.entities.Entity;
import uet.oop.bomberman.entities.character.Bomber;
import uet.oop.bomberman.graphics.Sprite;

public class Wall extends Tile {

    public Wall(int x, int y, Sprite sprite) {
        super(x, y, sprite);
    }

    @Override
    public boolean collide(Entity e) {
        if (e instanceof Bomber && Game.isBomberPassWall()) {
            return true;
        }
        return false;
    }
}
