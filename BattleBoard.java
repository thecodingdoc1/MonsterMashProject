import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.commons.lang3.ArrayUtils;

public class BattleBoard {
	static Scanner s = new Scanner(System.in);
	static int numPlayers = 0;
	char[][] battleBoard = new char[10][10];
	
	public void buildBattleBoard() {
		for (char[] row : battleBoard) {
			Arrays.fill(row, '*');
		}
	}
	
	public void reDrawBoard() {
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
	
	public void userInput() {
		//Revonfigure userInput to use userVO with name, stats, and things to save information
		numPlayers = 0;
		String player1Name = "Frank";
		String player2Name = "Nessie";
		String player3Name = "BigFoot";
		String player4Name = "Drax";
		
		//Make this a property in userVO
		boolean p1IsHuman = false;
		boolean p2IsHuman = false;
		boolean p3IsHuman = false;
		boolean p4IsHuman = false;
		
		//Incorporate message util to display message instead of using SOUTs, make constants(property files)
		System.out.println("Welcome to Monster Mash!");
		System.out.println("Everyone is given a monster.");
		System.out.println("Each turn, each player will be able to decide what direction they will move in.");
		System.out.println("If you encounter another monster, they will fight each other.");
		System.out.println("The last monster standing wins!!");
		System.out.println("Right now, we have a limit of 4 monsters per game.");
		System.out.println("To begin, how many people will be playing?");

		/*runs endless loop when input char
		* Add ability to provide own name
		*/

		do {
			System.out.println("Please input a number 1 - 4.");
			try {
				numPlayers = s.nextInt();
		} catch (InputMismatchException e) {
			s.nextLine();
		} catch(StringIndexOutOfBoundsException e ) {
			s.hasNextLine();
		}
		} while(numPlayers >= 4  || numPlayers  <= 0);
		
	
		/*Set up in other class to configure players
		*	incorporate to display messages
		*/
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
		
		//Move to set up 
		Monster player1 = new Monster(player1Name, p1IsHuman);
		Monster player2 = new Monster(player2Name, p2IsHuman);
		Monster player3 = new Monster(player3Name, p3IsHuman);
		Monster player4 = new Monster(player4Name, p4IsHuman);
		
		/* Incoporate messages
		 *  Make sure that random attacks and health are competative
		 */
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
		while(Monster.numMonstersDead < 3) {
		//reDrawBoard();
		 for (Monster m : listOfMonsters) {
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
			if(m.isAlive()) {
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

}