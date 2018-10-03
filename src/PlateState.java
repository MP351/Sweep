import javax.swing.*;
import java.awt.*;

public class PlateState {
    private ImageIcon image;
    private Statics.FieldStates fieldState;

    public PlateState(Statics.FieldStates state) {
        this.fieldState = state;
        assignImages();
    }

    private void assignImages() {
        image = new ImageIcon(getClass().getResource("img/" + fieldState.toString().toLowerCase() + ".png"));
    }

    public Statics.FieldStates getFieldState() {
        return fieldState;
    }

    public Image getImage() {
        return image.getImage();
    }

    public void setFieldFlagged() {
        fieldState = Statics.FieldStates.FLAGGED;
        assignImages();
    }

    public void setFieldOpened() {
        fieldState = Statics.FieldStates.OPENED;
        assignImages();
    }

    public void setFieldClosed() {
        fieldState = Statics.FieldStates.CLOSED;
        assignImages();
    }

    public void setFieldBombed() {
        fieldState = Statics.FieldStates.BOMBED;
        assignImages();
    }

    public void setFieldNoBomb() {
        fieldState = Statics.FieldStates.NOBOMB;
        assignImages();
    }

    public void setFieldUnflagged() {
        setFieldClosed();
    }

    public boolean isIt(Statics.FieldStates state) {
        return fieldState == state ? true : false;
    }

    public boolean isClosed() {
        return fieldState == Statics.FieldStates.CLOSED ? true : false;
    }
}
