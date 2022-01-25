package com.company;

import java.util.LinkedList;

import java.util.Scanner;

import static com.company.MazeSolver.searchPathDFS;
import static com.company.ShowMaze.copyMaze;
//Writen by Patrick Cassidy
//Code explantion can be found on the MazeSolver.java file
//Change mazes by editing the 2D array and by following the conventions.
//Explantion of algorithms (not code) from https://en.wikipedia.org/wiki/Maze-solving_algorithm
//Zybooks Section 9.6 on DFS and graph traversal algorithms

public class Driver {
    public static void main(String[] args) {

        /**
         * Conventions:
         *
         * maze[row][col]
         *
         * Values: 0 = not-visited node
         *         1 = wall (blocked)
         *         2 = visited node
         *         9 = target node
         */
        int[][] maze =
                {       {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1},
                        {1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1},
                        {1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1},
                        {1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1},
                        {1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1},
                        {1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1},
                        {1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 9, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
                };
        int[][] maze1 =
                {       {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 1},
                        {1, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 1},
                        {1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1},
                        {1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1},
                        {1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1},
                        {1, 0, 1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1},
                        {1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 9, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
                };
        int[][] maze2 =
                {       {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
                        {1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1},
                        {1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1},
                        {1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1},
                        {1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1},
                        {1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1},
                        {1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1},
                        {1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 1, 1},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 9, 1},
                        {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
                };
        int cont = 0;
        int size = 0;

        Scanner input = new Scanner(System.in);
        LinkedList<Integer> path = new LinkedList<>();

        System.out.println("Welcome To the Maze Solver, Please Select a Maze ");
        System.out.println("MAZE 1 = 1, MAZE 2 = 2, Maze 3 = 3 ");

        //All Example Mazes are 13,13 by Default, If different Change these values
        int [][] copy = new int[13][13];
        cont = input.nextInt();

        if(cont == 1) {
            copyMaze(maze1,copy);
        }
        if(cont == 2) {
            copyMaze(maze2,copy);
        }
        if(cont == 3) {
            copyMaze(maze,copy);
        }

        System.out.println("0 INDICATES THAT AREA HAS BEEN SEARCHED");
        System.out.println("BEFORE");
        ShowMaze.printMaze(copy);
        boolean solved;

        //In all Example graphs the starting Cordinates are 1,1 Change if using different convention
        solved = searchPathDFS(copy, 1, 1, path);
        System.out.println("AFTER: MAZE HAS BEEN SOLVED");
        ShowMaze.printMaze(copy);


    }
}
