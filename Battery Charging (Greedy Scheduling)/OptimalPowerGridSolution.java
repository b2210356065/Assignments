import java.util.ArrayList;

/**
 * A class that represents the optimal solution for the Power Grid optimization scenario (Dynamic Programming)
 */

public class OptimalPowerGridSolution {
    private int maxNumberOfSatisfiedDemands;
    private ArrayList<Integer> hoursToDischargeBatteriesForMaxEfficiency;

    public OptimalPowerGridSolution(int maxNumberOfSatisfiedDemands, ArrayList<Integer> hoursToDischargeBatteriesForMaxEfficiency) {
        this.maxNumberOfSatisfiedDemands = maxNumberOfSatisfiedDemands;
        this.hoursToDischargeBatteriesForMaxEfficiency = hoursToDischargeBatteriesForMaxEfficiency;
        System.out.println("Maximum number of satisfied gigawatts: "+maxNumberOfSatisfiedDemands);
        System.out.print("Hours at which the battery bank should be discharged: ");
        for (int k = 0; k < hoursToDischargeBatteriesForMaxEfficiency.size(); k++) {
            if (k == hoursToDischargeBatteriesForMaxEfficiency.size() - 1) {
                System.out.println(hoursToDischargeBatteriesForMaxEfficiency.get(k));
            } else {
                System.out.print(hoursToDischargeBatteriesForMaxEfficiency.get(k) + ", ");
            }
        }


    }

    public OptimalPowerGridSolution() {

    }

    public int getmaxNumberOfSatisfiedDemands() {
        return maxNumberOfSatisfiedDemands;
    }

    public ArrayList<Integer> getHoursToDischargeBatteriesForMaxEfficiency() {
        return hoursToDischargeBatteriesForMaxEfficiency;
    }

}
