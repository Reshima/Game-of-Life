
/**********************************************************************************************************************************
*	File: Shima_GameOfLife.java
*	Author: Rachael Shima
*	Class: CS 141 - Programming and Problem Solving
*
*	Assignment: Program 6
*	Date Last Modified:
*
*	Purpose: This program runs a simulation of a game using a game board.
*		The rules of the Game Of Life are as follows:
*		For alive cells (X):
*			Each cell with less than two neighbors, who are alive, dies of loneliness in the next generation.
*			Each cell with more than three nieghbors, who are alive, dies of overpopulation in the next generation.
*			Each cell with two or three neighbors, who are alive, continues to live in the next generation.
*		For dead cells (0):
*			Each cell with three neighbors, who are alive, comes to life in the next generation.
*		'Neighbors' are the cells diagonally, vertically and horizontally adjacent.
*
*************************************************************************************************************************************/
import java.util.Scanner;
import java.io.*;
import java.util.StringTokenizer;
import java.io.FileNotFoundException;

class Shima_GameOfLife
{
  private int rows;
  private int columns;
  private char[][] board;
  private int compute = 0;
//Method: main
//Purpose: This is the main method and the one the user interacts directly with.
  public static void main(String[] args) throws IOException
  {
    Scanner input = new Scanner(System.in);
    System.out.print("Play the Game of Life? ");
    String initialize = input.nextLine();
    char answer = initialize.charAt(0);
    if(answer == 'y' || answer == 'Y')
    {
      System.out.print("What board would you like to use? ");
      String start = input.nextLine();
      File initial = new File(start);
      if(initial.exists())
      {
        Shima_GameOfLife game = new Shima_GameOfLife(initial);
        System.out.print("How many generations do you wish to compute? ");
        int generations = input.nextInt();
        game.computeNextGeneration(generations);
        System.out.println("Thank you for playing the Game Of Life!");
      }
      else
      {
        System.out.println("Error file not found. Please type in the name of a file");
      }
    }
  }
//Method: Constructor
//Purpose: This method initializes a new game board by prompting the user for the file name and loading
//the game board data from the file.
  public Shima_GameOfLife(File play) throws IOException
  {
    Scanner file = new Scanner(play);
    String first = file.nextLine();
    StringTokenizer num = new StringTokenizer(first);
    String vertical = num.nextToken();
    String horizontal = num.nextToken();
    columns = Integer.parseInt(vertical);
    rows = Integer.parseInt(horizontal);
    board = new char[rows][columns];
    for(int r = 0; r < rows; r++)
    {
      String line = file.nextLine();
      String[] split = new String[columns];
      split = line.split(" ");
      for(int c = 0; c < columns; c++)
      {
        board[r][c] = split[c].charAt(0);
      }
    }
  }
//Method: getColumn
//Purpose: This method retrieves the number of columns in the array.
  public int getColumns() throws IOException
  {
    return columns;
  }
//Method: getRows
//Purpose: This method retrieves the number of rows in the array.
  public int getRows() throws IOException
  {
    return rows;
  }
//Method: getCell
//Purpose: This method retrieves the value of the cell indicated by the column and row number provided.
  public char getCell(int column, int row) throws IOException
  {
    char cell = board[row][column];
    return cell;
  }
//Method: setCell
//Purpose: This method assigns a value to the cell indicated by the column and row number provided.
  public void setCell(int column, int row, int value) throws IOException
  {
    if(value == 0)
    {
      board[row][column] = '0';
    }
    else if (value == 1)
    {
      board[row][column] = 'X';
    }
  }
//Method: computeNextGeneration
/*Purpose: This method creates a temporary 2D array to compute the next iteration of the board containing
	the next generation of organisms, as determined by the Rules of Life. Then it updates the board to
	represent the next generation. The argument passed in represents the number of generations the user
	wants to compure. To compute each generation, the method should recursively call itself and decrement
	the integer until it terminates when there are no mre generations left to compute.*/
  public void computeNextGeneration(int gen) throws IOException
  {
    if(gen > 0)
    {
      System.out.println("Generation " + compute + ":");
      char[][] temp = new char[this.getRows()][this.getColumns()];
      for(int r = 0; r < this.getRows(); r++)
      {
        for(int c = 0; c < this.getColumns(); c++)
        {
          temp[r][c] = board[r][c];
        }
      }
      this.print();
      int aliveCount;
      for(int r = 0; r < temp.length; r++)
      {
        if(r == 0)
        {
          for(int c = 0; c < temp[r].length; c++)
          {
             aliveCount = 0;
            if(c == 0)
            {
              if(temp[r][c+1] == 'X')
              {
                aliveCount++;
              }
              if(temp[r+1][c] == 'X')
              {
                aliveCount++;
              }
              if(temp[r+1][c+1] == 'X')
              {
                aliveCount++;
              }
              if(this.getCell(c,r) == 'X')
              {
                if(aliveCount > 3 || aliveCount < 2)
                {
                  this.setCell(c,r,0);
                }
              }
              if(this.getCell(c,r) == '0')
              {
                if(aliveCount == 3)
                {
                  this.setCell(c,r,1);
                }
              }
            }
            else if(c == board[r].length - 1)
            {
              if(temp[r][c-1] == 'X')
              {
                aliveCount++;
              }
              if(temp[r+1][c-1] == 'X')
              {
                aliveCount++;
              }
              if(temp[r+1][c] == 'X')
              {
                aliveCount++;
              }
              if(this.getCell(c,r) == 'X')
              {
                if(aliveCount > 3 || aliveCount < 2)
                {
                  this.setCell(c,r,0);
                }
              }
              if(this.getCell(c,r) == '0')
              {
                if(aliveCount == 3)
                {
                  this.setCell(c,r,1);
                }
              }
            }
            else
            {
              if(temp[r][c-1] == 'X')
              {
                aliveCount++;
              }
              if(temp[r][c+1] == 'X')
              {
                aliveCount++;
              }
              if(temp[r+1][c-1] == 'X')
              {
                aliveCount++;
              }
              if(temp[r+1][c] == 'X')
              {
                aliveCount++;
              }
              if(temp[r+1][c+1] == 'X')
              {
                aliveCount++;
              }
              if(this.getCell(c,r) == 'X')
              {
                if(aliveCount > 3 || aliveCount < 2)
                {
                  this.setCell(c,r,0);
                }
              }
              if(this.getCell(c,r) == '0')
              {
                if(aliveCount == 3)
                {
                  this.setCell(c,r,1);
                }
              }
            }
          }
        }
        else if(r == temp.length - 1)
        {
          for(int c = 0; c < temp[r].length; c++)
          {
            aliveCount = 0;
            if(c == 0)
            {
              if(temp[r-1][c] == 'X')
              {
                aliveCount++;
              }
              if(temp[r-1][c+1] == 'X')
              {
                aliveCount++;
              }
              if(temp[r][c+1] == 'X')
              {
                aliveCount++;
              }
              if(this.getCell(c,r) == 'X')
              {
                if(aliveCount > 3 || aliveCount < 2)
                {
                  this.setCell(c,r,0);
                }
              }
              if(this.getCell(c,r) == '0')
              {
                if(aliveCount == 3)
                {
                  this.setCell(c,r,1);
                }
              }
            }
            else if(c == temp[r].length - 1)
            {
              if(temp[r-1][c-1] == 'X')
              {
                aliveCount++;
              }
              if(temp[r-1][c] == 'X')
              {
                aliveCount++;
              }
              if(temp[r][c-1] == 'X')
              {
                aliveCount++;
              }
              if(this.getCell(c,r) == 'X')
              {
                if(aliveCount > 3 || aliveCount < 2)
                {
                  this.setCell(c,r,0);
                }
              }
              if(this.getCell(c,r) == '0')
              {
                if(aliveCount == 3)
                {
                  this.setCell(c,r,1);
                }
              }
            }
            else
            {
              if(temp[r-1][c-1] == 'X')
              {
                aliveCount++;
              }
              if(temp[r-1][c] == 'X')
              {
                aliveCount++;
              }
              if(temp[r-1][c+1] == 'X')
              {
                aliveCount++;
              }
              if(temp[r][c-1] == 'X')
              {
                aliveCount++;
              }
              if(temp[r][c+1] == 'X')
              {
                aliveCount++;
              }
              if(this.getCell(c,r) == 'X')
              {
                if(aliveCount > 3 || aliveCount < 2)
                {
                  this.setCell(c,r,0);
                }
              }
              if(this.getCell(c,r) == '0')
              {
                if(aliveCount == 3)
                {
                  this.setCell(c,r,1);
                }
              }
            }
          }
        }
        else
        {
          for(int c = 0; c < temp[r].length; c++)
          {
            aliveCount = 0;
            if(c == 0)
            {
              if(temp[r-1][c] == 'X')
              {
                aliveCount++;
              }
              if(temp[r-1][c+1] == 'X')
              {
                aliveCount++;
              }
              if(temp[r][c+1] == 'X')
              {
                aliveCount++;
              }
              if(temp[r+1][c] == 'X')
              {
                aliveCount++;
              }
              if(temp[r+1][c+1] == 'X')
              {
                aliveCount++;
              }
              if(this.getCell(c,r) == 'X')
              {
                if(aliveCount > 3 || aliveCount < 2)
                {
                  this.setCell(c,r,0);
                }
              }
              if(this.getCell(c,r) == '0')
              {
                if(aliveCount == 3)
                {
                  this.setCell(c,r,1);
                }
              }
            }
            else if(c == temp[r].length - 1)
            {
              if(temp[r-1][c-1] == 'X')
              {
                aliveCount++;
              }
              if(temp[r-1][c] == 'X')
              {
                aliveCount++;
              }
              if(temp[r][c-1] == 'X')
              {
                aliveCount++;
              }
              if(temp[r+1][c-1] == 'X')
              {
                aliveCount++;
              }
              if(temp[r+1][c] == 'X')
              {
                aliveCount++;
              }
              if(this.getCell(c,r) == 'X')
              {
                if(aliveCount > 3 || aliveCount < 2)
                {
                  this.setCell(c,r,0);
                }
              }
              if(this.getCell(c,r) == '0')
              {
                if(aliveCount == 3)
                {
                  this.setCell(c,r,1);
                }
              }
            }
            else
            {
              if(temp[r-1][c-1] == 'X')
              {
                aliveCount++;
              }
              if(temp[r-1][c] == 'X')
              {
                aliveCount++;
              }
              if(temp[r-1][c+1] == 'X')
              {
                aliveCount++;
              }
              if(temp[r][c-1] == 'X')
              {
                aliveCount++;
              }
              if(temp[r][c+1] == 'X')
              {
                aliveCount++;
              }
              if(temp[r+1][c-1] == 'X')
              {
                aliveCount++;
              }
              if(temp[r+1][c] == 'X')
              {
                aliveCount++;
              }
              if(temp[r+1][c+1] == 'X')
              {
                aliveCount++;
              }
              if(this.getCell(c,r) == 'X')
              {
                if(aliveCount > 3 || aliveCount < 2)
                {
                  this.setCell(c,r,0);
                }
              }
              if(this.getCell(c,r) == '0')
              {
                if(aliveCount == 3)
                {
                  this.setCell(c,r,1);
                }
              }
            }
          }
        }
      }
      compute++;
      gen--;
      this.computeNextGeneration(gen);
    }
  }
//Method: print:
//Purpose: This method prints out the board to the console
  public void print() throws IOException
  {
    for(int r = 0; r < board.length; r++)
    {
      for(int c = 0; c < board[r].length; c++)
      {
        System.out.print(board[r][c] + " ");
      }
      System.out.println();
    }
  }
}
