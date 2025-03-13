import java.io.IOException;

public class algo {

    public static Integer[] Insertion(Integer []data) throws IOException {
        int n = data.length;
        for (int i = 1; i < n; ++i) {
            int key = data[i];
            int j = i - 1;

            while (j >= 0 && data[j] > key) {
                data[j + 1] = data[j];
                j = j - 1;
            }
            data[j + 1] = key;
        }
        return data;
    }
    public static Integer[] Merge(Integer []data) throws IOException {
        if (data.length > 1) {
            int size = data.length / 2;
            Integer[] left = new Integer[size];
            Integer[] right = new Integer[data.length - size];
            for (int i = 0; i < size; i++) {
                left[i] = data[i];
            }
            for (int i = 0; i < data.length - size; i++) {
                right[i] = data[i + size];
            }

            left = Merge(left);
            right = Merge(right);
            return Mergesort(left,right,data);
        }
        return data;
    }
    public static Integer[] Mergesort(Integer[] left,Integer[] right,Integer []data) throws IOException {
        int leftn = 0;
        int rightn = 0;
        int dataIndex = 0;

        while (leftn < left.length && rightn < right.length) {
            if (left[leftn] <= right[rightn]) {
                data[dataIndex] = left[leftn];
                leftn++;
            } else {
                data[dataIndex] = right[rightn];
                rightn++;
            }
            dataIndex++;
        }

        while (leftn < left.length) {
            data[dataIndex] = left[leftn];
            leftn++;
            dataIndex++;
        }

        while (rightn < right.length) {
            data[dataIndex] = right[rightn];
            rightn++;
            dataIndex++;
        }

        return data;
    }
    public static Integer[] Counting(Integer []data) throws IOException {
        if (data.length <= 1) {
            return data;
        }
        int max = data[0];
        int min = data[0];
        for (int num : data) {
            if (num > max) {
                max = num;
            }
            if (num < min) {
                min = num;
            }
        }

        int[] count = new int[max - min + 1];
        for (int num : data) {
            count[num - min]++;
        }

        int index = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                data[index++] = i + min;
                count[i]--;
            }
        }
        return data;
    }
    public static Integer Linear(Integer []data,int aranan) throws IOException {
        for(int i=0;i<data.length;i++){
            if(data[i]==aranan){
                return i;
            }
        }
        return -1;
    }
    public static Integer Binary(Integer []data,int aranan) throws IOException {
        int sol = 0;
        int sag = data.length - 1;
        while (sol <= sag) {
            int orta = sol + (sag - sol) / 2;
            if (data[orta] == aranan) {
                return orta;
            }
            if (data[orta] < aranan) {
                sol = orta + 1;
            } else {
                sag = orta - 1;
            }
        }
        return -1;
    }


}
