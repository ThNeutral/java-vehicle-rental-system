package VehiclesPool.Interfaces;

import VehiclesData.Base.BaseVehicleData;

public interface IPool {
    public BaseVehicleData getVehicleData(int index);
    public void printVehiclesData();
}
