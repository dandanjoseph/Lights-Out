package lightsout;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import static lightsout.LightsOut.array;

/**
 * Solve a lights out board
 * <p>
 *
 * @author dandan
 * @edu.uwp.cs.340.course CSCI 340
 * @edu.uwp.cs.340.Assignment 0
 * @bugs none
 */
public class LightsOut {

    /**
     * Public array of 5x5 called array
     */
    public static int[][] array = new int[5][5];

    /**
     * Public array of 5x5 called array1
     */
    public static int[][] array1 = new int[5][5];

    /**
     * Public solution of 5x5 called solution
     */
    public static int[][] solution = new int[5][5];

    /**
     * Public reset solution set to 0 but still a 5x5 array
     */
    public static int[][] resetsolution1 = new int[][]{{0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}, {0, 0, 0, 0, 0}};

    /**
     * This is the main driver of the program, it takes in args[0] parameter and
     * runs the program
     *
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {

        String f = (args[0]);
        // this is where we set the arg to our String f to be used in loading
        loadboard(f);
        //this is where we call our load board function using our file passed in through f
        System.out.println("Board to be solved");
        //this is a print statement to notify the user of the board being solved
        printboard(array);
        //this is where print board is called to print the original board to the user
        System.out.println(" ");
        //we print another empty space for formating and readability
        System.out.println("Solutions to board, if empty assume non exist");
        //we print out a notice to the user to let them know the following solutions to the board
        solveboard();
        //we call solveboard method to solve the board and print solutions

    }

    /**
     * This method loads the boards contents from the file being passed as a
     * args
     *
     * @param filename
     * @throws FileNotFoundException
     */
    public static void loadboard(String filename) throws FileNotFoundException {

        Scanner scan = new Scanner(new File(filename));
        //we need a new scanner to load the data from the file
        for (int i = 0; i < 5; i++) {
            //we set a loop to run through the array
            for (int j = 0; j < 5; j++) {
                //we use another loop to run through the array in another direction
                array[i][j] = scan.nextInt();
                //we fill the array with the data (the next ints)
                array1[i][j] = array[i][j];
                //finally we make a copy of this array to use later in the program 
            }
        }

    }

    /**
     * This is the method called printboard where it takes the array with the
     * data in it and prints it
     *
     * @param array
     */
    public static void printboard(int[][] array) {
        for (int i = 0; i < 5; i++) {
            //we need to loop through the array in 1 direciton
            for (int j = 0; j < 5; j++) {
                //we loop through the array in the other direction too
                System.out.print(array[i][j]);
                //we print out array for the locations in the array i and j
            }
            System.out.println("");
            //we print out a extra space line for formatting purposes
        }
    }

    /**
     * This is the method checkstuff were we check to see if there is a 1
     * located in the array being solved
     *
     * @param array
     * @return
     */
    public static boolean checkstuff(int[][] array) {

        boolean b1 = false;
        //we have a boolean b1 set to false as a defalt
        for (int i = 0; i < 5; i++) {
            //next we loop through the array in 1 direction
            for (int j = 0; j < 5; j++) {
                //next we loop through the array in the other direction
                if (array[i][j] == 1) {
                    //next we see if the array has a 1 in it
                    return false;
                    //this will retrun false and break it
                } else {
                    b1 = true;
                    //else b1 is true identifying a solution to the board
                }//if it gets through the whole thing it will return b1 

            }

        }

        return b1;
    }

