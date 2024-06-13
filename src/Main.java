import CLI.CLI;
import CLI.Helpers.VehicleTypes;
import Invoices.BaseInvoice;
import VehiclesData.BaseVehicleData;
import VehiclesPool.CarsPool;
import VehiclesPool.IPool;
import VehiclesPool.MotorcyclesPool;
import VehiclesPool.VansPool;

public class Main {
    public static void main(String[] args) {
        VehicleTypes vt = CLI.getVehicleTypeFromUser();
        IPool pool = switch (vt) {
            case CAR -> new CarsPool();
            case MOTORCYCLE -> new MotorcyclesPool();
            case VAN -> new VansPool();
        };
        BaseVehicleData bvd = CLI.getVehicleDataFromUser(pool);
        BaseInvoice bi = CLI.getInvoiceDataFromUser(vt, bvd);
        CLI.printInvoice(bi);
    }
}