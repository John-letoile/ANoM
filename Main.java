import java.util.ArrayList;
import java.util.Arrays;

import image.ImageProcessor;
import lib.Picture;
import model.Blob;
import detection.BlobDetector;
import detection.BeadFinder;
import tracking.BeadTracker;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import java.io.FileNotFoundException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // String directoryPath = "data/run_1";
        //
        // File directory = new File(directoryPath);
        // File[] files = directory.listFiles();
        //
        // if (files == null) {
        // System.out.println("Could not read directory: " + directoryPath);
        // return;
        // }
        //
        // files = Arrays.stream(files)
        // .filter(f -> f.isFile() && f.getName().endsWith(".jpg"))
        // .toArray(File[]::new);
        //
        // Arrays.sort(files, Comparator.comparing(File::getName));
        //
        // BeadTracker bdt = new BeadTracker();
        // ImageProcessor imgp = new ImageProcessor();
        //
        // try (PrintWriter writer = new PrintWriter(new
        // FileWriter("displacements.txt"))) {
        //
        // for (int i = 0; i < files.length - 1; i++) {
        // Picture result1 = new Picture(files[i].getPath());
        // Picture result2 = new Picture(files[i + 1].getPath());
        //
        // ArrayList<Double> dists = bdt.minDistance(imgp.applyTau(result1, 180),
        // imgp.applyTau(result2, 180), 30,
        // 25);
        //
        // for (double distance : dists) {
        // writer.printf("%.4f%n", distance);
        // }
        // }
        //
        // } catch (IOException e) {
        // System.err.println("Could not write displacement data: " + e.getMessage());
        // }

        ArrayList<Double> numbers = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File("displacements.txt"))) {
            while (scanner.hasNextDouble()) {
                numbers.add(scanner.nextDouble());
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found.");
        }

        double dispersionConstant = physics.PhysicalCalc.calculateVariance(numbers);
        double BoltzmannConstant = physics.PhysicalCalc.findBoltzmannConstant(dispersionConstant);
        double AvogadroConstant = physics.PhysicalCalc.findAvogadro(BoltzmannConstant);
        System.out.println(BoltzmannConstant);
        System.out.println(AvogadroConstant);
    }
}
