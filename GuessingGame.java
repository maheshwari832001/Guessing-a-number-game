import java.util.Scanner;
import java.util.Random;

public class GuessingGame{
	public static void main (String [] args) 
	{
		Scanner sc = new Scanner(System.in);
		String play = "yes";
		while(play.equals("yes"))
		{
			Random rand = new Random();
			int randnum = rand.nextInt(100);
			int guess = -1;
			int tries = 0;
			
			while(guess!= randnum)
			{
				System.out.print("Guess the number between 1 to 100: ");
				guess = sc.nextInt();
				tries++;
				if(guess == randnum)
				{
					System.out.println("Well done! yor guessed it right!");
					System.out.println("you only took "+tries + "guessess");
					System.out.println("Would u like to play again ? Yes or No");
					play = sc.next().toLowerCase();
				}
				else if(guess>randnum)
				{
					System.out.println("your guess is too high,try again");
				}
				else
				{
					System.out.println("your guess is too low,try again");
				}
			}
		}
		sc.close();
	}
}
