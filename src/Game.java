import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Game {
    private int cols;
    private int rows;
    private JPanel panel;
    private Matrix matrix;
    private Plate[][] field;
    private Statics.GameState gameState;
    private int bombsRemaining;
    private int openPlatesCount;

    public Game(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        matrix = new Matrix(cols, rows);
        field = new Plate[cols][rows];
        bombsRemaining = matrix.getCountOfBombs();
        openPlatesCount = 0;
    }

    public void start() {
        for (int i=0;i<cols;i++) {
            for (int j=0;j<rows;j++) {
                field[i][j] = new Plate(new PlateType(matrix.getMatrix()[i][j]));
            }
        }

        panel = new JPanel() {
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
        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                int x = mouseEvent.getX() / Statics.IMAGE_SIZE;
                int y = mouseEvent.getY() / Statics.IMAGE_SIZE;

                if (mouseEvent.getButton() == MouseEvent.BUTTON1) //LeftButton
                    leftClick(x, y);
                else if (mouseEvent.getButton() == MouseEvent.BUTTON3) //RightButton
                    rightClick(x, y);
                else if (mouseEvent.getButton() == MouseEvent.BUTTON2) //MiddleButton
                    start();

                panel.repaint();
            }
        });

        panel.setPreferredSize(new Dimension(Statics.COLS * Statics.IMAGE_SIZE, Statics.ROWS * Statics.IMAGE_SIZE));
        gameState = Statics.GameState.PLAY;
    }

    public void leftClick(int x, int y) {
        if (gameState != Statics.GameState.PLAY) return;

        Plate plate = field[x][y];
        if (plate == null) {
            System.out.println("NULL");
            return;
        }

        if (plate.isClosed()) {
            openPlatesCount += plate.setOpened();

            if (plate.getType().getFieldType() == Statics.FieldTypes.ZERO) {
                openNearZeroPlates(x, y);
            } else if (plate.getType().getFieldType() == Statics.FieldTypes.BOMB) {
                plate.setBombed();
                gameOver();
            }
        }

        if (checkEnd())
            gameWin();
    }

    public void rightClick(int x, int y) {
        if (gameState != Statics.GameState.PLAY) return;

        Plate plate = field[x][y];
        if (plate == null) {
            System.out.println("NULL");
            return;
        }

        if (plate.isClosed()) {
            plate.setFlagged();
            bombsRemaining--;
            openPlatesCount++;
        } else if (plate.isFlagged()) {
            plate.unflag();
            bombsRemaining++;
            openPlatesCount--;
        }

        if (checkEnd())
            gameWin();
    }

    private boolean checkEnd() {
        System.out.println(openPlatesCount);
        if (bombsRemaining == 0 && openPlatesCount == (cols * rows)) {
            return true;
        } else return false;
    }

    private void gameWin() {
        gameState = Statics.GameState.WIN;
    }

    private void gameOver() {
        for (int i=0;i<cols;i++) {
            for (int j=0;j<rows;j++) {
                Plate plate = field[i][j];
                if (plate.isClosed() && plate.isBomb()) {
                    openPlatesCount += plate.setOpened();
                } else if (plate.isFlagged() && !plate.isBomb()) {
                    plate.setError();
                }
            }
        }

        gameState = Statics.GameState.GO;
    }

    private void openNearZeroPlates(int x, int y) {
        int x_near = x-1;
        int y_near = y-1;

        for (int i=x_near;i<x_near+3;i++) {
            for (int j=y_near;j<y_near+3;j++) {
                if (i < 0 || j < 0 || i >= cols || j >= rows)
                    continue;
                else if (field[i][j].isClosed() && field[i][j].getType().getFieldType() == Statics.FieldTypes.ZERO) {
                    openPlatesCount += field[i][j].setOpened();
                    openNearZeroPlates(i, j);
                } else {
                    openPlatesCount += field[i][j].setOpened();
                }
            }
        }
    }

    public JPanel getPanel() {
        return panel;
    }
}
