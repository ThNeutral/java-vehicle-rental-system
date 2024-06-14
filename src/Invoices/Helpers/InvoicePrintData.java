package Invoices.Helpers;

import VehiclesData.Base.BaseVehicleData;

import java.util.Date;

public class InvoicePrintData {
        public Date actualEndDate;
        public String customerName;
        public BaseVehicleData rentedVehicle;
        public Date reservationStartDate;
        public Date reservationEndDate;
        public int reservationTimeDifference;
        public int actualTimeDifference;
        public float rentalCost;
        public float baseInsuranceCost;
        public float insuranceCostChange;
        public float earlyReturnDiscountForRent;
        public float earlyReturnDiscountForInsurance;
        public float totalRent;
        public float totalInsurance;
}
