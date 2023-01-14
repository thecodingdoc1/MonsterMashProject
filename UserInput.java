import java.util.Scanner;
import java.util.InputMismatchException;

public class UserInput
{

    private static final String WELCOME = "Welcome to Monster Mash!";
    private static final String WELCOME_2 = "Everyone is given a monster.";
    private static final String WELCOME_3 = "Each turn, each player will be able to decide what direction they will move in.";
    private static final String WELCOME_4 = "If you encounter another monster, they will fight each other.";
    private static final String WELCOME_5 = "The last monster standing wins!!";
	private static final String WELCOME_6 = "Right now, we have a limit of 4 monsters per game.";
	private static final String WELCOME_7 = "To begin, how many people will be playing?";
	private static final String PLAYER_INPUT_NUM = "Please input a number 1 - 4.";
	private static final String PLAYER = "Player ";
	private static final String PLAYER_NAME_INPUT = " what is the name of your monster?";


	static Scanner s = new Scanner(System.in);

public void userInput() {

        Scanner s = new Scanner();

		Integer numPlayers = 0;
		UserVO player1 = new UserVO ("Frank", false);
        UserVO player2 = new UserVO ("Nessie", false);
		UserVO player3 = new UserVO ("BigFoot", false);
		UserVO player4 = new UserVO ("Drax", false);
		
		System.out.println(WELCOME);
		System.out.println(WELCOME_2);
		System.out.println(WELCOME_3);
		System.out.println(WELCOME_4);
		System.out.println(WELCOME_5);
		System.out.println(WELCOME_6);
		System.out.println(WELCOME_7);

		/*runs endless loop when input char
		* Add ability to provide own name
		*/

		do {
			System.out.println(PLAYER_INPUT_NUM);
			try {
				numPlayers = s.nextInt();
		} catch (InputMismatchException e) {
			s.nextLine();
		} catch(StringIndexOutOfBoundsException e ) {
			s.hasNextLine();
		}
		} while(numPlayers >= 4  || numPlayers  <= 0);
		

		for (int i = 1; i <= numPlayers; i++) {
			switch(i)
				case 1:
					System.out.println(PLAYER + i + PLAYER_NAME_INPUT);
					player1.setUserName(s.nextLine());
					player1.setIsHuman(true);
					break;
				case 2:
					System.out.println(PLAYER + i + PLAYER_NAME_INPUT);
					player2.setUserName(s.nextLine());
					player2.setIsHuman(true);
					break;
				case 3:
					System.out.println(PLAYER + i + PLAYER_NAME_INPUT);
					player3.setUserName(s.nextLine());
					player3.setIsHuman(true);
					break;
				case 4:
					System.out.println(PLAYER + i + PLAYER_NAME_INPUT);
					player4.setUserName(s.nextLine());
					player4.setIsHuman(true);
					break;
				default:
					System.out.println("Error, please restart game. :(");
		}
		
		//Move to set up 
		Monster player1 = new Monster(player1);
		Monster player2 = new Monster(player2);
		Monster player3 = new Monster(player3);
		Monster player4 = new Monster(player4);
		
		/* Incoporate messages
		 *  Make sure that random attacks and health are competative
		 */
		System.out.println("Each monster has been given a random attack and health");
		System.out.println("Players are:"); 
		System.out.println("Player 1: Name: " + player1.getName() + " Attack: " + player1.getAttack() + " Health: " + player1.getHealth());
		System.out.println("Player 2: Name: " + player2.getName() + " Attack: " + player2.getAttack() + " Health: " + player2.getHealth());
		System.out.println("Player 3: Name: " + player3.getName() + " Attack: " + player3.getAttack() + " Health: " + player3.getHealth());
		System.out.println("Player 4: Name: " + player4.getName() + " Attack: " + player4.getAttack() + " Health: " + player4.getHealth());
		System.out.println("Each player, human and NPC, have been randomly generated a spot on the battleboard.");
		System.out.println("Here are your spots.");
		reDrawBoard();
		
		//start(player1, player2, player3, player4);
		s.close();
	}
}