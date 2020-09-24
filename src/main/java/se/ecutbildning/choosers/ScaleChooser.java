package se.ecutbildning.choosers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import se.ecutbildning.MidiPianoPlayer;
import se.ecutbildning.OptionsHolder;

import java.util.Arrays;

public class ScaleChooser extends LabeledChooser {

    @Override
    public void restoreDefaultValue() {
        setValue(getOptions().get(0));
        OptionsHolder.setCurrentScale(OptionsHolder.nameToIntArray((String)getValue()));
    }

    private static ObservableList<String> getOptions(){
        ObservableList<String> values = FXCollections.observableArrayList();
        values.addAll(Arrays.asList(OptionsHolder.getScaleNames()));
        return values;
    }

    public ScaleChooser(int xLocation, int yLocation){
        super(xLocation, yLocation, "Scale:");
        setPrefWidth(150);
        setItems(getOptions());
        setValue(getOptions().get(0));

        setOnAction(event -> {
            OptionsHolder.setCurrentScale(OptionsHolder.nameToIntArray((String)getValue()));
            MidiPianoPlayer.updateAllNotePanes();
        });


    }
}
