package chopin.imageconverter;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import chopin.exception.PatternNotExhaustive;
import chopin.jsonconverter.JsonConverter;

public final class Convert{

    /**
     * Logger.
     */
    static Logger log = Logger.getLogger("Log");

    private Convert(){}

    static void setColors(Map<String, Float> cls){
        colors=cls;
    }
    /**
     * List of notes.
     */
    static final String[] notes = 
        {"A", "Bb", "B", "C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab"};

    /**
     * List of notes of range rangeBase.
     */
    static String[] rangeNotes = new String[7];

    /**
     * Max number of loops.
     */
    static int MAX_LOOP = 3;

    /**
     * 
     * @param file
     * @return
     */
    static private Map<String, Float> colors;

    static public String convert(String file){
        colors = JsonConverter.getColor(file);
        String color = mainColor();
        setRangeNotes(rangeBase(color), mode(color));
        Float max = colors.get(color);
        String ret = "";
        int times;
        float prec = 1;
        for (Map.Entry<String, Float> e : colors.entrySet()) {
            if (prec - e.getValue() >= 0.05) {
                ret += "sample ";
                ret += Writer.sampleGuit(
                    getBlue(e.getKey()) > getRed(e.getKey()));
                ret += "\n";
                prec = e.getValue();
            }
            times = (int) ((e.getValue()*MAX_LOOP)/max);
            if (times == 0) {
                ret += convertNote(e.getKey(), 3);
            } else {
                ret += Writer.loop(
                    times, 
                    convertColor(e.getKey()));
            }
            ret += "sleep " + String.valueOf(similarity(e.getKey())) + " \n";
        }
        return ret;
    }

    static private String convertColor(String color) {
        String ret = "sample ";
        ret += Writer.sampleAmbient(
            getBlue(color) > getRed(color), 
            (getRed(color) 
            + getGreen(color) 
            + getBlue(color))/3 > 127);
        ret +="\n";
        for (int i = 0; i < 3; i++){
            ret += convertNote(color, i);
            ret += "\n";
        }
        ret += "sleep " + String.valueOf(
            similarity(color))
            + "\n";
        return ret;
    }

    static private String convertNote(String color, int simType) {
        String ret ="";
        float sim = 0;
        int note = (int) rangeBase(color)/2;
        switch (simType) {
            case 0: 
                sim = similarityRed(color); 
                break;
            case 1:
                sim = similarityGreen(color);
                break;
            case 2:
                sim = similarityBlue(color);
                break;
            case 3:
                sim = (similarityRed(color) 
                    + similarityGreen(color) 
                    + similarityBlue(color)) / 3;
                break;
            default: 
                throw new IllegalArgumentException();
        }
        Map<String, Float> opt = new HashMap<String, Float>();
            opt.put("amp:", (Float) ((float) 0));
            opt.put("release:", (Float) ((float) 0));
        opt.put("amp:", sim);
        opt.put("release:", sim*3);
        ret += Writer.note(
            rangeNotes[(note + simType)%7], 
            opt)
            + "sleep " 
            + String.valueOf(sim)
            + "\n";
        return ret;

    }

    /**
     * Calculate rangeBase note from JSON Map
     * @param colors JSON Map
     * @return rangeBase
     */
    static int rangeBase(String color) {
        try {
            return rangeBaseRGB (
                (int) getRed(color),
                (int) getGreen(color),
                (int) getBlue(color)
            );
        } catch (PatternNotExhaustive e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Link color note according to the description in parseIntToColor.pdf
     */
    static private int rangeBaseRGB(int r, int g, int b) throws PatternNotExhaustive{
        r = (int) ((float) (r/85.01));
        g = (int) ((float) (g/85.01));
        b = (int) ((float) (b/85.01));

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
        
        throw new PatternNotExhaustive("value rgb "
            + String.valueOf(r)
            + String.valueOf(g)
            + String.valueOf(b)
            + " not catched.");
    }

    static boolean mode(String color){
        int ret = ((int) getRed(color)
            + (int) getGreen(color)
            + (int) getBlue(color)
            )/3;
        if (ret < 0 || ret > 255) {
            throw new IllegalArgumentException(
                "The average of the colors have to be between 0 and 255."
            );
        }
        return ret>126;
    }

    static void setRangeNotes(int range, boolean mode){
        for (int i=0; i<3; i++) {
            rangeNotes[i]=Convert.notes[(range+i*2)%12];
        }
        if (mode) {
            rangeNotes[2]=Convert.notes[(range+4)%12];
        } else {
            rangeNotes[2]=Convert.notes[(range+3)%12];
        }

        for (int i=3; i<7; i++) {
            rangeNotes[i]=Convert.notes[(range+i*2-1)%12];
        }

    }

    static String mainColor(){
        float max = 0;
        String color = "#000000";
        for (Map.Entry<String, Float> e : colors.entrySet()) {
            if (e.getValue() > max) {
                max = e.getValue();
                color = e.getKey();
            }
        }
        return color;
    }

    static float similarityRed (String color) {
        int r1 = (int) (getRed(color)/85.01);
        int count = 0;
        for (Map.Entry<String, Float> e : colors.entrySet()) {
            int r2 = (int) (getRed(e.getKey())/85.01);
            if (r1 == r2) {
                count++;
            }
        }
        //log.info("count : " + String.valueOf(count));
        //log.info("size : " + String.valueOf(colors.size()));
        return ((float) count)/((float) (colors.size()));
    }

    static float similarityGreen (String color) {
        int g1 = (int) ((float) getGreen(color)/85.01);
        int count = 0;
        for (Map.Entry<String, Float> e : colors.entrySet()) {
            int g2 = (int) (getGreen(e.getKey())/85.01);
            if (g1 == g2) {
                count++;
            }
        }
        //log.info("count : " + String.valueOf(count));
        //log.info("size : " + String.valueOf(colors.size()));
        return ((float) count)/((float) (colors.size()));
    }

    static float similarityBlue (String color) {
        int b1 = (int) (getBlue(color)/85.01);
        int count = 0;
        for (Map.Entry<String, Float> e : colors.entrySet()) {
            int b2 = (int) (getBlue(e.getKey())/85.01);
            if (b1 == b2) {
                count++;
            }
        }
        //log.info("count : " + String.valueOf(count));
        //log.info("size : " + String.valueOf(colors.size()));
        return ((float) count)/((float) (colors.size()));
    }

    static float similarity (String color) {
        return (similarityRed(color) 
            + similarityGreen(color) 
            + similarityBlue(color)) / 3;
    }

    static private float getRed(String color) {
        return (float) Long.parseLong(color.substring(1, 3), 16);
    }

    static private float getGreen(String color) {
        return (float) Long.parseLong(color.substring(3, 5), 16);
    }

    static private float getBlue(String color) {
        return (float) Long.parseLong(color.substring(5, 7), 16);
        
    }


}