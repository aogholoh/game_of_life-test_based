/**
Ailen Ogholoh
CSCI 1583
Homework 5
October 18, 2013
**/
import java.util.Scanner;
import java.io.File;
public class GameOfLife//This is a text-based version of John Conway's Game of Life, which uses an input file as its initial game board
{

	public static void main( String[] args ) throws Exception{
		String fileName = args[ 0 ];
		
		File initialGameBoard = new File( fileName );
		Scanner input = new Scanner( initialGameBoard );

        //create a 2D string array called "firstBoard" with length designated by the input file
		String[][] firstBoard = new String[ input.nextInt() ][ input.nextInt() ];
		
		for( int row = 0; row < firstBoard.length; row++ ){
			for( int column = 0; column < firstBoard[row].length; column++ ){

				firstBoard[ row ][ column ] = input.next();

                //if the program has reached the last index the array's second dimension
				if( column == ( firstBoard[row].length - 1 ) ){
					System.out.print( firstBoard[ row ][ column ] + "\n" );
				}
				else
					System.out.print( firstBoard[ row ][ column ] + " " );
			}
		}

		input.close();
		System.out.println( " " );//print a line to separate each board from the next
		boardCreator( firstBoard );			
	}//end of main method

	//method used to make all new boards after the initial board has been read into the program
	public static void boardCreator( String[][] currentGameBoard )	{
		//int timer = 0;

		do{
            String[][] nextGameBoard = new String[ currentGameBoard.length ] [ currentGameBoard[ 0 ].length ];
            String currentCellValue;
            String newCellValue;
            int liveNeighbors;
            
            for( int row = 0; row < currentGameBoard.length; row++ ){
                for( int column = 0; column < currentGameBoard[ row ].length; column++ ){

                    currentCellValue = currentGameBoard[ row ] [ column ];
                    liveNeighbors = neighborChecker( row, column, currentGameBoard );
                    newCellValue = rules( currentCellValue, liveNeighbors );
                    nextGameBoard[ row ] [ column ] = newCellValue;

                    //if the program has reached the last index the array's second dimension
                    if( column == ( nextGameBoard[row].length - 1 ) ){
                        System.out.print( nextGameBoard[ row ][ column ] + "\n" );
                    }
                    else
                        System.out.print( nextGameBoard[ row ][ column ] + " " );
                }
            
            }

            System.out.println( " " );//print a line to separate each board from the next
            currentGameBoard = nextGameBoard;//assign the array nextGameBoard to currentGameBoard.
            timer++;

		}while(true);//Loops infinitely
		
	}//end of method

    //loops through the eight neighbors of the current cell
	public static int neighborChecker( int row, int column, String[][] gameBoard ){
		
		int liveNeighbors = 0;
		String neighborValue;
        //(index + 1) % length
        //(index - 1) + length
 
        //loops through three rows: the current cell's row
        //the one above the current cell
        //and the one below the current cell
        for( int neighborRow = row - 1; neighborRow < row + 2; neighborRow++ ){

            //loops through three columns: the current cell's column
            //the one before the current cell
            //and the one after the current cell
            for( int neighborColumn = column - 1; neighborColumn < column + 2; neighborColumn++ ){

                //ensures that the current cell is not counted as a neighbor of itself
                if( ( neighborColumn != column ) || ( neighborRow != row ) ){

                    //the following selections are to ensure line wrapping
                    int rowIndex = neighborRow;
                    int columnIndex = neighborColumn;
                
                    if( rowIndex < 0){
                        rowIndex = gameBoard.length - 1;
                    }
                    else if( rowIndex == gameBoard.length ){
                        rowIndex = 0;
                    }
                    if( columnIndex < 0 ){
                        columnIndex = gameBoard[row].length - 1;
                    }
                    else if( columnIndex == gameBoard[row].length){
                        columnIndex = 0;
                    }

                    neighborValue = gameBoard [ rowIndex ][ columnIndex ]; 

                    if( neighborValue.equals( "1" ) ){
                        liveNeighbors++;
                    }
                }						
            
            }
        }
		
		return liveNeighbors;	
    }//end of method "neighborChecker"

    //checks the cell and applies the game's rules to it for the next board's value
	public static String rules( String cellLife, int liveNeighbors )
	{
		String newCellLife;

		if( cellLife.equals( "1" ) )//if the cell is alive
		{
			if( ( liveNeighbors == 2 ) || ( liveNeighbors == 3 ) )
			{
				newCellLife = "1";
			}
			else
			{
				newCellLife = "0";
			}
			return newCellLife;
		}
		else//cell is dead 
		{
			if( liveNeighbors == 3 )
			{	
				newCellLife = "1";
			}
			else
			{
				newCellLife = "0";
			}
			return newCellLife;
		}	
	}//end of method
}//End of class
