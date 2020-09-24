package se.ecutbildning.choosers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import se.ecutbildning.MidiPianoPlayer;
import se.ecutbildning.OptionsHolder;

public class PitchChooser extends LabeledChooser {


    @Override
    public void restoreDefaultValue() {
        setValue(getOptions().get(getOptions().size()/2));
        OptionsHolder.adjustBaseNoteValue(Integer.parseInt((String)getValue()));
    }

    private static ObservableList<String> getOptions(){
        ObservableList<String> values = FXCollections.observableArrayList();
        for(int i = -36; i <= 36; i++){
            values.add(""+i);
        }
        return values;
    }

    public PitchChooser(int xLocation, int yLocation){
        super(xLocation, yLocation, "Pitch offset:");
        setPrefWidth(60);
        setItems(getOptions());
        setValue(getOptions().get(getOptions().size()/2));

        // moves pitches on the whole key board
        setOnAction(event -> {
            OptionsHolder.adjustBaseNoteValue(Integer.parseInt((String)getValue()));
            MidiPianoPlayer.updateAllNotePanes();
        });


    }

}
