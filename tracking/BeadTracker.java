package tracking;

import java.util.ArrayList;
import java.util.Comparator;

import detection.*;
import lib.Picture;
import model.Blob;

public class BeadTracker {
    public ArrayList<Double> minDistance(Picture pic1, Picture pic2, int min_pixels, int min) {
        ArrayList<Double> dists = new ArrayList<>();
        ArrayList<Double> tempDist = new ArrayList<>();
        BeadFinder bdf = new BeadFinder();
        BlobDetector bbf = new BlobDetector();
        ArrayList<Blob> beadPrev = bdf.getBeads(min, bbf.findBlobs(pic1));
        ArrayList<Blob> beadCurrent = bdf.getBeads(min, bbf.findBlobs(pic2));

        for (Blob beadC : beadCurrent) {
            for (Blob beadP : beadPrev) {
                tempDist.add(beadC.distanceTo(beadP));
            }

            tempDist.sort(Comparator.naturalOrder());
            if (tempDist.getFirst() <= min_pixels)
                dists.add(tempDist.getFirst());
            tempDist.clear();
        }

        return dists;
    }

}
