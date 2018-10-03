public class Matrix {
    private int x_count;
    private int y_count;
    private int binMatrix[][];
    private Statics.FieldTypes fieldMatrix[][];
    private int countOfBombs;

    public Matrix(int x_count, int y_count) {
        this.x_count = x_count;
        this.y_count = y_count;

        countOfBombs = Math.round(x_count * y_count / 7);
        binMatrix = new int[x_count][y_count];
        fieldMatrix = new Statics.FieldTypes[x_count][y_count];

        genMatrix();
    }

    private void genMatrix() {
        int bombGenRemaining = countOfBombs;
        while (bombGenRemaining != 0) {
            int x = (int) Math.round(Math.random() * (x_count-1));
            int y = (int) Math.round(Math.random() * (y_count-1));
            System.out.println("X - " + x + "; Y - " + y);
            binMatrix[x][y] = 1;

            bombGenRemaining--;
        }

        makeFieldMatrix();
    }

    private void makeFieldMatrix() {
        int count;
        for (int i=0;i<x_count;i++) {
            for (int j=0;j<y_count;j++) {
                if (binMatrix[i][j] == 1) {
                    fieldMatrix[i][j] = Statics.FieldTypes.BOMB;
                    continue;
                } else {
                    count = countBombsNear(i, j);
                    switch (count) {
                        case 0:
                            fieldMatrix[i][j] = Statics.FieldTypes.ZERO;
                            break;
                        case 1:
                            fieldMatrix[i][j] = Statics.FieldTypes.NUM1;
                            break;
                        case 2:
                            fieldMatrix[i][j] = Statics.FieldTypes.NUM2;
                            break;
                        case 3:
                            fieldMatrix[i][j] = Statics.FieldTypes.NUM3;
                            break;
                        case 4:
                            fieldMatrix[i][j] = Statics.FieldTypes.NUM4;
                            break;
                        case 5:
                            fieldMatrix[i][j] = Statics.FieldTypes.NUM5;
                            break;
                        case 6:
                            fieldMatrix[i][j] = Statics.FieldTypes.NUM6;
                            break;
                        case 7:
                            fieldMatrix[i][j] = Statics.FieldTypes.NUM7;
                            break;
                        case 8:
                            fieldMatrix[i][j] = Statics.FieldTypes.NUM8;
                            break;
                    }
                }
            }
        }
    }

    private int countBombsNear(int x, int y) {
        int count = 0;
        int x_near = x-1;
        int y_near = y-1;
        for (int i=x_near;i<x_near+3;i++) {
            for (int j=y_near;j<y_near+3;j++) {
                if (i < 0 || j < 0 || i >= x_count || j >= y_count)
                    continue;
                else if (binMatrix[i][j] == 1)
                    count++;
            }
        }
        return count;
    }

    public Statics.FieldTypes[][] getMatrix() {
        return fieldMatrix;
    }

    public int getCountOfBombs() {
        return countOfBombs;
    }
}
