package tracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;

import detection.*;
import lib.Picture;
import model.Blob;

public class BeadTracker {
    private final BeadFinder beadFinder;
    private final BlobDetector blobDetector;

    public BeadTracker() {
        this(new BeadFinder(), new BlobDetector());
    }

    public BeadTracker(BeadFinder beadFinder, BlobDetector blobDetector) {
        this.beadFinder = beadFinder;
        this.blobDetector = blobDetector;
    }

    public List<Double> minDistance(Picture pic1, Picture pic2, int min_pixels, int min) {
        List<Double> dists = new ArrayList<>();
        List<Blob> beadPrev = beadFinder.getBeads(min, blobDetector.findBlobs(pic1));
        List<Blob> beadCurrent = beadFinder.getBeads(min, blobDetector.findBlobs(pic2));

        for (Blob beadC : beadCurrent) {
            double minDistance = Double.POSITIVE_INFINITY;

            for (Blob previous : beadPrev) {
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
