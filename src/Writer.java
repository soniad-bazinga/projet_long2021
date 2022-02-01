

public class Writer {
    static String[] rangeNotes = new String[7];

    //Initialise les rangeNote avec les notes de la gamme 
    static void setRangeNotes(int range, boolean mode){
        for (int i=0; i<3; i++)
            rangeNotes[i]=Convert.notes[(range+i*2)%12];
        
        if (mode) rangeNotes[2]=Convert.notes[(range+4)%12];
        else rangeNotes[2]=Convert.notes[(range+3)%12];

        for (int i=3; i<7; i++)
            rangeNotes[i]=Convert.notes[(range+i*2-1)%12];

    }

    //On peut imaginer un argument forme pour marquer la rythmique
    //TODO : change times, add choir etc
    enum Forme {ROND, TRIANGLE, CARRE};
    static String backSample(Forme f) throws RangeNoteNull{
        if (!Utilities.isNull(rangeNotes)){
            String ret = "200.times do\n\tplay :"+rangeNotes[0]+"\n\tsleep 0.5\n\tplay :"+
            rangeNotes[1]+"\n\tsleep 0.5\n\tplay :"+rangeNotes[2]+"\n\tsleep 0.5\n";
            switch(f){
                case ROND:
                    return ret + "\tplay :"+rangeNotes[1]+"end";
                case TRIANGLE:
                    return ret + "end";
                case CARRE:
                return "";
            }
  
        }
 
        throw new RangeNoteNull();
    }

}