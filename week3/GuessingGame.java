import java.util.Scanner;

public class GuessingGame {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Welcome to the CSC254 Guessing Game!");

        var goal = (int)(1 + Math.random()*100);

        while(true) {
            System.out.print("Guess a number between 1 and 100: ");
            if(in.hasNextInt()) {
                var guess = in.nextInt();
                if (guess == goal) {
                    System.out.println("Correct!");
                    break;
                } else if (guess < goal) {
                    System.out.println("Your guess was too low");
                } else {
                    System.out.println("Your guess was too high");
                }
            } else {
                System.out.println("Invalid response, try again.");
                in.next(); // burn the invalid input
            }
        }
    }
}
