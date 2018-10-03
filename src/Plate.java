import java.awt.*;

public class Plate {
    private PlateType type;
    private PlateState state;

    public Plate(PlateType type) {
        this.type = type;
        this.state = new PlateState(Statics.FieldStates.CLOSED);
    }

    public PlateType getType() {
        return type;
    }

    public boolean isClosed() {
        return state.isClosed();
    }

    public boolean isFlagged() {
        return state.isIt(Statics.FieldStates.FLAGGED);
    }

    public boolean isBomb() {
        return type.isIt(Statics.FieldTypes.BOMB);
    }

    public int setOpened() {
        if (state.isIt(Statics.FieldStates.CLOSED)) {
            state.setFieldOpened();
            return 1;
        } else {
            return 0;
        }
    }

    public void setFlagged() {
        state.setFieldFlagged();
    }

    public void setBombed() {
        state.setFieldBombed();
    }

    public void setError() {
        state.setFieldNoBomb();
    }

    public void unflag() {
        state.setFieldUnflagged();
    }

    public Image getImage() {
        switch (state.getFieldState()) {
            case CLOSED:
                return state.getImage();
            case FLAGGED:
                return state.getImage();
            case BOMBED:
                return state.getImage();
            case NOBOMB:
                return state.getImage();
            default:
                return type.getImage();
        }
    }
}
