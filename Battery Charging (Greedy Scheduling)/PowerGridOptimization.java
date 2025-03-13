import java.util.ArrayList;

/**
 * This class accomplishes Mission POWER GRID OPTIMIZATION
 */
public class PowerGridOptimization {
    private ArrayList<Integer> amountOfEnergyDemandsArrivingPerHour;
    public int sum=0;
    public PowerGridOptimization(ArrayList<Integer> amountOfEnergyDemandsArrivingPerHour){
        this.amountOfEnergyDemandsArrivingPerHour = amountOfEnergyDemandsArrivingPerHour;
        for (int k:amountOfEnergyDemandsArrivingPerHour){
            this.sum+=k;
        }
        System.out.println("The total number of demanded gigawatts: "+sum);
    }

    public ArrayList<Integer> getAmountOfEnergyDemandsArrivingPerHour() {
        return amountOfEnergyDemandsArrivingPerHour;
    }

    public OptimalPowerGridSolution getOptimalPowerGridSolutionDP(){
        int N = amountOfEnergyDemandsArrivingPerHour.size();
        Integer[] SOL = new Integer[N + 1];
        ArrayList<ArrayList<Integer>> HOURS = new ArrayList<>();

        SOL[0] = 0;
        HOURS.add(new ArrayList<>()); // HOURS(0) boş bir liste

        for (int j = 1; j <= N; j++) {
            int maxVal = Integer.MIN_VALUE;
            int bestI = -1;
            for (int i = 0; i < j; i++) {
                int val = SOL[i] + Math.min(amountOfEnergyDemandsArrivingPerHour.get(j - 1), chargingFunction(j - i));
                if (val > maxVal) {
                    maxVal = val;
                    bestI = i;
                }
            }
            SOL[j] = maxVal;
            ArrayList<Integer> hoursForJ = new ArrayList<>(HOURS.get(bestI));
            hoursForJ.add(j);
            HOURS.add(hoursForJ);
        }

        return new OptimalPowerGridSolution(SOL[N], HOURS.get(HOURS.size() - 1));
    }
    private int chargingFunction(int hours) {
        return hours * hours;
    }
}
