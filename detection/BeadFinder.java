package detection;

import java.util.ArrayList;

import lib.Picture;
import model.Blob;

public class BeadFinder {
    public ArrayList<Blob> getBeads(int min, ArrayList<Blob> blobs) {
        for (int i = blobs.size() - 1; i >= 0; i--) {
            if (blobs.get(i).size() < min) {
                blobs.remove(i);
            }
        }

        return blobs;
    }
}
