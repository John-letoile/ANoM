package detection;

import interfaces.IBlob;
import interfaces.IBlobDetector;
import interfaces.IFactory;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import lib.Picture;

public class BlobDetector implements IBlobDetector{
    public List<IBlob> findBlobs(Picture image){
        ArrayList<IBlob> blobs = new ArrayList<>();
        boolean[][] visited = new boolean[image.width()][image.height()];

        for (int x = 0; x < image.width(); x++) {
            for (int y = 0; y < image.height(); y++) {
                if (isForeground(image.get(x, y)) && !visited[x][y]) {
                    IBlob blob = IFactory.createBlob();
                    dfs(x, y, image, visited, blob);
                    blobs.add(blob);
                }
            }
        }

        return blobs;
    }

    private void dfs(int x, int y, Picture image,
            boolean[][] visited, IBlob blob) {

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
