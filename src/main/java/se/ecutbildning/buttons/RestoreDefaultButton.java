package se.ecutbildning.buttons;

import se.ecutbildning.MidiPianoPlayer;

public class RestoreDefaultButton extends AppButton {



    @Override
    public void onAction() {
        MidiPianoPlayer.restoreDefault();
    }




    public RestoreDefaultButton(int xLocation, int yLocation){
        super(xLocation,yLocation,"Restore default");
        setPrefWidth(100);
    }
}
