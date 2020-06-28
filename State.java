/**
 * This is a state object that represents each configuration of the 8 puzzle.
 * Project 1 
 * @author Rahul Mitra
 *
 * @see Scanner
 * @see ArrayList
 * @see Collections
 * 
 * version 1.3
 */
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
public class State{
    //instance variables
    int row = 3;//row size of board
    int col = 3;//column size of board
    int [][]arr;//2-D array to represent the board
    int last = 0;//the last pieace to move
    ArrayList<Integer> pathTraversed = new ArrayList<Integer>();//array list to store the path traversed
    /**
     * Non-parameterized contructor to initialize the state object
     * 
     */
    public State(){
        arr = new int[3][3];
    }
    /**
     * To populate the board with user input
     * @param none
     * @return none
     */
    public void populate(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the list of integers separted by a space in row major order: ");
        String input = sc.nextLine();
        int k = 0;
        for ( int i = 0; i < this.row; i ++){
            for ( int j = 0; j < this.col; j++){
                this.arr[i][j] = Character.getNumericValue(input.charAt(k));
                k+=2;
            }
        }
        sc.close();

    }
    /**
     * To randomly populate the board
     * @param none
     * @return none
     */
    public void populateRandom(){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for ( int i = 0; i < 9 ; i++){
            list.add(i);
        }
        Collections.shuffle(list);//shuffling the list
        int k = 0;
        for ( int i = 0; i < this.row; i++){
            for ( int j = 0; j < this.col ; j++){
            this.arr[i][j] = list.get(k);
            k++;
            }
        }   
    }
    /**
     * To count the number of inversions in the board
     * @param none
     * @return int - number of intversions in the board
     */
    public int countInversions(){
        ArrayList<Integer> list = new ArrayList<Integer>();//array list to store the elements of the matrix in row major form
        int ctr = 0; //to store the state in row major form 
        for ( int i = 0; i < this.arr.length; i++){
            for ( int j = 0; j < this.arr.length; j++){
                if ( this.arr[i][j] != 0){
                list.add(arr[i][j]);
                }
            }
        }
        //calculating the number of inversions
        for ( int i = 0; i < list.size() - 1 ; i++){
            for ( int j = i + 1; j < list.size(); j++){
                    if ( list.get(i) > list.get(j)){
                    ctr++;
                    }
                
                }
        }
        return ctr;
    }
    /**
     * To make the goal state based on parity and the number of inversions
     * @return State - a particular goal state based on the number of inversions in the board
     */
    public State makeGoalState(){
    State goal = new State();
    int parityZeroList[][] = {{ 1, 2, 3}, { 4, 5, 6}, {7, 8, 0}};
    int parityOneList[][] = {{ 1, 2, 3}, {8, 0, 4}, {7, 6, 5}};
    if (this.countInversions() % 2 == 0){
        for ( int i = 0; i < this.row; i ++){
            for ( int j = 0; j < this.col ; j++){
                goal.arr[i][j] = parityZeroList[i][j];
            }
        }
    }
    else{
        for ( int i = 0; i < this.row; i ++){
            for ( int j = 0; j < this.col ; j++){
                goal.arr[i][j] = parityOneList[i][j];
                }
            }
        }
        return goal;
    }
    /**
     * To get the position of the blank in the board
     * @return - Integer list which stores the row number of the blank 
     * in the first index and the column number of the blank
     * in the second index
     */
    public ArrayList<Integer> getBlankPosition() {
        ArrayList<Integer> position = new ArrayList<Integer>();
        for ( int i = 0; i < this.row; i++){
            for ( int j = 0; j < this.col ; j++){
                if ( arr[i][j] == 0){
                     position.add(i);
                     position.add(j);
                     return position;

                }
            }
        }
        return position;
    }
    /**
     * To swap two tiles in the board
     * @param i,j - int row and column positions to be swapped with x and y
     * @param x,y - int row and column positions to be swapped with i and j
     * @return none
     */
    public void swap( int i, int j, int x, int y){
        int temp = this.arr[i][j];
        this.arr[i][j] = this.arr[x][y];
        this.arr[x][y] = temp;
    }
    /**
     * To get the direction a piece can move
     * @param currentPiece - int represents a number on the board
     * @return String - represents the direction in which the passed number can move
     */
    public String getMove(int currentPiece){
        ArrayList<Integer> list = new ArrayList<Integer>();//to get the position of the blank
        list = this.getBlankPosition();
        int blankRowNum = list.get(0);
        int blankColNum = list.get(1);
        if ( blankRowNum > 0 && currentPiece == this.arr[blankRowNum - 1][blankColNum]){
            return "DOWN";
        }
        else if ( blankRowNum < this.row - 1 && currentPiece == this.arr[blankRowNum + 1][blankColNum]){
            return "UP";
        }
        else if ( blankColNum > 0 && currentPiece == this.arr[blankRowNum][blankColNum - 1]){
            return "RIGHT";
        }
        else if ( blankColNum < this.col - 1 && currentPiece == this.arr[blankRowNum][blankColNum + 1]){
            return "LEFT";
        }
        
        return null; // return null if the piece can't move
    }
    /**
     * To move the current piece
     * @param current - the piece that is to be moved
     * @return String - the direction in which the piece was moved
     */
    public String movePiece(int current){
        String direction = this.getMove(current);//gets the direction to move
        if ( direction != null){
            ArrayList<Integer> list = new ArrayList<Integer>();//to get the position of the blank
            list = this.getBlankPosition();
            int blankRowNum = list.get(0);
            int blankColNum = list.get(1);
            switch(direction){
                case "LEFT":
                    this.swap(blankRowNum, blankColNum, blankRowNum, blankColNum + 1);//move the piece right or move the zero the left
                    break;
                case "RIGHT":
                    this.swap(blankRowNum, blankColNum, blankRowNum, blankColNum - 1);
                    break;
                case "UP":
                    this.swap(blankRowNum, blankColNum, blankRowNum + 1, blankColNum);
                    break;
                case "DOWN":
                    this.swap(blankRowNum, blankColNum, blankRowNum - 1, blankColNum);
                    break;
                }
                if( direction != null){
                last = current;
                }
        }
        return direction;
    }
    /**
     * To check if a goal state has been reached
     * @param none
     * @return boolean - returns true if the State is the goal state or false otherwise
     */
    public boolean isGoalState(){
        //zero parity goal state
        if (this.countInversions()%2 == 0){
        int goal[][] = { {1,2,3} , {4,5,6} , {7,8,0}};//zero parity goal state 
        for ( int i = 0; i < this.row; i++){
            for ( int j = 0; j < this.col ; j++){
                if ( this.arr[i][j] != goal[i][j]){
                    return false;
                }
            }
        }
        return true;
    }
    //one parity goal state
    else{
        int goal[][] = { {1, 2, 3}, {8, 0, 4}, {7, 6, 5}};
        for ( int i = 0; i < this.row; i++){
            for ( int j = 0; j < this.col; j++){
                if (this.arr[i][j] != goal[i][j]){
                    return false;
                }
            }
        }
        return true;
        }
    }
    /**
     * To get a set of the all the possible move from a particular configuration
     * @param none
     * @return Integer list - represents all the pieces that can be moved in the current configuration
     */
    public ArrayList<Integer> getMovesSet(){
        ArrayList<Integer> movesSet = new ArrayList<Integer>();
        for ( int i = 0; i < this.row; i ++){
            for ( int j = 0; j < this.col ; j++){
                int curr = this.arr[i][j];//stores current piece
                if ( this.getMove(curr) != null){
                    movesSet.add(curr);//puts the moves the current piece can make into the array list
                }
             }
        }
        return movesSet;
    }
    /**
     * To make a copy of the current state
     * @param none
     * @return State - a copy of the current state
     */
    public State makeCopy(){
        State copy = new State();
        for ( int i = 0; i < this.row; i ++){
            for ( int j = 0; j < this.col ; j++){
                copy.arr[i][j] = this.arr[i][j];
            }
        }
        for ( int i = 0; i < pathTraversed.size(); i++){
            copy.pathTraversed.add(this.pathTraversed.get(i));//pathTraversed stores the pieces that have been moved so far
        }
        return copy;
    }
    /**
     * To return a list of all the states that can be vistied
     * Effectively returning the children of some initial state
     * @param none
     * @return State list - list of states that can be visited from the current state
     */
    public ArrayList<State> visitStates(){
        ArrayList<State> children = new ArrayList<State>();//gets all the children of the state
        ArrayList<Integer> piecesToMove = this.getMovesSet();
        for ( int i = 0; i < piecesToMove.size(); i++){
            int currentPiece = piecesToMove.get(i);
            if ( currentPiece != this.last){//making sure we don't go back to a previous state
                State nextState = this.makeCopy();
                nextState.movePiece(currentPiece);//moving the current piece
                nextState.pathTraversed.add(currentPiece);//adding the piece that was moved to the path traversed list
                children.add(nextState);//adding to the children list
            }
        }
        return children;
    }
    /**
     * Breadth first search method
     * To find the moves to the goal state using Breeadth first search algorithm
     * @param none
     * @return Integer list representing the pieces that need to be moved to reach 
     * the respective goal state
     */
    public ArrayList<Integer> BFS(){
        State startState = this.makeCopy();//starting state
        if ( startState.isGoalState()){
            System.out.println();
            System.out.println("The initial state is the goal state.Total number of search tree nodes explored is 0.");//if the start state is the goal state
            return startState.pathTraversed;
        }
        ArrayList<State> states = new ArrayList<State>();//frontier FIFO queue
        ArrayList<State> explored = new ArrayList<State>();//explored set
        states.add(startState);
        while(states.size() > 0){
            //simulating queue with an array list
            if ( states.isEmpty()){
                return null;//fail
            }
            State state = states.get(0);//popping the first element of the queue
            states.remove(0);
            explored.add(state);//adding it to the end of the queue
            ArrayList<State> children = state.visitStates();
            for ( int i = 0; i < children.size(); i++){
                State child = children.get(i);
                if ( !explored.contains(child) && !states.contains(child)){
                    if ( child.isGoalState()){
                        System.out.println();
                        System.out.println("Solution Found!");
                        System.out.println();
                        System.out.println("The total number of search tree nodes explored is: "+explored.size());
                        return child.pathTraversed;
                    }
                    states.add(child);
                }
            }
        
        }
        ArrayList<Integer> emptyList = new ArrayList<Integer>();//returning an empty list so that no error is caused
        return emptyList;
    }
    /**
     * A* using the h1 heuristic method
     * This method uses A*h1 to find the moves needed to reach the goal state
     * @param none
     * @return Integer list - represents the pieces that need to be moved to reach the respective goal state
     */
    public ArrayList<Integer> Astarh1(){
        myQueue priorityq = new myQueue();//frontier
        State firstState = this.makeCopy();
        ArrayList<State> explored = new ArrayList<State>();//explored set
        priorityq.add(firstState, 0);
        while(priorityq.size()>0){
            State current = priorityq.pop();
            if ( current.isGoalState()){
                System.out.println();
                System.out.println("Solution Found!");
                System.out.println();
                System.out.println("The number of search tree nodes explored is: "+explored.size());
                return current.pathTraversed;
                
            }
            explored.add(current);
            ArrayList<State> children = current.visitStates();
            for ( int i = 0; i < children.size();i++){
                State currentChild = children.get(i);
                if ( !priorityq.contains(currentChild) && !explored.contains(currentChild)){
                    int f = currentChild.g() + currentChild.h1();//this is where the code differs frpm A* h1
                    priorityq.add(currentChild, f);
                }
            }
        }
        ArrayList<Integer> emptyList = new ArrayList<Integer>();//returning an empty list so that no error is caused
        return emptyList;
    }
    /**
     * A* using the h2 heuristic method
     * This method uses A*h2 to find the moves needed to reach the goal state
     * @param none
     * @return Integer list - represents the pieces that need to be moved to reach the respective goal state
     */
    public ArrayList<Integer> Astarh2(){
        myQueue priorityq = new myQueue(); //frontier
        State firstState = this.makeCopy();
        priorityq.add(firstState, 0);
        ArrayList<State> explored = new ArrayList<State>();//explored set
        while(priorityq.size()>0){
            State current = priorityq.pop();
            if ( current.isGoalState()){
                System.out.println();
                System.out.println("Solution Found!");
                System.out.println();
                System.out.println("The number of search tree nodes explored is: "+explored.size());
                return current.pathTraversed;
                
            }
            explored.add(current);
            ArrayList<State> children = current.visitStates();
            for ( int i = 0; i < children.size();i++){
                State currentChild = children.get(i);
                if ( !priorityq.contains(currentChild) && !explored.contains(currentChild)){
                int f = currentChild.g() + currentChild.h2();//this is where the code differs frpm A* h1
                priorityq.add(currentChild, f);
                }
            }
        }
        ArrayList<Integer> emptyList = new ArrayList<Integer>();//returning an empty list so that no error is caused
        return emptyList;
    }
    /**
     * Calculating the path cost of getting to a certain state
     * @param none
     * @return int - path cost of getting to a certain state
     */
    public int g(){
        return this.pathTraversed.size();
    }
    /**
     * Calcualting the h1 heuristic i.e. the number of misplaced tiles
     * @param none
     * @return int - representing the number of misplaced tiles
     */
    public int h1(){
        int count = 0;
        int inv = this.countInversions();
        if ( inv % 2 == 0){
            int goal[][] = { {1,2,3} , {4,5,6} , {7,8,0}};//zero parity goal state
            for ( int i = 0; i < this.row; i++){
                for ( int j = 0; j < this.col; j++){
                    if ( this.arr[i][j] != 0){
                        if ( this.arr[i][j]!=goal[i][j]){
                            count++;
                        }
                    }

                }
            }
        }
            else{
            int goal[][] = { {1, 2, 3}, {8, 0, 4}, {7, 6, 5}};//one parity goal state
            for ( int i = 0; i < this.row; i++){
                for ( int j = 0; j < this.col; j++){
                    if ( this.arr[i][j] != 0){
                        if ( this.arr[i][j]!=goal[i][j]){
                            count++;
                        }
                    }

                }

            }
        }
        return count;
        
    }
    /**
     * Calcualting the h2 heuristic i.e. the sum of the manhattan distance for all the tiles
     * @param none
     * @return int - representing the sum of the manhattan distance
     */
    public int h2(){
        int distance = 0;
        int inv = this.countInversions();
        if ( inv % 2 == 0){
            int goal[][] = { {1,2,3} , {4,5,6} , {7,8,0}};//zero parity goal state
            for ( int i = 0; i < this.row; i++){
                for ( int j = 0; j < this.col; j++){
                    if ( this.arr[i][j] != 0 && goal[i][j] != 0){
                       distance += Math.abs(this.IndexOfx(goal[i][j]) - this.IndexOfx(this.arr[i][j])) + Math.abs(this.IndexOfy(goal[i][j]) - this.IndexOfy(this.arr[i][j]));

                    }

                }
            }
        }
            else{
            int goal[][] = { {1, 2, 3}, {8, 0, 4}, {7, 6, 5}};//one parity goal state
            for ( int i = 0; i < this.row; i++){
                for ( int j = 0; j < this.col; j++){
                    if ( this.arr[i][j] != 0 && goal[i][j] != 0){
                        distance += Math.abs(this.IndexOfx(goal[i][j]) - this.IndexOfx(this.arr[i][j])) + Math.abs(this.IndexOfy(goal[i][j]) - this.IndexOfy(this.arr[i][j]));
                    }

                }

            }
        }
        //System.out.println("h2 value is: "+distance);
        return distance;

    }
    //following are two auxilliary methods that were implemented to calculate the manhattan distance
    /**
     * To return the row index for a certain number in the board
     * @param x - the number whose row index is being evaluated
     * @return int - row index of the passed number
     */
    public int IndexOfx(int x){
        for ( int i = 0 ; i < 3; i++){
            for ( int j = 0; j < 3; j++){
                if ( this.arr[i][j] == x){
                    return i;
                }
            }
        }
        return -1;

    }
    /**
     * To return the column index for a certain number in the board
     * @param y - the number whose column index is being evaluated
     * @return int - column index of the passed number
     */
    public int IndexOfy(int y){
        for ( int i = 0 ; i < 3; i++){
            for ( int j = 0; j < 3; j++){
                if ( this.arr[i][j] == y){
                    return j;
                }
            }
        }
        return -1;

    }
    /**
     * To print a certain state in an orderly manner
     * @param none
     * @return none
     */
    public void print(){
            for ( int i = 0; i < this.row; i++){
                for ( int j = 0 ; j < this.col ; j++){
                    System.out.print(this.arr[i][j] + " ");
                }
            System.out.println();
        }
        System.out.println();
    }
}