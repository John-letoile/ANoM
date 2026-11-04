package physics;

import java.util.List;

public class PhysicalCalc {
    public static double calculateVariance(List<Double> data) {
        double sumSquared = 0.0;

        for (double datum : data) {
            datum = datum * Constants.pixelToMeterRatio;
            sumSquared += (datum * datum);
        }

        return sumSquared / (2 * data.size());
    }

    public static double findBoltzmannConstant(double DispersionConstant) {
        return (6 * Math.PI * Constants.viscosity * Constants.beadRadius * DispersionConstant)
                / Constants.roomTemperature;
    }

    public static double findAvogadro(double BoltzmannConstant) {
        return Constants.universalGasConstant / BoltzmannConstant;
    }
}
