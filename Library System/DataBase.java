import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class DataBase {
    public static HashMap<Integer, ArrayList>members=new HashMap<>();
    public static HashMap<Integer, ArrayList>books=new HashMap<>();



    public String gethistory() throws IOException {
        ArrayList<Integer>listestud=new ArrayList<>();
        ArrayList<Integer>listeacademic=new ArrayList<>();
        ArrayList<Integer>listehandwr=new ArrayList<>();
        ArrayList<Integer>listeprint=new ArrayList<>();
        int numberborow=0;
        int numberreadlib=0;
        String yazacstud="";
        String yazacsaca="";
        String yazachwr="";
        String yazacpr="";
        String yazacborow="";
        String yazacreadinlib="";
        for(int i=1;i<=members.size();i++){
            if(members.get(i).get(0).equals("S")){
                listestud.add(i);
                yazacstud=yazacstud+"Student [id: "+i+"]\n";
            }
            if(members.get(i).get(0).equals("A")){
                listeacademic.add(i);
                yazacsaca=yazacsaca+"Academic [id: "+i+"]\n";
            }
        }
        for(int i=1;i<=books.size();i++){
            if(books.get(i).get(1)!="0"&&books.get(i).get(2).equals("0")){
                numberreadlib++;
                yazacreadinlib=yazacreadinlib+"The book ["+i+"] was read in library by member ["+books.get(i).get(3)+"] at "+books.get(i).get(1)+"\n";
            }
            if(books.get(i).get(1)!="0"&&books.get(i).get(2)!="0"){
                numberborow++;
                yazacborow=yazacborow+"The book ["+i+"] was borrowed by member ["+books.get(i).get(3)+"] at "+books.get(i).get(1)+"\n";
            }
            if(books.get(i).get(0).equals("P")){
                listeprint.add(i);
                yazacpr=yazacpr+"Printed [id: "+i+"]\n";
            }
            if(books.get(i).get(0).equals("H")){
                listehandwr.add(i);
                yazachwr=yazachwr+"Handwritten [id: "+i+"]\n";
            }
        }
        return "History of library:\n\n"+"Number of students: "+listestud.size()+"\n"+yazacstud+"\n"+"Number of academics: "+listeacademic.size()+"\n"+
                yazacsaca+"\n"+"Number of printed books: "+listeprint.size()+"\n"+yazacpr+"\n"+"Number of handwritten books: "+listehandwr.size()
                +"\n"+yazachwr+"\n"+"Number of borrowed books: "+numberborow+"\n"+yazacborow+"\n"+"Number of books read in library: "+numberreadlib+"\n"+
                yazacreadinlib;
    }
}
