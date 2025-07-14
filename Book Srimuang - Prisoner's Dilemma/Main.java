import java.util.Scanner;
import java.util.Random;
/**
 * Write a description of class Main here.
 *  This program is a prisonners dilemma game, the aim of this game is too get more points than the AI by the end of the rounds. 
 *  The player can either pick cooperate or defect and based on what the AI has chosen on the same round the player will either gain or lose points.
 * @author Book Srimuang
 * @version Version 1, 11/06/25
 */
public class Main 
{
    // instance variables
    private int playerScore = 0;
    private int aiScore = 0;
    private int roundCount = 0;
    private final int MAX_ROUNDS = 5;

    public Main() {
        menuScreen();
    }

    public void gameInstructions() {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("");
        System.out.println("Welcome to Prisoners Dilemma!");
        System.out.println();
        System.out.println("Press Enter to continue...");
        keyboard.nextLine();
        clearScreen();

        System.out.println(" ");
        System.out.println("Prisoners Dilemma concept in game theory that demonstrates a conflict between");
        System.out.println("your own individual self and the collective well-being of everyone else");
        System.out.println("It highlights how what's best for the individual isn't always best for everyone.");
        System.out.println("Press Enter to continue...");
        keyboard.nextLine();
        clearScreen();

        System.out.println(" ");
        System.out.println("Imagine two prisoners both got arrested for a crime. Each of them has the option to cooperate with each other");
        System.out.println("or betray the other by confessing to the crime.");
        System.out.println();
        System.out.println("Press Enter to continue...");
        keyboard.nextLine();
        clearScreen();

        System.out.println(" ");
        System.out.println("Depending on their choices, they face different consequences:");
        System.out.println("If they cooperate, they get time reduced in their sentence");
        System.out.println("If one betrays while the other cooperates, the betrayer will go free while the one that cooperates goes to prison");
        System.out.println("If both betray, both still get a sentence but a smaller one");
        System.out.println();
        System.out.println("Press Enter to continue...");
        keyboard.nextLine();
        clearScreen();

        System.out.println(" ");
        System.out.println("DISCLAIMER: This game is purely a simulation and not related to any real-world events.");
        System.out.println("The sole purpose of this program is purely to educate and is not meant to offend in anyway.");
        System.out.println();
        System.out.println("Press Enter to continue...");
        keyboard.nextLine();
        clearScreen();

    }

    public void menuScreen() {
        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Prisoner's Dilemma Menu ---");
            System.out.println(" ");
            System.out.println("1. View Instructions/Game Theory");
            System.out.println("2. Start Game");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            if (keyboard.hasNextInt()) {
                choice = keyboard.nextInt();
                keyboard.nextLine(); // Consume newline
            } else {
                System.out.println();
                System.out.println("Invalid input. Please enter a number.");
                keyboard.nextLine(); // Clear invalid input
                continue;
            }

            switch (choice) {
                case 1:
                    gameInstructions(); // Call main method and restarts and displays instructions again
                    System.out.println(" ");
                    break;
                case 2:
                    startGame(); // Starts the actual game
                    break;
                case 3:
                    System.out.println("");
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
            }
        }
    }

    public void startGame() {
        playerScore = 0;
        aiScore = 0;
        roundCount = 0;

        Scanner keyboard = new Scanner(System.in);
        String playerChoice = "";

        //  prints what round it is if the round is below max amount of rounds
        while (roundCount < MAX_ROUNDS) {
            System.out.println();
            System.out.println("----Round" + " " + (roundCount + 1) + "----");
            roundCount++;

            // gets the user choice
            while (true) {
                System.out.println();
                System.out.println("Choose (C)ooperate or (D)efect:");
                String input = keyboard.nextLine().trim().toLowerCase();

                if (input.equals("c") || input.equals("cooperate")) {
                    playerChoice = "cooperate";
                    break;
                } else if (input.equals("d") || input.equals("defect")) {
                    playerChoice = "defect";
                    break;
                } else {
                    System.out.println("Invalid choice. Please enter C or D.");
                }
            }
            // gets the AI's choice
            String aiChoice = aiRandom();

            // display users and AI's choices
            System.out.println("You chose: " + playerChoice);
            System.out.println("AI chose: " + aiChoice);
        }

    }
    
    // picks between cooperate and defect randomly
    public String aiRandom() {
        Random rand = new Random();
        return rand.nextBoolean() ? "cooperate" : "defect";
    }
    
    // cooperating first round and then mirrors the users previous move.
    public void aiTitforTat() {
        
    }
    
    // always defect
    public void aiDefect() {
        
    }
    
    // always cooperate
    public void aiCooperate() {
        
    }

    public void clearScreen() {
        System.out.print('\u000C');
    }
}

