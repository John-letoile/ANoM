package interfaces;

import java.util.List;
import lib.Picture;

public interface IBlobDetector {
    public List<IBlob> findBlobs(Picture image);
}