    /**
     * This method is called solveboard and we use it to solve the board.
     * <p>
     * We loop through to id the first 5 bits of the program to id which ones
     * have 1 in them and then continue to solve the board and create a solution
     */
    public static void solveboard() {

        for (int k = 0; k < 32; k++) {
            //we loop through 32 because we use binary numbers to id bits in the first row
            if (k % 2 == 1) {
                //if k mod 2 equals 1 then we know that we are at the farthest bit to the right
                makemove(0, 4);
                //we make a move on that bit if its identified
                reverse(solution, 0, 4);
                //next we reverse our solution board too to take note of our moves
            }
            if ((k / 2) % 2 == 1) {
                //if k mod 2 /2 equals 1 then we know that we are at the second farthest bit to the right
                makemove(0, 3);
                //we make a move on that bit if its identified
                reverse(solution, 0, 3);
                //next we reverse our solution board too to take note of our moves
            }
            if ((k / 4) % 2 == 1) {
                //if k mod 4 /2 equals 1 then we know that we are at the third farthest bit to the right
                makemove(0, 2);
                //we make a move on that bit if its identified
                reverse(solution, 0, 2);
                //next we reverse our solution board too to take note of our moves
            }//move in [0][2]
            if ((k / 8) % 2 == 1) {
                //if k mod 8 /2 equals 1 then we know that we are at the forth farthest bit to the right
                makemove(0, 1);
                //we make a move on that bit if its identified
                reverse(solution, 0, 1);
                //next we reverse our solution board too to take note of our moves
            }//move in [0][1]
            if ((k / 16) % 2 == 1) {
                //if k mod 8 /2 equals 1 then we know that we are at the forth farthest bit to the right
                makemove(0, 0);
                //we make a move on that bit if its identified
                reverse(solution, 0, 0);
                //next we reverse our solution board too to take note of our moves
            }//move in[0][0]

            for (int i = 0; i < 4; i++) {
                //next now that we id'd the first board we are going to loop through the rest of the board
                for (int j = 0; j < 5; j++) {
                    //we need another for loop to loop through the board
                    if (array[i][j] == 1) {
                        //if its equal to 1 at a point

                        makemove(i + 1, j);
                        //we make a move at that point
                        reverse(solution, i + 1, j);
                        //we reverse the same point on the solution board

                    }
                }
            }
            boolean check = checkstuff(array);
            //we have a boolean check that calls our check stuff method with the value of array
            if (check) {
                //we check to see if it returns true
                System.out.println(" ");
                //we print out a extra line
                printboard(solution);
                //next we print out our solution

            }

            resetBoard(array, array1);
            //we reset the board back to its original version
            resetsolution(solution);
            //we reset the solution as well
            check = false;
            //we have a check equal to false at the bottom to reset
        }

    }

    /**
     * This is the reset board method were we reset the boards back to original
     * by copying it over
     *
     * @param array
     * @param array1
     */
    public static void resetBoard(int[][] array, int[][] array1) {
        for (int i = 0; i < 5; i++) {
            //we need a loop to go through the board in 1 way 
            for (int j = 0; j < 5; j++) {
                //we need another loop to unwind
                array[i][j] = array1[i][j];
                //next we set the contents of 1 array to be the other one reseting the board
            }

        }

    }

    /**
     * This is the reset solution we reset our solution board back to all 0's to
     * find more solutions
     *
     * @param array
     */
    public static void resetsolution(int[][] array) {
        for (int i = 0; i < 5; i++) {
            //we need a loop to unwind the solution
            for (int j = 0; j < 5; j++) {
                //we need another loop to unwind the solution again
                array[i][j] = resetsolution1[i][j];
                //this is where we set the solution 
            }

        }

    }

    /**
     * This is where we reverse the board to flip the bits from 1 to 0 and back
     *
     * @param i
     * @param j
     */
    public static void reverse(int i, int j) {

        if (array[i][j] == 1) {
            //we check to see if the array at a position is equal to 1
            array[i][j] = 0;
            //we set that position to zero
        } else if (array[i][j] == 0) {
            //we check to see if its 0
            array[i][j] = 1;
            //we set that bit to 1 now
        }

    }

    /**
     * This is the overloaded reverse method that includes a array in the
     * parameter and we reverse bits from 1 to 0 and 0 to 1
     *
     * @param array
     * @param i
     * @param j
     */
    public static void reverse(int[][] array, int i, int j) {

        if (array[i][j] == 1) {
            //if the array at position is equal to 1 we then set it to 0
            array[i][j] = 0;
            //we set it to zero
        } else if (array[i][j] == 0) {
            //if its equal to zero
            array[i][j] = 1;
            //we set that value to 1
        }

    }

    /**
     * This is the makemove method were we choose to make moves on the board and
     * we use ifs to run through the board we call reverse were we need to make
     * moves
     *
     * @param i
     * @param j
     */
    public static void makemove(int i, int j) {
        int r = i;
        //we set i to r 
        int c = j;
        //we set j to c

        if (r > 0) {
            //if r is greater than 0
            reverse(r - 1, c);
            //we reverse the values at that point -1
        }
        if (c > 0) {
            //if c is greater than 0
            reverse(r, c - 1);
            //we reverse those values at that point -1

        }
        reverse(r, c);
        //we reverse the values at the original point
        if (c < 4) {
            //if c is less than 4
            reverse(r, c + 1);
            //we reverse the values at that point +1
        }
        if (r < 4) {
            //if r is less than 4
            reverse(r + 1, c);
            //we reverse the values at that point +1
        }

    }

}
