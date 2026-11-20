package interfaces;

import model.Blob;
import image.ImageProcessor;
import detection.BlobDetector;
import detection.BeadFinder;
import tracking.BeadTracker;

public interface IFactory {
    public static IBlob createBlob(){
        return new Blob();
    }

    public static IImageProcessor createImageProcessor(){
        return new ImageProcessor();
    }

    public static IBlobDetector createBlobDetector(){
        return new BlobDetector();
    }

    public static BeadFinder createBeadFinder(){
        return new BeadFinder();
    }

    public static IBeadTracker createBeadTracker(){
        IBlobDetector blobDetector = createBlobDetector();
        BeadFinder beadFinder = createBeadFinder();

        return new BeadTracker(beadFinder, blobDetector);
    }
}
