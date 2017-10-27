package store;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorkDay {

    private static LocalTime TIME_OPENING = LocalTime.of(8, 00);
    private static LocalTime TIME_CLOSING = LocalTime.of(21,00);
    private static LocalTime TIME_DISCOUNT_START = LocalTime.of(18, 00);
    private static LocalTime TIME_DISCOUNT_END = LocalTime.of(20,00);
    private static LocalTime CURRENT_TIME = LocalTime.of(8,00);

    private boolean isWeekend = false;

    private float profitOfTheDay = 0;
    private float cleanProfitOfTheDay = 0;

    List<AcquisitionUnit> listOfAllDrinks = new ArrayList<>();
    List<AcquisitionUnit> listOfOrderedDrinks = new ArrayList<>();

    public WorkDay() {
        CurrentStock.readFromFile(listOfAllDrinks);

        for(int i = 0; i < 13; i++){
            generateActionsForAnHour();
        }

        endOfTheDayShenanigans();
        checkIfTheRefillOfTheStockIsNeeded();
    }

    private void endOfTheDayShenanigans() {
        System.out.println("Profit at the end of the day is: " + Float.toString(profitOfTheDay));
        System.out.println("Clean profit at the end of the day is: " + Float.toString(cleanProfitOfTheDay));
    }

    private void checkIfTheRefillOfTheStockIsNeeded() {

        for(AcquisitionUnit unit : listOfAllDrinks){
            if(unit.getStock() < 10){
                unit.setStock(unit.getStock() + 150);
                cleanProfitOfTheDay = cleanProfitOfTheDay - (unit.getPurchase_price()*150);
                System.out.println("Bought 150 unit of: " + unit.getName().toString());
            }
        }
        System.out.println("Clean profit of the day after restock: " + Float.toString(cleanProfitOfTheDay));
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

    private void executeOrder(){
        //TODO: Check what must be written into app's console

        //TODO: Implement end-of-the-day mechanics

        //TODO: Implement end-of-the-month mechanics

        //TODO: Implement the rewriting of the stock file

        if(listOfOrderedDrinks.isEmpty()){
            System.out.println("Customer chose nothing<-------------------------------------------------------------------------------------------------------------");
        }else {
            int counter = 1; //Amount of drinks in the list of a customer

            for(AcquisitionUnit unit : listOfOrderedDrinks){
                System.out.printf("%-20s", unit.getName());
                System.out.print(" *** ");
                System.out.printf("%-5s", Float.toString(unit.getPurchase_price()));
                System.out.print(" *** ");

                extraChargeCalculations(unit, counter);

                System.out.print("\n");

                profitOfTheDay += unit.getPurchase_price();
                reduceStock(unit);
                counter++;
            }
            listOfOrderedDrinks.clear();
            counter = 1;
        }
    }

    private void extraChargeCalculations(AcquisitionUnit unit, int counter){
        if(counter > 2){
            cleanProfitOfTheDay += unit.getPurchase_price()*0.07;
            System.out.print(" 7%  *** ");
            System.out.printf("%7.6s", Float.toString(unit.getPurchase_priceWithExtraCharge(0.07f)));
            System.out.print(" *** Rule of extra charge: More the 2 items on the shopping list");
        }else{
            if(CURRENT_TIME.plusMinutes(1).isAfter(TIME_DISCOUNT_START) && CURRENT_TIME.plusMinutes(1).isBefore(TIME_DISCOUNT_END)){
                cleanProfitOfTheDay += unit.getPurchase_price()*0.08;
                System.out.print(" 8%  *** ");
                System.out.printf("%7.6s",Float.toString(unit.getPurchase_priceWithExtraCharge(0.08f)));
                System.out.print(" *** Rule of extra charge: Discount hours (from 18:00 till 20:00)");
            }else{ if(isWeekend){
                cleanProfitOfTheDay += unit.getPurchase_price()*0.15;
                System.out.print(" 15% *** ");
                System.out.printf("%7.6s",Float.toString(unit.getPurchase_priceWithExtraCharge(0.15f)));
                System.out.print(" *** Rule of extra charge: Currently is a weekend day");
            }else{
                cleanProfitOfTheDay += unit.getPurchase_price()*0.1;
                System.out.print(" 10% *** ");
                System.out.printf("%7.6s",Float.toString(unit.getPurchase_priceWithExtraCharge(0.1f)));
                System.out.print(" *** Rule of extra charge: Default extra charge");
            }
            }
        }
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

        System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");
        System.out.println("Customers from the time interval from: " + CURRENT_TIME.toString() + " to " + CURRENT_TIME.plusHours(1).toString());
        System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////");

        for(int i = 0; i < number_of_customers_in_next_hour; i++){
            generateOrderForTheNextHour();
            executeOrder();
            System.out.println("------------------------------------------------------------------------------------------------");
        }

        CURRENT_TIME = CURRENT_TIME.plusHours(1);
    }

}
