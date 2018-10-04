import javax.swing.*;

public class Game {
    private int cols;
    private int rows;
    private JPanel panel;
    private JLabel label;
    private Matrix matrix;
    private Plate[][] field;

    private Statics.GameState gameState;
    //private int bombsRemaining;
    //private int openPlatesCount;

    public Game(int cols, int rows) {
        this.cols = cols;
        this.rows = rows;
        matrix = new Matrix(cols, rows);
        field = new Plate[cols][rows];
        //bombsRemaining = matrix.getCountOfBombs();
        //openPlatesCount = 0;
    }

    public void start() {
        for (int i=0;i<cols;i++) {
            for (int j=0;j<rows;j++) {
                field[i][j] = new Plate(new PlateType(matrix.getMatrix()[i][j]));
            }
        }

        panel = new FieldPanel(this, field, cols, rows);
        label = new JLabel("Find the bombs!");

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
            plate.setOpened();

            if (plate.isZero()) {
                openNearZeroPlates(x, y);
            } else if (plate.isBomb()) {
                plate.setBombed();
            }
        }
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
        } else if (plate.isFlagged()) {
            plate.unflag();
        }
    }

    public void win() {
        label.setText("You WIN!");
        gameState = Statics.GameState.WIN;
    }

    public void go() {
        for (int i=0;i<cols;i++) {
            for (int j=0;j<rows;j++) {
                Plate plate = field[i][j];
                if (plate.isClosed() && plate.isBomb()) {
                    plate.setOpened();
                } else if (plate.isFlagged() && !plate.isBomb()) {
                    plate.setError();
                }
            }
        }

        label.setText("You loose!");
        gameState = Statics.GameState.GO;
    }

    private void openNearZeroPlates(int x, int y) {
        int x_near = x-1;
        int y_near = y-1;

        for (int i=x_near;i<x_near+3;i++) {
            for (int j=y_near;j<y_near+3;j++) {
                if (i < 0 || j < 0 || i >= cols || j >= rows)
                    continue;
                else if (field[i][j].isClosed() && field[i][j].isZero()) {
                    field[i][j].setOpened();
                    openNearZeroPlates(i, j);
                } else {
                    field[i][j].setOpened();
                }
            }
        }
    }

    public JPanel getPanel() {
        return panel;
    }

    public JLabel getLabel() {
        return label;
    }

    public int getCols() {
        return cols;
    }

    public int getRows() {
        return rows;
    }

    public int getBombCount() {
        return matrix.getCountOfBombs();
    }
}
