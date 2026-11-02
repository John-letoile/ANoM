package detection;

import lib.Picture;
import model.Blob;
import java.util.ArrayList;
import java.util.List;

public class BlobFinder implements BlobDetector {
    private final ForegroundPredicate predicate;

    public BlobFinder() {
        this(new WhiteForegroundPredicate());
    }

    public BlobFinder(ForegroundPredicate predicate) {
        this.predicate = predicate;
    }

    public List<Blob> detect(Picture image) {
        ArrayList<Blob> blobs = new ArrayList<>();
        boolean[][] visited = new boolean[image.width()][image.height()];

        for (int x = 0; x < image.width(); x++) {
            for (int y = 0; y < image.height(); y++) {
                if (predicate.isForeground(image.get(x, y)) && !visited[x][y]) {
                    Blob blob = new Blob();
                    dfs(x, y, image, visited, blob);
                    blobs.add(blob);
                }
            }
        }

        return blobs;
    }

    private void dfs(int x, int y, Picture image,
                     boolean[][] visited, Blob blob) {
        if (x < 0 || x >= image.width() || y < 0 || y >= image.height()) {
            return;
        }

        if (visited[x][y] || !predicate.isForeground(image.get(x, y))) {
            return;
        }

        visited[x][y] = true;
        blob.add(x, y);

        dfs(x + 1, y, image, visited, blob);
        dfs(x - 1, y, image, visited, blob);
        dfs(x, y + 1, image, visited, blob);
        dfs(x, y - 1, image, visited, blob);
    }
}

