import java.awt.Color;

public class Writer {
    //TODO enum ??
    static final String[] notes = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};

    //On cherche à établir les correspondances couleur/note selon
    //la description données dans parseIntToColor.pdf
    static String rangeBase(int r, int g, int b){
        r /= 85;
        g /= 85;
        b /= 85;

        if (r==0 && g==0 && b==0) return notes[6];
        else if (r==1 && g==1 && b==1) return notes[9];
        else if (r==2 && g==2 && b==2) return notes[5];
        else if (r==0 && (g==0 || r==2)) return notes[0];
        else if ((g==2 && b==2) || (g==b && r==0)) return notes[1];
        else if ((r==0 || r==1) && g==2 && b==1) return notes[2];
        else if (r==0 && b==0) return notes[3];
        else if (r==1 && (g==1 || g==2) && r==0) return notes[4];
        else if (r==2 && g==2) return notes[5];
        else if (r==2 && g==1 && b==0) return notes[6];
        else if ((r==1 || r==2) && g==0 && b==0) return notes[7];
        else if (r==2 && (g==0 || g==1) && b==1) return notes[8];
        else if (r==2 && g==0 && b==2) return notes[9];
        else if ((r==b) && g<r) return notes[10];
        else return notes[11];
    }

    public static void main (String[] args){
        System.out.println("Notes:"); Utilities.print(notes);
        Test.testRangeBase();
    }

}