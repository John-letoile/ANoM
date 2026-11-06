package model;

public class Blob {
    private double sumX;
    private double sumY;
    private int mass;

    public Blob() {
        this.sumX = 0.0;
        this.sumY = 0.0;
        this.mass = 0;
    }

    public void add(int x, int y) {
        this.mass++;
        this.sumX += x;
        this.sumY += y;

    }

    public int getMass() {
        return mass;
    }

    public double getX() {
        return sumX / mass;
    }

    public double getY() {
        return sumY / mass;
    }

    public double distanceTo(Blob other) {
        double dx = this.getX() - other.getX();
        double dy = this.getY() - other.getY();
        return Math.hypot(dx, dy);
    }

    public String toString() {
        return String.format("%d (%.4f, %.4f)", mass, getX(), getY());
    }
}
