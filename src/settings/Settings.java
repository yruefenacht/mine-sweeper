package settings;

import javafx.scene.paint.Color;

import java.util.Map;

public class Settings {

    public static final int BOMB_COUNT = 20;
    public static final int CELL_SIZE = 30;
    public static final int CELL_COUNT = 14;
    public static final int TOP = 100;
    public static final int SCENE_WIDTH = CELL_COUNT * CELL_SIZE;
    public static final int SCENE_HEIGHT = CELL_COUNT * CELL_SIZE + TOP;
    public static final double ICON_SIZE = 40;
    public static final int FONT_SIZE = 30;
    public static final int CELL_FONT_SIZE = 20;
    public static final int PADDING_ICON = 5;
    public static final int PADDING_MENU = 30;

    public static final Color GREEN = Color.rgb(169, 215, 81);
    public static final Color LIGHT_GREEN = Color.rgb(162, 208, 73);
    public static final Color CELL_HOVER = Color.rgb(199, 235, 111);
    public static final Color BEIGE = Color.rgb(228, 194, 159);
    public static final Color LIGHT_BEIGE = Color.rgb(215, 184, 153);
    public static final Color TOP_COLOR = Color.rgb(1, 37, 42);

    public static final Map<Integer, Color> NUMBER_COLORS = Map.of(
        1, Color.rgb(7, 120, 203),
        2, Color.rgb(46, 142, 72),
        3, Color.rgb(215, 44, 52),
        4, Color.rgb(126, 36, 156),
        5, Color.rgb(255, 141, 50),
        6, Color.rgb(0, 153, 165)
    );
}
