package chopin.imageconverter;

import java.util.Map;
import java.util.logging.Logger;

public final class Writer {

    private Writer(){}

    /**
     * Logger.
     */
    static Logger log = Logger.getLogger("Log");

    /** 
     * Options allowed with note.
     */
    static String[] options = {"amp:", "release:"};

    /**
     * Sample available.
     */
    static String[] sampleDrum = {":drum_bass_hard", ":drum_cymbal_open", 
        ":drum_snare_hard"};
    static String[] sampleAmbient = {":ambi_glass_hum", ":ambi_haunted_hum", 
        ":ambi_piano", ":ambi_choir"};
    static String[] sampleGuit = {"sample :guit_e_fifths", "sample :guit_em9"};

    /**
     * Allows writing loop a certain number of times. 
     * @param times number of time the program will repeat lo
     * @param lo content of the loop
     * @return loop under string form
     */
    static String loop(int times, String lo) {
        return String.valueOf(times) + ".times do\n"
                + lo
                + "end\n";
    }

    /**
     * Allows writing loop an infinite number of times. 
     * @param lo content of the loop
     * @return loop under string form
     */
    static String loop(String lo) {
        return "loop do\n"
                + lo
                +"end\n";
    }

    /**
     * Writing note. 
     * @param n note
     * @param opt option and value's option
     * @return note under string form
     */
    static String note(String n, Map<String,Float> opt){
        String ret = "play :" + n;
        if (opt != null) {
            for (Map.Entry<String, Float> e : opt.entrySet()) {
                if (Utilities.contains(options, e.getKey())) {
                    ret += ", " + e.getKey() + " " + e.getValue();
                }
            }
        }
        return  ret + "\n";
    }

    static String sample(boolean cool, boolean light) {
        if (cool && light) {
            return sampleAmbient[3];
        } else if (cool) {
            return sampleAmbient[1];
        } else if (light) {
            return sampleAmbient[0];
        } else {
            return sampleAmbient[2];
        }
    }


}