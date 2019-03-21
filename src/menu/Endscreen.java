package menu;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import settings.Settings;

public class Endscreen {

    private static Label endscreen;


    public static void init() {

        endscreen = new Label();
        endscreen.setPrefWidth(Settings.SCENE_WIDTH);
        endscreen.setPrefHeight(Settings.CELL_SIZE * Settings.CELL_COUNT);
        endscreen.setAlignment(Pos.CENTER);
        endscreen.setVisible(false);
        endscreen.setTextFill(Color.WHITE);
        endscreen.setFont(Font.font("Gameplay", Settings.END_FONT_SIZE));
        endscreen.setBackground(new Background(new BackgroundFill(
            Color.rgb(8, 8, 8, 0.5),
            CornerRadii.EMPTY,
            Insets.EMPTY)));
    }


    public static void setText(String text) {

        endscreen.setText(text);
    }


    public static void show() {

        endscreen.setVisible(true);
    }


    public static void hide() {

        endscreen.setVisible(false);
    }


    public static Label getEndscreen() {

        return endscreen;
    }

}
