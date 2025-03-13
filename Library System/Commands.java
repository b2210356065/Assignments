import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Commands extends Main{
    DataBase data=new DataBase();
    public String addmember(String satir){
        //All of the commands without getHistory are running in this clas
        if(satir.split("\t").length==2&&(satir.split("\t")[1].equals("A")||satir.split("\t")[1].equals("S"))){
            ArrayList<String>membersstats=new ArrayList<>();
            membersstats.add(satir.split("\t")[1]);//membertype
            membersstats.add("0");//have how many books
            membersstats.add("0");//how many extend command
            membersstats.add("0");//how much fee
            int memberno=data.members.size()+1;
            data.members.put(data.members.size()+1,membersstats);
            if(satir.split("\t")[1].equals("S")){
                return "Created new member: Student [id: "+memberno+"]";
            }
            else {
                return "Created new book: Academic [id: "+memberno+"]";
            }
        }
        else {
            return "There is no such member type please try again";
        }
    }
    public String addbook(String satir){
        if(satir.split("\t").length==2&&(satir.split("\t")[1].equals("H")||satir.split("\t")[1].equals("P"))){
            ArrayList<String>booksstats=new ArrayList<>();
            booksstats.add(satir.split("\t")[1]);//booktype
            booksstats.add("0");
            booksstats.add("0");
            booksstats.add("0");
            int bookno=data.books.size()+1;
            data.books.put(data.books.size()+1,booksstats);
            if(satir.split("\t")[1].equals("H")){
                return "Created new book: Handwritten [id: "+bookno+"]";
            }
            else {
                return "Created new book: Printed [id: "+bookno+"]";
            }
        }
        else {
            return "There is no such book type please try again";
        }
    }
    public String borrow(String satir){
        try {
            if (satir.split("\t").length == 4
                    && Integer.parseInt(satir.split("\t")[1]) <= data.books.size()
                    && Integer.parseInt(satir.split("\t")[2]) <= data.members.size()//controlling correct format
                    &&data.books.get(Integer.parseInt(satir.split("\t")[1])).get(3).equals("0")) {//controlling is book tooken
                int bookno=Integer.parseInt(satir.split("\t")[1]);
                int memberno=Integer.parseInt(satir.split("\t")[2]);
                List liste=data.books.get(bookno);
                List listmember=data.members.get(memberno);
                String date=satir.split("\t")[3];
                String[] datelist=date.split("-");
                if(data.books.get(bookno).get(0).equals("P")) {
                    if (data.members.get(Integer.parseInt(satir.split("\t")[2])).get(0).equals("S")) {
                        if(Integer.parseInt((String) data.members.get(memberno).get(1))<2) {//controlling how much books members have
                            LocalDateTime verilis = LocalDateTime.of(Integer.parseInt(datelist[0]), Integer.parseInt(datelist[1]), Integer.parseInt(datelist[2]), 0, 0, 0);
                            LocalDateTime teslim = verilis.plusWeeks(1);
                            liste.set(1, date);
                            liste.set(2, Integer.toString(teslim.getYear()) + "-" + Integer.toString(teslim.getMonthValue()) + "-" + Integer.toString(teslim.getDayOfMonth()));
                            liste.set(3, memberno);
                            data.books.put(bookno, (ArrayList) liste);
                            listmember.set(1, Integer.toString(Integer.parseInt((String) data.members.get(memberno).get(1)) + 1));
                            data.members.put(memberno, (ArrayList) listmember);
                        }
                        else{
                            return "You have exceeded the borrowing limit!";
                        }
                    } else {
                        if(Integer.parseInt((String) data.members.get(memberno).get(1))<4) {//controlling how much books members have

                            LocalDateTime verilis = LocalDateTime.of(Integer.parseInt(datelist[0]), Integer.parseInt(datelist[1]), Integer.parseInt(datelist[2]), 0, 0, 0);
                            LocalDateTime teslim = verilis.plusWeeks(2);
                            liste.set(1, date);
                            liste.set(2, Integer.toString(teslim.getYear()) + "-" + Integer.toString(teslim.getMonthValue()) + "-" + Integer.toString(teslim.getDayOfMonth()));
                            liste.set(3, memberno);
                            data.books.put(bookno, (ArrayList) liste);
                            listmember.set(1, Integer.toString(Integer.parseInt((String) data.members.get(memberno).get(1)) + 1));
                            data.members.put(memberno, (ArrayList) listmember);
                        }
                        else{
                            return "You have exceeded the borrowing limit!";
                        }
                    }
                    return "The book ["+bookno+"] was borrowed by member ["+memberno+"] at "+date;
                }
                else {
                    if (data.members.get(Integer.parseInt(satir.split("\t")[2])).get(0).equals("S")) {
                        return "Students can not read handwritten books!";
                    }
                    else {
                        return "You cannot borrow this book!";
                    }
                }
            }
            else {
                return "You cannot borrow this book!";
            }
        }catch (NumberFormatException e){
            return "Wrong Command";
        }
    }
    public String readinlib(String satir){
        if (satir.split("\t").length == 4
                && Integer.parseInt(satir.split("\t")[1]) <= data.books.size()
                && Integer.parseInt(satir.split("\t")[2]) <= data.members.size()
                &&data.books.get(Integer.parseInt(satir.split("\t")[1])).get(3).equals("0")) {//controlling is book tooken
            int bookno=Integer.parseInt(satir.split("\t")[1]);
            int memberno=Integer.parseInt(satir.split("\t")[2]);
            List listmember=data.members.get(memberno);
            List liste=data.books.get(bookno);
            String date=satir.split("\t")[3];
            if(data.books.get(bookno).get(0).equals("P")) {
                if (data.members.get(Integer.parseInt(satir.split("\t")[2])).get(0).equals("S")) {
                    liste.set(1,date);
                    liste.set(3,memberno);
                    data.books.put(bookno, (ArrayList) liste);;
                    listmember.set(1,Integer.toString(Integer.parseInt((String) data.members.get(memberno).get(1))));
                    data.members.put(memberno, (ArrayList) listmember);
                } else {
                    liste.set(1,date);
                    liste.set(3,memberno);
                    data.books.put(bookno, (ArrayList) liste);
                    listmember.set(1,Integer.toString(Integer.parseInt((String) data.members.get(memberno).get(1))));
                    data.members.put(memberno, (ArrayList) listmember);
                }
                return "The book ["+bookno+"] was read in library by member ["+memberno+"] at "+date;
            }
            else {
                if (data.members.get(Integer.parseInt(satir.split("\t")[2])).get(0).equals("S")) {
                    return "Students can not read handwritten books!";
                } else {
                    liste.set(1,date);
                    liste.set(3,memberno);
                    data.books.put(bookno, (ArrayList) liste);
                    listmember.set(1,Integer.toString(Integer.parseInt((String) data.members.get(memberno).get(1))));
                    data.members.put(memberno, (ArrayList) listmember);
                    return "The book ["+bookno+"] was read in library by member ["+memberno+"] at "+date;
                }
            }
        }
        else {
            return "You can not read this book!";
        }

    }
    public String extend(String satir){
        if (satir.split("\t").length == 4
                && Integer.parseInt(satir.split("\t")[1]) <= data.books.size()
                && Integer.parseInt(satir.split("\t")[2]) <= data.members.size()
                &&data.books.get(Integer.parseInt(satir.split("\t")[1])).get(3)!="0"){//controlling is book tooken
            String[] date=satir.split("\t")[3].split("-");
            LocalDateTime deadline=LocalDateTime.of(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]),0,0,0);
            List liste=data.members.get(Integer.parseInt(satir.split("\t")[2]));
            List listbook=data.books.get(Integer.parseInt(satir.split("\t")[1]));
            String lastdate= (String) listbook.get(2);
            String[] lastdatelist=lastdate.split("-");
            LocalDateTime lastdatetime=LocalDateTime.of(Integer.parseInt(lastdatelist[0]),Integer.parseInt(lastdatelist[1]),Integer.parseInt(lastdatelist[2]),0,0,0);
            if(data.members.get(Integer.parseInt(satir.split("\t")[2])).get(2).equals("0")) {
                if(data.books.get(Integer.parseInt(satir.split("\t")[1])).get(1)!="0"&&data.books.get(Integer.parseInt(satir.split("\t")[1])).get(2).equals("0")){
                    return "You cannot extend the duration of the book you read in the library.";
                }
                if (lastdatetime.isBefore(deadline)){
                    return "You cannot extend the deadline!";
                }
                else{
                    if (data.members.get(Integer.parseInt(satir.split("\t")[2])).get(0).equals("S")) {
                        if (Integer.parseInt((String) liste.get(2)) <= 1) {//control how much extend command
                            LocalDateTime ext = lastdatetime.plusWeeks(1);
                            liste.set(2, "1");
                            listbook.set(2, Integer.toString(ext.getYear()) + "-" + Integer.toString(ext.getMonthValue()) + "-" + Integer.toString(ext.getDayOfMonth()));
                            data.members.put(Integer.parseInt(satir.split("\t")[2]), (ArrayList) liste);
                            data.books.put(Integer.parseInt(satir.split("\t")[1]), (ArrayList) listbook);
                        }
                        else {
                            return "You cannot borrow this book!";
                        }
                    } else if (data.members.get(Integer.parseInt(satir.split("\t")[2])).get(0).equals("A")) {
                        if (Integer.parseInt((String) liste.get(2)) <= 1) {
                            LocalDateTime ext = lastdatetime.plusWeeks(2);
                            liste.set(2, "1");
                            listbook.set(2, Integer.toString(ext.getYear()) + "-" + Integer.toString(ext.getMonthValue()) + "-" + Integer.toString(ext.getDayOfMonth()));
                            data.members.put(Integer.parseInt(satir.split("\t")[2]), (ArrayList) liste);
                            data.books.put(Integer.parseInt(satir.split("\t")[1]), (ArrayList) listbook);
                        }
                        else {
                            return "You cannot borrow this book!";
                        }
                    }
                    return "The deadline of book ["+satir.split("\t")[1]+"] was extended by member ["+satir.split("\t")[2]+"] at "+satir.split("\t")[3]+"\n"+
                        "New deadline of book ["+satir.split("\t")[1]+"] is "+listbook.get(2);
                }
            }
            else {
                return "You cannot extend the deadline!";
            }
        }
        else {
            return "Wrong Command";
        }

    }
    public String returnbook(String satir){
        String[] line=satir.split("\t");
        int memberno= Integer.parseInt((String) line[2]);
        int bookno= Integer.parseInt((String) line[1]);
        boolean flag=true;
        String cikti="";
        List ozellikler1=data.members.get(memberno);
        List ozellikler2=data.books.get(bookno);
        if(data.books.get(bookno).get(1).equals("0")){
            return "";
        } else if (!data.books.get(1).equals("0")&&ozellikler2.get(2).equals("0")) {//for read in lib
            cikti="The book ["+bookno+"] was returned by member ["+memberno+"] at "+line[3]+" Fee: 0";
        } else {//doing something on date for borrowing
            String sarttarih= (String) ozellikler2.get(2);
            LocalDateTime lastdate=LocalDateTime.of(Integer.parseInt(sarttarih.split("-")[0]),Integer.parseInt(sarttarih.split("-")[1]),Integer.parseInt(sarttarih.split("-")[2]),0,0,0);
            String[] date=((String) line[3]).split("-");
            LocalDateTime teslim=LocalDateTime.of(Integer.parseInt(date[0]),Integer.parseInt(date[1]),Integer.parseInt(date[2]),0,0,0);
            Duration fark=Duration.between(lastdate,teslim);
            if (fark.toDays()<=0){
                cikti="The book ["+bookno+"] was returned by member ["+memberno+"] at "+line[3]+" Fee: 0";
            }
            else {
                ozellikler1.set(3, Integer.toString((int) (fark.toDays()+Integer.parseInt((String) ozellikler1.get(3)))));
                cikti="The book ["+bookno+"] was returned by member ["+memberno+"] at "+line[3]+" Fee: "+fark.toDays();
                flag=false;
            }
        }
        ozellikler2.set(1,"0");
        ozellikler2.set(2,"0");
        ozellikler2.set(3,"0");
        ozellikler1.set(1,Integer.toString(Integer.parseInt((String) data.members.get(memberno).get(1))-1));
        data.members.put(memberno, (ArrayList) ozellikler1);
        data.books.put(bookno, (ArrayList) ozellikler2);
        if (flag==false){
            return cikti+"\n"+"You must pay a penalty!";
        }
        return cikti;
    }
}
