package VehiclesData;

public class CarData extends BaseVehicleData {
    protected int safetyRating;

    public void setSafetyRating(int safetyRating) {
        this.safetyRating = safetyRating;
    }

    public int getSafetyRating() {
        return safetyRating;
    }
}
