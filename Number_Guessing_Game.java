/*
     *  Author: Su
     *  Date: 10/08/2020
    */
// Imports
import java.util.Scanner;
import java.util.Random;

public class Number_Guessing_Game {

    public static void main(String[] args) {

        // Variables
        int game_stage = 0;
        int num_of_guesses = 1;
        int num_to_guess = 0;
        int num_domain = 0;

        // Greetings
        System.out.println("Welcome to Su's Number Guessing Game!");
        System.out.println("=====================================");

        // Class that checks imputs
        Scanner scanner = new Scanner(System.in);

        // Ask player to enter name and greet them
        System.out.print("\nPlease enter your name: ");
        String name = scanner.next();
        System.out.println("\nHello " + name + "!");

        // Rules
        while (game_stage == 0) {

            System.out.println("\nWould you like to know the rules? (Y/N)");
            String permission = scanner.next();

            if (user_verification(permission).equals("true")) {
                System.out.println("\nRules: ");
                System.out.println("=========================");
                System.out.println("There is a randomly generated number that you will have to guess.");
                System.out.println("You will have a limited amount of guesses.");
                game_stage++;

            } else if (user_verification(permission).equals("false")) {
                System.out.println("Ok... I guess.");
                game_stage++;
            } else {
                System.out.println(user_verification(permission));
            }
        }

        // Set number of lives
        while (game_stage == 1) {

            // Set Lives
            System.out.println("\nHow many guesses would you like?");
            String response = scanner.next();
            try {
                if (valid_number(response).equals("true")) {
                    num_of_guesses = Integer.parseInt(response);
                    game_stage++;
                } else {
                    System.out.println(valid_number(response));
                }
            } catch(Exception exception) {
                System.out.println("Please enter a positive number.");   
            }

        }

        // Set domain for random number
        while (game_stage == 2) {
            System.out.println("\nStarting from 0, what would you like the maximum number be?");
            String response = scanner.next();
            try {
                if (valid_number(response).equals("true")) {

                    // Adds one because the number in the parenthesis is the limit. It can't reach there.
                    int domain = Integer.parseInt(response) + 1;

                    // Randomly generated number 
                    Random random_number = new Random();
                    num_to_guess = random_number.nextInt(domain);
                    num_domain = Integer.parseInt(response);
                    game_stage++;
                } else {
                    System.out.println(valid_number(response));
                }
            } catch(Exception exception) {
                System.out.println("Please enter a positive number.");   
            }

        }
        
        System.out.println("\nNow that all the settings have been completed.");
        // Game Start Menu
        while (game_stage == 3) {
            
            System.out.println("Would you like to start the game? (Y/N)");
            String permission = scanner.next();

            // Checking if the player has started the game
            if (user_verification(permission).equals("true")) {
                System.out.println("\nYou have decided to start the game.");
                game_stage++;
            } else if (user_verification(permission).equals("false")) {
                System.out.println("\nWhy would you do this to me... I just wanted to play with you.");
            } else {
                System.out.println(user_verification(permission));
            }
        }
        
        System.out.println("\nYour number is between 0 and " + num_domain + ".");
        System.out.println("You have " + num_of_guesses + " guesses left.");

        // Main Game Loop
        while (game_stage == 4 && num_of_guesses != 0) {
            
            String guessStr = scanner.next();
            try {
                
                int guess = Integer.parseInt(guessStr);

                // Higher lower checker
                if (num_Check(guess, num_to_guess) == 1) {
                    num_of_guesses --;
                    System.out.println("\n" + guess + " is greater than your number.");
                    System.out.println("You have " + num_of_guesses + " guesses left.");
                } else if (num_Check(guess, num_to_guess) == -1){
                    num_of_guesses --;
                    System.out.println("\n" + guess + " is less than your number.");
                    System.out.println("You have " + num_of_guesses + " guesses left.");
                } else {
                    num_of_guesses --;
                    game_stage ++;
                }
            } catch (Exception exception) {
                System.out.println("Please enter a number.");
            }
        }
    
        // Lose Scene
        if (num_of_guesses == 0) {
            System.out.println("\nGAME OVER | YOU HAVE LOST");
            System.out.println("Your number was: " + num_to_guess);
            scanner.close();
        }
        
        // Win Scene
        if (game_stage == 5) {
            System.out.println("\nYOU HAVE WON | CONGRATULATIONS");
            System.out.println("Your number was: " + num_to_guess + " and you had " + num_of_guesses + " guesses remaining.");
            scanner.close();
        }
    }

    

    // Classes
    // ========

    // Checks if the input was a valid answer to a Y/N question.
    public static String user_verification(String user_response) {
        if (user_response.equals("Y") || user_response.equals("y")) {
            return "true";
        } else if (user_response.equals("n") || user_response.equals("N")) {
            return "false";
        } else {
            return "That is not a valid option, please use either 'Y' or 'N'.";
        }
    }

    // Checks if the number will not create errors.
    public static String valid_number(String user_response) {
        if (Integer.parseInt(user_response) > 0) {
            return "true";
        } else {
            return user_response + " is not a valid option, please enter a positive number.";
        }
    }

    // Determines if the guess is higher/lower than the random number.
    public static int num_Check(int guess, int num) {
        if (guess > num) {
            return 1; 
        } else if (guess < num) {
            return -1;
        } else {
            return 0;
        }
    }
}