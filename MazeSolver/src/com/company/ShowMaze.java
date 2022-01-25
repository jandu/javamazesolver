package com.company;

import java.util.ArrayList;

public class ShowMaze {

    //This function shows the maze in a more human readable form
    //Essentially all it does it take the value of the maze and populate it with
    //different values depending on the number in the matrix

    public static void printMaze(int[][] maze){


        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[row].length; col++) {

                if (maze[row][col] == 1) {
                    System.out.print("#");
                }
                if (maze[row][col] == 0) {
                    System.out.print(" ");
                }
                if (maze[row][col] == 2) {
                    System.out.print("0");
                }
                if (maze[row][col] == 9) {
                    System.out.print("▣");
                }

            }
            System.out.print("\n");

        }

    }
    //thus function is basically used to copy a 2D array to one place or another

    public static void copyMaze(int[][] maze,int input[][]) {
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze[row].length; col++) {
                input[row][col] = maze[row][col];

            }
        }
    }

}
