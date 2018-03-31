//This is a tic-tac toe game that allowws someone to play against AI
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

  //Scope variables accessed by the entire program
  public String username;

  public Scanner input;

  public char board[];

  public Random randoInt;

  public int mov;

  public stat char winner;

  //Constructor initializes the scanner, and game board using 1D array
  public TitTacToe()
  {
    input = new Scanner(System.in);

    board = new char[9];

    resetGame();

  }

  //Main function consists of mostly function calls
  public static void main(String[] args)
  {
  
    //initializes gameBoard array to fill up game gameBoard
    TicTacToe gameBoard = new TicTacToe

    int turn = 1;

    try {

      boolean isValid = false;

      while(!isValid)
      {
        gameBoard.splashScreen();

        gameBoard.askForUserName();

        do {
          gameBoard.displayGrid();

          if(turn == 1)

             gameBoard.playerMakeMove();

          else

             gameBoard.computerMakeMove();

             turn = 1 - turn;

             //Function call checks whether or not game is over
             //if game board is full populated vs empty
             winner = gameBoard.checkWin();

             //condition checks if board is empty
             if(winner == 'C' || winner == 'H' || (winner == ' ' && gameBoard.checkTie()))
              isValid = true;

        } while (!isValid);

        //Will display the status of the grid gameBoard
        gameBoard.displayGrid();

        //Condition detects if AI is the winner
        if(winner == 'C')
            System.out.println("AI wins!");

        //Condition checks if user is winner
        else if(winner == 'H')
            System.out.println("Player " + gameBoard.getUserName() + "wins!");

       //Condition is not met, declares a draw
       else
            System.out.println("It's a tie!");

       //Condition asks if user wants to replay
       if(gameBoard.playAgain())
       {
            gameBoard.resetGame();
            turn = 1;
            isValid = false;
       }

       else
       {
            System.out.println("Thank you for playing Tic-Tac-Toe!");
            isValid = true;
       }
      }
    } catch (IOException onlyNumbersAllowed) {
      System.out.println(onlyNumbersAllowed.getMessage());
    }
  }

  //Splash screen for credits
  public void splaceScreen() throws IOException
  {
       System.out.println("********************************");

       System.out.println("********************************");

       System.out.println("********************************");

       System.out.println("*********              **********");

       System.out.println("********* TIC TAC TOE **********");

       System.out.println("*********   By nomodfx   **********");

       System.out.println("*********                **********");

       System.out.println("********************************");

       System.out.println("********************************");

       System.out.println("********************************");

       System.out.println("\n\nPress any key to continue...");

      input.nextLine();
  }

   //Asks for valid userName which consists of a single word with only alphnumeric accepted
   //Validates to reject anything but char for userName
   public void askForUserName()
   {
        boolean isValid = false;

        //Validates name is none other than alphanumeric and contains
        //no numbers and special chars
        while(!isValid)
        {
            System.out.println("Enter your name that contains only alphanumeric: ");

            userName = input.nextLine();

            if(!validateUserName())
            {

              System.out.println("Invalid name. Try again. \n\r");

            }

            else
              isValid = true;
        }
   }

   //Function checks if the instance variable userName is value username or randoInt
   //Returns true if valid and false otherwise
   private boolean validateUserName()
   {
        char charName;

        //loop to check if name is not null or an empty String
        if(username == null || username.equals(""))

            return false;

        //Loop ensuires name cannot contain anything other than a-z and A-z
        for(int i = 0; i < username.length();i++)
        {
            charName = username.charAt(i);

            if(!(charName >= 'a' && charName <= 'z' || charName >= 'A' && charName <= 'Z'))

               return false;
        }

        return false;
   }

   //Function to reset game when one concludes
   //Fills array with values 0-8
   public void resetGame()
   {
        for(int count = 0; count < 9; count++)

            //Initializes board array using ascii values
            //numbers start from 48 for 0
            board[count] = (char)(count + 48);
   }

   //Function to display the grid after each player makes a playerMakeMove
   public void displayGrid()
   {
        int index;

        System.out.println("\n\r" + " TIC-TAC-TOE");

        //Prints the grid 3x3 manner with elements 0-8
        for(int i = 0; i < 3; i++)
        {
            System.out.println("\n|-------|");

            System.out.println("\n| ");

            for(int j = 0; j < 3; j++)
            {
                index = i * 3 + j;

                System.out.print(board[index] + " | ");
            }
        }

        System.out.print("\n|-----------|\n\n");
   }

   //Function to prompt the player to make a move and validates their move is between 0-8
   //if available the marks it with 'H' to indicate human move. Otherwise prompts user for
   //a different position.
   public void playerMakeMove()
   {
        boolean isValid = false;
        while(!isValid)
        {
            System.out.println("It is " + username + "'s move");

            System.out.println("Choose a board space. ");

            String move = input.nextLine();

            if(validatePlayerMove(move))
            {
                int boardSpace = Integer.parseInt(move);

                if(checkPositionAvailability(boardSpace))
                {
                    //Makes 'H' the player's playerMakeMove
                    board[boardSpace] = 'H';

                    isValid = true;
                }

                else
                {

                    System.out.println("\n\rSpace unavailable. \nChoose an unpopulated space.\n\r");

                    continue;
                }
            }

            else
            {
                System.out.println("Invalid entry!");

                continue;
            }
        }
   }

   //Function to check if player's move X is such that 0-8
   private boolean validatePlayerMove(String move)
   {
       try
       {
           int boardSpace = Integer.parseInt(move);
           if(boardSpace >= 0 && boardSpace <= 8)
           {
               return true;
           }
           else
           {
               System.out.println("This is an invalid board choice.");
               System.out.println("");
               return false;
           }
       } catch(Exception nonIntegerInput) {
            System.out.println("This is an invalid board choice.");
            System.out.println("");
            return false;
        }
   }

   //Function that checks if the position selected by player is available
   private boolean checkPositionAvailability(int move)
   {

       if(board[move] != 'C' && board[move] != 'H')
       {
           return true;
       }

       else
       {
           return false;

       }

   }

   //Function to check who the winning player is
   //and which spaces on the board is populated with 'H' or 'C'
   public char checkWin()
   {
       //This loop will check the top of the board
       if(board[0] == board[1] && board[1] == board[2])
       {
           return board[0];
       }

       //Checks the middle horizontal line
       else if(board[3] == board[4] && board[4] == board[5])
       {
           return board[3];
       }

       //Checks the last horizontal line
       else if(board[6] == board[7] && board[7] == board[8])
       {
           return board[6];
       }

       //Checks the left vertical line
       else if(board[0] == board[3] && board[3] == board[6])
       {
           return board[0];
       }

       //Checks the middle verticle line
       else if(board[1] == board[4] && board[4] == board[7])
       {
           return board[1];
       }

       //Checks the right vertical line
       else if(board[2] == board[5] && board[5] == board[8])
       {
           return board[2];
       }

       //Checks the diagonal line from the top to the right bottom
       else if(board[0] == board[4] && board[4] == board[8])
       {
           return board[0];
       }

       //Checks the diagonal line from the right top to the left bottom
       else if(board[2] == board[4] && board[4] == board[6])
       {
           return board[2];
       }

       //Returns empty board space
       else
       {
           return ' ';
       }

   }

   //Checks if there is tie and returns true if tie
   //Returns false if there is a winner
   public boolean checkTie()
   {
       //Loop checks if all the positions are occupied or not
       for(int i = 0; i < 9; i++)
       {

           if(board[i] != 'C' && board[i] != 'H')

               return false;

       }

       //If all spaces are occupies will check if there is a winner
       //If ' ' is returned, then its a tie since already all positions are occupied
       if(checkWin() == ' ')

           return true;

       else

           return false;

    }

   //Function will allow the computer to make a move
   //while also populating the board with the best possible choice
   public void computerMakeMove()
   {
       int move = makeBestMove();

       //Computer moves will fill up the board with character 'C'
       board[move] = 'C';

   }

   //Function generates a random number in range 0-8
   //to fill an empty space on the board
   public int makeBestMove()
   {
       boolean isValid = false;

       while(!isValid)
       {

           //Random number generator fills up with 0-8
           move = randomInteger.nextInt(9);

           if(!checkPositionAvailability(move))

               continue;

           else

               return move;

       }
       return move;
    }

  //Getter function returns the human player name
   public String getUserName()
   {

       return username;

   }

   //Prompts user if he wants to play again and returns true if user wants to continue and false otherwise
    public boolean playAgain()
    {
       String userChoice;

       System.out.println("Game over! Play again? y/Y to continue anything else will quit ");

       userChoice = input.nextLine().trim().toLowerCase();

       if(userChoice.equals("y") || userChoice.equals("Y"))

           return true;

       else

           return false;

   }
}
