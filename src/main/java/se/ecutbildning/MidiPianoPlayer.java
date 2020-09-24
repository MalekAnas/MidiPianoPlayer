package se.ecutbildning;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import se.ecutbildning.buttons.RestoreDefaultButton;
import se.ecutbildning.choosers.*;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Synthesizer;

/**
 * JavaFX App
 *
 * This Application is a  MidiPianoPlayer
 *
 */
public class MidiPianoPlayer extends Application {

    // root pane, the background of the app
    private static Pane root = new Pane();

    // synthesizer and music channels for each row of NotePanes
    // (to allow 2 instances of the same note to be played at the same time)
    private static Synthesizer synthesizer;
    public static Synthesizer getSynthesizer() {
        return synthesizer;
    }

    private static MidiChannel channel1;
    private static MidiChannel channel2;
    private static MidiChannel channel3;
    private static MidiChannel channel4;

    // init. root, synthesizer and channels
    static {
        root.setBackground(new Background(new BackgroundFill(ColorPalette.BCG, null, null)));

        try {
            synthesizer = MidiSystem.getSynthesizer();
            synthesizer.open();
            channel1 = synthesizer.getChannels()[0];
            channel2 = synthesizer.getChannels()[1];
            channel3 = synthesizer.getChannels()[2];
            channel4 = synthesizer.getChannels()[3];
        } catch (MidiUnavailableException e){
            e.printStackTrace();
        }
    }

    public static void changeInstrument(int instrumentNum){
        channel1.programChange(instrumentNum);
        channel2.programChange(instrumentNum);
        channel3.programChange(instrumentNum);
        channel4.programChange(instrumentNum);
    }

    // silences all ongoing notes
    public static void silienceAll(){
        channel1.allSoundOff();
        channel2.allSoundOff();
        channel3.allSoundOff();
        channel4.allSoundOff();

    }

    // panels divided in rows
    public static NotePane[] notePanesRow1 = new NotePane[12];
    public static NotePane[] notePanesRow2 = new NotePane[12];
    public static NotePane[] notePanesRow3 = new NotePane[11];
    public static NotePane[] notePanesRow4 = new NotePane[10];

    // note numbers to be passed to NotePanes after adding the baseNoteValue to it
    // notes in order                      :  C  C# D  D# E  F  F# G  G# A  A#  B   C
    // corresponding note nums modifiers   :  0 +1 +2 +3 +4 +5 +6 +7 +8 +9 +10 +11 +12
    private static int[] noteNumsRow1 ={15,16,17,18,19,20,21,22,23,24,25,26};
    private static int[] noteNumsRow2 ={10,11,12,13,14,15,16,17,18,19,20,21};
    private static int[] noteNumsRow3 ={5,6,7,8,9,10,11,12,13,14,15};
    private static int[] noteNumsRow4 ={0,1,2,3,4,5,6,7,8,9};

    static {
        buildNotePaneRow(notePanesRow1, KeyMapper.NOTE_CHARS_ROW_1, noteNumsRow1, channel1);
        buildNotePaneRow(notePanesRow2, KeyMapper.NOTE_CHARS_ROW_2, noteNumsRow2, channel2);
        buildNotePaneRow(notePanesRow3, KeyMapper.NOTE_CHARS_ROW_3, noteNumsRow3, channel3);
        buildNotePaneRow(notePanesRow4, KeyMapper.NOTE_CHARS_ROW_4, noteNumsRow4, channel4);
    }




    // pane building
    private static void buildNotePaneRow(NotePane[] notePanesRow, char[] noteCharsRow, int[] noteNumsRow, MidiChannel channel){
        for(int i = 0; i<notePanesRow.length; i++){
            notePanesRow[i] = new NotePane(noteCharsRow[i], noteNumsRow[i], channel);
        }
    }

    // pane placement from base value
    private static void placeNotePaneRow(NotePane[] notePanesRow, double originX, double originY){
        for(int i = 0; i<notePanesRow.length; i++){
            notePanesRow[i].relocate(originX+(NotePane.SIDE_LENGTH+5)*i, originY);
            root.getChildren().add(notePanesRow[i]);
        }
    }

    // removes all NotePanes from root
    private static void removeRowOfNotePanes(NotePane[] notePanes){
        for(NotePane notePane : notePanes){
            root.getChildren().remove(notePane);
        }
    }
    private static void removeAllNotePanes(){
        removeRowOfNotePanes(notePanesRow1);
        removeRowOfNotePanes(notePanesRow2);
        removeRowOfNotePanes(notePanesRow3);
        removeRowOfNotePanes(notePanesRow4);
    }

