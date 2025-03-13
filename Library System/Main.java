import java.io.*;

public class Main {
    public static void main(String[] args) {
        Commands command =new Commands();
        DataBase data=new DataBase();
        try {
            BufferedReader buffr = new BufferedReader(new FileReader(args[0]));

            // output.txt dosyasına yazmak için PrintWriter kullanıyoruz
            PrintWriter printw = new PrintWriter(new FileWriter(args[1]));

            String satir;
            while ((satir = buffr.readLine()) != null) {
                data.gethistory();
                if(satir.startsWith("addMember")) {
                    printw.println(command.addmember(satir));
                }
                else if(satir.startsWith("addBook") ){
                    printw.println(command.addbook(satir));
                }
                else if(satir.startsWith("borrowBook")) {
                    printw.println(command.borrow(satir));
                } else if (satir.startsWith("readInLibrary")) {
                    printw.println(command.readinlib(satir));
                }
                else if (satir.startsWith("extendBook")) {
                    printw.println(command.extend(satir));
                }
                else if (satir.startsWith("returnBook")) {
                    printw.println(command.returnbook(satir));
                } else if (satir.startsWith("getTheHistory")) {
                    printw.println(data.gethistory());
                } else {
                    printw.println("Wrong Command");
                }

            }
            printw.close();
            buffr.close();
        } catch (FileNotFoundException e) {
        } catch (IOException e) {
        }catch (NumberFormatException e){
        }
    }
}



