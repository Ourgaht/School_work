import jbotsim.Color;
import jbotsim.Message;
import jbotsim.Node;
import jbotsim.Point;
import java.util.Random;
import java.util.List;

public class GeoNode extends Node {
    @Override
    public void onMessage(Message message) {
        Bundle bundle = (Bundle)message.getContent();
        if (bundle.target.equals(getLocation())) {
            System.out.println("Message reçu : " + bundle.text);
        }else{
            route(bundle);
        }
    }
    public void route(Bundle bundle){
        setColor(Color.red);
        Node src = this;
        List<Node> list = src.getNeighbors();
        list.add(this); //Pour un algo bien glouton
        Node closestPoint = list.get(0);
        for(int i = 1; i < list.size(); i++){
            if(list.get(i).distance(bundle.target) < closestPoint.distance(bundle.target))
                closestPoint = list.get(i);
        }
        if(closestPoint == src){
            //route aléatoire
            double distSrc = src.distance(bundle.target);
            List<Node> list2 = src.getNeighbors();
            Random rand = new Random();
            Node nodePif = list2.get(rand.nextInt(list2.size()));
            while(distSrc < nodePif.distance(bundle.target)){
                int r = rand.nextInt(nodePif.getNeighbors().size());
                nodePif.send(nodePif.getNeighbors().get(r), bundle);
                nodePif = nodePif.getNeighbors().get(r);

            }
            closestPoint = nodePif;
        }
        src.send(closestPoint, bundle);
    }
}