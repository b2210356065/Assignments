import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

class UrbanTransportationApp implements Serializable {
    static final long serialVersionUID = 99L;

    public HyperloopTrainNetwork readHyperloopTrainNetwork(String filename) {
        HyperloopTrainNetwork hyperloopTrainNetwork = new HyperloopTrainNetwork();
        hyperloopTrainNetwork.readInput(filename);
        return hyperloopTrainNetwork;
    }

    /**
     * Function calculate the fastest route from the user's desired starting point to
     * the desired destination point, taking into consideration the hyperloop train
     * network.
     * @return List of RouteDirection instances
     */
    public List<RouteDirection> getFastestRouteDirections(HyperloopTrainNetwork network) {
        List<RouteDirection> routeDirections = new ArrayList<>();
        HashSet<String> visited = new HashSet<>();

        Station startStation = network.startPoint;
        // pq of List<RouteDirection> also cost is double
        // compare the cost of the two lists
        PriorityQueue<List<RouteDirection>> pq = new PriorityQueue<>((a,b)->{
            double costA = 0;
            double costB = 0;
            for(RouteDirection rd : a){
                costA += rd.duration;
            }
            for(RouteDirection rd : b){
                costB += rd.duration;
            }
            return Double.compare(costA,costB);
        });
        List<RouteDirection> start = new ArrayList<>();
        start.add(new RouteDirection(startStation.description,startStation.description,0,false));
        pq.add(start);
        while(!pq.isEmpty()){
            List<RouteDirection> current = pq.poll();
            RouteDirection last = current.get(current.size()-1);
            if(visited.contains(last.endStationName)){
                continue;
            }
            visited.add(last.endStationName);
            if(last.endStationName.equals(network.destinationPoint.description)){
                routeDirections = current;
                break;
            }
            Station currentStation = network.startPoint;
            // find the current station
            for(TrainLine line : network.lines){
                for(Station station : line.trainLineStations){
                    if(station.description.equals(last.endStationName)){
                        currentStation = station;
                        break;
                    }
                }
                if(currentStation != network.startPoint){
                    break;
                }
            }
            for (TrainLine line : network.lines) {
                // if the curr station is in the line take the previous station and the next station
                for (int i = 0; i < line.trainLineStations.size(); i++) {
                    if (line.trainLineStations.get(i).description.equals(last.endStationName)) {
                        if (i > 0) {
                            Station prev = line.trainLineStations.get(i - 1);
                            if (!visited.contains(prev.description)) {
                                List<RouteDirection> next = copy(current);
                                next.add(new RouteDirection(last.endStationName, prev.description, distance(prev, line.trainLineStations.get(i), network.averageTrainSpeed), true));
                                pq.add(next);
                            }
                        }
                        if (i < line.trainLineStations.size() - 1) {
                            Station next = line.trainLineStations.get(i + 1);
                            if (!visited.contains(next.description)) {
                                List<RouteDirection> next2 = copy(current);
                                next2.add(new RouteDirection(last.endStationName, next.description, distance(next, line.trainLineStations.get(i), network.averageTrainSpeed), true));
                                pq.add(next2);
                            }
                        }
                    }
                    // add walking to the next station
                    List<RouteDirection> next3 = copy(current);
                    next3.add(new RouteDirection(last.endStationName, line.trainLineStations.get(i).description, distance(line.trainLineStations.get(i), currentStation, network.averageWalkingSpeed), false));
                    pq.add(next3);
                }
            }
            // add walking to end
            List<RouteDirection> next4 = copy(current);
            next4.add(new RouteDirection(last.endStationName, network.destinationPoint.description, distance(network.destinationPoint, currentStation, network.averageWalkingSpeed), false));
            pq.add(next4);
        }
        // delete first index of the routeDirections
        routeDirections.remove(0);
        return routeDirections;
    }


    public List <RouteDirection> copy(List<RouteDirection> routeDirections){
        List<RouteDirection> k =new ArrayList<>();
        for(int i =0;i<routeDirections.size();i++){
            k.add(routeDirections.get(i));
        }
        return k;
    }
    public double distance(Station s1,Station s2,double speed){
        Point p1=s1.coordinates;
        Point p2=s2.coordinates;
        int x1 = p1.x;
        int y1 = p1.y;
        int x2 = p2.x;
        int y2 = p2.y;
        double distance = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        distance=distance/speed;
        return distance;
    }
    public String formatDouble(double value) {
        return String.format("%.2f", value);
    }
    public int roundDoubleToInt(double value) {
        return (int) Math.round(value);
    }

    /**
     * Function to print the route directions to STDOUT
     */
    public void printRouteDirections(List<RouteDirection> directions) {
        double time=0;
        for(int i=0;i<directions.size();i++){
            time+=directions.get(i).duration;
        }
        System.out.println("The fastest route takes "+roundDoubleToInt(time)+" minute(s).");
        System.out.println("Directions\n" +
                "----------");
        for(int i=0;i<directions.size();i++){
            System.out.print((i+1)+".");
            if(directions.get(i).trainRide){
                System.out.print(" Get on the train from ");
            }else{
                System.out.print(" Walk from ");
            }
            System.out.println("\""+directions.get(i).startStationName+"\" to " +"\""+directions.get(i).endStationName+"\""+" for "+formatDouble(directions.get(i).duration)+" minutes.");
        }
    }
}