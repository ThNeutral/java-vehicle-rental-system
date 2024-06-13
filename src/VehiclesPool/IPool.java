package VehiclesPool;

import VehiclesData.BaseVehicleData;

public interface IPool {
    public BaseVehicleData getVehicleData(int index);
    public void printVehiclesData();
}
