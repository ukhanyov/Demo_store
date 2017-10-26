package store;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WorkDay {

    private static LocalTime TIME_OPENING = LocalTime.of(8, 00);
    private static LocalTime TIME_CLOSING = LocalTime.of(21,00);
    private static LocalTime CURRENT_TIME = LocalTime.of(8,00);

    List<AcquisitionUnit> listOfAllDrinks = new ArrayList<>();
    List<AcquisitionUnit> listOfOrderedDrinks = new ArrayList<>();

    public WorkDay() {
        CurrentStock.readFromFile(listOfAllDrinks);

        if(CURRENT_TIME.isBefore(TIME_CLOSING)){
            generateActionsForTheNextHour();
        }
    }

    private void generateOrder() {
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

        if(listOfOrderedDrinks.isEmpty()){
            System.out.println();
        }else {
            for(AcquisitionUnit unit : listOfOrderedDrinks){
                System.out.printf("%-20s", unit.getName());
                System.out.print(" *** ");
                System.out.printf("%-5s", Float.toString(unit.getPurchase_price()));
                System.out.print("\n");
            }
            listOfOrderedDrinks.clear();
        }
        System.out.println("------------------------------------------------");
    }

    private void generateActionsForTheNextHour(){
        int number_of_customers_in_next_hour = new Random().nextInt(10) + 1;
        LocalTime time = LocalTime.now();

        for(int i = 0; i < number_of_customers_in_next_hour; i++){
            generateOrder();
            executeOrder(time);
        }

        CURRENT_TIME.plusHours(1);
    }

    public void printStuff(){
        for(AcquisitionUnit unit : listOfAllDrinks){
            System.out.println(unit.toString());
        }
    }
}
