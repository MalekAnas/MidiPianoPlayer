package se.ecutbildning.choosers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import se.ecutbildning.MidiPianoPlayer;

public class InstrumentChooser extends LabeledChooser {


    @Override
    public void restoreDefaultValue() {
        setValue(getOptions().get(0));
        MidiPianoPlayer.changeInstrument(extractInstrumentNumber());
    }

    private static ObservableList<String> getOptions(){
        ObservableList<String> values = FXCollections.observableArrayList();
        for(int i = 0; i <= 127; i++){
            values.add(i+".  "+ MidiPianoPlayer.getSynthesizer().getLoadedInstruments()[i].getName());
        }
        return values;
    }

    private int extractInstrumentNumber(){
        StringBuilder itemNumString = new StringBuilder();
        for(int i = 0;((String)getValue()).charAt(i)!='.';i++){
            itemNumString.append(((String)getValue()).charAt(i));
        }
        return Integer.parseInt(itemNumString.toString());
    }

    public InstrumentChooser(int xLocation, int yLocation){
        super(xLocation, yLocation, "Instrument:");
        setPrefWidth(150);
        setItems(getOptions());
        setValue(getOptions().get(0));

        setOnAction(event -> {
            MidiPianoPlayer.changeInstrument(extractInstrumentNumber());
            MidiPianoPlayer.updateAllNotePanes();
        });


    }

}
