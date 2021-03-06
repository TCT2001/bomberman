package uet.oop.bomberman.controller;

import uet.oop.bomberman.game.Board;
import uet.oop.bomberman.game.Game;

public class Controls {
    private Board board;

    public Controls(Board board) {
        this.board = board;
    }

    //pause
    public void pause() {
        board.gamePause();

    }

    //resume
    public void resume() {
        board.gameResume();
    }

    //nextlv
    public void levelUpGame() {
        board.nextLevel();
    }

    //upspeed
    public void upSpeedGame() {
        board.speedUpGame();
    }

    //quit
    public void quitGame() {
        board.quitGame();
    }

    //can't die
    public void immortalGame() {
        board.immortalGame();
    }

    public void addBoomSize() {
        board.addBombSize();
    }
    public void addFlameSize() {
        Game.addBombRadius(1);
    }

}
