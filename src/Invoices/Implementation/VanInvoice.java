package Invoices.Implementation;

import Invoices.Base.BaseInvoice;
import Invoices.Helpers.InvoicePrintData;

public class VanInvoice extends BaseInvoice {
    protected int dailyRentalCost = 50;
    protected int dailyRentalCostForLongPeriod = 40;
    protected float insurancePercentagePerDay = 0.0003f;
    protected float insuranceCostChangePercentage = -0.15f;

    protected int driversExperience;
    @Override
    public void setSpecificInformation(int specificInformation) {
        driversExperience = specificInformation;
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
        if (driversExperience > 5) {
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
