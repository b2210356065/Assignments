import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.String;
public class Main {
    public static void main(String[] args) {
        File input1 = new File(args[0]);
        File input2 = new File(args[1]);
        File cikti = new File("output.txt");

        try (BufferedReader girdikonum = new BufferedReader(new FileReader(input1));
             BufferedReader girdihamle = new BufferedReader(new FileReader(input2));
             FileWriter output = new FileWriter(cikti)) {
            Map <Integer, List<Character>>koordinatlar=new HashMap<>();
            int puan=0;
            output.write("Game board:\n");
            String satir;
            int sayac=1;
            int sd=0;
            int yıldızkonum = 0;
            int genis=0;
            while ((satir = girdikonum.readLine()) != null) {
                genis=((satir.length())+1)/2;
                ArrayList<Character> tahta=new ArrayList<>();
                for (int i=0; i<=genis-1;i++) {
                    if (satir.charAt(2 * i)=='*') {
                        yıldızkonum = sayac*10 + i;
                    }
                    tahta.add(satir.charAt(2*i));
                }
                koordinatlar.put(sayac,tahta);
                sd=sayac;
                sayac++;
            }
            int genislik=genis;
            int yukseklik=sd;
            for(int j=1;j<=yukseklik;j++) {
                for (int k=0;k<=genislik-1;k++) {
                    output.write(koordinatlar.get(j).get(k)+" ");
                }
                output.write("\n");
            }
            boolean ss=true;
            String satir1=girdihamle.readLine();
            int yıldızkonumyy=(yıldızkonum/10)%10;
            int yıldızkonumxx=yıldızkonum%10;
            for (int i=0;i<=(satir1.length()-1)/2;i++){
                int yıldızkonumy=(yıldızkonum/10)%10;
                int yıldızkonumx=yıldızkonum%10;

                boolean a=true;
                boolean b=true;
                boolean c=true;
                boolean d=true;
                if (satir1.charAt(2*i)=='R'){
                    yıldızkonumxx=right(yıldızkonumx,genislik);
                    a=false;
                }
                else if (satir1.charAt(2*i)=='U'){
                    yıldızkonumyy=up(yıldızkonumy,sd);
                    b=false;
                }
                else if (satir1.charAt(2*i)=='D'){
                    yıldızkonumyy=down(yıldızkonumy,sd);
                    c=false;
                }
                else if (satir1.charAt(2*i)=='L'){
                    yıldızkonumxx=left(yıldızkonumx,genislik);
                    d=false;
                }
                ////////////////////////////////////////////////////////
                if (koordinatlar.get(yıldızkonumyy).get(yıldızkonumxx)=='W'){
                    if (a==false){
                        yıldızkonumxx=left(yıldızkonumx,genislik);
                    }
                    if (b==false){
                        yıldızkonumyy=down(yıldızkonumy,sd);
                    }
                    if (c==false){
                        yıldızkonumyy=up(yıldızkonumy,sd);
                    }
                    if (d==false){
                        yıldızkonumxx=right(yıldızkonumx,genislik);
                    }
                }
                if ((koordinatlar.get(yıldızkonumyy).get(yıldızkonumxx)=='R')){
                    koordinatlar.get(yıldızkonumy).set(yıldızkonumx,'X');
                    koordinatlar.get(yıldızkonumyy).set(yıldızkonumxx,'*');
                    puan+=10;
                }
                else if((koordinatlar.get(yıldızkonumyy).get(yıldızkonumxx)=='Y')){
                    koordinatlar.get(yıldızkonumy).set(yıldızkonumx,'X');
                    koordinatlar.get(yıldızkonumyy).set(yıldızkonumxx,'*');
                    puan+=5;
                }
                else if((koordinatlar.get(yıldızkonumyy).get(yıldızkonumxx)=='B')){
                    koordinatlar.get(yıldızkonumy).set(yıldızkonumx,'X');
                    koordinatlar.get(yıldızkonumyy).set(yıldızkonumxx,'*');
                    puan-=5;
                }
                else if(koordinatlar.get(yıldızkonumyy).get(yıldızkonumxx)=='H'){
                    koordinatlar.get(yıldızkonumy).set(yıldızkonumx,' ');
                    ss=false;
                    break;
                }
                else{
                    koordinatlar.get(yıldızkonumy).set(yıldızkonumx,koordinatlar.get(yıldızkonumyy).get(yıldızkonumxx));
                    koordinatlar.get(yıldızkonumyy).set(yıldızkonumxx,'*');
                }
                yıldızkonum=10*yıldızkonumyy+yıldızkonumxx;
            }
            output.write("\n");
            output.write("Your movement is:\n");
            output.write(satir1+"\n\n");
            output.write("Your output is\n");

            for(int j=1;j<=sd;j++) {
                for (int k=0;k<=genislik-1;k++) {
                    output.write(koordinatlar.get(j).get(k)+" ");
                }
                output.write("\n");
            }
            output.write("\n");
            if (ss==false){
                output.write("Game Over!\n");
            }
            output.write("Score: "+Integer.toString(puan)+"\n");
        } catch (FileNotFoundException e) {
            System.out.println("Belirtilen dosya bulunamadı.");
        } catch (IOException e) {
            System.out.println("Girdi-çıktı hatası oluştu.");
        }
    }
    public static int left(int i,int j){
        if (i==0){
            i=j-1;
        }
        else {
            i -= 1;
        }
        return i;
    }
    public static int right(int i,int j){
        if (i==j-1){
            i=0;
        }
        else {
            i += 1;
        }
        return i;

    }
    public static int up(int i,int j){
        if (i==1){
            i=j;
        }
        else {
            i -= 1;
        }
        return i;
    }
    public static int down(int i,int j){
        if (i==j){
            i=1;
        }
        else {
            i+= 1;
        }
        return i;
    }
}