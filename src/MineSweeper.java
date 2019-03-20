import field.MineField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import menu.FlagCounter;
import menu.GameState;
import menu.GameStates;
import menu.GameTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import settings.Settings;

public class MineSweeper extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Root
        BorderPane root = new BorderPane();

        //Top
        GameTimer.init();
        FlagCounter.init();
        HBox top = new HBox(FlagCounter.getFlagCounterPane(), GameTimer.getTimerPane());
        top.setAlignment(Pos.CENTER);
        top.setPrefHeight(Settings.TOP);
        top.setBackground(new Background(new BackgroundFill(Settings.TOP_COLOR, CornerRadii.EMPTY, Insets.EMPTY)));
        top.setSpacing(Settings.PADDING_MENU);

        //Center
        MineField.init(Settings.CELL_COUNT);
        Pane mineFieldPane = new Pane();
        mineFieldPane.getChildren().addAll(MineField.getCells());

        //Merge
        root.setTop(top);
        root.setCenter(mineFieldPane);

        //Scene
        Scene scene = new Scene(root, Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);

        //Listener
        scene.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.R) {
                mineFieldPane.getChildren().clear();
                MineField.init(Settings.CELL_COUNT);
                mineFieldPane.getChildren().addAll(MineField.getCells());
                FlagCounter.reset();
                GameTimer.reset();
                GameState.STATE = GameStates.READY;
            }
        });

        //Stage
        primaryStage.setTitle("MineSweeper");
        primaryStage.getIcons().add(new Image(MineSweeper.class.getResourceAsStream("app-icon.png")));
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
