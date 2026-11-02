package detection;

import model.Blob;
import java.util.List;

public interface BeadFilter {
    List<Blob> apply(List<Blob> blobs);
}
