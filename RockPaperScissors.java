import java.util.Scanner;
import java.util.Random;

public class RockPaperScissors {
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        String[] choices = {"rock", "paper", "scissors"};

        do {
            System.out.println("Let's play Rock, Paper, Scissors!");
            System.out.print("Enter your choice (rock, paper, or scissors): ");
            String playerChoice = sc.nextLine().toLowerCase();
            int comp_Index = rand.nextInt(3);
            String computerChoice = choices[comp_Index];
            System.out.println("Computer chooses: " + computerChoice);

            if (playerChoice.equals(computerChoice)) 
            {
                System.out.println("It's a tie!");
            } 
            else if ((playerChoice.equals("rock") && computerChoice.equals("scissors")) ||
                    (playerChoice.equals("paper") && computerChoice.equals("rock")) ||
                    (playerChoice.equals("scissors") && computerChoice.equals("paper"))) {
                System.out.println("You win!");
            } 
            else 
            {
                System.out.println("Computer wins!");
            }
            System.out.println("Would you like to play again? (yes or no)");
        }
        while (sc.nextLine().equalsIgnoreCase("yes"));

        System.out.println("Game over.");
        sc.close();
    }
}