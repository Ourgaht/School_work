import jbotsim.Node;
import jbotsim.Point;

import java.util.LinkedList;
import java.util.Queue;

public class waypointNode extends Node {
    private Queue<Point> destinations = new LinkedList<Point>();
    private double speed = 1; // Number of units to be moved in each step.

    void addDestination(Point x){
        destinations.add(x);
    }

    void addDestination2(double x, double y){
        addDestination(new Point(x, y));
    }

    void setSpeed(double x){
        speed = x;
    }

    @Override
    public void onClock() {
        super.onClock();
        if (!destinations.isEmpty()) {
            if(getLocation().distance(destinations.peek()) > speed){
                setDirection(destinations.peek());
                move(speed);
            }
            else
                setLocation(destinations.remove());
        }

    }
    public static void main(String[] args){

    }
}
