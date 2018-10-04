import javax.swing.*;
import java.awt.*;

public class PlateState {
    public enum FieldStates { OPENED, CLOSED, FLAGGED, BOMBED, NOBOMB };
    private Image image;
    private FieldStates fieldState;

    public PlateState() {
        this.fieldState = FieldStates.CLOSED;
        assignImages();
    }

    private void assignImages() {
        image = new ImageIcon(getClass().getResource("img/" + fieldState.toString().toLowerCase() + ".png")).getImage();
    }

    public FieldStates getFieldState() {
        return fieldState;
    }

    public Image getImage() {
        return image;
    }

    public void setFieldFlagged() {
        fieldState = FieldStates.FLAGGED;
        assignImages();
    }

    public void setFieldOpened() {
        fieldState = FieldStates.OPENED;
        assignImages();
    }

    public void setFieldClosed() {
        fieldState = FieldStates.CLOSED;
        assignImages();
    }

    public void setFieldBombed() {
        fieldState = FieldStates.BOMBED;
        assignImages();
    }

    public void setFieldNoBomb() {
        fieldState = FieldStates.NOBOMB;
        assignImages();
    }

    public void setFieldUnflagged() {
        setFieldClosed();
    }

    public boolean isClosed() {
        return fieldState == FieldStates.CLOSED ? true : false;
    }

    public boolean isFlagged() {
        return fieldState == FieldStates.FLAGGED ? true : false;
    }

    public boolean isBombed() {
        return fieldState == FieldStates.BOMBED ? true : false;
    }

    public boolean isNoBomb() {
        return fieldState == FieldStates.NOBOMB ? true : false;
    }
}
