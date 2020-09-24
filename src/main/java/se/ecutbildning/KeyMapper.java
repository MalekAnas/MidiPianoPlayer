package se.ecutbildning;

import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;

public class KeyMapper {



    // note characters to be put on NotePanes
    public final static char[] NOTE_CHARS_ROW_1 ={'1','2','3','4','5','6','7','8','9','0','-','='};
    public final static char[] NOTE_CHARS_ROW_2 ={'Q','W','E','R','T','Y','U','I','O','P','[',']'};
    public final static char[] NOTE_CHARS_ROW_3 ={'A','S','D','F','G','H','J','K','L',';','\''};
    public final static char[] NOTE_CHARS_ROW_4 ={'Z','X','C','V','B','N','M',',','.','/'};

    // key-combinations to be mapped to NotePanes
    private final static KeyCombination[] KEY_COMBINATIONS_ROW_1 = new KeyCombination[12];
    private final static KeyCombination[] KEY_COMBINATIONS_ROW_2 = new KeyCombination[12];
    private final static KeyCombination[] KEY_COMBINATIONS_ROW_3 = new KeyCombination[11];
    private final static KeyCombination[] KEY_COMBINATIONS_ROW_4 = new KeyCombination[10];

    static {

        KEY_COMBINATIONS_ROW_1[0] = new KeyCodeCombination(KeyCode.DIGIT1);
        KEY_COMBINATIONS_ROW_1[1] = new KeyCodeCombination(KeyCode.DIGIT2);
        KEY_COMBINATIONS_ROW_1[2] = new KeyCodeCombination(KeyCode.DIGIT3);
        KEY_COMBINATIONS_ROW_1[3] = new KeyCodeCombination(KeyCode.DIGIT4);
        KEY_COMBINATIONS_ROW_1[4] = new KeyCodeCombination(KeyCode.DIGIT5);
        KEY_COMBINATIONS_ROW_1[5] = new KeyCodeCombination(KeyCode.DIGIT6);
        KEY_COMBINATIONS_ROW_1[6] = new KeyCodeCombination(KeyCode.DIGIT7);
        KEY_COMBINATIONS_ROW_1[7] = new KeyCodeCombination(KeyCode.DIGIT8);
        KEY_COMBINATIONS_ROW_1[8] = new KeyCodeCombination(KeyCode.DIGIT9);
        KEY_COMBINATIONS_ROW_1[9] = new KeyCodeCombination(KeyCode.DIGIT0);
        KEY_COMBINATIONS_ROW_1[10] = new KeyCodeCombination(KeyCode.MINUS);
        KEY_COMBINATIONS_ROW_1[11] = new KeyCodeCombination(KeyCode.EQUALS);

        KEY_COMBINATIONS_ROW_2[0] = new KeyCodeCombination(KeyCode.Q);
        KEY_COMBINATIONS_ROW_2[1] = new KeyCodeCombination(KeyCode.W);
        KEY_COMBINATIONS_ROW_2[2] = new KeyCodeCombination(KeyCode.E);
        KEY_COMBINATIONS_ROW_2[3] = new KeyCodeCombination(KeyCode.R);
        KEY_COMBINATIONS_ROW_2[4] = new KeyCodeCombination(KeyCode.T);
        KEY_COMBINATIONS_ROW_2[5] = new KeyCodeCombination(KeyCode.Y);
        KEY_COMBINATIONS_ROW_2[6] = new KeyCodeCombination(KeyCode.U);
        KEY_COMBINATIONS_ROW_2[7] = new KeyCodeCombination(KeyCode.I);
        KEY_COMBINATIONS_ROW_2[8] = new KeyCodeCombination(KeyCode.O);
        KEY_COMBINATIONS_ROW_2[9] = new KeyCodeCombination(KeyCode.P);
        KEY_COMBINATIONS_ROW_2[10] = new KeyCodeCombination(KeyCode.OPEN_BRACKET);
        KEY_COMBINATIONS_ROW_2[11] = new KeyCodeCombination(KeyCode.CLOSE_BRACKET);

        KEY_COMBINATIONS_ROW_3[0] = new KeyCodeCombination(KeyCode.A);
        KEY_COMBINATIONS_ROW_3[1] = new KeyCodeCombination(KeyCode.S);
        KEY_COMBINATIONS_ROW_3[2] = new KeyCodeCombination(KeyCode.D);
        KEY_COMBINATIONS_ROW_3[3] = new KeyCodeCombination(KeyCode.F);
        KEY_COMBINATIONS_ROW_3[4] = new KeyCodeCombination(KeyCode.G);
        KEY_COMBINATIONS_ROW_3[5] = new KeyCodeCombination(KeyCode.H);
        KEY_COMBINATIONS_ROW_3[6] = new KeyCodeCombination(KeyCode.J);
        KEY_COMBINATIONS_ROW_3[7] = new KeyCodeCombination(KeyCode.K);
        KEY_COMBINATIONS_ROW_3[8] = new KeyCodeCombination(KeyCode.L);
        KEY_COMBINATIONS_ROW_3[9] = new KeyCodeCombination(KeyCode.SEMICOLON);
        KEY_COMBINATIONS_ROW_3[10] = new KeyCodeCombination(KeyCode.QUOTE);

        KEY_COMBINATIONS_ROW_4[0] = new KeyCodeCombination(KeyCode.Z);
        KEY_COMBINATIONS_ROW_4[1] = new KeyCodeCombination(KeyCode.X);
        KEY_COMBINATIONS_ROW_4[2] = new KeyCodeCombination(KeyCode.C);
        KEY_COMBINATIONS_ROW_4[3] = new KeyCodeCombination(KeyCode.V);
        KEY_COMBINATIONS_ROW_4[4] = new KeyCodeCombination(KeyCode.B);
        KEY_COMBINATIONS_ROW_4[5] = new KeyCodeCombination(KeyCode.N);
        KEY_COMBINATIONS_ROW_4[6] = new KeyCodeCombination(KeyCode.M);
        KEY_COMBINATIONS_ROW_4[7] = new KeyCodeCombination(KeyCode.COMMA);
        KEY_COMBINATIONS_ROW_4[8] = new KeyCodeCombination(KeyCode.PERIOD);
        KEY_COMBINATIONS_ROW_4[9] = new KeyCodeCombination(KeyCode.SLASH);

    }



