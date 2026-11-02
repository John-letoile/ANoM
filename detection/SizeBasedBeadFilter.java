package detection;

import model.Blob;
import java.util.ArrayList;
import java.util.List;

public class SizeBasedBeadFilter implements BeadFilter {
    private final int minSize;

    public SizeBasedBeadFilter(int minSize) {
        this.minSize = minSize;
    }

    public List<Blob> apply(List<Blob> blobs) {
        ArrayList<Blob> filtered = new ArrayList<>();
        for (Blob blob : blobs) {
            if (blob.size() >= minSize) {
                filtered.add(blob);
            }
        }
        return filtered;
    }
}
