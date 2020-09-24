package se.ecutbildning.choosers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import se.ecutbildning.MidiPianoPlayer;
import se.ecutbildning.OptionsHolder;

public class VelocityChooser extends LabeledChooser {


    @Override
    public void restoreDefaultValue() {
        setValue(getOptions().get(9));
        OptionsHolder.setNoteVelocity(Integer.parseInt((String)getValue()));
    }

    private static ObservableList<String> getOptions(){
        ObservableList<String> values = FXCollections.observableArrayList();
        for(int i = 10; i <= 200; i+=10){
            values.add(""+i);
        }
        return values;
    }

    public VelocityChooser(int xLocation, int yLocation){
        super(xLocation, yLocation, "Note velocity:");
        setPrefWidth(90);
        setItems(getOptions());
        setValue(getOptions().get(9));

        // moves pitches on the whole key board
        setOnAction(event -> {
            OptionsHolder.setNoteVelocity(Integer.parseInt((String)getValue()));
            MidiPianoPlayer.updateAllNotePanes();
        });


    }
}
