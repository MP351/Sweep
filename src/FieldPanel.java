import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FieldPanel<T extends Game> extends JPanel {
    private Plate[][] field;
    private int cols;
    private int rows;
    private T context;

    public FieldPanel(T t, Plate[][] field, int cols, int rows) {
        super();
        this.context = t;
        this.field = field;
        this.cols = cols;
        this.rows = rows;

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                int x = mouseEvent.getX() / Statics.IMAGE_SIZE;
                int y = mouseEvent.getY() / Statics.IMAGE_SIZE;

                if (mouseEvent.getButton() == MouseEvent.BUTTON1) //LeftButton
                    context.leftClick(x, y);
                else if (mouseEvent.getButton() == MouseEvent.BUTTON3) //RightButton
                    context.rightClick(x, y);
                else if (mouseEvent.getButton() == MouseEvent.BUTTON2) //MiddleButton
                    context.start();

                repaint();
            }
        });

        setPreferredSize(new Dimension(Statics.COLS * Statics.IMAGE_SIZE, Statics.ROWS * Statics.IMAGE_SIZE));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        for (int i=0;i<cols;i++) {
            for (int j=0;j<rows;j++) {
                graphics.drawImage(field[i][j].getImage(),
                        i * Statics.IMAGE_SIZE,
                        j * Statics.IMAGE_SIZE,
                        Statics.IMAGE_SIZE, Statics.IMAGE_SIZE,
                        this);
            }
        }
    }
}
