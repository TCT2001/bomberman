package uet.oop.bomberman.entities.character.enemy.ai;

public class AILow extends AI {

	//0 1 2 3 up right down left
	@Override
	public int calculateDirection() {
		// TODO: cài đặt thuật toán tìm đường đi
		return random.nextInt(4);
	}

}
