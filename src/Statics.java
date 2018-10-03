public class Statics {
    public enum FieldTypes { ZERO, NUM1, NUM2, NUM3, NUM4, NUM5, NUM6, NUM7, NUM8, BOMB };
    public enum FieldStates { OPENED, CLOSED, FLAGGED, BOMBED, NOBOMB };
    public enum GameState { PLAY, WIN, GO }

    public static final int COLS = 5;
    public static final int ROWS = 5;
    public static final int IMAGE_SIZE = 50;
}
