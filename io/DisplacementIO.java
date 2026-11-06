package io;

import java.util.List;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;
import java.io.File;

public class DisplacementIO {
    public static void write(List<Double> data, String filename) {
        try (PrintWriter writer = new PrintWriter(filename)) {
            for (double distance : data) {
                writer.printf("%.4f%n", distance);
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not write displacement data", e);
        }
    }

    public static List<Double> read(String filename) {
        List<Double> numbers = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextDouble()) {
                numbers.add(scanner.nextDouble());
            }
        } catch (IOException e) {
            throw new RuntimeException("Could not read displacement data", e);
        }

        return numbers;
    }
}
