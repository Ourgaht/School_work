import jbotsim.Color;
import jbotsim.Link;
import jbotsim.Node;
import jbotsim.Topology;
import jbotsim.event.SelectionListener;
import jbotsim.event.StartListener;
import jbotsimx.ui.JViewer;

public class Main implements SelectionListener, StartListener {
    Topology tp;
    GeoNode sourceNode;
    GeoNode targetNode;

    public Main() {
        tp = new Topology();
        tp.setClockSpeed(100); // slow down for visualization
        tp.setDefaultNodeModel(GeoNode.class);
        tp.addSelectionListener(this);
        tp.addStartListener(this);
        new JViewer(tp);
        tp.start();
    }

    @Override
    public void onSelection(Node node) {
        GeoNode selectedNode = (GeoNode) node;
        if (sourceNode == null) {
            sourceNode = selectedNode;
            sourceNode.setColor(Color.red);
        } else if (targetNode == null){
            targetNode = selectedNode;
            targetNode.setIcon("/flag.png"); // To be adapted
            targetNode.setSize(14);
            sourceNode.route(new Bundle(targetNode.getLocation(), "HELLO", 0));
        }
    }
    @Override
    public void onStart() {
        if (targetNode != null) {
            targetNode.setIcon("/jbotsimx/ui/circle.png");
            targetNode.setSize(8);
            targetNode = null;
        }
        sourceNode = null;
        for (Node node : tp.getNodes())
            node.setColor(null);
        for (Link link : tp.getLinks())
            link.setWidth(1);
    }

    public static void main(String[] args) {
        new Main();
    }
}