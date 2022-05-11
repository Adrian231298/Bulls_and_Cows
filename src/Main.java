import java.util.Scanner;
import java.util.Arrays;

public class Main 
{
    private static final Scanner Sc = new Scanner(System.in);
    private static  String Code = "";
    private static int Bulls = 0;
    private static int Cows = 0;
    private static int PossibleSymbols = 0;


    public static void main(String[] args) {
      System.out.println("Input the length of the secret code:");
      int length = Sc.nextInt();
      System.out.println("Input the number of possible symbols in the code:");
      PossibleSymbols = Sc.nextInt();
      if (CheckNInput(length)) {
	Code = RandomSecretNumber(length);
      } else {
      	System.out.printf("Error: can\'t generate a secret number with a length of %d because there aren\'t enough unique digits.\n", length);
	    
      }
      System.out.println(Code);

      System.out.println(SecretMessageRanges(length));

      BullsAndCows(length);
      
    }

    private static void BullsAndCows(int length)
    {
      int turn = 1;
      System.out.println("Okay, let's start a game!");
      boolean isCorrect = false;
      while (!isCorrect) {
	System.out.printf("Turn %d:\n", turn);
	String number = Sc.next();
	String modified_code = GwessBullsProduceNewString(number);
	GwessCows(modified_code, number);
	PrintResult();
	turn += 1;
	if (Bulls == length) {
	  isCorrect = true;
	  System.out.println("Congratulations! You guessed the secred code.");
	}
	Bulls = 0;
	Cows = 0;
      }
    }

    private static String GwessBullsProduceNewString(String gwess)
    {
      String modified_code = "";
        for (int i = 0; i < Code.length(); i++) {
            if (gwess.charAt(i) == Code.charAt(i)) {
                Bulls += 1;
            } else {
               modified_code += Code.charAt(i);
            }
        }
	return modified_code;
    }
    private static void GwessCows(String modified_code, String gwess)
    {
        for (int i = 0; i < gwess.length(); i++) {
            Cows += SearchForNumberOfCharsInString(modified_code, gwess.charAt(i));
        }
    }
    private static void PrintResult()
    {
        if (Bulls == 0 && Cows == 0) {
            System.out.println("Grade: None.");
        } else if (Cows > 0 && Bulls == 0) {
	  if (Cows > 1) {
            System.out.printf("Grade: %d cows.\n", Cows);
	  } else {
	    System.out.println("Grade: 1 cow.");
	  }
        } else if (Bulls > 0 && Cows == 0) {
	  if( Bulls > 1 ) {
            System.out.printf("Grade: %d bulls.\n", Bulls);
	  } else {
	    System.out.println("Grade: 1 bull.");
	  }
        } else {
	  if (Bulls == 1) {
	    System.out.printf("Grade: 1 bull and %d cows.\n", Cows);
	  } else if (Cows == 1) {
	    System.out.printf("Grade: %d bulls and 1 cow.\n",Bulls);
	  } else if (Bulls > 1 && Cows > 1)
	  {
            System.out.printf("Grade: %d bulls and %d cows.\n",
                    Bulls, Cows);
	  }
        }
    }
    private static int SearchForNumberOfCharsInString(String s, char c) {
        int res = 0;
        for (int i = 0; i < s.length(); i++){
               if (c == s.charAt(i)) {
                   res += 1;
            }
        }
        return res;
    }
    private static String RandomSecretNumber(int n) 
    {
      StringBuilder sb = new StringBuilder();
      while (true) {
	if (sb.length() == n) {
	  break;
	}
	int rng = (int)(Math.random() * PossibleSymbols);
	//int rng = 48 + super_rng;
	//if ((rng >= 48 && rng <= 57) || (rng >= 97 && rng <= 122)) {
	if (rng >= 0 && rng <= PossibleSymbols) {
	  char crng = Character.forDigit(rng, PossibleSymbols);
	  if (sb.length() == 0) {
	    sb.append(crng);
	  } else {
	    boolean isPresent = false;
	    for (int i = 0; i < sb.length(); i++) {
	      if (sb.charAt(i) == crng) {
		isPresent = true;
		break;
	      } 
	    }
	    if (isPresent == false) {
	      sb.append(crng);
	    }
	  }
	} else {
	  continue;
	}
      }
      return sb.toString();
    }
    private static boolean CheckNInput(int n) {
      if (n < 10 && n > 0) {
	return true;
      } else {
	return false;
      }
    }
    private static String SecretMessageRanges(int length)
    {
      char[] starts = new char[length];
      int i = 0;
      while (i < length) {
	starts[i] = '*';
	i += 1;
      }
      int min = 97;
      int max = 97;
      if (CharacterGreaterThanTen()) {
	int after_ints = PossibleSymbols - 10;
	max = min + after_ints - 1;
	return String.format("The secret is prepared: %s (0-9, %c-%c).",
	    String.copyValueOf(starts),
	    (char)min,
	    (char)max);
      }
     if (!CharacterGreaterThanTen()) {
       min = 0;
       max = min + PossibleSymbols - 1;
       return String.format("The secret is prepared: %s (%d-%d).",
	  String.copyValueOf(starts), 
	  min,
	  max);
     }
     return "Wrong number of symbols";
    }
    private static boolean CharacterGreaterThanTen()
    {
      if (PossibleSymbols > 10) {
	return true;
      } else {
	return false;
      }
    }
  
}



