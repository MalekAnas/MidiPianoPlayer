package se.ecutbildning.choosers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import se.ecutbildning.MidiPianoPlayer;

public class KeyPatternChooser extends LabeledChooser{


    @Override
    public void restoreDefaultValue() {
        setValue(getOptions().get(0));
        MidiPianoPlayer.arrangeNotePanesInKeyboardPattern();
    }

    private static ObservableList<String> getOptions(){
        ObservableList<String> values = FXCollections.observableArrayList();
        values.addAll("keyboard","grid");
        return values;
    }

    public KeyPatternChooser(int xLocation, int yLocation){
        super(xLocation, yLocation, "Key pattern:");
        setPrefWidth(100);
        setItems(getOptions());
        setValue(getOptions().get(0));

        setOnAction(event -> {
            if( (getValue()).equals(getOptions().get(0)) ){
                MidiPianoPlayer.arrangeNotePanesInKeyboardPattern();
            } else if( (getValue()).equals(getOptions().get(1)) ){
                MidiPianoPlayer.arrangeNotePanesInGridPattern();
            }
            MidiPianoPlayer.updateAllNotePanes();
        });


    }

}
