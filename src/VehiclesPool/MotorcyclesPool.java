package VehiclesPool;

import VehiclesData.MotorcycleData;

public class MotorcyclesPool implements IPool {
    private MotorcycleData[] motorcyclesData;
    public MotorcyclesPool() {
        MotorcycleData md1 = new MotorcycleData();
        md1.setVehicleBrand("Brand1");
        md1.setVehicleModel("Motorcycle1");
        md1.setVehiclePrice(15000);
        MotorcycleData md2 = new MotorcycleData();
        md2.setVehicleBrand("Brand2");
        md2.setVehicleModel("Motorcycle2");
        md2.setVehiclePrice(8000);
        MotorcycleData md3 = new MotorcycleData();
        md3.setVehicleBrand("Brand3");
        md3.setVehicleModel("Motorcycle3");
        md3.setVehiclePrice(5000);
        motorcyclesData = new MotorcycleData[]{md1, md2, md3};
    }

    @Override
    public void printVehiclesData() {
        System.out.println("Available motorcycles:");
        for (int i = 0; i < motorcyclesData.length; i++) {
            MotorcycleData cd = motorcyclesData[i];
            System.out.println((i + 1) + " - " + cd.getVehicleBrand() + " " + cd.getVehicleModel());
            System.out.println("\tMotorcycle Value: " + cd.getVehiclePrice());
        }
    }

    @Override
    public MotorcycleData getVehicleData(int index) {
        try {
            return motorcyclesData[index];
        } catch (Exception e) {
            return null;
        }
    }
}
