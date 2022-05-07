package chopin.imageconverter;

import java.util.logging.Logger;

public final class Utilities {
    
    private Utilities() {}

    /**
     * Logger.
     */
    static Logger log = Logger.getLogger("Log");

    public static void print(String[] tab){
        for (String str : tab){
            System.out.print(str + " ");
        }
        System.out.println();
    }

    public static boolean isNull(String[] tab){
        if (tab.length==0) {
            return true;
        }
        for (String s: tab){
            if (s==null) {
                return true;
            }
        }
        return false;    
    }

    public static boolean contains(String[] container, String content) {
        for (String s : container) {
            if(s.equals(content)) {
                return true;
            }
        }
        
        return false;
    }
}