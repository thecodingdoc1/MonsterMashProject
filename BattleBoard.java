import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;
public class BattleBoard extends Monster {
	static Scanner s = new Scanner(System.in);
	static int numPlayers = 0;
	static char[][] battleBoard = new char[10][10];
	
	public static void buildBattleBoard() {
		for (char[] row : battleBoard) {
			Arrays.fill(row, '*');
		}
	}
	
	public static void reDrawBoard() {
		int tile = 1;
		while(tile <= 30) {System.out.print("-"); tile++; };
		System.out.println();
		for(int i = 0; i < battleBoard.length; i++)  {
			for (int j = 0; j < battleBoard[i].length; j++) {
				System.out.print("| "+ battleBoard[i][j] + " |");
			}
			System.out.println();
		}
		tile = 1;
		while(tile <= 30) {System.out.print("-"); tile++; };
		System.out.println();
}
	
	public static void userInput() {
		
		numPlayers = 0;
		String player1Name = "Frank";
		String player2Name = "Nessie";
		String player3Name = "BigFoot";
		String player4Name = "Drax";
		
		boolean p1IsHuman = false;
		boolean p2IsHuman = false;
		boolean p3IsHuman = false;
		boolean p4IsHuman = false;
		
		System.out.println("Welcome to Monster Mash!");
		System.out.println("Everyone is given a monster.");
		System.out.println("Each turn, each player will be able to decide what direction they will move in.");
		System.out.println("If you encounter another monster, they will fight each other.");
		System.out.println("The last monster standing wins!!");
		System.out.println("Right now, we have a limit of 4 monsters per game.");
		
		//runs endless loop when input char
		do {
			try {
				System.out.println("To begin, how many people will be playing?");
				s.nextLine();
				numPlayers = s.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Please input a number 1 - 4.");
		}
		} while(numPlayers > 4  || numPlayers  <= 0);
		
	
		
		for (int i = 1; i <= numPlayers; i++) {
			System.out.println("Player " + i + " what is the name of your monster?");
			if (i == 1) {
				s.nextLine();
				player1Name= s.nextLine();
				if(player1Name.charAt(0) == player2Name.charAt(0) || player1Name.charAt(0) == player3Name.charAt(0) || player1Name.charAt(0) == player4Name.charAt(0)) {
					System.out.println("Please type in a name that does not begin with the letters: " + player2Name.charAt(0) + " " + player3Name.charAt(0) + " " + player4Name.charAt(0));
					player1Name = s.nextLine();
				}
				p1IsHuman = true;
			} else if ( i == 2) {
				player2Name= s.nextLine();
				if(player2Name.charAt(0) == player1Name.charAt(0) || player2Name.charAt(0) == player3Name.charAt(0) || player2Name.charAt(0) == player4Name.charAt(0)) {
					System.out.println("Please type in a name that does not begin with the letters: " + player1Name.charAt(0) + " " + player3Name.charAt(0) + " " + player4Name.charAt(0));
					player2Name = s.nextLine();
				}
				p2IsHuman = true;
			} else if(i == 3){
				player3Name= s.nextLine();
				if(player3Name.charAt(0) == player1Name.charAt(0) || player3Name.charAt(0) == player2Name.charAt(0) || player3Name.charAt(0) == player4Name.charAt(0)) {
					System.out.println("Please type in a name that does not begin with the letters: " + player1Name.charAt(0) + " " + player2Name.charAt(0) + " " + player4Name.charAt(0));
					player3Name = s.nextLine();
				}
				p3IsHuman = true;
			} else {
				player4Name= s.nextLine();
				if(player4Name.charAt(0) == player1Name.charAt(0) || player4Name.charAt(0) == player2Name.charAt(0) || player4Name.charAt(0) == player3Name.charAt(0)) {
					System.out.println("Please type in a name that does not begin with the letters: " + player1Name.charAt(0) + " " + player2Name.charAt(0) + " " + player3Name.charAt(0));
					player4Name = s.nextLine();
				}
				p4IsHuman = true;
			}
				
		}
		
		Monster player1 = new Monster(player1Name, p1IsHuman);
		Monster player2 = new Monster(player2Name, p2IsHuman);
		Monster player3 = new Monster(player3Name, p3IsHuman);
		Monster player4 = new Monster(player4Name, p4IsHuman);
		
		System.out.println("Each monster has been given a random attack and health");
		System.out.println("Players are:"); 
		System.out.println("Player 1: Name: " + player1.name + " Attack: " + player1.getAttack() + " Health: " + player1.getHealth());
		System.out.println("Player 2: Name: " + player2.name + " Attack: " + player2.getAttack() + " Health: " + player2.getHealth());
		System.out.println("Player 3: Name: " + player3.name + " Attack: " + player3.getAttack() + " Health: " + player3.getHealth());
		System.out.println("Player 4: Name: " + player4.name + " Attack: " + player4.getAttack() + " Health: " + player4.getHealth());
		System.out.println("Each player, human and NPC, have been randomly generated a spot on the battleboard.");
		System.out.println("Here are your spots.");
		reDrawBoard();
		
		start(player1, player2, player3, player4);
		s.close();
	}
	public static void start(Monster player1, Monster player2, Monster player3, Monster player4) {
		Monster[] listOfMonsters = new Monster[4]; 
		
		listOfMonsters[0] = player1;
		listOfMonsters[1] = player2;
		listOfMonsters[2] = player3;
		listOfMonsters[3] = player4;
		while(Monster.numMonstersDead < 4) {
		//reDrawBoard()
		 for (Monster m : listOfMonsters) {
			 System.out.println(m.isHuman());
			 int arrayItemIndex = ArrayUtils.indexOf(listOfMonsters, m);
			 if (m.isAlive() && !m.isHuman()) {
				 m.moveMonsterAuto(listOfMonsters, arrayItemIndex);
			 } else if(m.isAlive() && m.isHuman()) {
				 m.move(listOfMonsters, arrayItemIndex, s);
			 } else if(!m.isAlive()) {
				 continue;
			 }
		 }
		}
		for (Monster m : listOfMonsters) {
			if(!m.isAlive()) {
			endgame(m, player1, player2, player3, player4);
			}
		}
		s.close();
	}
	
	
	
