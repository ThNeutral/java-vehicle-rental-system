package Invoices;

import VehiclesData.BaseVehicleData;

import java.util.Date;

public class BaseInvoice implements ISpecific{
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
    }

    public void printInvoice() {

    }
}
