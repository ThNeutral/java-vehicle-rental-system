package CLI;


import CLI.Helpers.VehicleTypes;
import Invoices.Base.BaseInvoice;
import Invoices.Helpers.InvoicePrintData;
import Invoices.Implementation.CarInvoice;
import Invoices.Implementation.MotorcycleInvoice;
import Invoices.Implementation.VanInvoice;
import VehiclesData.Base.BaseVehicleData;
import VehiclesData.Implementation.CarData;
import VehiclesPool.Interfaces.IPool;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class CLI {
    public static VehicleTypes getVehicleTypeFromUser() {
        System.out.println("Welcome to the Vehicle Rental System!");
        System.out.println("Please, select type of vehicle you have been renting by inputting either an index or a name:");
        System.out.println("1 - Car");
        System.out.println("2 - Motorcycle");
        System.out.println("3 - Van");

        while (true) {
            System.out.print(">");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();

            if (Objects.equals(input, "1") || Objects.equals(input.toLowerCase(), "car")) {
                return VehicleTypes.CAR;
            }

            if (Objects.equals(input, "2") || Objects.equals(input.toLowerCase(), "motorcycle")) {
                return VehicleTypes.MOTORCYCLE;
            }

            if (Objects.equals(input, "3") || Objects.equals(input.toLowerCase(), "van")) {
                return VehicleTypes.VAN;
            }

            System.out.println("Could not recognise input. Please, try again.");
        }
    }

    public static BaseVehicleData getVehicleDataFromUser(IPool pool) {
        pool.printVehiclesData();
        System.out.println("Please, select a vehicle you have been using by inputting an index");
        while (true) {
            System.out.print(">");
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            try {
                int index = Integer.parseInt(input);
                BaseVehicleData vd = pool.getVehicleData(index - 1);
                if (vd == null) {
                    System.out.println("Could not find a vehicle with that index");
                } else {
                    return vd;
                }
            } catch (Exception e) {
                System.out.println("Input should be an integer");
            }
        }
    }

    public static BaseInvoice getInvoiceDataFromUser(VehicleTypes vt, BaseVehicleData bvd) {
        BaseInvoice bi = switch (vt) {
            case CAR -> new CarInvoice();
            case MOTORCYCLE -> new MotorcycleInvoice();
            case VAN -> new VanInvoice();
        };
        Scanner sc = new Scanner(System.in);
        System.out.println("Please, provide information about your rent");
        System.out.print("Your Name >");
        bi.setCustomerName(sc.nextLine());
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        while (true) {
            System.out.print("Reservation start date (formatted as dd-mm-yyyy) >");
            String dateString = sc.nextLine();
            try {
                Date date = df.parse(dateString);
                if ((date.getTime() - (new Date()).getTime()) > 0) {
                    System.out.println("Start date cannot be in the past");
                } else {
                    bi.setReservationStartDate(date);
                    break;
                }
            } catch (Exception e) {
                System.out.println("Input is not a valid date");
            }
        }
        while (true) {
            System.out.print("Reservation end date (formatted as dd-mm-yyyy) >");
            String dateString = sc.nextLine();
            try {
                Date date = df.parse(dateString);
                bi.setReservationEndDate(date);
                break;
            } catch (Exception e) {
                System.out.println("Input is not a valid date");
            }
        }
        bi.setActualEndDate(new Date());
        bi.setRentedVehicle(bvd);
        switch (vt) {
            case CAR -> {
                CarData cvd = (CarData) bvd;
                bi.setSpecificInformation(cvd.getSafetyRating());
            }
            case MOTORCYCLE -> {
                while (true) {
                    System.out.print("Your age >");
                    String ageString = sc.nextLine();
                    try {
                        int ageInt = Integer.parseUnsignedInt(ageString);
                        bi.setSpecificInformation(ageInt);
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid input");
                    }
                }
            }
            case VAN -> {
                while (true) {
                    System.out.print("Your experience driving a van >");
                    String experienceString = sc.nextLine();
                    try {
                        int experienceInt = Integer.parseUnsignedInt(experienceString);
                        bi.setSpecificInformation(experienceInt);
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid input");
                    }
                }
            }
        }
        return bi;
    }

    public static void printInvoice(InvoicePrintData ipd) {
        System.out.println("Your invoice: \n");

        DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

        System.out.println("XXXXXXXXXX");
        System.out.println("Date: " + df.format(ipd.actualEndDate));
        System.out.println("Customer Name: " + ipd.customerName);
        System.out.println("Rented Vehicle: " + ipd.rentedVehicle.getVehicleBrand() + " " + ipd.rentedVehicle.getVehicleModel());
        System.out.println();
        System.out.println("Reservation start date: " + df.format(ipd.reservationStartDate));
        System.out.println("Reservation end date: " + df.format(ipd.reservationEndDate));
        System.out.println("Reserved rental days: " + ipd.reservationTimeDifference + " day(s)");
        System.out.println();
        System.out.println("Actual return date: " + df.format(ipd.actualEndDate));
        System.out.println("Actual rental days: " + ipd.actualTimeDifference + " day(s)");
        System.out.println();
        System.out.println("Rental cost per day: $" + String.format("%.2f", ipd.rentalCost));
        System.out.println("Base insurance cost per day: $" + String.format("%.2f", ipd.baseInsuranceCost));
        System.out.println("Insurance cost " + (ipd.insuranceCostChange > 0 ? "increase" : "decrease") + " per day: $" + String.format("%.2f",Math.abs(ipd.insuranceCostChange)));
        System.out.println("Final insurance cost per day: $" + String.format("%.2f", ipd.baseInsuranceCost + ipd.insuranceCostChange));
        System.out.println();
        System.out.println("Early return discount for rent: $" + String.format("%.2f", ipd.earlyReturnDiscountForRent));
        System.out.println("Early return discount for insurance: $" + String.format("%.2f", ipd.earlyReturnDiscountForInsurance));
        System.out.println();
        System.out.println("Total rent: $" + String.format("%.2f", ipd.totalRent));
        System.out.println("Total insurance: $" + String.format("%.2f", ipd.totalInsurance));
        System.out.println("Total: $" + String.format("%.2f", ipd.totalInsurance + ipd.totalRent));
        System.out.println("XXXXXXXXXX");
    }
}
