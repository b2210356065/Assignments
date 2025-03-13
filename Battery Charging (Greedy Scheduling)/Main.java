import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Main class
 */
// FREE CODE HERE
public class Main {
    public static void main(String[] args) throws IOException {

       /** MISSION POWER GRID OPTIMIZATION BELOW **/

        System.out.println("##MISSION POWER GRID OPTIMIZATION##");
        // TODO: Your code goes here
        // You are expected to read the file given as the first command-line argument to read 
        // the energy demands arriving per hour. Then, use this data to instantiate a 
        // PowerGridOptimization object. You need to call GetOptimalPowerGridSolutionDP() method
        // of your PowerGridOptimization object to get the solution, and finally print it to STDOUT.
        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        String line= reader.readLine();
        String [] d=line.split(" ");
        ArrayList<Integer> demanded_power = new ArrayList<>();
        for(String k:d){
            demanded_power.add(Integer.parseInt(k));
        }
        PowerGridOptimization powerGridOptimization=new PowerGridOptimization(demanded_power);
        OptimalPowerGridSolution solution = powerGridOptimization.getOptimalPowerGridSolutionDP();
        int j=powerGridOptimization.sum-solution.getmaxNumberOfSatisfiedDemands();
        System.out.println("The number of unsatisfied gigawatts: "+j);

        System.out.println("##MISSION POWER GRID OPTIMIZATION COMPLETED##");

        /** MISSION ECO-MAINTENANCE BELOW **/
        System.out.println("##MISSION ECO-MAINTENANCE##");
        // TODO: Your code goes here
        // You are expected to read the file given as the second command-line argument to read
        // the number of available ESVs, the capacity of each available ESV, and the energy requirements
        // of the maintenance tasks. Then, use this data to instantiate an OptimalESVDeploymentGP object.
        // You need to call getMinNumESVsToDeploy(int maxNumberOfAvailableESVs, int maxESVCapacity) method
        // of your OptimalESVDeploymentGP object to get the solution, and finally print it to STDOUT.
        BufferedReader reader1 = new BufferedReader(new FileReader(args[1]));
        String line1= reader1.readLine();
        String line2=reader1.readLine();
        String [] l1=line1.split(" ");
        String [] l2=line2.split(" ");
        ArrayList<Integer> maintenanceTaskEnergyDemands1=new ArrayList<>();
        ArrayList<Integer> maintenanceTaskEnergyDemands2=new ArrayList<>();
        for (String f:l1){
            maintenanceTaskEnergyDemands1.add(Integer.parseInt(f));
        }
        for (String f:l2){
            maintenanceTaskEnergyDemands2.add(Integer.parseInt(f));
        }
        OptimalESVDeploymentGP finalDeployment = new OptimalESVDeploymentGP(maintenanceTaskEnergyDemands2);
        int k =finalDeployment.getMinNumESVsToDeploy(maintenanceTaskEnergyDemands1.get(0),maintenanceTaskEnergyDemands1.get(1));
        if(k<0){
            System.out.println("Warning: Mission Eco-Maintenance Failed.");
        }else{
            System.out.println("The minimum number of ESVs to deploy: "+finalDeployment.getMaintenanceTasksAssignedToESVs().size());
            for(int i=0;i<finalDeployment.getMaintenanceTasksAssignedToESVs().size();i++){
                System.out.println("ESV "+(i+1)+" tasks: "+finalDeployment.getMaintenanceTasksAssignedToESVs().get(i));
            }
        }
    
        System.out.println("##MISSION ECO-MAINTENANCE COMPLETED##");
        reader.close();
        reader1.close();
    }
}
