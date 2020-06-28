/**
 * Simple priority queue type data structure used to implement the A*h1 and A*h2 algorithms
 * @author Rahul Mitra
 * @see ArrayList
 */
import java.util.ArrayList;
public class myQueue{
    //Instance variables
    ArrayList<myQueue> pq;//Queue to simulate a priority queue
    int distance;//distance variable that's pushed into the queue along with state
    State state;//state object that's pushed into the queue along with the distance
    /**
     * None parameterized constructor to initialize a myQueue object
     * @param none
     * @return none
     */
    public myQueue(){
        pq  = new ArrayList<myQueue>();
    }
    /**
     * Parameterized constructor to initialize a myQueue object
     * @param newState - state that's passed into the myQueue object
     * @param newDistance - distance that's passed into the myQueue object
     */
    public myQueue(State newState, int newDistance){
        pq = new ArrayList<myQueue>();
        this.state = newState;
        this.distance = newDistance;
    }
    /**
     * Return the size of the queue
     * @param none
     * @return none
     */
    public int size(){
        return pq.size();
    }
    /**
     * Pushing a new entry into the priority queue
     * @param state - state object that's pushed into the priority queue
     * @param d - distance variable that's pushed into the priority queue
     * @return none
     */
    public void add(State state, int d){
        pq.add(new myQueue(state, d));
    }
    /**
     * Checking if the priority queue contains a particular state
     * @param testState - the state that is tested for presence in the priority queue
     * @return boolen - true if the queue contains the passed State, false otherwise
     */
    public boolean contains(State testState){
        for ( int i = 0; i < pq.size(); i++){
            if ((pq.get(i).state).equals(testState)){
                return true;
            }
        }
        return false;
    }
    /**
     * Returns the state with smallest distance to the goal state from the priority queue
     * @param none
     * @return the state with the smallest distance is popped from the priority queue
     */
    public State pop(){
        //Calculating the minimum distance in the queue
        int min = Integer.MAX_VALUE;
        int position = 0;
        for (int i = 0 ; i < pq.size(); i++){
            if ( pq.get(i).distance < min){
                min = pq.get(i).distance;
                position = i;
            }
        }
        return (pq.remove(position).state);
    }
}

    