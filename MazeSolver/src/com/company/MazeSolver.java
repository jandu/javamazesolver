package com.company;


import java.util.LinkedList;
import java.util.List;

public class MazeSolver {
    /**
     * Background Information:
     * This code implements a Recursive Depth First Search algorithm
     * to find the goal in the maze.
     *
     *To understand how this works let's first talk about a normal DFS traveral
     * on a graph, and then apply it
     *
     *Let's say our starting Vertex is A
     *
     *  (A)------(B)------(Y)-----(E)
     *   |        |
     *   |        |
     *  (X)      (G)------(C)
     *            |
     *            |
     *           (T)
     * /
     *Now, the first thing this algoritm will do is visit One of the adjecent Node, A or B
     * in this case it doesnt really matter, as it will depend on the specfics of the algorithm
     *Regardless, Whatever node we end up choosing will continue to follow it's path visting every
     * vertex until we have to reach point where are there no neighboring nodes, then we backtrack
     * and repeat the process again, making sure to keep track of nodes we visted using a stack
     * Let's call this stack visitednodes:
     *
     *Assuming the next Node is B,  We can now select either G or Y, as Both are Valid DFS Traversals
     *
     * Selecting G, Our vistednodes stack now looks like this, A,B,G,
     *
     * Now, There are two valid DFS traversals C and T, Selecting T gets us to dead end.
     * A,B,G,T We now have to backtrack to the nearest node and see if there are any unvisited nodes.
     * Here, we go back to G, and now we see that there's a unvisted node C
     * A,B,G,T,C
     * Of course, C is also a Dead End so now we have to backtrack to G, we already visted G, so we backtrack again
     * Finally, reaching B we have unvisted nodes, Y,E
     *
     * After visting these nodes, we backtrack yet again, finally back to A,  A has one unvisted node
     * X
     * With this, we have traversed the entire graph
     *
     *The questions that aries in our context, is 1, How do we implement this in code, 2 What needs to be changed
     * so we can use this to solve mazes.
     *
     * Let's start the first obvious difference.  A maze requires us to find A single Node instead of all of them.
     * Hence, we do not need to continue searching all the nodes after we find a valid path from start to finish, that
     * is unless we care about finding the shortest possible path.  However, for this you would need a more complex
     * algorithm than what we are going to use here.
     *
     * Next, let's think about the maze. There are going to walls in the maze where cannot traverse.  Thus, we need
     * a way of keeping track of which nodes are traversable and which are not. Moreover, we need to have a way to
     * represent the maze.
     *
     * Let's make a 2d int array called int maze[][]
     *      int[][] maze =
     *                 {       {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
     *                         {1, 0, 1, 0, 1, 0, 1, 0, 0, 0, 0, 0, 1},
     *                         {1, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 0, 1},
     *                         {1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 0, 1},
     *                         {1, 0, 1, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1},
     *                         {1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 0, 0, 1},
     *                         {1, 0, 1, 0, 1, 0, 0, 0, 1, 1, 1, 0, 1},
     *                         {1, 0, 1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1},
     *                         {1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 9, 1},
     *                         {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
     *                 };
     *We can now represent a maze by using a matrix
     *
     *
     * Conventions:
     *      <p>
     * maze[row][col]
     *     <p>
     * Values: 0 = not-visited node
     *      * 1 = wall (blocked)
     *      * 2 = visited node
     *      * 0 = free node
     *      * 9 = target node
     *      */

    /**
     * Now let's talk about the meat of this program, which is aucutally relativley simple at its core
     * The function, takes in the maze array that we talked about eariler, and it also takes in two x and y
     * coordinates.  This starting location of the maze, or in graph terms the starting Node
     *
     * What we're also going to notice quickly, is that function is acutually recursive, in that if
     * we find a valid node (one that is not a wall, represented by 0) the function will aucutally call itself
     *from inside the function with new x and y parameters. These parameters are shifted by 1,0, -1 depending
     * on what direction we want to go left,right,up, down.
     *
     * In essesnse, though we've left out some details. This is how the algorithm, "moves" through the maze.
     *It does so through essentially brute force. By exhausting all possible options until there are none left
     * That means it's not particularlly effcient, but it will eventually yeild us an answer
     */
    public static boolean searchPathDFS(int[][] maze, int x, int y
            , List<Integer> path) {
        /**
         * Looking through this code, we see that there are alot of if statements followed by
         * path.add(x)
         * path.add(y)
         *
         * What this is doing is adding a valid node to the path,
         * Which only happens if the element is valid,
         * We do this so that we can have a list of coordinates as the full path
         * when we are completed.
         *
         * In some implementations, the stack tells us which nodes we've visted so
         * that we don't visit them again.  However, in our case we can just change values in the
         * matrix to represent visited nodes
         * How this works will make more sense as we continue
         * on
         *
         */
        //Checks to see if the element is the goal point, if so mazed is solved
        if (maze[y][x] == 9) {
            path.add(x);
            path.add(y);
            return true;
        }

        //Checks to see if the element is a free space
        if (maze[y][x] == 0) {
            //Changes element to 2, indicating that the node has already been visited
            maze[y][x] = 2;
            /**
             * After finding a free node, we now need to check all sourounding nodes to see which ones
             * are a free space and which ones are not.  Now notice that there is no if statement which says
             * if maze[y][x] = 1  Because it has to cycle through all of these function calls before it
             * can even get to that point.
             *
             * Indeed, if we go back to our discussion about depth first search,  this how we implement backtracking
             * because if hit a dead end, the function will auctmaticlly break off the function call.
             */

            //Move Direction iterator X (LEFT -1/RIGHT+1)
            //                        Y (UP +1, Down -1)
            int dx = -1;
            int dy = 0;
            //1
            if (searchPathDFS(maze, x + dx, y + dy, path)) {
                path.add(x);
                path.add(y);



                return true;
            }

            dx = 1;
            dy = 0;
            //2
            if (searchPathDFS(maze, x + dx, y + dy, path)) {
                path.add(x);
                path.add(y);



                return true;
            }

            dx = 0;
            dy = -1;
            //3
            if (searchPathDFS(maze, x + dx, y + dy, path)) {
                path.add(x);
                path.add(y);


                return true;
            }

            dx = 0;
            dy = 1;
            //4
            if (searchPathDFS(maze, x + dx, y + dy, path)) {
                path.add(x);
                path.add(y);


                return true;
            }
        }
        return false;
    }



}






