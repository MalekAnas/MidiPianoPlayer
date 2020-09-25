package se.ecutbildning.choosers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import se.ecutbildning.MidiPianoPlayer;
import se.ecutbildning.OptionsHolder;

import java.util.Arrays;

public class KeyChooser extends LabeledChooser{


    @Override
    public void restoreDefaultValue() {
        setValue(getOptions().get(0));
        OptionsHolder.setCurrentKeyNum(OptionsHolder.symbolToNoteNum((String)getValue()));
    }

    private static ObservableList<String> getOptions(){
        ObservableList<String> values = FXCollections.observableArrayList();
        values.addAll(Arrays.asList(OptionsHolder.getNoteSymbols()));
        return values;
    }

    public KeyChooser(int xLocation, int yLocation){
        super(xLocation, yLocation, "Key:");
        setPrefWidth(60);
        setItems(getOptions());
        setValue(getOptions().get(0));

        setOnAction(event -> {
            OptionsHolder.setCurrentKeyNum(OptionsHolder.symbolToNoteNum((String)getValue()));
            MidiPianoPlayer
                    .updateAllNotePanes();
        });


    }
}
