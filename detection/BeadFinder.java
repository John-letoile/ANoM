package detection;

import java.util.List;

import model.Blob;

public class BeadFinder {
    public List<Blob> getBeads(int min, List<Blob> blobs) {
        return blobs.stream()
                .filter(blob -> blob.getMass() >= min)
                .toList();
    }
}
