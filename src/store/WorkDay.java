package store;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorkDay {

    private static LocalTime TIME_OPENING = LocalTime.of(8, 00);
    private static LocalTime TIME_CLOSING = LocalTime.of(21,00);
    private static LocalTime CURRENT_TIME = LocalTime.of(8,00);
    private static LocalTime OPERATING_TIME = TIME_OPENING;

    List<AcquisitionUnit> listOfAllDrinks = new ArrayList<>();
    List<AcquisitionUnit> listOfOrderedDrinks = new ArrayList<>();

    public WorkDay() {
        CurrentStock.readFromFile(listOfAllDrinks);

        while(CURRENT_TIME.isBefore(TIME_CLOSING)){
            generateActionsForAnHour();
        }



        checkIfTheRefillOfTheStockIsNeeded();
    }

    private void checkIfTheRefillOfTheStockIsNeeded() {
        //TODO:
        //Create a functionality for refilling stores stock
    }

    private void generateOrderForTheNextHour() {
        int number_of_acquisition_a_u = new Random().nextInt(11);

        if(number_of_acquisition_a_u != 0){
            for (int i = 0; i < number_of_acquisition_a_u; i++) {

                int chosen_drinks = new Random().nextInt(8);

                if(listOfAllDrinks.get(chosen_drinks).getStock() != 0) {
                    listOfOrderedDrinks.add(listOfAllDrinks.get(chosen_drinks));
                }else {
                    i--;
                    if(listOfAllDrinks.get(0).getStock() == 0 &&
                       listOfAllDrinks.get(0).getStock() == listOfAllDrinks.get(1).getStock() &&
                       listOfAllDrinks.get(0).getStock() == listOfAllDrinks.get(2).getStock() &&
                       listOfAllDrinks.get(0).getStock() == listOfAllDrinks.get(3).getStock() &&
                       listOfAllDrinks.get(0).getStock() == listOfAllDrinks.get(4).getStock() &&
                       listOfAllDrinks.get(0).getStock() == listOfAllDrinks.get(5).getStock() &&
                       listOfAllDrinks.get(0).getStock() == listOfAllDrinks.get(6).getStock() &&
                       listOfAllDrinks.get(0).getStock() == listOfAllDrinks.get(7).getStock()){
                        break;
                    }
                }
            }
        }
    }

    private void executeOrder(LocalTime time){
        System.out.println("Time of the buy: " + time.toString());
        //TODO:
        //Implement the mechanics of the extra charge

        //TODO:
        //Check what must be written into app's console

        //TODO:
        //Implement end-of-the-day mechanics

        //TODO:
        //Implement end-of-the-month mechanics

        //TODO:
        //Implement the rewriting of the stock file

        if(listOfOrderedDrinks.isEmpty()){
            System.out.println("Customer chose nothing<-------------------------------------------------------------");
        }else {
            for(AcquisitionUnit unit : listOfOrderedDrinks){
                System.out.printf("%-20s", unit.getName());
                System.out.print(" *** ");
                System.out.printf("%-5s", Float.toString(unit.getPurchase_price()));
                System.out.print("\n");

                reduceStock(unit);
            }
            listOfOrderedDrinks.clear();
        }
        System.out.println("------------------------------------------------");
    }

    private void reduceStock(AcquisitionUnit unit) {
        for (AcquisitionUnit unit_from_main_list : listOfAllDrinks){
            if(unit.equals(unit_from_main_list)){
                unit_from_main_list.setStock(unit_from_main_list.getStock() - 1);
                break;
            }
        }
    }

    private void generateActionsForAnHour(){
        int number_of_customers_in_next_hour = new Random().nextInt(10) + 1;

        for(int i = 0; i < number_of_customers_in_next_hour; i++){
            //TODO:
            //Fix the minutes
            int interval = 60 / number_of_customers_in_next_hour;
            OPERATING_TIME = OPERATING_TIME.plusMinutes(i + new Random().nextInt(interval));

            generateOrderForTheNextHour();
            executeOrder(OPERATING_TIME);
        }

        CURRENT_TIME = CURRENT_TIME.plusHours(1);
        OPERATING_TIME = CURRENT_TIME;
    }


    public void printStuff(){
        for(AcquisitionUnit unit : listOfAllDrinks){
            System.out.println(unit.toString());
        }
    }
}
