package tracking;

import model.Blob;
import java.util.List;

public interface BeadTracker {
    List<Blob> track(List<Blob> previous, List<Blob> current);
}
