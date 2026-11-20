package interfaces;

import java.util.List;
import lib.Picture;

public interface IBeadTracker {
    public List<Double> minDistance(Picture pic1, Picture pic2, int min_pixels, int min);
}
