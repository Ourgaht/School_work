import jbotsim.Node;

public class movingNode extends Node {
    @Override
    public void onStart(){
        setDirection(Math.random()*2*Math.PI);
    }
    @Override
    public void onClock(){
        move();
        wrapLocation();
    }
}