    // first removes all NotePanes from root, then places them back in keyboard-like pattern
    public static void arrangeNotePanesInKeyboardPattern(){
        removeAllNotePanes();
        placeNotePaneRow(notePanesRow1, 45,170);
        placeNotePaneRow(notePanesRow2, 70,225);
        placeNotePaneRow(notePanesRow3, 85,280);
        placeNotePaneRow(notePanesRow4, 110,335);
    }

    // first removes all NotePanes from root, then places them back in grid-like pattern
    public static void arrangeNotePanesInGridPattern(){
        removeAllNotePanes();
        placeNotePaneRow(notePanesRow1, 65,170);
        placeNotePaneRow(notePanesRow2, 65,225);
        placeNotePaneRow(notePanesRow3, 65,280);
        placeNotePaneRow(notePanesRow4, 65,335);
    }





    // pitch chooser
    private static PitchChooser pitchChooser = new PitchChooser(70,30);
    static {
        root.getChildren().addAll(pitchChooser.getLabel(),pitchChooser);
    }

    // key chooser
    private static KeyChooser keyChooser = new KeyChooser(150,30);
    static {
        root.getChildren().addAll(keyChooser.getLabel(),keyChooser);
    }

    // scale chooser
    private static ScaleChooser scaleChooser = new ScaleChooser(230,30);
    static {
        root.getChildren().addAll(scaleChooser.getLabel(),scaleChooser);
    }

    // instrument chooser
    private static InstrumentChooser instrumentChooser = new InstrumentChooser(400,30);
    static {
        root.getChildren().addAll(instrumentChooser.getLabel(),instrumentChooser);
    }

    // end notes on key release chooser
    private static EndNotesOnKeyReleaseChooser endNotesOnKeyReleaseChooser = new EndNotesOnKeyReleaseChooser(570,30);
    static {
        root.getChildren().addAll(endNotesOnKeyReleaseChooser.getLabel(), endNotesOnKeyReleaseChooser);
    }

    // notes out of scale disable chooser
    private static OutOfScaleNotesDisabledChooser outOfScaleNotesDisabledChooser = new OutOfScaleNotesDisabledChooser(70,85);
    static {
        root.getChildren().addAll(outOfScaleNotesDisabledChooser.getLabel(), outOfScaleNotesDisabledChooser);
    }

    // velocity of the note released chooser
    private static VelocityChooser velocityChooser = new VelocityChooser(240,85);
    static {
        root.getChildren().addAll(velocityChooser.getLabel(),velocityChooser);
    }

    // key pattern chooser
    private static KeyPatternChooser keyPatternChooser = new KeyPatternChooser(350,85);
    static {
        root.getChildren().addAll(keyPatternChooser.getLabel(), keyPatternChooser);
    }

    // restore default button
    private static RestoreDefaultButton restoreDefaultButton = new RestoreDefaultButton(610,105);
    static {
        root.getChildren().add(restoreDefaultButton);
    }


    // updates all NotePanes after a change in the options
    public static void updateAllNotePanes(){
        for(NotePane np : notePanesRow1){
            np.updateDisplay();
        }
        for(NotePane np : notePanesRow2){
            np.updateDisplay();
        }
        for(NotePane np : notePanesRow3){
            np.updateDisplay();
        }
        for(NotePane np : notePanesRow4){
            np.updateDisplay();
        }
    }




    // restore default values
    public static void restoreDefault(){
        pitchChooser.restoreDefaultValue();
        keyChooser.restoreDefaultValue();
        scaleChooser.restoreDefaultValue();
        instrumentChooser.restoreDefaultValue();
        endNotesOnKeyReleaseChooser.restoreDefaultValue();
        outOfScaleNotesDisabledChooser.restoreDefaultValue();
        velocityChooser.restoreDefaultValue();
        keyPatternChooser.restoreDefaultValue();
        updateAllNotePanes();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setScene(new Scene(root, 780,420));
        primaryStage.setResizable(false);
        //set the title
        primaryStage.setTitle("Midi Piano Player");
        primaryStage.getIcons().add(new Image(String.valueOf(MidiPianoPlayer.class.getResource("/pngs/silver_piano.png"))));


        primaryStage.show();

        arrangeNotePanesInKeyboardPattern();
        KeyMapper.mapKeys(primaryStage.getScene());
    }

    public static void main(String[] args) {
        launch(args);

    }

}