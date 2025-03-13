import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class reader {
    public static void main(String args[]) throws IOException {

    }
    public static Integer[] flow(String file,int size)throws IOException{
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        bufferedReader.readLine();
        List<Integer> flowd= new ArrayList<>();
        String line;
        int sayac=0;
        while ((line = bufferedReader.readLine()) != null&&sayac<size) {//burada dosya okumayı kalan satırdanmı devam ettircek asıl önemli kısım o
            String[] split = line.split(",");
            flowd.add(Integer.parseInt(split[6]));
            sayac++;
        }


        bufferedReader.close(); // Dosya okuyucuyu kapat
        Integer[] flow=new Integer[flowd.size()];

        return flowd.toArray(flow);
    }
}
