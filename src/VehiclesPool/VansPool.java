package VehiclesPool;

import VehiclesData.VanData;

public class VansPool implements IPool{
    private VanData[] vansData;
    public VansPool() {
        VanData vd1 = new VanData();
        vd1.setVehicleBrand("Brand1");
        vd1.setVehicleModel("Van1");
        vd1.setVehiclePrice(15000);
        VanData vd2 = new VanData();
        vd2.setVehicleBrand("Brand2");
        vd2.setVehicleModel("Van2");
        vd2.setVehiclePrice(8000);
        VanData vd3 = new VanData();
        vd3.setVehicleBrand("Brand3");
        vd3.setVehicleModel("Van3");
        vd3.setVehiclePrice(5000);
        vansData = new VanData[]{vd1, vd2, vd3};
    }

    @Override
    public void printVehiclesData() {
        System.out.println("Available motorcycles:");
        for (int i = 0; i < vansData.length; i++) {
            VanData cd = vansData[i];
            System.out.println((i + 1) + " - " + cd.getVehicleBrand() + " " + cd.getVehicleModel());
            System.out.println("\tMotorcycle Value: " + cd.getVehiclePrice());
        }
    }

    @Override
    public VanData getVehicleData(int index) {
        try {
            return vansData[index];
        } catch (Exception e) {
            return null;
        }
    }
}
