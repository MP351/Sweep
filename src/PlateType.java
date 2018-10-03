import javax.swing.*;
import java.awt.*;

public class PlateType {
    private ImageIcon image;
    private Statics.FieldTypes fieldType;

    public PlateType(Statics.FieldTypes fieldType) {
        this.fieldType = fieldType;
        assignImage();
    }

    public Image getImage() {
        return image.getImage();
    }

    public Statics.FieldTypes getFieldType() {
        return fieldType;
    }

    public boolean isIt(Statics.FieldTypes type) {
        return fieldType == type ? true : false;
    }

    private void assignImage() {
        image = new ImageIcon(getClass().getResource("img/" + fieldType.toString().toLowerCase() + ".png"));
    }
}
