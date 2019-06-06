package field;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import menu.*;
import settings.*;

class Cell extends StackPane {

    private boolean isUncovered = false;
    private boolean isFlagged = false;
    private boolean isDark;
    private Color backgroundColor;
    private ImageView flagIcon;
    private int value;
    private int x;
    private int y;


    Cell(int x, int y, int value, boolean isDark) {

        this.x = x;
        this.y = y;
        this.value = value;
        this.isDark = isDark;
        this.setTranslateX(x * Settings.CELL_SIZE);
        this.setTranslateY(y * Settings.CELL_SIZE);
        this.setPrefSize(Settings.CELL_SIZE, Settings.CELL_SIZE);
        this.backgroundColor = this.isDark ? Settings.GREEN : Settings.LIGHT_GREEN;
        this.setBackground(new Background(new BackgroundFill(this.backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
        this.setAlignment(Pos.CENTER);
        this.flagIcon = new ImageView(new Image(this.getClass().getClassLoader().getResourceAsStream("flag.png")));
        this.flagIcon.setFitHeight(Settings.ICON_SIZE / 2);
        this.flagIcon.setFitWidth(Settings.ICON_SIZE / 2);

        addHoverEffects();
        addClickListener();
    }


    void uncover() {

        Color backgroundColor = this.isDark ? Settings.BEIGE : Settings.LIGHT_BEIGE;
        this.setBackground(new Background(new BackgroundFill(backgroundColor, CornerRadii.EMPTY, Insets.EMPTY)));
        addIcon();
        this.isUncovered = true;

        if(MineField.allCellsAreUncovered()) {
            GameTimer.stop();
            GameState.STATE = GameStates.GAMEOVER;
            EndScreen.setText("You Won");
            EndScreen.show();
        }
    }


    private void addIcon() {

        if(value < 0) {

            ImageView bombIcon = new ImageView(
                new Image(this.getClass().getClassLoader().getResourceAsStream("explosion.png"))
            );
            bombIcon.setFitWidth(Settings.ICON_SIZE / 2);
            bombIcon.setFitHeight(Settings.ICON_SIZE / 2);
            this.getChildren().add(bombIcon);

        } else if(value >= 1) {

            Label valueLabel = new Label(Integer.toString(this.value));
            valueLabel.setPrefSize(Settings.CELL_SIZE, Settings.CELL_SIZE);
            valueLabel.setAlignment(Pos.CENTER);
            valueLabel.setTextFill(Settings.NUMBER_COLORS.get(this.value));
            valueLabel.setFont(new Font("Arial", Settings.CELL_FONT_SIZE));
            this.getChildren().add(valueLabel);
        }
    }


    private void toggleFlag() {

        if(this.isFlagged) {
            this.getChildren().remove(this.flagIcon);
            this.isFlagged = false;
            FlagCounter.increase();
        } else {
            if(!FlagCounter.isEmpty()) {
                this.getChildren().add(this.flagIcon);
                this.isFlagged = true;
                FlagCounter.decrease();
            }
        }
    }


    private void uncoverNeighbors(int x, int y) {

        Cell cell = MineField.getCellAt(x, y);

        if(cell == null) return;

        if(cell.getValue() > 0)
            cell.uncover();

        if(cell.getValue() == 0 && !cell.isUncovered) {
            cell.uncover();
            uncoverNeighbors(x, y-1);
            uncoverNeighbors(x+1, y-1);
            uncoverNeighbors(x+1, y);
            uncoverNeighbors(x+1, y+1);
            uncoverNeighbors(x, y+1);
            uncoverNeighbors(x-1, y+1);
            uncoverNeighbors(x-1, y);
            uncoverNeighbors(x-1, y-1);
        }
    }


    private void addClickListener() {

        this.setOnMouseClicked(e -> {

            if(GameState.STATE == GameStates.GAMEOVER)
                return;

            //Right click
            if(e.getButton() == MouseButton.SECONDARY) {
                if(!this.isUncovered)
                    toggleFlag();

            //Left click
            } else {
                if(!this.isUncovered && !this.isFlagged) {

                    if(GameState.STATE == GameStates.READY) {
                        GameTimer.start();
                        GameState.STATE = GameStates.RUNNING;
                    }
                    if(this.value == 0) {
                        uncoverNeighbors(this.x, this.y);
                    }
                    else if(this.value == -1) {
                        MineField.uncoverAllBombs();
                        GameTimer.stop();
                        GameState.STATE = GameStates.GAMEOVER;
                        EndScreen.setText("Game Over");
                        EndScreen.show();
                    }
                    else
                        uncover();
                }
            }
        });
    }


    private void addHoverEffects() {

        this.setOnMouseEntered(e -> {

            if(!this.isUncovered) {
                this.setBackground(
                    new Background(new BackgroundFill(Settings.CELL_HOVER, CornerRadii.EMPTY, Insets.EMPTY))
                );
            }
        });

        this.setOnMouseExited(e -> {

            if(!this.isUncovered) {
                this.setBackground(
                    new Background(new BackgroundFill(this.backgroundColor, CornerRadii.EMPTY, Insets.EMPTY))
                );
            }
        });
    }


    int getX() {

        return this.x;
    }


    int getY() {

        return this.y;
    }


    int getValue() {

        return this.value;
    }


    boolean isUncovered() {

        return this.isUncovered;
    }

}
