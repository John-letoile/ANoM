import java.util.ArrayList;

import image.*;
import lib.Picture;
import detection.*;
import model.Blob;

public class Main {
    private final ImageLoader loader;
    private final Thresholder thresholder;
    private final BlobDetector detector;
    private final BeadFilter beadFilter;

    public Main(ImageLoader loader, Thresholder thresholder,
                BlobDetector detector, BeadFilter beadFilter) {
        this.loader = loader;
        this.thresholder = thresholder;
        this.detector = detector;
        this.beadFilter = beadFilter;
    }

    public static void main(String[] args) {
        ImageLoader loader = new FilesystemImageLoader();
        Thresholder thresholder = new GrayscaleBinaryThresholder();
        BlobDetector detector = new BlobFinder();
        BeadFilter beadFilter = new SizeBasedBeadFilter(25);

        Main pipeline = new Main(loader, thresholder, detector, beadFilter);
        pipeline.run("frame00001.jpg");
    }

    public void run(String filename) {
        Picture image = loader.load(filename);
        Picture binary = thresholder.apply(image, 180);

        ArrayList<Blob> blobs = (ArrayList<Blob>) detector.detect(binary);

        ArrayList<Blob> beads = (ArrayList<Blob>) beadFilter.apply(blobs);
        for (Blob b : beads) {
            System.out.println(b);
        }
    }
}

