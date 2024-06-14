## Vehicle Rental System
This project is an implementation of rental system for vehicle in Java language. 

## How to use
To use a system, simply run application and you will be welcomed with message to choose what type of vehicle you have rented. 
After this, app will ask you for to choose the type of vehicle you have rented and to provide information about your rent. 
After collecting all information, the system will calculate price for your rent and show an invoice for your rent.

## Implementation details
Project uses static class ```CLI``` for any communication with terminal, such as collection input information or showing invoice.
When user runs application, they will see available type of vehicles and will be asked to choose whichever they have used. This is handled by ```CLI.getVehicleTypeFromUser()``` method which returns enum of possible vehicles types.
Next, based on selection. user will see pool of available vehicles, shown by ```CLI.getVehicleDataFromUser()``` method, based on relevant implementation of ```IPool``` interface, for example, ```CarsPool``` for cars.
After selecting vehicle, user will be asked to provide relevant information by ```CLI.getInvoiceDataFromUser()```.
After collecting all information, application will create a relevant child of ```BaseInvoice``` class and will call ```baseInvoice.getInvoicePrintData()``` to calculate relevant prices and to get an instance of ```InvoicePrintData```, which will be passed to ```CLI.printInvoice()``` method, which will print an invoice.