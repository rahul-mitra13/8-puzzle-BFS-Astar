/**
 * Driver program for Breadth first Search implementation to solve the 8 puzzle
 * @author Rahul Mitra
 * @see ArrayList
 * @see Scanner 
 */
import java.util.Scanner;
import java.util.ArrayList;
 public class breadthfirstSearch {
  public static void main(String args[]){
    Scanner sc = new Scanner(System.in);
    State initialState = new State();
    System.out.println("Select how you want to enter the initial configuration: ");
    System.out.println("Enter 1 to enter some configuration or enter 2 randomize it. Enter choice: ");
    int choice = sc.nextInt();
    if ( choice == 1){
    initialState.populate();
    }
    else if ( choice == 2){
      initialState.populateRandom();
    }
    System.out.println();
    System.out.println("Finding the solution: ");
    final long startTime = System.currentTimeMillis();
    ArrayList<Integer> solutionBFS = initialState.BFS();
    final long endTime = System.currentTimeMillis();
    System.out.println("The inital state was: ");
    System.out.println();
    initialState.print();
    System.out.println();
    if ( initialState.countInversions() % 2 == 0){
      System.out.println("The parity is zero.");
    }
    else{
      System.out.println("The parity is one.");
    }
    System.out.println("The goal state is: ");
    System.out.println();
    State goal = initialState.makeGoalState();
    goal.print();
    System.out.println();
    int numMoves = solutionBFS.size();
    System.out.println("The total number of moves required is: "+numMoves);
    System.out.println("The solution path using BFS is: "+solutionBFS);
    System.out.println("Total execution time of BFS is: " + (endTime - startTime) + "ms");
    System.out.println();
    if ( numMoves < 3){
      System.out.println("The required moves are: ");
      System.out.println();
      for ( int i = 0; i < solutionBFS.size(); i++){
        initialState.movePiece(solutionBFS.get(i));
        initialState.print();
      }
    }
    else{
    System.out.println("The first 3 moves are: ");
    System.out.println();
    for ( int i = 0; i < 3; i++){
      initialState.movePiece(solutionBFS.get(i));
      initialState.print();
    }
  }
    sc.close();
  }
}
