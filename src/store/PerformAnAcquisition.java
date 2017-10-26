package store;

import java.time.LocalTime;

public class PerformAnAcquisition {

    private int extraCharge;
    private LocalTime acquisitionTime;

    public PerformAnAcquisition(int extraCharge, LocalTime acquisitionTime) {
        this.extraCharge = extraCharge;
        this.acquisitionTime = acquisitionTime;
    }
}
