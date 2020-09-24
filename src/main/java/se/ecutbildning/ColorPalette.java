package se.ecutbildning;

import javafx.scene.paint.Color;

public class ColorPalette {


    // Application colors
    public static final Color BCG =  new Color(0.2,0.2,0.2,1);
    public static final Color CONTROLS = new Color(0.5,0.3,0.6,0.5);
    public static final Color BTN_ON_1 = new Color(0.4,0.9,0.2,0.3);
    public static final Color BTN_ON_2 = new Color(0.2,0.2,0.5,1);
    public static final Color BTN_OFF_1 = new Color(0.5,0.9,0.4,0.6);
    public static final Color BTN_OFF_2 = new Color(0.2,0.2,0.7,0.2);



    // Constructor and singleton instance
    private ColorPalette(){}
    private static final  ColorPalette instance = new ColorPalette();
}
