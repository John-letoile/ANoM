package detection;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import lib.Picture;
import model.Blob;

public class BlobDetector {
    public ArrayList<Blob> findBlobs(Picture image) {
        ArrayList<Blob> blobs = new ArrayList<>();
        boolean[][] visited = new boolean[image.width()][image.height()];

        for (int x = 0; x < image.width(); x++) {
            for (int y = 0; y < image.height(); y++) {
                if (isForeground(image.get(x, y)) && !visited[x][y]) {
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

        if (x < 0 || x >= image.width() ||
            y < 0 || y >= image.height()) {
            return;
        }

        if (visited[x][y] || !isForeground(image.get(x, y))) {
            return;
        }

        visited[x][y] = true;
        blob.add(x, y);

        dfs(x + 1, y, image, visited, blob);
        dfs(x - 1, y, image, visited, blob);
        dfs(x, y + 1, image, visited, blob);
        dfs(x, y - 1, image, visited, blob);
    }

    private boolean isForeground(Color color) {
        return (color.equals(Color.WHITE));
    }
}
