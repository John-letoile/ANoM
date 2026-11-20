package detection;

import interfaces.IBlob;
import java.util.List;

public class BeadFinder {
    public List<IBlob> getBeads(int min, List<IBlob> blobs) {
        return blobs.stream()
                .filter(blob -> blob.getMass() >= min)
                .toList();
    }
}
