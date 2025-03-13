import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class Timeandcommands extends Main{

    public static Map<String,LocalDateTime> setswitchtime=new TreeMap<>();
    public static ArrayList<String>setswitchtimelist=new ArrayList<>();
    public static ArrayList<LocalDateTime>swicthed=new ArrayList<>();
    public int year;
    public int month;
    public int day;
    public int hour;
    public int min;
    public int sec;
    int skipingtime;
    String[] timevaluehour;
    String[] timevalueyear;
    String timevalue;
    String yearmonthday;
    String hourminsec;
    public ArrayList<Integer> timeList = new ArrayList<Integer>();
    Devices objeler=new Devices();
    public static LocalDateTime now=LocalDateTime.of(0,1,1,0,0,0,0);
    private LocalDateTime pastcontroller=LocalDateTime.of(0,1,1,0,0,0,0);//for controlling given time is before or after//ENCAPSULATION
    public static HashMap<String,Double>Energy=new HashMap<>();//plug energy map

    public Timeandcommands(){
    }


    public int time (int year,int month,int day,int hour,int min,int sec){
        this.year=year;
        this.month=month;
        this.day=day;
        this.hour=hour;
        this.min=min;
        this.sec=sec;
        this.pastcontroller = LocalDateTime.of(year,month,day,hour,min,sec);
        if(pastcontroller.isEqual(now)){
            return 3;
        }
        else if (pastcontroller.isAfter(now)){
            this.now = LocalDateTime.of(year,month,day,hour,min,sec);
        }
        else {
            return 0;
        }
        try {
            for (String name : setswitchtimelist) {
                if (now.isBefore(setswitchtime.get(name))) {

                }
                else {
                    if (objeler.devicessituation.get(name).substring(0, objeler.devicessituation.get(name).length() - 2).equals("On")) {
                        switchcommand(name, "Off");
                        setswitchtime.remove(name);
                    } else if (objeler.devicessituation.get(name).substring(0, objeler.devicessituation.get(name).length() - 2).equals("Off")) {
                        switchcommand(name, "On");
                        setswitchtime.remove(name);
                    }
                }

            }
            return 1;
        }catch (NullPointerException e){
            return 2;
        }
    }
    /*I defined the functions of the commands as integers because returning a number allowed me to give output based on the type of error, as different types of errors can occur.*/
    public int setwhite(String name,String kelvin,String brightness){
        if (this.objeler.devicessituation.containsKey(name)){
            if (this.objeler.devicessituation.get(name).substring(this.objeler.devicessituation.get(name).length()-2).equals("CL")) {
                try {
                    if (Integer.parseInt(kelvin) <= 6500 && Integer.parseInt(kelvin) >= 2500 && Integer.parseInt(brightness) >= 0 && Integer.parseInt(brightness) <= 100) {
                        this.objeler.devicessecondersituation.put(name, kelvin);
                        this.objeler.devicestriplersituation.put(name, brightness);
                        this.objeler.lampscolor.put(name, "0xFFFFFF");
                        return 1;
                    }
                } catch (NumberFormatException e) {
                    return 0;
                }
            }

            if (this.objeler.devicessituation.get(name).substring(this.objeler.devicessituation.get(name).length()-2).equals("SL")) {
                try {
                    if (Integer.parseInt(kelvin) <= 6500 && Integer.parseInt(kelvin) >= 2500 && Integer.parseInt(brightness) >= 0 && Integer.parseInt(brightness) <= 100) {
                        this.objeler.devicessecondersituation.put(name, kelvin);
                        this.objeler.devicestriplersituation.put(name, brightness);
                        return 1;
                    }
                } catch (NumberFormatException e) {
                    return 0;
                }
            }

        }
        return 2;
    }


    public int plugout(String name){
        double energy=-1.0;
        String amper="-1";
        long time=-1;
        if (this.objeler.devicessituation.containsKey(name)) {
            if (this.objeler.devicessituation.get(name).substring(this.objeler.devicessituation.get(name).length() - 2, this.objeler.devicessituation.get(name).length()).equals("SP")) {
                try {
                    if (objeler.plugcontroller.get(name).equals("plugout")){
                        return 3;
                    }
                    else if(objeler.plugcontroller.get(name).equals("plugin")){
                        if (objeler.devicessituation.get(name).substring(0, objeler.devicessituation.get(name).length() - 2).equals("On")) {
                            Duration duration = Duration.between(objeler.timecontroller.get(name),now);
                            time = duration.toMinutes() + objeler.timecontrollerfark.get(name);
                            amper = objeler.devicessecondersituation.get(name).substring(0, objeler.devicessecondersituation.get(name).length() - 2);
                            try {
                                energy =(float) 220/60 * Double.parseDouble(amper) * time;
                            } catch (NumberFormatException e) {
                                return 15;
                            }
                        } else {
                            if (objeler.timecontrollerfark.containsKey(name)) {
                                time = objeler.timecontrollerfark.get(name);
                            }
                            if (objeler.devicessecondersituation.containsKey(name)) {
                                amper = objeler.devicessecondersituation.get(name).substring(0, objeler.devicessecondersituation.get(name).length() - 2);
                            }
                            try {
                                energy = (float)220 * Double.parseDouble(amper) * time/60;
                            } catch (NumberFormatException e) {
                                return 15;
                            }
                        }
                        Energy.put(name,Math.round(energy * 100.0) / 100.0);
                        return 1;
                    }
                    else {
                        return 17;
                    }
                }catch (NullPointerException e){
                    return 14;
                }
            }
            else {
                return 2;
            }
        }
        else{
            return 0;
        }
    }
    public int plugin(String name,String amper) {
        if (Double.parseDouble(amper) > 0) {
            if (this.objeler.devicessituation.containsKey(name)) {
                if (this.objeler.devicessituation.get(name).substring(this.objeler.devicessituation.get(name).length() - 2, this.objeler.devicessituation.get(name).length()).equals("SP")) {
                    try {
                        if (this.objeler.plugcontroller.get(name).equals("plugin")) {
                            return 4;
                        }
                        else {
                            if (objeler.devicessituation.get(name).substring(0, objeler.devicessituation.get(name).length() - 2).equals("On")) {
                                objeler.devicessecondersituation.put(name, amper+"SP");
                                objeler.plugcontroller.put(name, "plugin");
                                objeler.timecontrollerfark.put(name, (long) 0);
                                objeler.timecontroller.put(name, now);
                            } else if (objeler.devicessituation.get(name).substring(0, objeler.devicessituation.get(name).length() - 2).equals("Off")) {
                                objeler.devicessecondersituation.put(name, amper+"SP");
                                objeler.plugcontroller.put(name, "plugin");
                                objeler.timecontrollerfark.put(name, (long) 0);
                            }
                        }
                    } catch (NullPointerException e) {
                        if (objeler.devicessituation.get(name).substring(0, objeler.devicessituation.get(name).length() - 2).equals("On")) {
                            objeler.devicessecondersituation.put(name, amper+"SP");
                            objeler.plugcontroller.put(name, "plugin");
                            objeler.timecontrollerfark.put(name, (long) 0);
                            objeler.timecontroller.put(name, now);
                        } else if (objeler.devicessituation.get(name).substring(0, objeler.devicessituation.get(name).length() - 2).equals("Off")) {
                            objeler.devicessecondersituation.put(name, amper+"SP");
                            objeler.plugcontroller.put(name, "plugin");
                            objeler.timecontrollerfark.put(name, (long) 0);
                        }
                    }
                    return 1;
                }
                return 3;
            }
            else {
                return 0;
            }
        }
        else {
            return 2;
        }
    }
    public int changename(String namealready,String newname){
        if (namealready.equals(newname)){
            return 3;
        }
        else {
            if (this.objeler.devicessituation.containsKey(namealready)) {
                if (this.objeler.devicessituation.containsKey(newname)) {
                    return 2;
                } else {
                    this.objeler.devicessituation.put(newname, this.objeler.devicessituation.get(namealready));
                    this.objeler.devicessituation.remove(namealready);
                    this.objeler.devicessecondersituation.put(newname, this.objeler.devicessecondersituation.get(namealready));
                    this.objeler.devicessecondersituation.remove(namealready);
                    this.objeler.devicestriplersituation.put(newname, this.objeler.devicestriplersituation.get(namealready));
                    this.objeler.devicestriplersituation.remove(namealready);
                    this.objeler.timecontroller.put(newname, this.objeler.timecontroller.get(namealready));
                    this.objeler.timecontroller.remove(namealready);
                    this.objeler.timecontrollerfark.put(newname, this.objeler.timecontrollerfark.get(namealready));
                    this.objeler.timecontrollerfark.remove(namealready);
                    setswitchtime.put(newname, setswitchtime.get(namealready));
                    setswitchtime.remove(namealready);
                    setswitchtimelist.add(newname);
                    setswitchtimelist.remove(namealready);
                    Energy.put(newname, Energy.get(namealready));
                    Energy.remove(namealready);
                    if (this.objeler.plugcontroller.containsKey(namealready)) {
                        this.objeler.plugcontroller.put(newname, this.objeler.plugcontroller.get(namealready));
                        this.objeler.plugcontroller.remove(namealready);
                    }
                    if (this.objeler.lampscolor.containsKey(namealready)) {
                        this.objeler.lampscolor.put(newname, this.objeler.lampscolor.get(namealready));
                        this.objeler.lampscolor.remove(namealready);
                    }
                    return 1;
                }
            } else {
                return 0;
            }
        }
    }
    public String remove(String name){
        if (objeler.devicessituation.get(name).startsWith("On")){
            switchcommand(name,"Off");
        }
        if (objeler.plugcontroller.containsKey(name)) {
            if (objeler.plugcontroller.get(name).equals("plugin")) {
                plugout(name);
            }
        }
        if(objeler.devicessituation.get(name).substring(objeler.devicessituation.get(name).length()-2).equals("SC")) {

            LocalDateTime pastsecond = objeler.timecontroller.get(name);
            objeler.timecontroller.put(name,now);
            long howmuch = objeler.timecontrollerfark.get(name);
            Duration duration=Duration.between(pastsecond,now);
            long fark=duration.toMinutes();
            objeler.timecontrollerfark.put(name, howmuch+fark);
            Energy.put(name, (double) ((howmuch+fark)*Double.parseDouble(objeler.devicessecondersituation.get(name).substring(0,objeler.devicessecondersituation.get(name).length()-2))));
        }
        if (this.objeler.devicessituation.containsKey(name)) {
            String one= this.objeler.devicessituation.get(name);
            this.objeler.devicessituation.remove(name);
            String two=this.objeler.devicessecondersituation.get(name);
            this.objeler.devicessecondersituation.remove(name);
            String three=this.objeler.devicestriplersituation.get(name);
            this.objeler.devicestriplersituation.remove(name);
            this.objeler.timecontroller.remove(name);
            this.objeler.timecontrollerfark.remove(name);
            setswitchtime.remove(name);
            setswitchtimelist.remove(name);
            if(three==null){
                three= String.valueOf(Energy.get(name));
            }
            Energy.remove(name);
            if (this.objeler.plugcontroller.containsKey(name)) {
                this.objeler.plugcontroller.remove(name);
            }
            if (this.objeler.lampscolor.containsKey(name)) {
                this.objeler.lampscolor.remove(name);
            }
            return one+" "+two+" "+three;

        } else {
            return "hata";
        }

    }
    public int skipmin(int skipingtime){

        if (skipingtime < 0) {
            return 0;
        }
        if (skipingtime==0){
            return 2;
        }
        this.now=this.now.plusMinutes(skipingtime);
        this.pastcontroller=now;
        return 1;
    }
//In this function I controll the plugin plug out commands becasuse if there is a on or off for any device it must be go this function and I can controll what is it situation
    public int switchcommand(String name,String situation){
        if(this.objeler.devicessituation.containsKey(name)){
            String onofstiuation = this.objeler.devicessituation.get(name).substring(0,this.objeler.devicessituation.get(name).length()-2);
            String devicetype=this.objeler.devicessituation.get(name).substring(this.objeler.devicessituation.get(name).length()-2);
            if (onofstiuation.equals(situation)){
                return 2;
            }
            else{
                if (situation.equals("On")){
                    objeler.devicessituation.put(name,"On"+devicetype);
                    if (objeler.plugcontroller.containsKey(name)) {
                        objeler.timecontroller.put(name,now);
                    }
                    if(objeler.devicessituation.get(name).substring(objeler.devicessituation.get(name).length()-2).equals("SC")) {
                        objeler.timecontroller.put(name, now);
                    }
                }
                else if(situation.equals("Off")){
                    objeler.devicessituation.put(name,"Off"+devicetype);
                    if (objeler.plugcontroller.containsKey(name)) {
                        if (objeler.plugcontroller.get(name).equals("plugin")) {
                            LocalDateTime pastsecond = objeler.timecontroller.get(name);
                            objeler.timecontroller.put(name,now);
                            long howmuch = objeler.timecontrollerfark.get(name);
                            Duration duration=Duration.between(pastsecond,now);
                            long fark=duration.toMinutes();
                            objeler.timecontrollerfark.put(name, howmuch+fark);
                            plugout(name);
                        }
                    }
                    if(objeler.devicessituation.get(name).substring(objeler.devicessituation.get(name).length()-2).equals("SC")) {

                        LocalDateTime pastsecond = objeler.timecontroller.get(name);
                        objeler.timecontroller.put(name,now);
                        long howmuch = objeler.timecontrollerfark.get(name);
                        Duration duration=Duration.between(pastsecond,now);
                        long fark=duration.toMinutes();
                        objeler.timecontrollerfark.put(name, howmuch+fark);
                        Energy.put(name, (double) ((howmuch+fark)*Double.parseDouble(objeler.devicessecondersituation.get(name).substring(0,objeler.devicessecondersituation.get(name).length()-2))));
                    }
                }
                return 1;
            }
        }
        else {
            return 0;
        }
    }
    public int setkelvin(String name,String secondersituation){
        try {
            if (Integer.parseInt(secondersituation) <= 6500 && Integer.parseInt(secondersituation) >= 2500) {
                String secondersit = this.objeler.devicessecondersituation.get(name);
                try {
                    if (objeler.devicessituation.get(name).substring(objeler.devicessituation.get(name).length() - 2).equals("SL")
                            ||
                            objeler.devicessituation.get(name).substring(objeler.devicessituation.get(name).length() - 2).equals("CL")) {
                        if (secondersit.substring(secondersit.length() - 2).equals(secondersituation)) {
                            return 3;

                        } else {
                            this.objeler.devicessecondersituation.put(name, secondersituation + secondersit.substring(secondersit.length() - 2, secondersit.length()));
                            return 1;
                        }
                    }
                    else {
                        return 5;
                    }
                }catch (NullPointerException e){
                    return 4;
                }
            }
            else {
                return 0;
            }
        }catch (NumberFormatException e){
            return 2;
        }
    }
    public int brightness(String name,String value){
        if(this.objeler.devicessituation.containsKey(name)){
            if(this.objeler.devicessituation.get(name).substring(this.objeler.devicessituation.get(name).length()-2,this.objeler.devicessituation.get(name).length()).equals("SL")
                    ||
                    this.objeler.devicessituation.get(name).substring(this.objeler.devicessituation.get(name).length()-2,this.objeler.devicessituation.get(name).length()).equals("Cl")){
                try {
                    if (Integer.parseInt(value) >=0 && Integer.parseInt(value) <= 100){
                        this.objeler.devicestriplersituation.put(name, value);
                        return 1;
                    }
                    else {
                        return 3;
                    }
                }catch (NumberFormatException e){
                    return 6;
                }
            }
            else{
                return 2;
            }

        }
        return 0;
    }
    //set switch function understands the time and it keep this on memory
    public int setswitch(String name, String date){
        if (objeler.devicessituation.containsKey(name)) {
            ArrayList<Integer> time = new ArrayList<>();
            this.timevalue = date;
            hourminsec = timevalue.split("_")[1];
            yearmonthday = timevalue.split("_")[0];
            timevaluehour = hourminsec.split(":");
            timevalueyear = yearmonthday.split("-");
            for (String value : timevalueyear) {
                if (value == null) {
                    break;
                }
                time.add(Integer.parseInt(value));
            }
            for (String value : timevaluehour) {
                if (value == null) {
                    break;
                }
                time.add(Integer.parseInt(value));
            }
            LocalDateTime setswtichlocaldate = LocalDateTime.of(time.get(0), time.get(1), time.get(2), time.get(3), time.get(4), time.get(5));
            if (now.isBefore(setswtichlocaldate)) {
                setswitchtime.put(name, setswtichlocaldate);
                setswitchtimelist.add(name);
                swicthed.add(setswtichlocaldate);
                Collections.sort(swicthed);
                return 1;
            }
            else if(now.isEqual(setswtichlocaldate)){
                if(objeler.devicessituation.get(name).startsWith("On")){
                    switchcommand(name,"Off");
                    return 1;
                }
                else{
                    switchcommand(name,"On");
                    return 1;
                }
            } else {
                return 0;
            }
        }else {
            return 2;
        }
    }
    public int setcolorcode(String name,String code){
        if (objeler.devicessituation.containsKey(name)){
            if (objeler.lampscolor.containsKey(name)){
                if(objeler.isHexadecimal(code)){
                    objeler.lampscolor.put(name,code);
                    return 1;
                }
                else {
                    return 2;
                }
            }
            else {
                return 3;
            }
        }
        else {
            return 0;
        }
    }
    public int setcolor(String name,String code,String brightness){
        if (objeler.devicessituation.containsKey(name)){
            if (objeler.lampscolor.containsKey(name)){
                if(objeler.isHexadecimal(code)){
                    try {
                        if (0<=Integer.parseInt(brightness)&&Integer.parseInt(brightness)<=100){
                            objeler.lampscolor.put(name,code);
                            objeler.devicestriplersituation.put(name,brightness);
                            return 1;
                        }
                        else {
                            return 4;
                        }
                    }catch (NumberFormatException e){
                        return 5;
                    }
                }
                else {
                    return 2;
                }
            }
            else {
                return 3;
            }
        }
        else {
            return 0;
        }
    }

}
