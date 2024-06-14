import CLI.CLI;
import CLI.Helpers.VehicleTypes;
import Invoices.Base.BaseInvoice;
import Invoices.Helpers.InvoicePrintData;
import VehiclesData.Base.BaseVehicleData;
import VehiclesPool.Implemetation.CarsPool;
import VehiclesPool.Interfaces.IPool;
import VehiclesPool.Implemetation.MotorcyclesPool;
import VehiclesPool.Implemetation.VansPool;

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
        InvoicePrintData ipd = bi.getInvoicePrintData();
        CLI.printInvoice(ipd);
    }
}