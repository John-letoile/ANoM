import java.util.ArrayList;

import image.ImageProcessor;
import lib.Picture;
import model.Blob;
import detection.BlobDetector;
import detection.BeadFinder;
import tracking.BeadTracker;
import java.io.File;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        String directoryPath = "data/run_1";
        ImageProcessor imgp = new ImageProcessor();

        File directory = new File(directoryPath);
        File[] files = directory.listFiles();

        if (files == null) {
            System.out.println("Could not read directory.");
            return;
        }

        BeadTracker bdt = new BeadTracker();

        for (int i = 0; i < files.length - 1; i++) {

            if (!files[i].isFile() || !files[i + 1].isFile()) {
                continue;
            }

            Picture result1 = imgp.applyTau(new Picture(files[i].getPath()), 180);
            Picture result2 = imgp.applyTau(new Picture(files[i + 1].getPath()), 180);

            ArrayList<Double> dists = bdt.minDistance(result1, result2, 30, 25);
            System.out.println(dists.size());

            System.out.println(
                    files[i].getName() + " -> " + files[i + 1].getName());

            for (double distance : dists) {
                System.out.printf("%.4f%n", distance);
            }

            System.out.println("--------------------");
        }
    }
}
