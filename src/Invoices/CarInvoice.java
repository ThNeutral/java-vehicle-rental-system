package Invoices;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class CarInvoice extends BaseInvoice {
    protected float dailyRentalCost = 20f;
    protected float dailyRentalCostForLongPeriod = 15f;
    protected float insurancePercentagePerDay = 0.0001f;
    protected float insuranceCostReductionPercentage = 0.1f;
    protected int carSafetyRating;

    @Override
    public void setSpecificInformation(int specificInformation) {
        carSafetyRating = specificInformation;
    }

    @Override
    public void printInvoice() {
        int reservationTimeDifference = (int)((reservationEndDate.getTime() - reservationStartDate.getTime()) / (1000 * 60 * 60 * 24));
        int actualTimeDifference = (int)((actualEndDate.getTime() - reservationStartDate.getTime()) / (1000 * 60 * 60 * 24));

        float baseInsuranceCost = rentedVehicle.getVehiclePrice() * insurancePercentagePerDay;
        float insuranceCostReduction = 0;
        if (carSafetyRating > 3) {
            insuranceCostReduction = baseInsuranceCost * insuranceCostReductionPercentage;
        }

        float totalRent;
        float earlyReturnDiscountForRent = 0f;
        float totalInsurance;
        float earlyReturnDiscountForInsurance = 0f;
        float rentalCost = dailyRentalCost;
        if (reservationTimeDifference > daysForReducedCost) {
            rentalCost = dailyRentalCostForLongPeriod;
        }

        if (reservationTimeDifference == actualTimeDifference) {
            totalRent = rentalCost * reservationTimeDifference;
            totalInsurance = (baseInsuranceCost - insuranceCostReduction) * reservationTimeDifference;
        } else {
            int timeDifferenceBetweenActualAndReservationTime = reservationTimeDifference - actualTimeDifference;
            earlyReturnDiscountForRent = rentalCost * timeDifferenceBetweenActualAndReservationTime / 2;
            earlyReturnDiscountForInsurance = (baseInsuranceCost - insuranceCostReduction) * timeDifferenceBetweenActualAndReservationTime;

            totalRent = rentalCost * reservationTimeDifference - earlyReturnDiscountForRent;
            totalInsurance = (baseInsuranceCost - insuranceCostReduction) * reservationTimeDifference - earlyReturnDiscountForInsurance;
        }

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        System.out.println("XXXXXXXXXX");
        System.out.println("Date: " + df.format(actualEndDate));
        System.out.println("Customer Name: " + customerName);
        System.out.println("Rented Vehicle: " + rentedVehicle.getVehicleBrand() + " " + rentedVehicle.getVehicleModel());
        System.out.println();
        System.out.println("Reservation start date: " + df.format(reservationStartDate));
        System.out.println("Reservation end date: " + df.format(reservationEndDate));
        System.out.println("Reserved rental days: " + reservationTimeDifference + " days");
        System.out.println();
        System.out.println("Actual return date: " + df.format(actualEndDate));
        System.out.println("Actual rental days: " + actualTimeDifference + " days");
        System.out.println();
        System.out.println("Rental cost per day: $" + String.format("%.2f", rentalCost));
        System.out.println("Base insurance cost per day: $" + String.format("%.2f", baseInsuranceCost));
        System.out.println("Insurance cost reduction per day: $" + String.format("%.2f", insuranceCostReduction));
        System.out.println("Insurance cost per day: $" + String.format("%.2f", baseInsuranceCost - insuranceCostReduction));
        System.out.println();
        System.out.println("Early return discount for rent: $" + String.format("%.2f", earlyReturnDiscountForRent));
        System.out.println("Early return discount for insurance: $" + String.format("%.2f", earlyReturnDiscountForInsurance));
        System.out.println();
        System.out.println("Total rent: $" + String.format("%.2f", totalRent));
        System.out.println("Total insurance: $" + String.format("%.2f", totalInsurance));
        System.out.println("Total: $" + String.format("%.2f", totalInsurance + totalRent));
        System.out.println("XXXXXXXXXX");
    }
}
