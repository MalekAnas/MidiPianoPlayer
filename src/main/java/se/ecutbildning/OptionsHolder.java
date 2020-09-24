package se.ecutbildning;

public class OptionsHolder {



    // base note value (keys add their note value to base note when they release a note to the channel)
    // a value divisible by 12 here makes the lowest note (corresponding to the "Z" key) a C
    private static int baseNoteValue = 48;
    public static void adjustBaseNoteValue(int adjustment) {
        OptionsHolder.baseNoteValue = 48+adjustment;
    }
    public static int getBaseNoteValue() {
        return baseNoteValue;
    }



    // key number to be added to current scale numbers when determining what keyboard keys should be marked as in-scale (blue)
    private static int currentKeyNum = 0;
    public static int getCurrentKeyNum() {
        return currentKeyNum;
    }
    public static void setCurrentKeyNum(int currentKeyNum) {
        OptionsHolder.currentKeyNum = currentKeyNum;
    }

    // note symbols
    private final static String[] noteSymbols = new String[]{"C","C#","D","D#","E","F","F#","G","G#","A","A#","B"};
    public static String[] getNoteSymbols() {
        return noteSymbols;
    }
    public static int symbolToNoteNum(String noteSymbol){
        for(int i = 0; i < noteSymbols.length; i++){
            if(noteSymbol == noteSymbols[i]){
                return i;
            }
        }
        return 0; // should never be returned
    }

    // scale names
    private final static String[] scaleNames = new String[]{
            "Major","Minor","Pentatonic Minor", "Blues","Harmonic Minor", "Hungarian Minor","Whole Tone","Diminished"
    };
    public static String[] getScaleNames() {
        return scaleNames;
    }
    public static int[] nameToIntArray(String scaleName){
        switch (scaleName){
            case "Major" : return MAJOR;
            case "Minor" : return MINOR;
            case "Pentatonic Minor" : return PENTATONIC_MINOR;
            case "Blues" : return BLUES;
            case "Harmonic Minor" : return HARMONIC_MINOR;
            case "Hungarian Minor" : return HUNGARIAN_MINOR;
            case "Whole Tone" : return WHOLE_TONE;
            case "Diminished" : return DIMINISHED;
            default: return MAJOR; // should never be returned
        }
    }

    // Scales
    private static final int[] MAJOR = new int[]{0,2,4,5,7,9,11};
    private static final int[] MINOR = new int[]{0,2,3,5,7,8,10};
    private static final int[] PENTATONIC_MINOR = new int[]{0,3,5,7,10};
    private static final int[] BLUES = new int[]{0,3,5,6,7,10};
    private static final int[] HARMONIC_MINOR = new int[]{0,2,3,5,7,8,11};
    private static final int[] HUNGARIAN_MINOR = new int[]{0,2,3,6,7,8,11};
    private static final int[] WHOLE_TONE = new int[]{0,2,4,6,8,10};
    private static final int[] DIMINISHED = new int[]{0,2,3,5,6,8,9,11};


    private static int[] currentScale = MAJOR;
    public static void setCurrentScale(int[] currentScale) {
        OptionsHolder.currentScale = currentScale;
    }
    public static int[] getCurrentScale() {
        return currentScale;
    }

    // Notes out of scale disabled
    private static boolean outOfScaleNotesDisabled = false;
    public static boolean areOutOfScaleNotesDisabled() {
        return outOfScaleNotesDisabled;
    }
    public static void setOutOfScaleNotesDisabled(boolean outOfScaleNotesDisabled) {
        OptionsHolder.outOfScaleNotesDisabled = outOfScaleNotesDisabled;
    }

    // Cut notes short on key release
    private static boolean endNotesOnKeyRelease = true;
    public static boolean isEndNotesOnKeyRelease() {
        return endNotesOnKeyRelease;
    }
    public static void setEndNotesOnKeyRelease(boolean endNotesOnKeyRelease) {
        OptionsHolder.endNotesOnKeyRelease = endNotesOnKeyRelease;
        if(endNotesOnKeyRelease){
            MidiPianoPlayer.silienceAll();
        }
    }

    // Note velocity
    private static int noteVelocity = 100;
    public static void setNoteVelocity(int noteVelocity) {
        OptionsHolder.noteVelocity = noteVelocity;
    }
    public static int getNoteVelocity() {
        return noteVelocity;
    }



    private OptionsHolder(){}
    private static OptionsHolder optionsHolder = new OptionsHolder();
}
