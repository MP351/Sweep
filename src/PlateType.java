import javax.swing.*;
import java.awt.*;

public class PlateType {
    public enum FieldTypes { ZERO, NUM1, NUM2, NUM3, NUM4, NUM5, NUM6, NUM7, NUM8, BOMB };

    private ImageIcon image;
    private FieldTypes fieldType;

    public PlateType(int fieldType) {
        try {
            this.fieldType = initType(fieldType);
        } catch (Exception e) {
            e.printStackTrace();
        }

        assignImage();
    }

    public boolean isBomb() {
        return fieldType == FieldTypes.BOMB;
    }

    public boolean isZero() {
        return fieldType == FieldTypes.ZERO;
    }

    public boolean isNumber() {
        return !isBomb() && !isZero();
    }

    public Image getImage() {
        return image.getImage();
    }

    private void assignImage() {
        image = new ImageIcon(getClass().getResource("img/" + fieldType.toString().toLowerCase() + ".png"));
    }

    private FieldTypes initType(int type) throws Exception{
        switch (type) {
            case -1:
                return FieldTypes.BOMB;
            case 0:
                return FieldTypes.ZERO;
            case 1:
                return FieldTypes.NUM1;
            case 2:
                return FieldTypes.NUM2;
            case 3:
                return FieldTypes.NUM3;
            case 4:
                return FieldTypes.NUM4;
            case 5:
                return FieldTypes.NUM5;
            case 6:
                return FieldTypes.NUM6;
            case 7:
                return FieldTypes.NUM7;
            case 8:
                return FieldTypes.NUM8;
            default:
                throw new Exception("Wrong type argument");
        }
    }
}
