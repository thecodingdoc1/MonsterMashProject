import java.util.InputMismatchException;
import java.util.Scanner;

public class Monster {
	BattleBoard board = new BattleBoard();
	
	int maxXBoardPosition = board.battleBoard.length - 1;
	int maxYBoardPosition = board.battleBoard[0].length - 1;	

	public Monster() {}

	public Monster(UserVO player) {
		MonsterVO monster = new MonsterVO();
		monster.setName(player.getUserName());
		monster.setIsHuman(player.isHuman());
		generateMonster(monster);
	}
	
	private void generateMonster(MonsterVO monster)
	{	
		do {
			monster.setHealth((int) (Math.random() * 200));
			monster.setAttack((int) (Math.random() * 20));
		} while(health <= 100 || attack <= 10);
	
		do {
			monster.setPositionX((int) (Math.random() * maxXBoardPosition));
			monster.setPositionY((int) (Math.random() * maxYBoardPosition));
		} while (board.battleBoard[randomNumX][randomNumY] != '*' );
		
		
		board.battleBoard[monster.getPositionY][monster.getPositionX] = monster.getName().charAt(0);
	}
}