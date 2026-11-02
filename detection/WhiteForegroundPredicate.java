package detection;

import java.awt.Color;

public class WhiteForegroundPredicate implements ForegroundPredicate {
    public boolean isForeground(Color color) {
        return color.equals(Color.WHITE);
    }
}
