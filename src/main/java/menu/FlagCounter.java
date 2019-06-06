package menu;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import settings.Settings;

public class FlagCounter {

    private static HBox flagCounterPane;
    private static Label flagCounterLabel;
    private static int counter = Settings.BOMB_COUNT;


    public static void init() {

        flagCounterLabel = new Label(Integer.toString(counter));
        flagCounterLabel.setFont(new Font("Arial", Settings.MENU_FONT_SIZE));
        flagCounterLabel.setTextFill(Color.WHITE);

        ImageView flagCounterIcon = new ImageView(new Image(FlagCounter.class.getResourceAsStream("../flag.png")));
        flagCounterIcon.setFitWidth(Settings.ICON_SIZE);
        flagCounterIcon.setFitHeight(Settings.ICON_SIZE);

        flagCounterPane = new HBox(flagCounterIcon, flagCounterLabel);
        flagCounterPane.setAlignment(Pos.CENTER);
    }


    public static void increase() {

        counter++;
        flagCounterLabel.setText(Integer.toString(counter));
    }


    public static void decrease() {

        counter--;
        flagCounterLabel.setText(Integer.toString(counter));
    }


    public static void reset() {

        counter = Settings.BOMB_COUNT;
        flagCounterLabel.setText(Integer.toString(counter));
    }


    public static boolean isEmpty() {

        return counter == 0;
    }


    public static Pane getFlagCounterPane() {

        return flagCounterPane;
    }
}
