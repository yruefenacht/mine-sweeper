package menu;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Duration;
import settings.Settings;

public class GameTimer {

    private static Timeline timer;
    private static HBox timerPane;
    private static Label timerLabel;
    private static int counter = 0;
    private static boolean isRunning = false;


    public static void init() {

        timerLabel = new Label("0");
        timerLabel.setFont(new Font("Arial", Settings.MENU_FONT_SIZE));
        timerLabel.setTextFill(Color.WHITE);
        timer = new Timeline(new KeyFrame(
            Duration.millis(1000),
            e -> update()));
        timer.setCycleCount(Animation.INDEFINITE);

        ImageView timerIcon = new ImageView(new Image("res/alarm-clock.png"));
        timerIcon.setFitWidth(Settings.ICON_SIZE);
        timerIcon.setFitHeight(Settings.ICON_SIZE);

        timerPane = new HBox(timerIcon, timerLabel);
        timerPane.setAlignment(Pos.CENTER);
        timerPane.setSpacing(Settings.PADDING_ICON);
    }


    private static void update() {

        counter++;
        timerLabel.setText(Integer.toString(counter));
    }


    public static void start() {

        if(!isRunning) {
            timer.play();
            timer.jumpTo(Duration.ZERO);
            isRunning = true;
        }
    }


    public static void stop() {

        if(isRunning) {
            timer.stop();
            isRunning = false;
        }
    }


    public static void reset() {

        stop();
        counter = -1;
        update();
    }


    public static boolean isRunning() {

        return isRunning;
    }


    public static Pane getTimerPane() {

        return timerPane;
    }

}
