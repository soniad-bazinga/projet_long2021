public class Main {
    public static void main (String[] args){
        System.out.println("-----------------------------------------------");
        System.out.println("                Début des tests");
        System.out.println("-----------------------------------------------\n");
        System.out.println("Les notes à disposition sont les suivantes : "); Utilities.print(Convert.notes);
        System.out.println("\nOn appelle la fonction test de convertion d'une couleur en une note, qui donnera la gamme (on teste pour chaque note):");
        System.out.println(Test.testRangeBase());
    }
}