import jbotsim.Message;
import jbotsim.Node;

public class basicNode extends Node {
    @Override
    public void onStart() {
        // initialize the node variables
    }

    @Override
    public void onClock() {
        // code to be executed by this node in each round
    }

    @Override
    public void onMessage(Message message) {
        // processing of a received message
    }

    @Override
    public void onSelection() {
        // what to do when this node is selected by the user
    }
}
