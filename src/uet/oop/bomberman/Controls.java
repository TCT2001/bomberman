package uet.oop.bomberman;

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

    //new game
    public void newGame() {
       board.newGame();

    }

    //can't die
    public void immortalGame() {
        board.immortalGame();
    }

    public void addBoomSize(){
        board.addBombSize();
    }


}
