package tracking;

import model.Blob;
import java.util.ArrayList;
import java.util.List;

public class ProximityBeadTracker implements BeadTracker {
    private final double maxDistance;

    public ProximityBeadTracker(double maxDistance) {
        this.maxDistance = maxDistance;
    }

    @Override
    public List<Blob> track(List<Blob> previousBeads, List<Blob> currentBeads) {
        List<Blob> trackedBeads = new ArrayList<>();

        return trackedBeads;
    }
}

