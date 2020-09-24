package se.ecutbildning;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import javax.sound.midi.MidiChannel;

public class NotePane extends Pane {


    // Drop shadow radii
    private final static double DS_RADII = 8;

    // final static appearance values
    public static final int SIDE_LENGTH = 50;
    private static final CornerRadii PANE_RADII = new CornerRadii(10);

    // "what-button-to-press" label
    private Text keyCharText = new Text();

    // "what-note-will-be-played" label
    private Text noteSymbolText = new Text();
    private String decideNoteSymbol(){
        int noteNumDivRest = (noteNum + OptionsHolder.getBaseNoteValue()) % 12;
        return OptionsHolder.getNoteSymbols()[noteNumDivRest];
    }

    // note number for note to be emitted
    private int noteNum;
    private int getNotePlayed(){
        return noteNum+OptionsHolder.getBaseNoteValue();
    }

    // midi channel for for the note to be played on
    private MidiChannel midiChannel;

    // to prevent tote to start if it already in play
    private boolean isOn;

    // lets out the midi note and highlights the NotePane
    public void noteOn(){
        if(!isOn){
            setScaleX(0.95);
            setScaleY(0.95);
            isOn = true;
            if(!isInCurrentScale() && OptionsHolder.areOutOfScaleNotesDisabled()){
                return;
            }
            midiChannel.noteOn(getNotePlayed(), OptionsHolder.getNoteVelocity());
            setBackground(new Background(new BackgroundFill(getOnColor(), PANE_RADII, null)));
            setEffect(new DropShadow(DS_RADII, getOnColor()));
        }
    }

    // stops the note and un-highlights the NotePAne
    public void noteOff(){
        if(isOn){
            setScaleX(1);
            setScaleY(1);
            isOn = false;
            if(OptionsHolder.isEndNotesOnKeyRelease()){
                midiChannel.noteOff(getNotePlayed());
            }
            setBackground(new Background(new BackgroundFill(getOffColor(), PANE_RADII, null)));
            setEffect(new DropShadow(DS_RADII, getOffColor()));
        }
    }

    // checks if the note-to-be-played is in the current scale
    private boolean isInCurrentScale(){
        for(int i : OptionsHolder.getCurrentScale()){
            if(i == (getNotePlayed()-OptionsHolder.getCurrentKeyNum())%12){
                return true;
            }
        }
        return false;
    }

    // decide color
    private Color getOnColor(){
        if(isInCurrentScale()){
            return ColorPalette.BTN_ON_2;
        } else {
            return ColorPalette.BTN_ON_1;
        }
    }
    private Color getOffColor(){
        if(isInCurrentScale()){
            return ColorPalette.BTN_OFF_2;
        } else {
            return ColorPalette.BTN_OFF_1;
        }
    }


    // updates display after changes
    public void updateDisplay(){
        setBackground(new Background(new BackgroundFill(getOffColor(), PANE_RADII, null)));
        setEffect(new DropShadow(DS_RADII, getOffColor()));
        noteSymbolText.setText(decideNoteSymbol());
    }


    public NotePane(char keyChar, int noteNum, MidiChannel channel){

        this.noteNum = noteNum;
        this.midiChannel = channel;

        setPrefSize(SIDE_LENGTH, SIDE_LENGTH);
        setBackground(new Background(new BackgroundFill(getOffColor(), PANE_RADII, null)));
        setEffect(new DropShadow(DS_RADII, getOffColor()));

        getChildren().addAll(keyCharText, noteSymbolText);

        keyCharText.setText(""+keyChar);
        keyCharText.relocate(3,0);
        keyCharText.setStyle(" -fx-font-size: 10;");
        keyCharText.setFill(Color.LIGHTGRAY);

        noteSymbolText.setText(decideNoteSymbol());
        noteSymbolText.relocate(15,22);
        noteSymbolText.setStyle(" -fx-font-size: 22;");

        setOnMousePressed(e->{
            if(!e.isPrimaryButtonDown()){
                return;
            }
            if(e.isSecondaryButtonDown()){
                return;
            }
            e.setDragDetect(true);
            noteOn();
        });

        setOnMouseReleased(e->{
            if(e.isPrimaryButtonDown()){
                return;
            }
            noteOff();
        });

        setOnMouseDragged(e->{
            e.setDragDetect(false);
        });

        setOnDragDetected(e-> {
            startFullDrag();
        });

        setOnMouseDragEntered(e-> {
            if(!e.isPrimaryButtonDown()){
                return;
            }
            noteOn();
        });

        setOnMouseDragExited(e-> {
            noteOff();
        });

        setOnMouseExited(e->{
            if(!e.isPrimaryButtonDown()){
                return;
            }
            noteOff();
        });



    }
}
