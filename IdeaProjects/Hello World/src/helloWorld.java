import jbotsim.Topology;
import jbotsimx.ui.JViewer;

public class helloWorld {
    public static void main(String[] args) {
        Topology tp = new Topology();
        tp.setNodeModel("car", highwayCar.class);
        tp.setNodeModel("tower", highwayTower.class);
        tp.setNodeModel("server", highwayServer.class);
        new JViewer(tp);
        tp.start();
    }
}