import java.util.InputMismatchException;
import java.util.Scanner;

public class Monster {
	public Monster() {}

	public Monster(String name, boolean isHuman) {
		this.name = name;
		this.isHuman = isHuman;
		int health;
		int attack;
		int checkingOut = 534;
		//change
		int maxXBoardPosition = BattleBoard.battleBoard.length - 1;
		int maxYBoardPosition = BattleBoard.battleBoard[0].length - 1;
		int randomNumX, randomNumY;
		do {
			health = (int) (Math.random() * 200);
			attack = (int) (Math.random() * 20);
		} while(health <= 100 || attack <= 10);
		this.health = health;
		this.attack = attack;
		do {
			randomNumX = (int) (Math.random() * maxXBoardPosition);
			randomNumY = (int) (Math.random() * maxYBoardPosition);
		} while (BattleBoard.battleBoard[randomNumX][randomNumY] != '*' );
		
		
		this.xPosition = randomNumX;
		this.yPosition = randomNumY;
		
		this.nameChar1 = this.name.charAt(0);
		
		BattleBoard.battleBoard[this.yPosition][this.xPosition] = this.nameChar1;
		numOfMonsters++;
	}
	
	private int health ;
	private int attack;
	private int movement = 1;
	private boolean isHuman = false;
	
	public boolean isHuman() {
		return isHuman;
	}

	public void setIsHuman(boolean isHuman) {
		this.isHuman = isHuman;
	}

	private boolean alive = true;
	
	public int xPosition = 0;
	public int yPosition = 0;
	public String name;
	public char nameChar1 = 'B';
	public static int numOfMonsters = 0;
	public static int numMonstersDead = 0;
	private int damage = 0;
	
	
	public int getHealth() {
		return health;
	}
	
	public int getAttack() {
		return attack;
	}

	public int getMovement() {
		return movement;
	}

	public boolean isAlive() {
		return alive;
	}