    public static void mapKeys(Scene scene){

        scene.setOnKeyPressed(ke->{
            for(int i = 0; i<KEY_COMBINATIONS_ROW_1.length; i++){
                if(KEY_COMBINATIONS_ROW_1[i].match(ke)){
                    MidiPianoPlayer.notePanesRow1[i].noteOn();
                }
            }
            for(int i = 0; i<KEY_COMBINATIONS_ROW_2.length; i++){
                if(KEY_COMBINATIONS_ROW_2[i].match(ke)){
                    MidiPianoPlayer.notePanesRow2[i].noteOn();
                }
            }
            for(int i = 0; i<KEY_COMBINATIONS_ROW_3.length; i++){
                if(KEY_COMBINATIONS_ROW_3[i].match(ke)){
                    MidiPianoPlayer.notePanesRow3[i].noteOn();
                }
            }
            for(int i = 0; i<KEY_COMBINATIONS_ROW_4.length; i++){
                if(KEY_COMBINATIONS_ROW_4[i].match(ke)){
                    MidiPianoPlayer.notePanesRow4[i].noteOn();
                }
            }

        });

        scene.setOnKeyReleased(ke->{
            for(int i = 0; i<KEY_COMBINATIONS_ROW_1.length; i++){
                if(KEY_COMBINATIONS_ROW_1[i].match(ke)){
                    MidiPianoPlayer.notePanesRow1[i].noteOff();
                }
            }
            for(int i = 0; i<KEY_COMBINATIONS_ROW_2.length; i++){
                if(KEY_COMBINATIONS_ROW_2[i].match(ke)){
                    MidiPianoPlayer.notePanesRow2[i].noteOff();
                }
            }
            for(int i = 0; i<KEY_COMBINATIONS_ROW_3.length; i++){
                if(KEY_COMBINATIONS_ROW_3[i].match(ke)){
                    MidiPianoPlayer.notePanesRow3[i].noteOff();
                }
            }
            for(int i = 0; i<KEY_COMBINATIONS_ROW_4.length; i++){
                if(KEY_COMBINATIONS_ROW_4[i].match(ke)){
                    MidiPianoPlayer.notePanesRow4[i].noteOff();
                }
            }
        });




    }




    private KeyMapper(){}
    private static KeyMapper instance = new KeyMapper();
}
