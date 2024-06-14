package Invoices.Implementation;

import Invoices.Base.BaseInvoice;
import Invoices.Helpers.InvoicePrintData;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MotorcycleInvoice extends BaseInvoice {
    protected int dailyRentalCost = 15;
    protected int dailyRentalCostForLongPeriod = 10;
    protected float insurancePercentagePerDay = 0.0002f;
    protected float insuranceCostChangePercentage = 0.2f;

    protected int ridersAge;

    @Override
    public void setSpecificInformation(int specificInformation) {
        ridersAge = specificInformation;
    }

    @Override
    public InvoicePrintData getInvoicePrintData() {
        InvoicePrintData ipd = new InvoicePrintData();
        ipd.actualEndDate = actualEndDate;
        ipd.reservationEndDate = reservationEndDate;
        ipd.reservationStartDate = reservationStartDate;
        ipd.customerName = customerName;
        ipd.rentedVehicle = rentedVehicle;

        ipd.reservationTimeDifference = (int)((reservationEndDate.getTime() - reservationStartDate.getTime()) / (1000 * 60 * 60 * 24));
        ipd.actualTimeDifference = (int)((actualEndDate.getTime() - reservationStartDate.getTime()) / (1000 * 60 * 60 * 24));

        ipd.baseInsuranceCost = rentedVehicle.getVehiclePrice() * insurancePercentagePerDay;
        ipd.insuranceCostChange = 0;
        if (ridersAge < 25) {
            ipd.insuranceCostChange = ipd.baseInsuranceCost * insuranceCostChangePercentage;
        }

        ipd.earlyReturnDiscountForRent = 0f;
        ipd.earlyReturnDiscountForInsurance = 0f;
        ipd.rentalCost = dailyRentalCost;
        if (ipd.reservationTimeDifference > daysForReducedCost) {
            ipd.rentalCost = dailyRentalCostForLongPeriod;
        }

        if (ipd.reservationTimeDifference == ipd.actualTimeDifference) {
            ipd.totalRent = ipd.rentalCost * ipd.reservationTimeDifference;
            ipd.totalInsurance = (ipd.baseInsuranceCost + ipd.insuranceCostChange) * ipd.reservationTimeDifference;
        } else {
            int timeDifferenceBetweenActualAndReservationTime = ipd.reservationTimeDifference - ipd.actualTimeDifference;
            ipd.earlyReturnDiscountForRent = ipd.rentalCost * timeDifferenceBetweenActualAndReservationTime / 2;
            ipd.earlyReturnDiscountForInsurance = (ipd.baseInsuranceCost + ipd.insuranceCostChange) * timeDifferenceBetweenActualAndReservationTime;

            ipd.totalRent = ipd.rentalCost * ipd.reservationTimeDifference - ipd.earlyReturnDiscountForRent;
            ipd.totalInsurance = (ipd.baseInsuranceCost + ipd.insuranceCostChange) * ipd.reservationTimeDifference - ipd.earlyReturnDiscountForInsurance;
        }

        return ipd;
    }
}
