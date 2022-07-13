package helpers;

public enum Location {
    MOSCOW(55.755826, 37.6173),
    YEREVAN(40.1811, 44.5136),
    LONDON(51.509865, -0.118092);

    public final double latitude;
    public final double longitude;

    Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
