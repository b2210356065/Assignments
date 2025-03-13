import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UrbanInfrastructureDevelopment implements Serializable {
    static final long serialVersionUID = 88L;

    /**
     * Given a list of Project objects, prints the schedule of each of them.
     * Uses getEarliestSchedule() and printSchedule() methods of the current project to print its schedule.
     * @param projectList a list of Project objects
     */
    public void printSchedule(List<Project> projectList) {
        // TODO: YOUR CODE HERE

        for(int i=0;i<projectList.size();i++){
            int[] k =projectList.get(i).getEarliestSchedule();
            projectList.get(i).printSchedule(k);
        }
    }

    /**
     * TODO: Parse the input XML file and return a list of Project objects
     *
     * @param filename the input XML file
     * @return a list of Project objects
     */
    public List<Project> readXML(String filename) {
        List<Project> projectList = new ArrayList<>();
        try {
            // Create a new instance of DocumentBuilder
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Parse the XML file and normalize
            Document document = builder.parse(new File(filename));
            document.getDocumentElement().normalize();

            // Read the root element
            NodeList projectNodes = document.getElementsByTagName("Project");

            for (int i = 0; i < projectNodes.getLength(); i++) {
                Node projectNode = projectNodes.item(i);
                if (projectNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element projectElement = (Element) projectNode;

                    // Read project's name
                    String name = projectElement.getElementsByTagName("Name").item(0).getTextContent();

                    // Read tasks
                    List<Task> tasks = new ArrayList<>();
                    NodeList taskNodes = projectElement.getElementsByTagName("Task");
                    for (int j = 0; j < taskNodes.getLength(); j++) {
                        Node taskNode = taskNodes.item(j);
                        if (taskNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element taskElement = (Element) taskNode;

                            int taskID = Integer.parseInt(taskElement.getElementsByTagName("TaskID").item(0).getTextContent());
                            String description = taskElement.getElementsByTagName("Description").item(0).getTextContent();
                            int duration = Integer.parseInt(taskElement.getElementsByTagName("Duration").item(0).getTextContent());

                            List<Integer> dependencies = new ArrayList<>();
                            NodeList dependencyNodes = taskElement.getElementsByTagName("DependsOnTaskID");
                            for (int k = 0; k < dependencyNodes.getLength(); k++) {

                                dependencies.add(Integer.parseInt(dependencyNodes.item(k).getTextContent()));
                            }
                            tasks.add(new Task(taskID, description, duration, dependencies));
                        }
                    }

                    projectList.add(new Project(name, tasks));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return projectList;
    }
}

