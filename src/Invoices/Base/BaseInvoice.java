package Invoices.Base;

import Invoices.Helpers.InvoicePrintData;
import Invoices.Interfaces.ISpecific;
import VehiclesData.Base.BaseVehicleData;
import jdk.jshell.spi.ExecutionControl;

import java.util.Date;

public class BaseInvoice implements ISpecific {
    protected String customerName;
    protected BaseVehicleData rentedVehicle;
    protected Date reservationStartDate;
    protected Date reservationEndDate;
    protected Date actualEndDate;

    protected int daysForReducedCost = 7;

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setRentedVehicle(BaseVehicleData rentedVehicle) {
        this.rentedVehicle = rentedVehicle;
    }

    public void setReservationStartDate(Date reservationStartDate) {
        this.reservationStartDate = reservationStartDate;
    }

    public void setReservationEndDate(Date reservationEndDate) {
        this.reservationEndDate = reservationEndDate;
    }

    public void setActualEndDate(Date actualEndDate) {
        this.actualEndDate = actualEndDate;
    }

    @Override
    public void setSpecificInformation(int specificInformation) {
        throw new UnsupportedOperationException();
    }

    public InvoicePrintData getInvoicePrintData() {
        throw new UnsupportedOperationException();
    }
}
