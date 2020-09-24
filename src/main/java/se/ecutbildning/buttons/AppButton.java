package se.ecutbildning.buttons;

import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import se.ecutbildning.ColorPalette;

abstract public class AppButton extends Button {


    abstract public void onAction();



    private void pressAnimation(){
        new Thread(()->{
            setScaleX(0.95);
            setScaleY(0.95);
            setDisable(true);
            try{
                Thread.sleep(120);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            setScaleX(1);
            setScaleY(1);
            setDisable(false);
        }).start();
    }



    public AppButton(int xLocation, int yLocation, String buttonText){
        relocate(xLocation,yLocation);
        setText(buttonText);
        setPrefHeight(25);
        setBackground(new Background(new BackgroundFill(ColorPalette.CONTROLS, new CornerRadii(5), null)));
        setBorder(new Border(new BorderStroke(ColorPalette.BTN_ON_2, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(1))));
        setEffect(new DropShadow(5, Color.GRAY));
        setOnAction(e->{
            onAction();
            pressAnimation();
        });
    }

}
