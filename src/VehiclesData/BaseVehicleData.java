package VehiclesData;

public abstract class BaseVehicleData {
    protected String vehicleBrand;
    protected String vehicleModel;
    protected int vehiclePrice;

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehiclePrice(int vehiclePrice) {
        this.vehiclePrice = vehiclePrice;
    }

    public int getVehiclePrice() {
        return vehiclePrice;
    }
}