	public void setHealthAfterAttack (int decreaseHealth) {
		health -= decreaseHealth;
		if(health < 0) {
			alive = false;
			numMonstersDead += 1;
		}
	}
	public void setHealthAfterAttack (double decreaseHealth) {
			int intDecreaseHealth = (int) decreaseHealth;
			setHealthAfterAttack(intDecreaseHealth);
		}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		this.damage += damage;
	}

	public void moveMonsterAuto(Monster[] listOfMonsters, int arrayItemIndex) {
		int oldY = yPosition;
		int oldX = xPosition;
		boolean isSpaceOccupied = true;
	
		int maxXBoardSpace = BattleBoard.battleBoard.length - 1;
		int maxYBoardSpace = BattleBoard.battleBoard[0].length - 1;
		
		while(isSpaceOccupied) {
			int randMoveDirection = (int) (Math.random() * 4);
			String direction;
			
			if(randMoveDirection == 0) {
				direction = "North";
			} else if(randMoveDirection == 1) {
				direction = "East";
			} else if(randMoveDirection == 2) {
				direction = "South";
			} else {
				direction = "West";
			}
			int randMoveDistance = movement;
			System.out.println(listOfMonsters[arrayItemIndex].name + " has moved to the " + direction);
			
			BattleBoard.battleBoard[this.yPosition][this.xPosition]= '*';
			if(randMoveDirection == 0) {//North
				if((this.yPosition - randMoveDistance) < 0) {
					this.yPosition = 0;
				} else {
					this.yPosition = this.yPosition - randMoveDistance;
				}
			} else if (randMoveDirection == 1) {//East
				if((this.xPosition + randMoveDistance) > maxXBoardSpace) {
					this.xPosition = maxXBoardSpace;
				} else {
					this.xPosition = this.xPosition + randMoveDistance;
				}
			}else if (randMoveDirection == 2) {//South
				if((this.yPosition + randMoveDistance) > maxYBoardSpace) {
					this.yPosition = maxYBoardSpace;
				} else {
					this.yPosition = this.yPosition + randMoveDistance;
				}
			}else { // West
				if((this.xPosition - randMoveDistance) < 0) {
					this.xPosition = 0;
				} else {
					this.xPosition = this.xPosition - randMoveDistance;
				}
			}
			for (int i = 0; i < listOfMonsters.length; i++) {
				if(i == arrayItemIndex) {
					continue;
				} 
				//change while loop condition name in accordance with these names
				if(isSpaceFree(listOfMonsters, i, arrayItemIndex)) {
					isSpaceOccupied= false;
					attack(listOfMonsters, i, arrayItemIndex, oldX, oldY);
					break;
				} else {
					isSpaceOccupied = false;
				}
			}
		}// end of while loop
		BattleBoard.battleBoard[this.yPosition][this.xPosition] = this.nameChar1;
		BattleBoard.reDrawBoard();
	}//end of moveMonster
	
	public void move(Monster[] listOfMonsters, int arrayItemIndex, Scanner s) {
		int oldY = yPosition;
		int oldX = xPosition;
		boolean isSpaceOccupied = true;
		int maxXBoardSpace = BattleBoard.battleBoard.length - 1;
		int maxYBoardSpace = BattleBoard.battleBoard[0].length - 1;
		int direction = 0;
		
		System.out.println( "What direction do you want to move your monster?");
		do {
			System.out.println("Please indicate 1 for north, 2 for east, 3 for south, and 4 for west: ");
			try {
				direction = s.nextInt();
				
			} catch(InputMismatchException e) {
				//System.out.println("Please indicate 1 for north, 2 for east, 3 for south, and 4 for west: ");
				s.nextLine();
			}
		} while (direction <= 0 || direction >= 5);
		
		while(isSpaceOccupied) {
			int moveDistance = 1;
			
			BattleBoard.battleBoard[this.yPosition][this.xPosition]= '*';
			if(direction == 1) {//North
				if((this.yPosition - moveDistance) < 0) {
					this.yPosition = 0;
				} else {
					this.yPosition = this.yPosition - moveDistance;
				}
			} else if (direction == 2) {//East
				if((this.xPosition + moveDistance) > maxXBoardSpace) {
					this.xPosition = maxXBoardSpace;
				} else {
					this.xPosition = this.xPosition + moveDistance;
				}
			}else if (direction == 3) {//South
				if((this.yPosition + moveDistance) > maxYBoardSpace) {
					this.yPosition = maxYBoardSpace;
				} else {
					this.yPosition = this.yPosition + moveDistance;
				}
			}else { // West
				if((this.xPosition - moveDistance) < 0) {
					this.xPosition = 0;
				} else {
					this.xPosition = this.xPosition - moveDistance;
				}
			} 
			for (int i = 0; i < listOfMonsters.length; i++) {
				if(i == arrayItemIndex) {
					continue;
				} 
				if(isSpaceFree(listOfMonsters, i, arrayItemIndex)) {
					isSpaceOccupied= false;
					attack(listOfMonsters, i, arrayItemIndex, oldX, oldY);
					break;
				} else {
					isSpaceOccupied = false;
				}
			}
		}// end of while loop
		BattleBoard.battleBoard[this.yPosition][this.xPosition] = this.nameChar1;
		BattleBoard.reDrawBoard();
	}//end of move

	private boolean isSpaceFree(Monster[] listOfMonsters, int indexToCheck, int arrayItemIndex) {
		if((listOfMonsters[indexToCheck].xPosition) == (listOfMonsters[arrayItemIndex].xPosition) && (listOfMonsters[indexToCheck].yPosition) == (listOfMonsters[arrayItemIndex].yPosition)) {
			return true;
		} else {
			return false;
			//
		}
	}//End of isSpaceFree
	
	public void attack(Monster[] listOfMonsters, int indexToCheck, int arrayItemIndex, int oldX, int oldY) {
		//1System.out.println("stats" + indexToCheck + " " + arrayItemIndex + " " + isSpaceFree(listOfMonsters, indexToCheck, arrayItemIndex));
		int deadMonsterX = 0;
		int deadMonsterY = 0;
		this.yPosition = oldY;
		this.xPosition = oldX;
		System.out.println("Health of monsters before attack: " + listOfMonsters[arrayItemIndex].name + ": " + listOfMonsters[arrayItemIndex].getHealth() + " and " + listOfMonsters[indexToCheck].name + ": " + listOfMonsters[indexToCheck].getHealth() );
		listOfMonsters[indexToCheck].setHealthAfterAttack(listOfMonsters[arrayItemIndex].getAttack());
		listOfMonsters[arrayItemIndex].setHealthAfterAttack(listOfMonsters[indexToCheck].getAttack());
		listOfMonsters[arrayItemIndex].setDamage(listOfMonsters[arrayItemIndex].getAttack());
		listOfMonsters[indexToCheck].setDamage(listOfMonsters[indexToCheck].getAttack());
		System.out.println("Health after: "+ listOfMonsters[arrayItemIndex].name + ": " + listOfMonsters[arrayItemIndex].getHealth() + " and  " + listOfMonsters[indexToCheck].name + ": " + listOfMonsters[indexToCheck].getHealth() );
		if(listOfMonsters[indexToCheck].alive == false) {
			System.out.println(listOfMonsters[indexToCheck].name + " has been killed by " + listOfMonsters[arrayItemIndex].name + "!");
			deadMonsterX = listOfMonsters[indexToCheck].xPosition;
			deadMonsterY = listOfMonsters[indexToCheck].yPosition;
			BattleBoard.battleBoard[deadMonsterY][deadMonsterX]= '*';
			BattleBoard.reDrawBoard();
			//infinate loop redraws board
			//deletes both names 
			//instant dies
		} else if (listOfMonsters[indexToCheck].alive == false) {
			System.out.println(listOfMonsters[indexToCheck].name + " has been killed by " + listOfMonsters[arrayItemIndex].name + "!");
			deadMonsterX = listOfMonsters[indexToCheck].xPosition;
			deadMonsterY = listOfMonsters[indexToCheck].yPosition;
			BattleBoard.battleBoard[deadMonsterY][deadMonsterX]= '*';
			BattleBoard.reDrawBoard();
		}
	}
}