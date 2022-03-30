package main.java.console;

import java.util.HashMap;

public class DebugLogger {
    
    // map with debug type (as integer) and related boolean value
    public static HashMap<Integer, Boolean> typeMap = new HashMap<Integer, Boolean>();
    // copy of typeMap with string macro instead of boolean value
    public static HashMap<Integer, String> typeMapDirectory = new HashMap<Integer, String>();

    private static String previousDebugMessage = "";

    // print debug message if debug type is enable
    public static void print(int debugType, String str) {
        if (!str.equals(previousDebugMessage)) {
            if(typeMap.get(debugType)) {
                Console.layout();
                System.out.println(str);
                previousDebugMessage = str;
            } 
            else if(typeMap.get(DebugType.ALL)) {
                System.out.println(str);
                previousDebugMessage = str;
            }
        }
        Console.layout();
    }

    public static void setTypeMap() {
        typeMap.put(DebugType.ERROR, false);
        typeMapDirectory.put(DebugType.ERROR, "ERROR");
        typeMap.put(DebugType.ALL, false);
        typeMapDirectory.put(DebugType.ALL, "ALL");
        typeMap.put(DebugType.CONFIRM, true);
        typeMapDirectory.put(DebugType.CONFIRM, "CONFIRM");
    }

}
