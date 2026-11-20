package tracking;

import detection.*;
import interfaces.IBeadTracker;
import interfaces.IBlob;
import interfaces.IBlobDetector;
import java.util.ArrayList;
import java.util.List;
import lib.Picture;

public class BeadTracker implements IBeadTracker {
    private final BeadFinder beadFinder;
    private final IBlobDetector blobDetector;

    public BeadTracker(BeadFinder beadFinder, IBlobDetector blobDetector) {
        this.beadFinder = beadFinder;
        this.blobDetector = blobDetector;
    }

    public List<Double> minDistance(Picture pic1, Picture pic2, int min_pixels, int min) {
        List<Double> dists = new ArrayList<>();
        List<IBlob> beadPrev = beadFinder.getBeads(min, blobDetector.findBlobs(pic1));
        List<IBlob> beadCurrent = beadFinder.getBeads(min, blobDetector.findBlobs(pic2));

        for (IBlob beadC : beadCurrent) {
            double minDistance = Double.POSITIVE_INFINITY;

            for (IBlob previous : beadPrev) {
                double distance = beadC.distanceTo(previous);
                minDistance = Math.min(minDistance, distance);
            }

            if (minDistance <= min_pixels) {
                dists.add(minDistance);
            }
        }

        return dists;
    }

}
