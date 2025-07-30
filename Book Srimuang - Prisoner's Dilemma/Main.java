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
    private String lastPlayerChoice = "";
    private boolean gameInProgress = false;
    private String aiStrategy = ""; // "random", "titfortat", "alwaysdefect", "alwayscooperate"

    public static void main(String[] args) {
        new Main();
    }

    public Main() {
        menuScreen();
    }

    public void gameInstructions() {
        Scanner keyboard = new Scanner(System.in);

        System.out.println("\nWelcome to Prisoners Dilemma!");
        System.out.println("Press Enter to continue...");
        keyboard.nextLine();
        clearScreen();

        System.out.println("\nPrisoners Dilemma concept in game theory that demonstrates a conflict between");
        System.out.println("your own individual self and the collective well-being of everyone else");
        System.out.println("It highlights how what's best for the individual isn't always best for everyone.");
        System.out.println("Press Enter to continue...");
        keyboard.nextLine();
        clearScreen();

        System.out.println("\nImagine two prisoners both got arrested for a crime. Each of them has the option to cooperate with each other");
        System.out.println("or betray the other by confessing to the crime.");
        System.out.println("\nPress Enter to continue...");
        keyboard.nextLine();
        clearScreen();

        System.out.println("\nDepending on their choices, they face different consequences:");
        System.out.println("If they cooperate, they get time reduced in their sentence");
        System.out.println("If one betrays while the other cooperates, the betrayer will go free while the one that cooperates goes to prison");
        System.out.println("If both betray, both still get a sentence but a smaller one");
        System.out.println("\nPress Enter to continue...");
        keyboard.nextLine();
        clearScreen();

        System.out.println("\nDISCLAIMER: This game is purely a simulation and not related to any real-world events.");
        System.out.println("This program will touch on theme realating to betrayal and cooperation within a fictional prison scenario.");
        System.out.println("The choices that the AI makes within this program is not a direct reflection of real world ethical behaviour.");
        System.out.println("The sole purpose of this program is purely to educate and is not meant to offend in anyway.");
        System.out.println("\nPress Enter to continue...");
        keyboard.nextLine();
        clearScreen();

    }

    public void menuScreen() {
        clearScreen();
        Scanner keyboard = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Prisoner's Dilemma Menu ---");
            System.out.println(" ");
            System.out.println("1. View Instructions/Game Theory");
            System.out.println("2. " + (gameInProgress ? "Resume Game" : "Start Game"));
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            if (keyboard.hasNextInt()) {
                choice = keyboard.nextInt();
                keyboard.nextLine(); 
            } else {
                System.out.println();
                System.out.println("Invalid input. Please enter a number.");
                keyboard.nextLine(); 
                continue;
            }

            switch (choice) {
                case 1:
                    gameInstructions(); // Call main method and restarts and displays instructions again
                    System.out.println(" ");
                    break;
                case 2:
                    if (gameInProgress) {
                        startGame(false); // resume
                    } else {
                        startGame(true); // new game
                    }
                    break;
                case 3:
                    System.out.println("");
                    System.out.println("Goodbye!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
                    break;
            }
        }
    }

    public void startGame(boolean NewGame) {
        gameInProgress = true;
        clearScreen();

        // checks if the game is new or resuming. 
        if (NewGame) {
            playerScore = 0;
            aiScore = 0;
            roundCount = 0;
            lastPlayerChoice = "";

            String[] strategies = {"random", "titfortat", "alwaysdefect", "alwayscooperate"};
            Random rand = new Random();
            aiStrategy = strategies[rand.nextInt(strategies.length)];
            //System.out.println(aiStrategy); // displays chosen AI stratgy, here for testing.
        } else {
            System.out.println("Current scores:");
            System.out.println("Your score: " + playerScore);
            System.out.println("AI score: " + aiScore);
        }

        Scanner keyboard = new Scanner(System.in);
        String playerChoice = "";                                                                                                                       

        //  prints what round it is if the round is below max amount of rounds
        while (roundCount < MAX_ROUNDS) {
            System.out.println();
            System.out.println("----Round" + " " + (roundCount + 1) + "----");

            // gets the user choice
            while (true) {
                System.out.println();
                System.out.println("Choose (C)ooperate, (D)efect, or (Q)uit");
                String input = keyboard.nextLine().trim().toLowerCase();

                if (input.equals("c") || input.equals("cooperate")) {
                    playerChoice = "cooperate";
                    clearScreen();
                    break;
                } else if (input.equals("d") || input.equals("defect")) {
                    playerChoice = "defect";
                    clearScreen();
                    break;
                } else if (input.equals("q") || input.equals("quit")) {
                    clearScreen();
                    return; 
                } else {
                    System.out.println("Invalid choice. Please enter C, D, or Q to quit.");
                }

            }
            
            // gets the AI's choice
            String aiChoice = getAiMove();
            lastPlayerChoice = playerChoice;

            // display users and AI's choices
            System.out.println("You chose: " + playerChoice);
            System.out.println("AI chose: " + aiChoice);

            // store scores before updating
            int prevPlayerScore = playerScore;
            int prevAiScore = aiScore;

            // update scores
            scoreSystem(playerChoice, aiChoice);

            // calculate score differences
            int playerChange = playerScore - prevPlayerScore;
            int aiChange = aiScore - prevAiScore;

            // print score changes and totals
            System.out.println("\nScore change - You: " + playerChange + ", AI: " + aiChange);
            System.out.println("Total - Your score: " + playerScore);
            System.out.println("Total - AI score: " + aiScore);

            roundCount++;
        }
        gameSummary();
    }

    // updates player and ai scores based on their choices.
    public void scoreSystem(String playerChoice, String aiChoice) {
        if (playerChoice.equals("cooperate") && aiChoice.equals("cooperate")) {
            playerScore +=1;
            aiScore +=1;
        } else if (playerChoice.equals("defect") && aiChoice.equals("defect")) {
            playerScore -= 3;
            aiScore -= 3;
        } else if (playerChoice.equals("cooperate") && aiChoice.equals("defect")) {
            playerScore -= 5;
            aiScore += 2;
        } else if (playerChoice.equals("defect") && aiChoice.equals("cooperate")) {
            playerScore += 2;
            aiScore -= 5;
        }
    }

    // picks between cooperate and defect randomly
    public String aiRandom() {
        Random rand = new Random();
        return rand.nextBoolean() ? "cooperate" : "defect";
    }

    // cooperating first round and then mirrors the users previous move.
    public String aiTitforTat() {
        if (lastPlayerChoice.equals("")) {
            return "cooperate";
        } else {
            return lastPlayerChoice;
        }
    }

    // always defect
    public String aiDefect() {
        return "defect";
    }

    // always cooperate
    public String aiCooperate() {
        return "cooperate";
    }

    public String getAiMove() {
        switch (aiStrategy) {
            case "random":
                return aiRandom();
            case "titfortat":
                return aiTitforTat();
            case "alwaysdefect":
                return aiDefect();
            case "alwayscooperate":
                return aiCooperate();
            default:
                return aiRandom(); // fallback
        }
    }

    public void gameSummary() {
        clearScreen();
        Scanner keyboard = new Scanner(System.in);

        // displays sum of users points and ai points
        System.out.println("\n--- Game Summary ---");
        System.out.println("Your score: " + playerScore);
        System.out.println("AI score: " + aiScore);

        // checks to see who won and who lost and then displays a message based on that
        if (playerScore > aiScore) {
            System.out.println("You win!");
        } else if (playerScore < aiScore) {
            System.out.println("AI wins!");
        } else {
            System.out.println("It's a tie!");
        }

        System.out.println("\nWhat would you like to do next?");
        System.out.println("1. Play again");
        System.out.println("2. Return to main menu");
        System.out.println("3. Exit");

        while (true) {
            System.out.print("Enter your choice: ");
            String choice = keyboard.nextLine().trim();

            switch (choice) {
                case "1":
                    startGame(true); // Play again
                    return;
                case "2":
                    menuScreen(); // Return to main menu
                    return;
                case "3":
                    System.out.println("Goodbye!");
                    System.exit(0); // Exit program
                    break;
                default:
                    System.out.println("Invalid option. Please enter 1, 2, or 3.");
            }
        }
    }

    // clears screen
    public void clearScreen() {
        System.out.print('\u000C');
    }
}