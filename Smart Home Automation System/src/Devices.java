import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;

public class Devices {
    /*This code section initializes hash maps to store the primary, secondary, and tertiary properties of devices." +
            " Additionally, it creates maps to track plug-in commands and the time when the devices were plugged in. These maps are used to record and retrieve information about devices.*/
    public static HashMap<String,String> devicessituation=new HashMap<>();//Abstraction
    public static HashMap<String,String>devicessecondersituation=new HashMap<>();//Abstraction
    public static HashMap<String,String>devicestriplersituation=new HashMap<>();//Abstraction
    public static HashMap<String,String>lampscolor=new HashMap<>();
    public static HashMap<String,String>plugcontroller=new HashMap<>();
    public static HashMap<String, LocalDateTime> timecontroller=new HashMap<String, java.time.LocalDateTime>();
    public static HashMap<String,Long>timecontrollerfark=new HashMap<>();
    public Devices() {

    }
    public static boolean isHexadecimal(String colorCode) {
        colorCode = colorCode.toUpperCase();
        if (colorCode.startsWith("#") && colorCode.length() == 7) {
            for (int i = 1; i < colorCode.length(); i++) {
                char c = colorCode.charAt(i);
                if (!Character.isDigit(c) && (c < 'A' || c > 'F')) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }


    public int adder(String input){
        int durum=2;
        /*

         This method adds a new device to the system based on the input string provided.
         It first splits the input string into an array of words and determines the type of device being added.
         Depending on the device type, an instance of the corresponding class is created and its "adder" method is called.
         The return value from the adder method is then returned by this method.
         @param input the input string to be processed
         @return an integer representing the status of adding the device (0 for already existing, 1 for successfully added, 2 for invalid input, 16 for negative energy value)
         */
        String[]kelimeler= input.split("\t");
        if (kelimeler[1].equals("SmartLamp")) {
            Lamp lamba=new Lamp();
            durum=lamba.adder(input);
        }
        if (kelimeler[1].equals("SmartPlug")) {
            Plug priz=new Plug();
            durum=priz.adder(input);
        }
        if (kelimeler[1].equals("SmartCamera")) {
            Camera kamera=new Camera();
            durum=kamera.adder(input);
        }
        if (kelimeler[1].equals("SmartColorLamp")) {
            LampColour renklilamba=new LampColour();
            durum=renklilamba.adder(input);
        }
        return durum;
    }

    public static class Plug extends Devices{//INHETIRENCE
        Timeandcommands timecaller=new Timeandcommands();
        /*

         This method adds a device to the system and updates its status and energy consumption.

         @param input the input string containing information about the device

         @return 0 if the device is already in the system, 1 if the device is added successfully, 2 if the input format is invalid, 16 if the energy consumption value is negative.
         */
        public int adder(String input){//OVER WRITING
            Timeandcommands timecaller=new Timeandcommands();
            String[]kelimeler= input.split("\t");
            if(this.devicessituation.containsKey(kelimeler[2])) {
                return 0;
            }
            else {
                try {
                    if(kelimeler[3].equals("On")){
                    }
                    else if(kelimeler[3].equals("Off")){
                    }
                    else {
                        return 2;
                    }
                    try {
                        if (Double.parseDouble(kelimeler[4])>0){;
                        }
                        else{
                            return 16;
                        }
                    }catch (NumberFormatException e){
                        return 2;
                    }
                }catch (ArrayIndexOutOfBoundsException e){
                    try {
                        this.devicessituation.put(kelimeler[2], kelimeler[3] + "SP");
                    }catch (ArrayIndexOutOfBoundsException w){
                        this.devicessituation.put(kelimeler[2], "Off" + "SP");
                        this.devicessecondersituation.put(kelimeler[2], "0" + "SP");
                        timecaller.Energy.put(kelimeler[2],0.0);
                        plugcontroller.put(kelimeler[2],"plugout");
                    }
                    try {
                        this.devicessecondersituation.put(kelimeler[2], kelimeler[4] + "SP");
                    }catch (ArrayIndexOutOfBoundsException w){
                        this.devicessecondersituation.put(kelimeler[2], "0" + "SP");
                        timecaller.Energy.put(kelimeler[2],0.0);
                        plugcontroller.put(kelimeler[2],"plugout");
                    }
                    timecaller.Energy.put(kelimeler[2],0.0);
                    plugcontroller.put(kelimeler[2],"plugout");
                    return 1;
                }
                try {
                    this.devicessituation.put(kelimeler[2], kelimeler[3] + "SP");
                }catch (ArrayIndexOutOfBoundsException e){
                    this.devicessituation.put(kelimeler[2], "Off" + "SP");
                    this.devicessecondersituation.put(kelimeler[2], "0" + "SP");
                    timecaller.Energy.put(kelimeler[2],0.0);
                    plugcontroller.put(kelimeler[2],"plugout");
                }
                try {
                    this.devicessecondersituation.put(kelimeler[2], kelimeler[4] + "SP");
                }catch (ArrayIndexOutOfBoundsException e){
                    this.devicessecondersituation.put(kelimeler[2], "0" + "SP");
                    timecaller.Energy.put(kelimeler[2],0.0);
                    plugcontroller.put(kelimeler[2],"plugout");
                }
                timecaller.Energy.put(kelimeler[2],0.0);
                plugcontroller.put(kelimeler[2],"plugout");
                if(devicessituation.get(kelimeler[2]).substring(0,devicessituation.get(kelimeler[2]).length()-2).equals("On")){
                    timecaller.plugin(kelimeler[2],devicessecondersituation.get(kelimeler[2]).substring(0,devicessecondersituation.get(kelimeler[2]).length()-2));
                }
                return 1;
            }
        }
    }
    public static class LampColour extends Devices{
        public int adder(String input) {//OVERLOADING
            String[] kelimeler = input.split("\t");
            if (this.devicessituation.containsKey(kelimeler[2])) {
                return 0;
            }
            else {
                try {
                    if (kelimeler[3].equals("On")) {
                    } else if (kelimeler[3].equals("Off")) {
                    } else {
                        return 2;
                    }
                    if (kelimeler[4].startsWith("0")) {
                        if(isHexadecimal(kelimeler[4])){
                        }
                        else {
                            return 17;
                        }
                    }
                    else {
                        return 15;
                    }
                    try {
                        if (2500 <= Integer.parseInt(kelimeler[4]) && Integer.parseInt(kelimeler[4]) <= 6500) {
                            ;
                        }
                    } catch (NumberFormatException e) {
                        return 2;
                    }
                    try {
                        if (0 <= Integer.parseInt(kelimeler[5]) && Integer.parseInt(kelimeler[5]) <= 100) {
                            ;
                        } else {
                            return 14;
                        }
                    } catch (NumberFormatException e) {
                        return 2;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    try {
                        this.devicessituation.put(kelimeler[2], kelimeler[3] + "CL");
                    } catch (ArrayIndexOutOfBoundsException w) {
                        this.devicessituation.put(kelimeler[2], "Off" + "CL");
                        this.devicessecondersituation.put(kelimeler[2], "4000" + "CL");
                        this.devicestriplersituation.put(kelimeler[2], "100" + "CL");
                    }
                    try {
                        if(kelimeler[4].startsWith("0x")){
                            this.devicessecondersituation.put(kelimeler[2], kelimeler[4] + "CL");
                        }
                        else {
                            this.devicessecondersituation.put(kelimeler[2], kelimeler[4] + "CL");
                        }
                    } catch (ArrayIndexOutOfBoundsException w) {
                        this.devicessecondersituation.put(kelimeler[2], "4000" + "CL");
                        this.devicestriplersituation.put(kelimeler[2], "100" + "CL");
                    }
                    try {
                        this.devicestriplersituation.put(kelimeler[2], kelimeler[5] + "CL");
                    } catch (ArrayIndexOutOfBoundsException w) {
                        this.devicestriplersituation.put(kelimeler[2], "100" + "CL");
                    }
                    return 1;
                }
                try {
                    this.devicessituation.put(kelimeler[2], kelimeler[3] + "CL");
                } catch (ArrayIndexOutOfBoundsException w) {
                    this.devicessituation.put(kelimeler[2], "Off" + "CL");
                    this.devicessecondersituation.put(kelimeler[2], "4000" + "CL");
                    this.devicestriplersituation.put(kelimeler[2], "100" + "CL");
                }
                try {
                    if(kelimeler[4].startsWith("0x")){
                        this.devicessecondersituation.put(kelimeler[2], kelimeler[4] + "CL");
                    }
                    else {
                        this.devicessecondersituation.put(kelimeler[2], kelimeler[4] + "CL");
                    }
                } catch (ArrayIndexOutOfBoundsException w) {
                    this.devicessecondersituation.put(kelimeler[2], "4000" + "CL");
                    this.devicestriplersituation.put(kelimeler[2], "100" + "CL");
                }
                try {
                    this.devicestriplersituation.put(kelimeler[2], kelimeler[5] + "CL");
                } catch (ArrayIndexOutOfBoundsException w) {
                    this.devicestriplersituation.put(kelimeler[2], "100" + "CL");
                }
                return 1;
            }
        }
    }


    public static class Lamp extends Devices{

        /*
         * This method adds a lamp device to the system and sets its initial state and properties.
         * If the device already exists in the system, the method returns 0 and does not add the device again.
         * If the input format is invalid, the method returns a specific error code indicating the issue.
         * If the device is successfully added to the system, the method returns 1.
         * @param input a string representing the device's state and properties in a specific format
         * @return an integer indicating the status of the operation (0 for existing device, 1 for success, and specific error codes for various issues)
         */
        public int adder(String input){//OVERLOADING
            String[] kelimeler = input.split("\t");
            if (this.devicessituation.containsKey(kelimeler[2])) {
                return 0;
            }
            else {
                try {
                    if (kelimeler[3].equals("On")) {
                    } else if (kelimeler[3].equals("Off")) {
                    } else {
                        return 2;
                    }
                    try {
                        if (2500 <= Integer.parseInt(kelimeler[4]) && Integer.parseInt(kelimeler[4]) <= 6500) {
                            ;
                        } else {
                            return 15;
                        }
                    } catch (NumberFormatException e) {
                        return 2;
                    }
                    try {
                        if (0 <= Integer.parseInt(kelimeler[5]) && Integer.parseInt(kelimeler[5]) <= 100) {
                            ;
                        } else {
                            return 14;
                        }
                    } catch (NumberFormatException e) {
                        return 2;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    try {
                        this.devicessituation.put(kelimeler[2], kelimeler[3] + "SL");
                    } catch (ArrayIndexOutOfBoundsException w) {
                        this.devicessituation.put(kelimeler[2], "Off" + "SL");
                        this.devicessecondersituation.put(kelimeler[2], "4000" + "SL");
                        this.devicestriplersituation.put(kelimeler[2], "100" + "SL");
                    }
                    try {
                        this.devicessecondersituation.put(kelimeler[2], kelimeler[4] + "SL");
                    } catch (ArrayIndexOutOfBoundsException w) {
                        this.devicessecondersituation.put(kelimeler[2], "4000" + "SL");
                        this.devicestriplersituation.put(kelimeler[2], "100" + "SL");
                    }
                    try {
                        this.devicessecondersituation.put(kelimeler[2], kelimeler[5] + "SL");
                    } catch (ArrayIndexOutOfBoundsException w) {
                        this.devicestriplersituation.put(kelimeler[2], "100" + "SL");
                    }
                    return 1;
                }
                try {
                    this.devicessituation.put(kelimeler[2], kelimeler[3] + "SL");
                } catch (ArrayIndexOutOfBoundsException w) {
                    this.devicessituation.put(kelimeler[2], "Off" + "SL");
                    this.devicessecondersituation.put(kelimeler[2], "4000" + "SL");
                    this.devicestriplersituation.put(kelimeler[2], "100" + "SL");
                }
                try {
                    this.devicessecondersituation.put(kelimeler[2], kelimeler[4] + "SL");
                } catch (ArrayIndexOutOfBoundsException w) {
                    this.devicessecondersituation.put(kelimeler[2], "4000" + "SL");
                    this.devicestriplersituation.put(kelimeler[2], "100" + "SL");
                }
                try {
                    this.devicessecondersituation.put(kelimeler[2], kelimeler[5] + "SL");
                } catch (ArrayIndexOutOfBoundsException w) {
                    this.devicestriplersituation.put(kelimeler[2], "100" + "SL");
                }
                return 1;
            }
        }
    }

    public static class Camera extends Devices{
        public int adder(String input) {//OVERLOADING
            Timeandcommands timecaller=new Timeandcommands();
            /*

             Adds a camera to the device situation map and the device seconds situation map.
             The input string should contain the device ID, the camera status ("On" or "Off"),
             and the number of seconds the camera has been on (a non-negative double).
             @param input the input string containing the device ID, camera status, and camera seconds
             @return an integer indicating the result of adding the camera:
             python
             Copy code
             0 if the device is already in the map,
             markdown
             Copy code
             1 if the camera was added successfully,
             python
             Copy code
             2 if the input format is incorrect,
             csharp
             Copy code
             16 if the number of seconds is negative.
             */
            String[] kelimeler = input.split("\t");
            if (this.devicessituation.containsKey(kelimeler[2])) {
                return 0;
            } else {
                try {
                    try {
                        if (Double.parseDouble(kelimeler[3]) > 0) {
                            ;
                        } else {
                            return 16;
                        }
                    } catch (NumberFormatException e) {
                        return 2;
                    }
                    if (kelimeler[4].equals("On")) {
                    } else if (kelimeler[4].equals("Off")) {
                    } else {

                        return 2;
                    }
                } catch (ArrayIndexOutOfBoundsException e) {
                    try {
                        this.devicessecondersituation.put(kelimeler[2], kelimeler[3] + "SC");
                    } catch (ArrayIndexOutOfBoundsException a) {
                        this.devicessituation.put(kelimeler[2], "Off" + "SC");
                        this.devicessecondersituation.put(kelimeler[2], "0" + "SC");
                    }
                    try {
                        this.devicessituation.put(kelimeler[2], kelimeler[4] + "SC");
                    } catch (ArrayIndexOutOfBoundsException a) {
                        this.devicessituation.put(kelimeler[2], "Off" + "SC");
                    }
                    return 1;
                }
                timecontrollerfark.put(kelimeler[2], 0L);
                timecontroller.put(kelimeler[2], timecaller.now);
                timecaller.Energy.put(kelimeler[2], (double) 0);
                try {
                    this.devicessecondersituation.put(kelimeler[2], kelimeler[3] + "SC");
                } catch (ArrayIndexOutOfBoundsException a) {
                    this.devicessituation.put(kelimeler[2], "Off" + "SC");
                    this.devicessecondersituation.put(kelimeler[2], "0" + "SC");
                }
                try {
                    this.devicessituation.put(kelimeler[2], kelimeler[4] + "SC");
                } catch (ArrayIndexOutOfBoundsException a) {
                    this.devicessituation.put(kelimeler[2], "Off" + "SC");
                }
                String name=kelimeler[2];
                return 1;
            }
        }
    }
}
