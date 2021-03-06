import jbotsim.Point;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.ThreadLocalRandom;

public class Algorithm {

    protected List<Point> points;

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    //Je ne trouve pas la méthode distance, ni la méthode qui met en puissance
    private double distance(Point p1, Point p2) {
        return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
    }

    public List<Point> nearestNeighbour() {
        List<Point> newPoints = new ArrayList<>(points);
        List<Point> path = new ArrayList<>();
        path.add(newPoints.remove(ThreadLocalRandom.current().nextInt(0, newPoints.size()))); //We chose a random one
        while (!newPoints.isEmpty()) {
            if (newPoints.size() == 1)
                path.add(newPoints.remove(0));
            else {
                int j = 0;
                for (int i = 0; i < newPoints.size(); i++) {
                    if (distance(path.get(path.size() - 1), newPoints.get(i)) < distance(newPoints.get(j), path.get(path.size() - 1)))
                        j = i;
                }
                path.add(newPoints.remove(j));
            }
        }
        return path;
    }

    public List<Point> getItinerary() {
        return nearestNeighbour();
    }


    public double totalLenght(List<Point> p) { //Not used right now, don't know how to call it with the list of points
        Algorithm algorithm = new Algorithm();
        algorithm.setPoints(points);
        double S = 0;
        for (int i = 0; i < p.size() - 1; i++) {
            S = S + algorithm.distance(p.get(i), p.get(i + 1));
        }
        return S;
    }
}