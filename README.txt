
Project Description: This project uses 3 different techniques, Bread-First Search, Astarh1 and Astarh2 , to solve an 8-puzzle for any given initial configuration of the board.The project consists of a State class, a class in which a priority queue is defined and three tester programs for BFS, Astarh1 and Astarh2 respectively.

Dependencies: The following commands can be used to compile these programs on a Linux OS. 

Compilation: a) State.java class - This is a class that represents the current configuration of the puzzle board. To compile this class run the following terminal command: "javac State.java"

	     b) myQueue.java class - This is a class which was used to mimic a priority queue and was used in the implementation of Astarh1 and Astarh2. To compile this class run the following terminal 				     command: "javac myQueue.java" 

	     c) breadthfirstSearch.java class - This is a class which is essentially a tester program for my implementation of breadth-first search. To compile this class run the following terminal 							commands: "javac breadthfirstSearch.java" followed by "java breadthfirstSearch"

	     d) Astarh1.java class - This is a class which is essentially a tester program for my implementation of Astarh1. To compile this class run the following terminal commands: "javac Astarh1.java" 				     followed by "java Astarh1"

	     e) Astarh2.java class - This is a class which is essentially a tester program for my implementation of Astarh2.To compile this class run the following terminal commands: "javac Astarh2.java" 				     followed by "java Astarh2"

Input format: The user will be prompted to either manually choose(enter 1 when prompted) an initial configuration or randomize(enter 2 when prompted) the configuration. If the user chooses to manually enter a configuration, the user is expected to enter a series of numbers separated by a single space which represents the initial configuration of the puzzle in row major order. Any other input format will cause an error.After the last number is entered, the user should hit the "enter" button to run the tester program. 

Output: The output of the tester programs breadthfirstSearch.java, Astarh1.java and Astarh2.java are as follows - the program tells the user the number of search tree nodes explored, the initial state of the puzzle board, the parity of the puzzle board, the goal state, the amount of time it took the algorithm to find a solution in milliseconds, the number of moves to the solution, the first three moves that each respective algorithm takes, and the solution path - basically a list that represents the pieces that need to moved to achieve the goal state. 

Additional Information: Both Astarh1 and Astarh2 find a solution relatively fast for any initial configuration of the puzzle - usually with 30 seconds though for certain initial configurations (usually computer generated random configurations), Astarh1 and even Astarh2 heavily slow down. BFS is a lot slower than both of these. For certain configurations, BFS takes far too long to find a solution. Actual test cases were run where the algorithm took up to 10 minutes to solve the puzzle. 

