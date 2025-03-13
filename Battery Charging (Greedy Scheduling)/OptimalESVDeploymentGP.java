import java.util.ArrayList;
import java.util.Collections;

public class OptimalESVDeploymentGP {
    private ArrayList<Integer> maintenanceTaskEnergyDemands;
    private ArrayList<ArrayList<Integer>> maintenanceTasksAssignedToESVs = new ArrayList<>();

    public OptimalESVDeploymentGP(ArrayList<Integer> maintenanceTaskEnergyDemands) {
        this.maintenanceTaskEnergyDemands = maintenanceTaskEnergyDemands;
    }

    public ArrayList<ArrayList<Integer>> getMaintenanceTasksAssignedToESVs() {
        return maintenanceTasksAssignedToESVs;
    }

    public int getMinNumESVsToDeploy(int maxNumberOfAvailableESVs, int maxESVCapacity) {
        Collections.sort(maintenanceTaskEnergyDemands, Collections.reverseOrder());
        ArrayList<Integer> sums = new ArrayList<>();
        for (int i = 0; i < maintenanceTaskEnergyDemands.size(); i++) {
            int index = -1; 
            for (int j = 0; j < sums.size(); j++) {
                if (sums.get(j) + maintenanceTaskEnergyDemands.get(i) <= maxESVCapacity) {
                    index = j;
                    break;
                }
            }
            if (index == -1 ){
                if (maintenanceTasksAssignedToESVs.size() == maxNumberOfAvailableESVs) {return -1;}
                if (maintenanceTaskEnergyDemands.get(i) > maxESVCapacity) {return -1;}
                sums.add(0);
                maintenanceTasksAssignedToESVs.add(new ArrayList<>());
                index = sums.size() - 1;
            }
            maintenanceTasksAssignedToESVs.get(index).add(maintenanceTaskEnergyDemands.get(i));
            sums.set(index, sums.get(index) + maintenanceTaskEnergyDemands.get(i));
        
        }

        return maintenanceTasksAssignedToESVs.size();
    }


}