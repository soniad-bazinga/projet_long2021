package chopin.imageconverter;

public final class Convert{

    private Convert(){}

    //TODO enum ??
    static final String[] notes = {"A", "A#", "B", "C", "C#", "D", "D#", "E", "F", "F#", "G", "G#"};

    //On cherche à établir les correspondances couleur/note selon
    //la description données dans parseIntToColor.pdf
    static int rangeBase(int r, int g, int b) throws PatternNotExhaustive{
        r /= 85;
        g /= 85;
        b /= 85;

        if (r==0 && g==0 && b==0) { return 6; }
        else if (r==1 && g==1 && b==1) { return 9; }
        else if (r==2 && g==2 && b==2) { return 5; }
        else if (r==0 && (g==0 || g==1 && b==2)) { return 0; }
        else if (g==2 && b==2 || g==b && r==0) { return 1; }
        else if ((r==0 || r==1) && g==2 && b==1) { return 2; }
        else if (r==0 && b==0) { return 3; }
        else if (r==1 && (g==1 || g==2) && b==0) { return 4; }
        else if (r==2 && g==2) { return 5; }
        else if (r==2 && g==1 && b==0) { return 6; }
        else if ((r==1 || r==2) && g==0 && b==0) { return 7; }
        else if (r==2 && (g==0 || g==1) && b==1) { return 8; }
        else if (r==2 && g==0 && b==2) { return 9; }
        else if (r==b && g<r) { return 10; }
        else if (r==1 && b==2) { return 11; }
        
        throw new PatternNotExhaustive("value rgb "+ String.valueOf(r)+ String.valueOf(g)+ String.valueOf(b)+ " not catched.");
    }

    static boolean mode(int r, int g, int b){
        return (r+g+b)/3>126;
    }

}