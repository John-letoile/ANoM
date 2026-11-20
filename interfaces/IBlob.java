package interfaces;

public interface IBlob {
    public void add(int x, int y);
    public int getMass();
    public double getX();
    public double getY();
    public double distanceTo(IBlob other);
    public String toString();
}
