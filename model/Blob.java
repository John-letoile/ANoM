package model;

public class Blob {
    private double x;
    private double y;
    private int size;

    public Blob() {
        this.x = 0.0;
        this.y = 0.0;
        this.size = 0;
    }

    public void add(int x, int y) {
        this.size++;
        this.x = (this.x * (size - 1) + x)  / size;
        this.y = (this.y * (size - 1) + y)  / size;

    }

    public int size() {
        return size;
    }

    public double distanceTo(Blob other) {
        double dx = this.x - other.x;
        double dy = this.y - other.y;
        return Math.hypot(dx, dy);
    }

    public String toString() {
        return String.format("%d (%.4f, %.4f)", size, x, y);
    }
}
