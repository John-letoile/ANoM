package physics;

import java.util.List;

public class PhysicalCalc {
    public static double calculateDiffusionCoefficient(List<Double> data) {
        double sumSquared = 0.0;

        for (double datum : data) {
            datum = datum * Constants.PIXEL_TO_METER_RATIO;
            sumSquared += (datum * datum);
        }

        return sumSquared / (2 * data.size());
    }

    public static double findBoltzmannConstant(double DispersionConstant) {
        return (6 * Math.PI * Constants.VISCOSITY * Constants.BEAD_RADIUS * DispersionConstant)
                / Constants.ROOM_TEMPERATURE;
    }

    public static double findAvogadro(double BoltzmannConstant) {
        return Constants.UNIVERSAL_GAS_CONSTANT / BoltzmannConstant;
    }
}
