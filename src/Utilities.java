
public class Utilities {
    
    static void print(String[] tab){
        for(String str : tab)
            System.out.print(str + " ");
        System.out.println();
    }

    static boolean isNull(String[] tab){
        if (tab.length==0) return true;
        for(String s: tab){
            if (s==null) return true;
        }
        return false;    
    }
}