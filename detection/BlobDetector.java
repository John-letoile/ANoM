package detection;

import lib.Picture;
import model.Blob;
import java.util.List;

public interface BlobDetector {
    List<Blob> detect(Picture image);
}
