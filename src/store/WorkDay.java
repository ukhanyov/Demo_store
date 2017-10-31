package store;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static store.LogInfo.*;

public class WorkDay {

    private static LocalTime TIME_DISCOUNT_START = LocalTime.of(18, 00);
    private static LocalTime TIME_DISCOUNT_END = LocalTime.of(20,00);

    private LocalTime CURRENT_TIME;

    private boolean isWeekend = false;

    private float cleanProfitOfTheDay = 0;
    private float spendOnRestockThisDay = 0;

    List<String> logOfTheDailyFile = new ArrayList<>();

    List<AcquisitionUnit> listOfOrderedDrinks = new ArrayList<>();

    public WorkDay(Boolean checkWeekend) throws IOException{

        this.isWeekend = checkWeekend;
        CURRENT_TIME = LocalTime.of(OPENING_TIME.getHour(), OPENING_TIME.getMinute());

        for(int i = 0; i < CLOSING_TIME.getHour() - OPENING_TIME.getHour(); i++){

            generateActionsForAnHour();

        }

        checkIfTheRefillOfTheStockIsNeeded();

        writeDailyReport();

        System.out.println(STR_END_OF_THE_DAY);
    }

    /*
     * Start of the buying operations
     */

    private void generateActionsForAnHour(){
        //Takes care of the actions going in the shop for the next hour

        int number_of_customers_in_next_hour = new Random().nextInt(MAX_NUMBER_OF_CUSTOMERS) + 1; //Generates a number of customer for the next hour

        System.out.println(STR_LINE_SEPARATOR);
        System.out.println("Customers from the time interval from: " + CURRENT_TIME.toString() + " to " + CURRENT_TIME.plusHours(1).toString());
        System.out.println(STR_LINE_SEPARATOR);

        for(int i = 0; i < number_of_customers_in_next_hour; i++){

            generateOrderForTheNextCustomer();
            executeOrder();
            System.out.println(STR_LINE_SEPARATOR_BETWEEN_CUSTOMERS);
        }

        CURRENT_TIME = CURRENT_TIME.plusHours(1); //Adds one hour to the current timeline
    }

    private void generateOrderForTheNextCustomer() {

        //Generates a list drinks, bought by a customer

        int number_of_a_u = new Random().nextInt(MAX_NUMBER_OF_DRINKS_PER_CUSTOMER + 1);

        if(number_of_a_u != 0){

            for (int i = 0; i < number_of_a_u; i++) {

                int chosen_drinks = new Random().nextInt(LIST_OF_AVAILABLE_DRINKS.size()); //Picks a random drink from the list of drinks

                if(LIST_OF_AVAILABLE_DRINKS.get(chosen_drinks).getStock() != 0) {

                    listOfOrderedDrinks.add(LIST_OF_AVAILABLE_DRINKS.get(chosen_drinks));

                }else {
                    i--;
                    if(checkIfTheStockIsEmpty()) break; //Check if there left any drinks in the store
                }
            }
        }
    }

    private Boolean checkIfTheStockIsEmpty(){

        int breaker = 0;
        for (AcquisitionUnit unit : LIST_OF_AVAILABLE_DRINKS){
            if(unit.getStock() == 0) breaker++;
        }
        if(breaker == LIST_OF_AVAILABLE_DRINKS.size()) return true;

        return false;
    }

    private void executeOrder(){

        if(listOfOrderedDrinks.isEmpty()){

            System.out.println(STR_CUSTOMER_CHOSE_NOTHING);

        }else {

            int counter = 1; //Amount of drinks in the list of a customer

            for(AcquisitionUnit unit : listOfOrderedDrinks){

                System.out.printf("%-30s", unit.getName());
                System.out.print(" *** ");
                System.out.printf("%-5s", Float.toString(unit.getPurchase_price()));
                System.out.print(" *** ");

                extraChargeCalculations(unit, counter); //Calculates amount of extra charge by set rules

                System.out.print("\n");

                reduceStock(unit); //Reduces stock of drinks by 1 (of selected by the client drink of course)
                counter++;

                counterOfSoldDrinks(unit); //Every time when drink is sold, it's increases the counter of overall amount of sold drinks of this name

            }

            listOfOrderedDrinks.clear();
        }
    }

    private void extraChargeCalculations(AcquisitionUnit unit, int counter){
        //Calculates amount of extra charge by set rule
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

        for (AcquisitionUnit unit_from_main_list : LIST_OF_AVAILABLE_DRINKS){

            if(unit.equals(unit_from_main_list)){

                unit_from_main_list.setStock(unit_from_main_list.getStock() - 1);
                break;
            }
        }
    }

