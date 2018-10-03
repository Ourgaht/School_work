import jbotsim.Node;
import jbotsim.Point;
import jbotsim.Topology;
import jbotsimx.ui.CommandListener;
import jbotsimx.ui.JTopology;
import jbotsimx.ui.JViewer;
import jbotsimx.ui.painting.BackgroundPainter;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Controller implements CommandListener, BackgroundPainter {

    public static final String COMMAND_COMPUTE_TSP = "Compute TSP";

    private Topology topology;
    private JTopology jTopology;

    private List<Point> points = new ArrayList<>();

    public Controller() {
        topology = new Topology();
        topology.setDefaultNodeModel(Target.class);

        jTopology = new JTopology(topology);
        jTopology.addCommand(COMMAND_COMPUTE_TSP);
        jTopology.addCommandListener(this);
        jTopology.addBackgroundPainter(this);

        new JViewer(jTopology);
    }

    @Override
    public void onCommand(String command) {
        if (command.equals(COMMAND_COMPUTE_TSP)) {
            points.clear();
            for (Node node : topology.getNodes())
                if (node instanceof Target)
                    points.add(node.getLocation());

            // TODO: call TSP algorithms here
            // We will later replace this line by a proper algorithm call
            List<Point> itinerary = points;

            points = itinerary; // Make sure that the itinerary will be drawn

            assignToNode(itinerary);
        }
    }

    @Override
    public void paintBackground(Graphics2D g2d, Topology tp) {
        for (int i = 0; i < points.size(); i++) {
            Point from = points.get(i);
            Point to = points.get((i + 1) % points.size());
            g2d.drawLine((int) from.getX(), (int) from.getY(),
                    (int) to.getX(), (int) to.getY());
        }
    }

    protected void assignToNode(List<Point> itinerary) {
        if (!itinerary.isEmpty()) {
            WaypointNode node = new WaypointNode();
            node.setLocation(itinerary.get(0));
            for (int i = 1; i < itinerary.size() + 1; i++)
                node.addDestination(itinerary.get(i % itinerary.size()));
            topology.addNode(node);
        }
    }
}