	private static void endgame(Monster m, Monster player1, Monster player2, Monster player3, Monster player4) {
		System.out.println("Congratulation " + m.name + ", you have won Monster Mash!");
		System.out.println("below are the game stats: ");
		System.out.println("Player " + player1.name + " Health: " + player1.getHealth() + " Damage: " + player1.getDamage());
		System.out.println("Player " + player2.name + " Health: " + player2.getHealth() + " Damage: " + player2.getDamage());
		System.out.println("Player " + player3.name + " Health: " + player3.getHealth() + " Damage: " + player3.getDamage());
		System.out.println("Player " + player4.name + " Health: " + player4.getHealth() + " Damage: " + player4.getDamage());
	}

	public static void main(String[] args) {
		buildBattleBoard();
		//start();
		userInput();
		
		
		// create attack and death method
		// create win method
		/* after the last turn and ther is only one winner, then print out the leaderboard wiht the stats of each monster, including the remaining health of the last monster
		 * ask if they would like to play again, if so, rerun program.
		 */
	}
	
	
	/* user input
	 * ask the user for how many players, assign it to a variable of int
	 * 	ensure not over a general number (say 4)
	 * then have for loop that i <= player total
	 * in each iteration, create a new monster, random attack, health,
	 * give each monster the same movement
	 * then ask for what the name of the player is (or monster)
	 * ensure that the random number only assigns if given a certain value (no 100 hp, 90 attack monsters)
	 * give the stats for each monster to the screen
	 * ensure that if random generates and assigns a high health (700+) then only assign attack below 40 using while loop
	 * each monster would then have a specific position
	 * this starts a turn
	 * then I could ask for user inpu in regards to movement 
	 * do they want to move up, down, left, or right
	 * error checking- ensure just those answers, ensure no monster already in the space
	 * then move the monsters
	 * check if a monster is next to them, if not, next players turn
	 * if so, then attack the monster, reduce the other monsters health by the amount of attack
	 * check that alive = true; then next players turn
	 * at end of each players turn, report any attack, 
	 * update each player with the current health they have at the start of their turn
	 * then next round
	 * same thing until one monster left
	 * 
	 * can later add random power ups that spawn on the map at specific rounds (replenish health, armor, bonus next attack)
	 * 
	 */
}