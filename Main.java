import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import image.ImageProcessor;
import io.DisplacementIO;
import lib.Picture;
import physics.PhysicalCalc;
import tracking.BeadTracker;

public class Main {
    private static final double TAU = 180.94;
    private static final int MIN_PIXELS = 25;
    private static final int MIN_BLOB_MASS = 25;

    private static final String OUTPUT_FILE = "displacements.txt";

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Please provide an image directory.");
            return;
        }

        File directory = new File(args[0]);

        if (!directory.isDirectory()) {
            System.err.println("Invalid directory: " + args[0]);
            return;
        }

        // Initialize components
        ImageProcessor imageProcessor = new ImageProcessor();
        BeadTracker beadTracker = new BeadTracker();

        // Retrieve images
        List<File> images = getImages(directory);

        if (images.size() < 2) {
            System.err.println("At least two images are required.");
            return;
        }

        // Calculate displacements
        List<Double> displacements =
                calculateDisplacements(images, imageProcessor, beadTracker);

        if (displacements.isEmpty()) {
            System.err.println("No displacement measurements found.");
            return;
        }

        // Save measurements
        DisplacementIO.write(displacements, OUTPUT_FILE);

        // Calculate physical constants
        double diffusion =
                PhysicalCalc.calculateDiffusionCoefficient(displacements);

        double boltzmann =
                PhysicalCalc.findBoltzmannConstant(diffusion);

        double avogadro =
                PhysicalCalc.findAvogadro(boltzmann);

        // Display results
        System.out.println("Diffusion coefficient: " + diffusion);
        System.out.println("Boltzmann constant: " + boltzmann);
        System.out.println("Avogadro constant: " + avogadro);
    }

    private static List<File> getImages(File directory) {

        File[] files = directory.listFiles(
                file -> file.isFile() &&
                        file.getName().toLowerCase().endsWith(".jpg")
        );

        if (files == null) {
            return List.of();
        }

        Arrays.sort(files, Comparator.comparing(File::getName));

        return Arrays.asList(files);
    }

    private static List<Double> calculateDisplacements(
            List<File> images,
            ImageProcessor imageProcessor,
            BeadTracker beadTracker) {

        List<Double> displacements = new ArrayList<>();

        for (int i = 0; i < images.size() - 1; i++) {

            Picture image1 = new Picture(images.get(i).getPath());
            Picture image2 = new Picture(images.get(i + 1).getPath());

            Picture processed1 =
                    imageProcessor.applyTau(image1, TAU);

            Picture processed2 =
                    imageProcessor.applyTau(image2, TAU);

            displacements.addAll(
                    beadTracker.minDistance(
                            processed1,
                            processed2,
                            MIN_PIXELS,
                            MIN_BLOB_MASS
                    )
            );
        }

        return displacements;
    }
}