import org.knowm.xchart.*;
import org.knowm.xchart.style.Styler;
import java.util.Random;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
//
class Main {
//   }
    public static void main(String args[]) throws IOException {
        reader reader=new reader();
        // X axis data
        int[] inputAxis = {512, 1024, 2048, 4096, 8192, 16384, 32768, 65536 , 131072, 251282};

        // Create sample data for linear runtime
        double[][] yAxis = new double[3][10];
        yAxis[0] = new double[]{512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 251282};
        yAxis[1] = new double[]{300, 800, 1800, 3000, 7000, 15000, 31000, 64000, 121000, 231000};

        double[][] times1 = new double[3][10];
        double[][] times2 = new double[3][10];
        double[][] times3 = new double[3][10];
        double[][] times4 = new double[3][10];
        for(int j=0;j<inputAxis.length;j++){
            Integer[] flowduration= reader.flow(args[0],inputAxis[j]);
            times1[0][j]=timefor_Insert(flowduration);
            times1[1][j]=timefor_Merge(flowduration);
            times1[2][j]=timefor_Counting(flowduration);
            times4[0][j]=timefor_LineerSearch(flowduration);
            Arrays.sort(flowduration);
            times2[0][j]=timefor_Insert(flowduration);
            times2[1][j]=timefor_Merge(flowduration);
            times2[2][j]=timefor_Counting(flowduration);
            times4[1][j]=timefor_LineerSearch(flowduration);
            times4[2][j]=timefor_BinarySearch(flowduration);
            Collections.reverse(Arrays.asList(flowduration));
            times3[0][j]=timefor_Insert(flowduration);
            times3[1][j]=timefor_Merge(flowduration);
            times3[2][j]=timefor_Counting(flowduration);
        }

        // Save the char as .png and show it
        showAndSaveChart("Random Data", inputAxis, times1);
        showAndSaveChart("Sort Data", inputAxis, times2);
        showAndSaveChart("Reverse Data", inputAxis, times3);
        showAndSaveChartforsearch("Lineer&Binary Search", inputAxis, times4);
        for(int k=0;k < times1.length;k++){
            for(int l=0;l < times1[k].length;l++){
                System.out.print(times1[k][l]+",");
            }
            System.out.println();
        }
        System.out.println();
        for(int k=0;k < times2.length;k++){
            for(int l=0;l < times2[k].length;l++){
                System.out.print(times2[k][l]+",");
            }
            System.out.println();
        }
        System.out.println();
        for(int k=0;k < times3.length;k++){
            for(int l=0;l < times3[k].length;l++){
                System.out.print(times3[k][l]+",");
            }
            System.out.println();
        }
        System.out.println();
        for(int k=0;k < times4.length;k++){
            for(int l=0;l < times4[k].length;l++){
                System.out.print(times4[k][l]+",");
            }
            System.out.println();
        }
        System.out.println();

    }

    public static void showAndSaveChart(String title, int[] xAxis, double[][] yAxis) throws IOException {
        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title(title)
                .yAxisTitle("Time in Milliseconds").xAxisTitle("Input Size").build();

        // Convert x axis to double[]
        double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        // Add a plot for a sorting algorithm
        chart.addSeries("Insertion Sort", doubleX, yAxis[0]);
        chart.addSeries("Merge Sort", doubleX, yAxis[1]);
        chart.addSeries("Counting Sort",doubleX,yAxis[2]);

        // Save the chart as PNG
        BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);

        // Show the chart
        new SwingWrapper(chart).displayChart();
    }
    public static void showAndSaveChartforsearch(String title, int[] xAxis, double[][] yAxis) throws IOException {
        // Create Chart
        XYChart chart = new XYChartBuilder().width(800).height(600).title(title)
                .yAxisTitle("Time in Milliseconds").xAxisTitle("Input Size").build();

        // Convert x axis to double[]
        double[] doubleX = Arrays.stream(xAxis).asDoubleStream().toArray();

        // Customize Chart
        chart.getStyler().setLegendPosition(Styler.LegendPosition.InsideNE);
        chart.getStyler().setDefaultSeriesRenderStyle(XYSeries.XYSeriesRenderStyle.Line);

        // Add a plot for a sorting algorithm
        chart.addSeries("Lineer Search on Random Data", doubleX, yAxis[0]);
        chart.addSeries("Lineer Search on Sorted Data", doubleX, yAxis[1]);
        chart.addSeries("Binary Search",doubleX,yAxis[2]);

        // Save the chart as PNG
        BitmapEncoder.saveBitmap(chart, title + ".png", BitmapEncoder.BitmapFormat.PNG);

        // Show the chart
        new SwingWrapper(chart).displayChart();
    }


    public static double timefor_Insert(Integer [] flowduration)throws IOException {
        algo algo = new algo();
        double times=0;
        for(int i=0;i<10;i++){
           long time1= System.currentTimeMillis();
            Integer[]k=algo.Insertion(flowduration);
            long time2= System.currentTimeMillis();
            double time=time2-time1;
            times+=time/10;
        }
        return times;
    }
    public static double timefor_Merge(Integer [] flowduration)throws IOException {
        algo algo = new algo();
        long time1, time2,time,times=0;
        Integer[]k= new Integer[0];
        for(int i=0;i<10;i++){
            time1= System.currentTimeMillis();
            k=algo.Merge(flowduration);
            time2= System.currentTimeMillis();
            time=  (time2-time1);
            times+=time/10;
        }
        return times;

    }
    public static double timefor_Counting(Integer [] flowduration)throws IOException {
        algo algo = new algo();
        double times=0;
        for(int i=0;i<10;i++){
            long time1= System.currentTimeMillis();
            Integer[]k=algo.Counting(flowduration);
            long time2= System.currentTimeMillis();
            double time=  (time2-time1);
            times+=time/10;
        }
        return times;

    }
    public static double timefor_LineerSearch(Integer [] flowduration)throws IOException {
        algo algo = new algo();
        Random random = new Random();
        double times=0;
        for(int i=0;i<1000;i++){
            int randomIndex = random.nextInt(flowduration.length);
            long time1= System.nanoTime();
            algo.Linear(flowduration,flowduration[randomIndex]);
            long time2= System.nanoTime();
            double time=  (time2-time1);
            times+=time/1000;
        }
        return times;
    }
    public static double timefor_BinarySearch(Integer [] flowduration)throws IOException {
        algo algo = new algo();
        Random random = new Random();
        double times=0;
        for(int i=0;i<1000;i++){
            int randomIndex = random.nextInt(flowduration.length);
            long time1= System.nanoTime();
            algo.Binary(flowduration,flowduration[randomIndex]);
            long time2= System.nanoTime();
            double time=  (time2-time1);
            times+=time/1000;
        }
        return times;
    }
}
