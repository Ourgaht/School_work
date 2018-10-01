import jbotsim.Node;
import jbotsim.Color;
import jbotsim.Message;

public class lonerMessageBased extends Node {
    boolean mayBeAlone = true;
    @Override
    public void onClock(){
        move();
        wrapLocation();
        setColor(mayBeAlone ? Color.green : Color.red);
        sendAll(new Message());
        mayBeAlone = true;
    }
    @Override
    public void onMessage(Message msg) {
        mayBeAlone = false;
    }
    @Override
    public void onStart(){
        setDirection(Math.random()*2*Math.PI);
    }
}
