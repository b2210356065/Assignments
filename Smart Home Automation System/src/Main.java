import java.io.*;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {


        BufferedReader reader = new BufferedReader(new FileReader(args[0]));
        PrintWriter writer = new PrintWriter(new FileWriter(args[1]));
        int kontrolcu=0;
        boolean kontrolcubool=false;
        String satir;
        Timeandcommands timecaller=new Timeandcommands();
        Devices objeler=new Devices();
        boolean setinitialtime=true;
        boolean zreport=true;//for controlling last zreport
        while ((satir = reader.readLine()) != null) {
            zreport=false;
            if(satir.equals("")){
                continue;
            }
            else {
                writer.println("COMMAND: "+satir);
            }
            try {
                kontrolcubool=false;
                ArrayList<Integer>time=new ArrayList<>();
                if (satir.length() >= 14 && satir.substring(0, 14).equals("SetInitialTime") && setinitialtime == true) {
                    kontrolcu++;
                    try {

                        timecaller.timevalue = satir.split("	")[1];
                        timecaller.hourminsec = timecaller.timevalue.split("_")[1];
                        timecaller.yearmonthday = timecaller.timevalue.split("_")[0];
                        timecaller.timevaluehour = timecaller.hourminsec.split(":");
                        timecaller.timevalueyear = timecaller.yearmonthday.split("-");

                        for (String value : timecaller.timevalueyear) {
                            if (value == null) {
                                break;
                            }
                            timecaller.timeList.add(Integer.parseInt(value));
                        }
                        for (String value : timecaller.timevaluehour) {
                            if (value == null) {
                                break;
                            }
                            timecaller.timeList.add(Integer.parseInt(value));
                        }
                        if (timecaller.timeList.size() == 6) {
                            int durum = 1;
                            if (timecaller.timeList.get(1) <= 12 && timecaller.timeList.get(2) <= 31 && timecaller.timeList.get(3) <= 24 && timecaller.timeList.get(4) <= 60 && timecaller.timeList.get(5) <= 60) {
                                durum = timecaller.time(timecaller.timeList.get(0), timecaller.timeList.get(1), timecaller.timeList.get(2), timecaller.timeList.get(3), timecaller.timeList.get(4), timecaller.timeList.get(5));
                                setinitialtime = false;
                                if (durum == 1) {

                                    writer.println("SUCCESS: Time has been set to " + satir.split("	")[1] + "!");
                                } else if (durum == 0) {
                                    writer.println("ERROR: This time is past");
                                }
                            } else {
                                writer.println("ERROR: Format of the initial date is wrong! Program is going to terminate!");
                                kontrolcu -= 2;
                            }
                        } else {
                            writer.println("ERROR: Format of the initial date is wrong! Program is going to terminate!");
                            kontrolcu -= 2;
                        }

                    }catch (ArrayIndexOutOfBoundsException j){
                        writer.println("ERROR: Format of the initial date is wrong! Program is going to terminate!");
                        kontrolcu -= 2;
                    }
                }


                else if (satir.length() >= 7&&satir.startsWith("SetTime")) {
                    timecaller.timevalue = satir.split("	")[1];
                    timecaller.hourminsec = timecaller.timevalue.split("_")[1];
                    timecaller.yearmonthday = timecaller.timevalue.split("_")[0];
                    timecaller.timevaluehour = timecaller.hourminsec.split(":");
                    timecaller.timevalueyear = timecaller.yearmonthday.split("-");

                    for (String value : timecaller.timevalueyear) {
                        if (value == null) {
                            break;
                        }
                        time.add(Integer.parseInt(value));
                    }
                    for (String value : timecaller.timevaluehour) {
                        if (value == null) {
                            break;
                        }
                        time.add(Integer.parseInt(value));
                    }
                    int durum=1;
                    if (time.get(1) <= 12 && time.get(2) <= 31 && time.get(3) <= 24 && time.get(4) <= 60 && time.get(5) <= 60) {
                        durum=timecaller.time(time.get(0), time.get(1), time.get(2), time.get(3), time.get(4), time.get(5));
                        if (durum==1) {
                        }
                        else if(durum==3){
                            writer.println("ERROR: There is nothing to change!");
                        }
                        else if (durum==0){
                            writer.println("ERROR: This time is past");
                        }
                    } else {
                        writer.println("ERROR: Time format is not correct!");
                    }
                }

                else if (satir.length() >= 3 &&satir.substring(0, 3).equals("Add")) {
                    int durumbelirleyici = objeler.adder(satir);
                    if (durumbelirleyici == 0) {
                        writer.println("ERROR: There is already a smart device with same name!");
                    }
                    if (durumbelirleyici == 1) {

                    }
                    if (durumbelirleyici == 2) {
                        writer.println("ERROR: Erroneous command!");
                    }
                    if (durumbelirleyici == 16) {
                        writer.println("ERROR: Ampere value must be a positive number!");
                    }
                    if (durumbelirleyici == 15) {
                        writer.println("ERROR: Kelvin value must be in range of 2000K-6500K!");
                    }
                    if (durumbelirleyici == 14) {
                        writer.println("ERROR: Brightness must be in range of 0%-100%!");
                    }
                    if (durumbelirleyici == 13) {
                        writer.println("ERROR: Megabyte value has to be a positive number!");
                    }
                    if (durumbelirleyici == 17) {
                        writer.println("ERROR: Color code value must be in range of 0x0-0xFFFFFF");
                    }
                }

                else if (satir.length() >= 11 &&satir.substring(0, 11).equals("SkipMinutes")) {
                    String skipminstr = satir.split("\t")[1];
                    if(satir.split("\t").length==2){
                        try {
                            int durum=timecaller.skipmin(Integer.parseInt(skipminstr));
                            if(durum==0){
                                writer.println("ERROR: Time cannot be reversed!");
                            }
                            else if(durum==1){

                            }
                            else if(durum==2){
                                writer.println("ERROR: There is nothing to skip!");
                            }
                        } catch (NumberFormatException e) {
                            writer.println("ERROR: Erroneous command!");
                        }
                    }
                    else {
                        writer.println("ERROR: Erroneous command!");
                    }
                }

                else if (satir.length() >= 6 &&satir.substring(0, 6).equals("Switch")) {
                    String namemain = satir.split("\t")[1];
                    String situationmain = satir.split("\t")[2];
                    int durumbelirleyici = timecaller.switchcommand(namemain, situationmain);
                    if (durumbelirleyici == 0) {
                        writer.println("ERROR: There is not such a device!");
                    }
                    if (durumbelirleyici == 1) {
                    }
                    if (durumbelirleyici == 2) {
                        writer.println("ERROR: This device is already switched on!");
                    }
                }

                else if (satir.length() >= 9 &&satir.startsWith("SetKelvin")){
                    String nameinmain = satir.split("\t")[1];
                    String secondersituationmain = satir.split("\t")[2];
                    int durum = timecaller.setkelvin(nameinmain, secondersituationmain);
                    if (durum == 1) {

                    }
                    if (durum == 0) {
                        writer.println("ERROR: Kelvin value must be in range of 2000K-6500K!");
                    }
                    if (durum == 2) {
                        writer.println("ERROR: Erroneous command!");
                    }
                    if (durum == 1) {

                    }
                    if (durum == 3) {
                        writer.println("ERROR: This device is already " + secondersituationmain + "K");
                    }
                    if (durum == 4) {
                        writer.println("ERROR: There is not such a device!");
                    }
                    if (durum == 5) {
                        writer.println("ERROR: This device is not a smart lamp!");
                    }
                }

                else if (satir.length() >="ChangeName".length() &&satir.substring(0, "ChangeName".length()).equals("ChangeName")) {
                    if (satir.contains("\t") && satir.split("\t").length == 3) {
                        int durum = timecaller.changename(satir.split("\t")[1], satir.split("\t")[2]);
                        if (durum == 1) {

                        }
                        if (durum == 0) {
                            writer.println("ERROR: There is not such a device!");
                        }
                        if (durum == 2) {
                            writer.println("ERROR: There is already a smart device with same name!");
                        }
                        if (durum == 3) {
                            writer.println("ERROR: Both of the names are the same, nothing changed!");
                        }
                    }
                }

                else if (satir.length() >= 8 &&satir.split("\t")[0].equals("SetColor")) {

                    int durum = timecaller.setcolor(satir.split("\t")[1], satir.split("\t")[2], satir.split("\t")[3]);
                    if (durum == 0) {
                        writer.println("ERROR: There is not such a device!");
                    }
                    if (durum == 1) {
                    }
                    if (durum == 2) {
                        writer.println("ERROR: Color code value must be in range of 0x0-0xFFFFFF");
                    }
                    if (durum == 3) {
                        writer.println("ERROR: This device is not a smart color lamp!");
                    }
                    if (durum == 4) {
                        writer.println("ERROR: Brightness must be in range of 0%-100%!");
                    }
                    if (durum == 5) {
                        writer.println("ERROR: Erroneous command!");
                    }


                }

                else if (satir.length() >= 12&&satir.substring(0, "setcolorcode".length()).equals("SetColorCode")) {
                    int durum = timecaller.setcolorcode(satir.split("\t")[1], satir.split("\t")[2]);
                    if (durum == 0) {
                        writer.println("ERROR: There is not such a device!");
                    }
                    if (durum == 1) {
                    }
                    if (durum == 2) {
                        writer.println("ERROR: Color code value must be in range of 0x0-0xFFFFFF");
                    }
                    if (durum == 3) {
                        writer.println("ERROR: This device is not a smart color lamp!");
                    }
                }

                else if (satir.length() >= 6&&satir.substring(0, 6).equals("Remove")) {
                    String key = satir.split("\t")[1];
                    String durum = timecaller.remove(key);
                    if (durum.equals("hata")) {
                        writer.println("ERROR: There is not such a device!");
                    } else {
                        String[] kelimeler = durum.split(" ");
                        writer.println("SUCCESS: Information about removed smart device is as follows:");
                        if (kelimeler[0].substring(kelimeler[0].length() - 2).equals("SP")) {
                            writer.println("Smart Plug " + key + " is " + kelimeler[0].substring(0, kelimeler[0].length() - 2) +
                                    " and consumed " + kelimeler[2] + "W so far (excluding current device), and its time to switch its status is null.");
                        }
                        if (kelimeler[0].substring(kelimeler[0].length() - 2).equals("SC")) {

                            writer.println("Smart Camera " + key + " is " + kelimeler[0].substring(0, kelimeler[0].length() - 2) +
                                    " and consumed " + kelimeler[2]
                                    + "MB so far (excluding current device), and its time to switch its status is null.");
                        }
                        if (kelimeler[0].substring(kelimeler[0].length() - 2).equals("CL")) {
                            if (kelimeler[1].startsWith("0x")) {
                                writer.println("Smart Color Lamp " + key + " is " + kelimeler[0].substring(0, kelimeler[0].length() - 2) + " and its color value is "
                                        + kelimeler[1].substring(0, kelimeler[1].length() - 2)
                                        + " with "
                                        + kelimeler[2].substring(0, kelimeler[2].length() - 2) + "% brightness, and its time to switch its status is null.");
                            } else {
                                writer.println("Smart Color Lamp " + key + " is " + kelimeler[0].substring(0, kelimeler[0].length() - 2) + " and its kelvin value is "
                                        + kelimeler[1].substring(0, kelimeler[1].length() - 2)
                                        + "K with "
                                        + kelimeler[2].substring(0, kelimeler[2].length() - 2) + "% brightness, and its time to switch its status is null.");
                            }
                        }
                        if (kelimeler[0].substring(kelimeler[0].length() - 2).equals("SL")) {
                            writer.println("Smart Lamp " + key + " is " + kelimeler[0].substring(0, kelimeler[0].length() - 2) + " and its kelvin value is "
                                    + kelimeler[1].substring(0, kelimeler[1].length() - 2)
                                    + "K with "
                                    + kelimeler[2].substring(0, kelimeler[2].length() - 2) + "% brightness, and its time to switch its status is null.");

                        }
                    }
                }

                else if (satir.length() >= 6&&satir.substring(0, "plugin".length()).equals("PlugIn")) {
                    int durum = timecaller.plugin(satir.split("\t")[1], satir.split("\t")[2]);
                    if (durum == 1) {

                    }
                    if (durum == 0) {
                        writer.println("ERROR: There is not such a device!");
                    }
                    if (durum == 2) {
                        writer.println("ERROR: Ampere value must be a positive number!");
                    }
                    if (durum == 3) {
                        writer.println("ERROR: This device is not a smart plug!");
                    }
                    if (durum == 4) {
                        writer.println("ERROR: There is already an item plugged in to that plug!");
                    }
                }

                else if (satir.length() >= 7&&satir.substring(0, "plugout".length()).equals("PlugOut")) {
                    int durum = timecaller.plugout(satir.split("\t")[1]);
                    objeler.plugcontroller.put(satir.split("\t")[1],"plugout");
                    if (durum == 0) {
                        writer.println("ERROR: There is not such a device!");
                    }
                    if (durum == 1) {

                    }
                    if (durum == 2) {
                        writer.println("ERROR: This device is not a smart plug!");
                    }
                    if (durum == 3) {
                        writer.println("ERROR: This plug has no item to plug out from that plug!");
                    }
                }

                else if (satir.length() >= "setbrightness".length()&&satir.substring(0, "setbrightness".length()).equals("SetBrightness")) {
                    int durum = timecaller.brightness(satir.split("\t")[1], satir.split("\t")[2]);
                    if (durum == 0) {
                        writer.println("ERROR: There is not such a device!");
                    }
                    if (durum == 1) {

                    }
                    if (durum == 2) {
                        writer.println("ERROR: This device is not a smart lamp!");
                    }
                    if (durum == 3) {
                        writer.println("ERROR: Brightness must be in range of 0%-100%!");
                    }
                }

                else if (satir.length() >= "setwhite".length()&&satir.substring(0, "setwhite".length()).equals("SetWhite")) {
                    int durum = timecaller.setwhite(satir.split("\t")[1], satir.split("\t")[2], satir.split("\t")[3]);
                    if (durum == 1) {

                    }
                    if (durum == 0) {
                        writer.println("ERROR: Erroneous command!");
                    }
                    if (durum == 2) {
                        writer.println("ERROR: There is not such a device!");
                    }
                }


                else if (satir.length() >= "setSwitch".length()&&satir.startsWith("SetSwitch")) {
                    int durum = timecaller.setswitch(satir.split("\t")[1], satir.split("\t")[2]);
                    if (durum == 0) {
                        writer.println("ERROR: Switch time cannot be in the past!");
                    }
                    if (durum == 1) {

                    }
                    if (durum == 2) {
                        writer.println("ERROR: There is not such a device!");
                    }
                }

                else if (satir.length() >= 3&&satir.startsWith("Nop")) {
                    boolean truefornop=true;
                    if (objeler.devicessituation.size() == 0) {
                        writer.println("ERROR: There is nothing to switch!");
                    } else {
                        for (int i=0;i<timecaller.swicthed.size();i++){
                            if (timecaller.now.isBefore(timecaller.swicthed.get(i))) {
                                LocalDateTime timenop1=timecaller.swicthed.get(i);
                                timecaller.time(timenop1.getYear(),timenop1.getMonthValue(),timenop1.getDayOfMonth(),timenop1.getHour(),timenop1.getMinute(),timenop1.getSecond());
                                truefornop = true;
                                break;
                            } else {
                                truefornop = false;
                            }
                        }
                    }
                    if (truefornop==true){

                    }
                    else {
                        writer.println("ERROR: This time is past");
                    }
                }



                else if (satir.length() >= 7&&satir.startsWith("ZReport")) {
                    List<String> keyList = new ArrayList<>(objeler.devicessituation.keySet());
                    writer.println("Time is:\t" + Integer.toString(timecaller.now.getYear()) + "-" + Integer.toString(timecaller.now.getMonthValue()) + "-" + Integer.toString(timecaller.now.getDayOfMonth()) + "_"
                            + Integer.toString(timecaller.now.getHour()) + ":" + Integer.toString(timecaller.now.getMinute()) + ":" + Integer.toString(timecaller.now.getSecond()));
                    List<String> keyListplug = new ArrayList<>(objeler.plugcontroller.keySet());
                    for (String key:keyListplug) {
                        if (objeler.plugcontroller.get(key).equals("plugin")){
                            if (objeler.devicessituation.get(key).startsWith("Off"))
                                timecaller.plugout(key);
                        }

                    }
                    for (String key : keyList) {
                        if (objeler.devicessituation.get(key).substring(objeler.devicessituation.get(key).length() - 2).equals("SP")) {
                            if (objeler.devicessituation.get(key).substring(0,objeler.devicessituation.get(key).length() - 2).equals("On")) {
                                writer.println("Smart Plug " + key + " is " + objeler.devicessituation.get(key).substring(0, objeler.devicessituation.get(key).length() - 2) +
                                        " and consumed " + timecaller.Energy.get(key) + "W so far (excluding current device), and its time to switch its status is null.");
                            }
                            else {
                                writer.println("Smart Plug " + key + " is " + objeler.devicessituation.get(key).substring(0, objeler.devicessituation.get(key).length() - 2) +
                                        " and consumed " + timecaller.Energy.get(key)+ "W so far (excluding current device), and its time to switch its status is null.");
                            }
                        }
                        if (objeler.devicessituation.get(key).substring(objeler.devicessituation.get(key).length() - 2).equals("SC")) {
                            writer.println("Smart Camera " + key + " is " + objeler.devicessituation.get(key).substring(0, objeler.devicessituation.get(key).length() - 2) +
                                    " and consumed " + timecaller.Energy.get(key)
                                    + "MB so far (excluding current device), and its time to switch its status is null.");
                        }
                        if(objeler.devicessituation.get(key).substring(objeler.devicessituation.get(key).length()-2).equals("CL")) {
                            if (objeler.devicessecondersituation.get(key).startsWith("0x")){
                                writer.println("Smart Color Lamp " + key + " is " + objeler.devicessituation.get(key).substring(0, objeler.devicessituation.get(key).length() - 2) + " and its color value is "
                                        + objeler.devicessecondersituation.get(key).substring(0, objeler.devicessecondersituation.get(key).length() - 2)
                                        + " with "
                                        + objeler.devicestriplersituation.get(key).substring(0, objeler.devicestriplersituation.get(key).length() - 2) + "% brightness, and its time to switch its status is null.");
                            }
                            else{
                                writer.println("Smart Color Lamp " + key + " is " + objeler.devicessituation.get(key).substring(0, objeler.devicessituation.get(key).length() - 2) + " and its kelvin value is "
                                        + objeler.devicessecondersituation.get(key).substring(0, objeler.devicessecondersituation.get(key).length() - 2)
                                        + "K with "
                                        + objeler.devicestriplersituation.get(key).substring(0, objeler.devicestriplersituation.get(key).length() - 2) + "% brightness, and its time to switch its status is null.");
                            }
                        }
                        if(objeler.devicessituation.get(key).substring(objeler.devicessituation.get(key).length()-2).equals("SL")) {
                            writer.println("Smart Lamp "+key +" is " +objeler.devicessituation.get(key).substring(0,objeler.devicessituation.get(key).length() - 2) + " and its kelvin value is "
                                    +objeler.devicessecondersituation.get(key).substring(0,objeler.devicessecondersituation.get(key).length() - 2)
                                    +"K with "+objeler.devicestriplersituation.get(key).substring(0,objeler.devicestriplersituation.get(key).length() - 2)+"% brightness, and its time to switch its status is null.");
                        }
                        zreport = true;
                    }
                }
                else {
                    writer.println("ERROR: Erroneous command!");
                }
                if (kontrolcu==0){
                    kontrolcubool=true;
                    writer.println("ERROR: First command must be set initial time! Program is going to terminate!");
                    break;
                }
                if (kontrolcu==-1){
                    kontrolcubool=true;
                    break;
                }
            } catch (DateTimeParseException e) {
                writer.println("ERROR: Time format is not correct!");
                e.printStackTrace();
            }catch (ArrayIndexOutOfBoundsException h){
                writer.println("ERROR: Erroneous command!");
            }
        }
        if (zreport||kontrolcubool==false){
            writer.println("ZReport:");
            List<String> keyListplug = new ArrayList<>(objeler.plugcontroller.keySet());
            for (String key:keyListplug) {
                if (objeler.plugcontroller.get(key).equals("plugin")){
                    objeler.plugcontroller.put(key, "plugout");
                }

            }
            List<String> keyList = new ArrayList<>(objeler.devicessituation.keySet());
            writer.println("Time is:\t"+Integer.toString(timecaller.now.getYear())+"-"+Integer.toString(timecaller.now.getMonthValue())+"-"+Integer.toString(timecaller.now.getDayOfMonth())+"_"
                    +Integer.toString(timecaller.now.getHour())+":"+Integer.toString(timecaller.now.getMinute())+":"+Integer.toString(timecaller.now.getSecond()));
            for(String key :keyList){
                if(objeler.devicessituation.get(key).substring(objeler.devicessituation.get(key).length()-2).equals("SP")) {
                    if (objeler.devicessituation.get(key).substring(0,objeler.devicessituation.get(key).length() - 2).equals("On")) {
                        writer.println("Smart Plug " + key + " is " + objeler.devicessituation.get(key).substring(0, objeler.devicessituation.get(key).length() - 2) +
                                " and consumed " + timecaller.Energy.get(key) + "W so far (excluding current device), and its time to switch its status is null.");
                    }
                    else {
                        writer.println("Smart Plug " + key + " is " + objeler.devicessituation.get(key).substring(0, objeler.devicessituation.get(key).length() - 2) +
                                " and consumed " + timecaller.Energy.get(key)+ "W so far (excluding current device), and its time to switch its status is null.");
                    }
                }
                if(objeler.devicessituation.get(key).substring(objeler.devicessituation.get(key).length()-2).equals("SC")) {
                    String name=key;
                    if(objeler.devicessituation.get(name).substring(0,objeler.devicessituation.get(name).length()-2).equals("On")) {

                        LocalDateTime pastsecond = objeler.timecontroller.get(name);
                        objeler.timecontroller.put(name,timecaller.now);
                        long howmuch = objeler.timecontrollerfark.get(name);
                        Duration duration=Duration.between(pastsecond,timecaller.now);
                        long fark=duration.toMinutes();
                        objeler.timecontrollerfark.put(name, howmuch+fark);
                        timecaller.Energy.put(name, (double) ((howmuch+fark)*Double.parseDouble(objeler.devicessecondersituation.get(name).substring(0,objeler.devicessecondersituation.get(name).length()-2))));
                    }
                    writer.println("Smart Camera " + key + " is " + objeler.devicessituation.get(key).substring(0,objeler.devicessituation.get(key).length() - 2) +
                            " and consumed " + timecaller.Energy.get(key)
                            + "MB so far (excluding current device), and its time to switch its status is null.");
                }
                if(objeler.devicessituation.get(key).substring(objeler.devicessituation.get(key).length()-2).equals("CL")) {
                    if (objeler.devicessecondersituation.get(key).startsWith("0x")){
                        writer.println("Smart Color Lamp " + key + " is " + objeler.devicessituation.get(key).substring(0, objeler.devicessituation.get(key).length() - 2) + " and its color value is "
                                + objeler.devicessecondersituation.get(key).substring(0, objeler.devicessecondersituation.get(key).length() - 2)
                                + " with "
                                + objeler.devicestriplersituation.get(key).substring(0, objeler.devicestriplersituation.get(key).length() - 2) + "% brightness, and its time to switch its status is null.");
                    }
                    else{
                        writer.println("Smart Color Lamp " + key + " is " + objeler.devicessituation.get(key).substring(0, objeler.devicessituation.get(key).length() - 2) + " and its kelvin value is "
                                + objeler.devicessecondersituation.get(key).substring(0, objeler.devicessecondersituation.get(key).length() - 2)
                                + "K with "
                                + objeler.devicestriplersituation.get(key).substring(0, objeler.devicestriplersituation.get(key).length() - 2) + "% brightness, and its time to switch its status is null.");
                    }
                }
                if(objeler.devicessituation.get(key).substring(objeler.devicessituation.get(key).length()-2).equals("SL")) {
                    writer.println("Smart Lamp "+key +" is " +objeler.devicessituation.get(key).substring(0,objeler.devicessituation.get(key).length() - 2) + " and its kelvin value is "
                            +objeler.devicessecondersituation.get(key).substring(0,objeler.devicessecondersituation.get(key).length() - 2)
                            +"K with "+objeler.devicestriplersituation.get(key).substring(0,objeler.devicestriplersituation.get(key).length() - 2)+"% brightness, and its time to switch its status is null.");
                }
            }

        }
        writer.close();
    }
}