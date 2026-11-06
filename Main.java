import java.util.ArrayList;
import java.util.Arrays;

import image.ImageProcessor;
import lib.Picture;
import tracking.BeadTracker;
import java.io.File;
import java.util.Comparator;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.io.FileNotFoundException;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Get directory path
        String directoryPath = args[0];
        File directory = new File(args[0]);
        File[] files = directory.listFiles();

        if (files == null) {
            System.out.println("Could not read directory: " + directoryPath);
            return;
        }

        files = Arrays.stream(files)
                .filter(f -> f.isFile() && f.getName().endsWith(".jpg"))
                .toArray(File[]::new);

        Arrays.sort(files, Comparator.comparing(File::getName));

        // Initialize pipeline
        BeadTracker bdt = new BeadTracker();
        ImageProcessor imgp = new ImageProcessor();

        // Write displacement data to file
        try (PrintWriter writer = new PrintWriter(new FileWriter("displacements.txt"))) {
            for (int i = 0; i < files.length - 1; i++) {
                Picture result1 = new Picture(files[i].getPath());
                Picture result2 = new Picture(files[i + 1].getPath());
                double tau = 180.94;

                ArrayList<Double> dists = bdt.minDistance(imgp.applyTau(result1, tau),
                        imgp.applyTau(result2, tau), 25,
                        25);

                for (double distance : dists) {
                    writer.printf("%.4f%n", distance);
                }
            }

        } catch (IOException e) {
            System.err.println("Could not write displacement data: " + e.getMessage());
        }

        // Read displacement data from file
        ArrayList<Double> numbers = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("displacements.txt"))) {
            while (scanner.hasNextDouble()) {
                numbers.add(scanner.nextDouble());
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        }

        // Find dispersion constant, Boltzmann constant, and Avogadro's number
        double dispersionConstant = physics.PhysicalCalc.calculateVariance(numbers);
        double BoltzmannConstant = physics.PhysicalCalc.findBoltzmannConstant(dispersionConstant);
        double AvogadroConstant = physics.PhysicalCalc.findAvogadro(BoltzmannConstant);
        System.out.println(BoltzmannConstant);
        System.out.println(AvogadroConstant);
    }
}
