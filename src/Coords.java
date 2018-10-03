public class Coords {
    private int x;
    private int y;

    public Coords(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Coords) {
            return ((Coords)o).getX() == x && ((Coords)o).getY() == y;
        }
        return super.equals(o);
    }
}
