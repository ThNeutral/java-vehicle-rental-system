package Invoices.Implementation;

import Invoices.Base.BaseInvoice;
import Invoices.Helpers.InvoicePrintData;

public class CarInvoice extends BaseInvoice {
    protected float dailyRentalCost = 20f;
    protected float dailyRentalCostForLongPeriod = 15f;
    protected float insurancePercentagePerDay = 0.0001f;
    protected float insuranceCostChangePercentage = -0.1f;
    protected int carSafetyRating;

    @Override
    public void setSpecificInformation(int specificInformation) {
        carSafetyRating = specificInformation;
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
        if (carSafetyRating > 3) {
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
