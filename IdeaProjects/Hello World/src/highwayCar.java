import jbotsim.Node;

public class highwayCar extends Node{
    double speed = 3 + Math.random(); // Random speed between 3 and 5

    public highwayCar(){
        setDirection(0); // Eastward
        setIcon("/car.png");
        setSize(10);
    }
    public void onClock() {
        move(speed);
        wrapLocation();
    }
}