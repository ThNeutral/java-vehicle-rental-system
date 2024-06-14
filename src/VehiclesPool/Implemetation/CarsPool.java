package VehiclesPool.Implemetation;

import VehiclesData.Implementation.CarData;
import VehiclesPool.Interfaces.IPool;

public class CarsPool implements IPool {
    private CarData[] carsData;
    public CarsPool() {
        CarData cd1 = new CarData();
        cd1.setVehicleBrand("Brand1");
        cd1.setVehicleModel("Car1");
        cd1.setVehiclePrice(15000);
        cd1.setSafetyRating(5);
        CarData cd2 = new CarData();
        cd2.setVehicleBrand("Brand2");
        cd2.setVehicleModel("Car2");
        cd2.setVehiclePrice(8000);
        cd2.setSafetyRating(4);
        CarData cd3 = new CarData();
        cd3.setVehicleBrand("Brand3");
        cd3.setVehicleModel("Car3");
        cd3.setVehiclePrice(5000);
        cd3.setSafetyRating(3);
        carsData = new CarData[]{cd1, cd2, cd3};
    }

    @Override
    public void printVehiclesData() {
        System.out.println("Available cars:");
        for (int i = 0; i < carsData.length; i++) {
            CarData cd = carsData[i];
            System.out.println((i + 1) + " - " + cd.getVehicleBrand() + " " + cd.getVehicleModel());
            System.out.println("\tCar Value: " + cd.getVehiclePrice());
            System.out.println("\tCar Safety Rating: " + cd.getSafetyRating());
        }
    }

    @Override
    public CarData getVehicleData(int index) {
        try {
            return carsData[index];
        } catch (Exception e) {
            return null;
        }
    }
}