    private void counterOfSoldDrinks (AcquisitionUnit unit){

        switch (unit.getName()){

            case "Вода_минеральная_Хорошо0p3":
                AMOUNT_OF_SOLD_Вода_минеральная_Хорошо_vol_0p3++;
                break;
            case "Вода_минеральная_Хорошо1p5":
                AMOUNT_OF_SOLD_Вода_минеральная_Хорошо_vol_1p5++;
                break;
            case "Пиво_Одесское_Новое":
                AMOUNT_OF_SOLD_Пиво_Одесское_Новое++;
                break;
            case "Красная_испанка":
                AMOUNT_OF_SOLD_Красная_испанка++;
                break;
            case "Сок_Богач_Грейпфрутовый":
                AMOUNT_OF_SOLD_Сок_Богач_Грейпфрутовый++;
                break;
            case "Енерджи_бум_Плюс":
                AMOUNT_OF_SOLD_Вода_Енерджи_бум_Плюс++;
                break;
            case "Мартини_Биссе":
                AMOUNT_OF_SOLD_Мартини_Биссе++;
                break;
            case "Два_моря":
                AMOUNT_OF_SOLD_Два_моря++;
                break;
        }
    }

    /*
     * End of the buying operations
     */

    private void writeDailyReport() throws IOException{
        System.out.println("Clean profit at the end of the day is: " + Float.toString(cleanProfitOfTheDay));

        logOfTheDailyFile.add("LOG INFO: Clean profit of the day: " + Float.toString(cleanProfitOfTheDay));
        logOfTheDailyFile.add("LOG INFO: Clean profit overall: " + Float.toString(CLEAN_PROFIT_FOR_30_DAYS));
        logOfTheDailyFile.add("Spend on restock this day: " + Float.toString(spendOnRestockThisDay));
        logOfTheDailyFile.add("LOG INFO: Money spend on restock: " + Float.toString(MONEY_SPEND_ON_RESTOCK));
        logOfTheDailyFile.add(STR_END_OF_THE_DAY);

        Path file = Paths.get("daily_report_inner_use_only.txt");
        Files.write(file, logOfTheDailyFile, Charset.forName("UTF-8"), StandardOpenOption.APPEND);
        logOfTheDailyFile.clear();
    }

    private void checkIfTheRefillOfTheStockIsNeeded() {

        for(AcquisitionUnit unit : LIST_OF_AVAILABLE_DRINKS){
            if(unit.getStock() < 10){
                unit.setStock(unit.getStock() + 150);

                spendOnRestockThisDay += unit.getPurchase_price()*150;

                System.out.println("Bought 150 unit of: " + unit.getName().toString() + " For the price of: " + unit.getPurchase_price());
                logOfTheDailyFile.add("Bought 150 unit of: " + unit.getName().toString() + " For the price of: " + unit.getPurchase_price());

                MONEY_SPEND_ON_RESTOCK += unit.getPurchase_price() * 150;

                switch (unit.getName()){
                    case "Вода_минеральная_Хорошо0p3":
                        AMOUNT_OF_RESTOCKED_Вода_минеральная_Хорошо_vol_0p3 += 150;
                        break;
                    case "Вода_минеральная_Хорошо1p5":
                        AMOUNT_OF_RESTOCKED_Вода_минеральная_Хорошо_vol_1p5 += 150;
                        break;
                    case "Пиво_Одесское_Новое":
                        AMOUNT_OF_RESTOCKED_Пиво_Одесское_Новое += 150;
                        break;
                    case "Красная_испанка":
                        AMOUNT_OF_RESTOCKED_Красная_испанка += 150;
                        break;
                    case "Сок_Богач_Грейпфрутовый":
                        AMOUNT_OF_RESTOCKED_Сок_Богач_Грейпфрутовый += 150;
                        break;
                    case "Енерджи_бум_Плюс":
                        AMOUNT_OF_RESTOCKED_Вода_Енерджи_бум_Плюс += 150;
                        break;
                    case "Мартини_Биссе":
                        AMOUNT_OF_RESTOCKED_Мартини_Биссе += 150;
                        break;
                    case "Два_моря":
                        AMOUNT_OF_RESTOCKED_Два_моря += 150;
                        break;
                }
            }
        }

        CLEAN_PROFIT_FOR_30_DAYS += cleanProfitOfTheDay;
    }
}
