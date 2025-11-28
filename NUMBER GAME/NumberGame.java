import java.util.Random;
import java.util.Scanner;

public class NumberGame {
    private static final int MIN_NUMBER = 1;
    private static final int MAX_NUMBER = 100;
    private static final int MAX_ATTEMPTS = 7;

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            playGame(scanner);
        }
    }

    private static void playGame(Scanner scanner) {
        Random random = new Random();
        int roundsPlayed = 0;
        int roundsWon = 0;
        int totalAttempts = 0;

        boolean keepPlaying = true;
        while (keepPlaying) {
            roundsPlayed++;
            int secretNumber = random.nextInt(MAX_NUMBER - MIN_NUMBER + 1) + MIN_NUMBER;
            int attemptsUsed = 0;
            boolean wonRound = false;

            System.out.printf("Round %d: Guess the number between %d and %d.%n",
                    roundsPlayed, MIN_NUMBER, MAX_NUMBER);

            while (attemptsUsed < MAX_ATTEMPTS) {
                System.out.printf("Attempt %d of %d. Enter your guess: ",
                        attemptsUsed + 1, MAX_ATTEMPTS);
                int guess = readInt(scanner);
                attemptsUsed++;
                totalAttempts++;

                if (guess == secretNumber) {
                    System.out.println("Correct! You guessed the number.");
                    wonRound = true;
                    roundsWon++;
                    break;
                } else {
                    int difference = Math.abs(guess - secretNumber);
                    if (difference <= 5) {
                        if (guess < secretNumber) {
                            System.out.println("Close! But slightly low.");
                        } else {
                            System.out.println("Close! But slightly high.");
                        }
                    } else {
                        if (guess < secretNumber) {
                            System.out.println("Too low.");
                        } else {
                            System.out.println("Too high.");
                        }
                    }
                }
            }

            if (!wonRound) {
                System.out.printf("Out of attempts! The correct number was %d.%n", secretNumber);
            }

            System.out.printf("Scoreboard -> Rounds Played: %d, Rounds Won: %d, Total Attempts: %d%n",
                    roundsPlayed, roundsWon, totalAttempts);

            System.out.print("Play again? (y/n): ");
            keepPlaying = askToPlayAgain(scanner);
            System.out.println();
        }

        System.out.println("Thanks for playing!");
    }

    private static int readInt(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.next().trim());
            } catch (NumberFormatException ex) {
                System.out.print("Invalid input. Please enter a whole number: ");
            }
        }
    }

    private static boolean askToPlayAgain(Scanner scanner) {
        while (true) {
            String input = scanner.next().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                return true;
            }
            if (input.equals("n") || input.equals("no")) {
                return false;
            }
            System.out.print("Please answer with 'y' or 'n': ");
        }
    }
}

