import jbotsim.Link;
import jbotsim.Node;
import jbotsim.event.TopologyListener;

public class highwayServer extends Node implements TopologyListener{
    public highwayServer(){
        setIcon("/server.png");
        setSize(10);
        disableWireless();
    }
    public void onStart(){
        for (Node node : getTopology().getNodes())
            if (node.getClass()==highwayTower.class)
                getTopology().addLink(new Link(this, node));
        getTopology().addTopologyListener(this);
    }
    public void onNodeAdded(Node node) {
        if (node.getClass()==highwayTower.class)
            getTopology().addLink(new Link(this, node));
    }
    public void onNodeRemoved(Node node) {
    }
}