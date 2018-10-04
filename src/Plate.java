import java.awt.*;

public class Plate {
    private PlateType type;
    private PlateState state;

    public Plate(PlateType type) {
        this.type = type;
        this.state = new PlateState();
    }

    public boolean isClosed() {
        return state.isClosed();
    }

    public boolean isFlagged() {
        return state.isFlagged();
    }

    public boolean isBombed() {
        return state.isBombed();
    }

    public boolean isNoBomb() {
        return state.isNoBomb();
    }

    public boolean isBomb() {
        return type.isBomb();
    }

    public boolean isZero() {
        return type.isZero();
    }

    public boolean isNumber() {
        return type.isNumber();
    }

    //TODO
    public void setOpened() {
        if (state.isClosed()) {
            GameManager.plateOpen();
            state.setFieldOpened();
        } else {
        }
    }

    public void setFlagged() {
        GameManager.flagged();
        state.setFieldFlagged();
    }

    public void setBombed() {
        GameManager.bombOpen();
        state.setFieldBombed();
    }

    public void setError() {
        state.setFieldNoBomb();
    }

    public void unflag() {
        GameManager.unflagged();
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
