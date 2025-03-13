import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HyperloopTrainNetwork implements Serializable {
    static final long serialVersionUID = 11L;
    public double averageTrainSpeed;
    public final double averageWalkingSpeed = 1000 / 6.0;
    public int numTrainLines;
    public Station startPoint;
    public Station destinationPoint;
    public List<TrainLine> lines;
    public String linename;

    /**
     * Method with a Regular Expression to extract integer numbers from the fileContent
     * @return the result as int
     */
    public int getIntVar(String varName, String fileContent) {
        Pattern p = Pattern.compile("[\\t ]*" + varName + "[\\t ]*=[\\t ]*([0-9]+)");
        Matcher m = p.matcher(fileContent);
        m.find();
        return Integer.parseInt(m.group(1));
    }

    /**
     * Write the necessary Regular Expression to extract string constants from the fileContent
     * @return the result as String
     */
    public String getStringVar(String varName, String fileContent) {
        Pattern p = Pattern.compile(varName + "\\s*=\\s*\"([^\"]+)\"");
        Matcher m = p.matcher(fileContent);
        if(!m.find()){return "#####";};
        return m.group(1);
    }

    /**
     * Write the necessary Regular Expression to extract floating point numbers from the fileContent
     * Your regular expression should support floating point numbers with an arbitrary number of
     * decimals or without any (e.g. 5, 5.2, 5.02, 5.0002, etc.).
     * @return the result as Double
     */
    public Double getDoubleVar(String varName, String fileContent) {
        // TODO: Your code goes here
        Pattern p = Pattern.compile("[\\t ]*" + varName + "[\\t ]*=\\s*([0-9]+(?:\\.[0-9]+)?)");
        Matcher m = p.matcher(fileContent);
        m.find();
        return Double.parseDouble(m.group(1));
    }

    /**
     * Write the necessary Regular Expression to extract a Point object from the fileContent
     * points are given as an x and y coordinate pair surrounded by parentheses and separated by a comma
     * @return the result as a Point object
     */
    public Point getPointVar(String varName, String fileContent) {
        Pattern p = Pattern.compile("[\\t ]*" + varName + "[\\t ]*=\\s*\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)");
        Matcher m = p.matcher(fileContent);
        if (m.find()) {
            int x = Integer.parseInt(m.group(1));
            int y = Integer.parseInt(m.group(2));
            return new Point(x, y);
        } else {
            return new Point(0, 0);
        }
    }

    /**
     * Function to extract the train lines from the fileContent by reading train line names and their 
     * respective stations.
     * @return List of TrainLine instances
     */
    public List<TrainLine> getTrainLines(String fileContent) {
        List<TrainLine> trainLines = new ArrayList<>();
        if(lines!=null){
            trainLines=lines;
        }
        Pattern pattern = Pattern.compile("\\(\\s*(\\d+)\\s*,\\s*(\\d+)\\s*\\)");
        Matcher matcher = pattern.matcher(fileContent);
        int i=1;
        List <Station> stations=new ArrayList<>();
        while (matcher.find()) {
            Station s1=new Station(new Point(Integer.parseInt(matcher.group(1)),Integer.parseInt(matcher.group(2))),linename+" Line Station "+i);
            i++;
            stations.add(s1);
        }
        TrainLine trainLine=new TrainLine(linename,stations);
        trainLines.add(trainLine);
        return trainLines;
    }

    /**
     * Function to populate the given instance variables of this class by calling the functions above.
     */
    public void readInput(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                if(!getStringVar("train_line_name",line).equals("#####")){
                    linename=getStringVar("train_line_name",line);
                    line=reader.readLine();
                    lines=getTrainLines(line);
                }
                else if(!getStringVar("destination_point",line).equals("#####")){
                    destinationPoint= new Station(getPointVar("destination_point",line),"Final Destination");
                }
                else if(!getStringVar("starting_point",line).equals("#####")){
                    startPoint = new Station(getPointVar("starting_point", line), "Starting Point");
                }
            }
            reader.close();
            String fileContent = new String(Files.readAllBytes(Paths.get(filename)));
            numTrainLines = getIntVar("num_train_lines", fileContent);
            startPoint = new Station(getPointVar("starting_point", fileContent), "Starting Point");
            averageTrainSpeed = getDoubleVar("average_train_speed", fileContent) * 1000 / 60;
            destinationPoint= new Station(getPointVar("destination_point",fileContent),"Final Destination");
        } catch (IOException e) {

        }

    }
}