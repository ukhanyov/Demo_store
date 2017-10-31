package store;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static store.LogInfo.*;

public class Main {
    public static void main(String[] args)throws IOException{

        CurrentStock.readFromFile(LogInfo.LIST_OF_AVAILABLE_DRINKS); //Reading from the CSV file thus generating a list of all available drinks

        //TODO: Implement proper month generator

        WorkDay day1 = new WorkDay(false);
        WorkDay day2 = new WorkDay(false);
        WorkDay day3 = new WorkDay(false);
        WorkDay day4 = new WorkDay(false);
        WorkDay day5 = new WorkDay(false);
        WorkDay day6 = new WorkDay(true);
        WorkDay day7 = new WorkDay(true);

        WorkDay day11 = new WorkDay(false);
        WorkDay day12 = new WorkDay(false);
        WorkDay day13 = new WorkDay(false);
        WorkDay day14 = new WorkDay(false);
        WorkDay day15 = new WorkDay(false);
        WorkDay day16 = new WorkDay(true);
        WorkDay day17 = new WorkDay(true);

        WorkDay day111 = new WorkDay(false);
        WorkDay day112 = new WorkDay(false);
        WorkDay day113 = new WorkDay(false);
        WorkDay day114 = new WorkDay(false);
        WorkDay day115 = new WorkDay(false);
        WorkDay day116 = new WorkDay(true);
        WorkDay day117 = new WorkDay(true);

        WorkDay day1111 = new WorkDay(false);
        WorkDay day1112 = new WorkDay(false);
        WorkDay day1113 = new WorkDay(false);
        WorkDay day1114 = new WorkDay(false);
        WorkDay day1115 = new WorkDay(false);
        WorkDay day1116 = new WorkDay(true);
        WorkDay day1117 = new WorkDay(true);

        WorkDay day11111 = new WorkDay(false);
        WorkDay day11112 = new WorkDay(false);

        writeMonthlyReport();

        reWriteCSVDataBase();

    }

    public static void reWriteCSVDataBase() throws IOException{
        FileWriter fileWriter = new FileWriter("stock.csv");

        fileWriter.append("name,purchase_price,classification,volume,beverage_strength,composition,stock");
        fileWriter.append("\n");

        for (AcquisitionUnit unit : LIST_OF_AVAILABLE_DRINKS){
            fileWriter.append(unit.getName());
            fileWriter.append(",");
            fileWriter.append(String.valueOf(unit.getPurchase_price()));
            fileWriter.append(",");
            fileWriter.append(unit.getClassification());
            fileWriter.append(",");
            fileWriter.append(String.valueOf(unit.getVolume()));
            fileWriter.append(",");
            fileWriter.append(unit.getBeverage_strength());
            fileWriter.append(",");
            fileWriter.append(unit.getComposition());
            fileWriter.append(",");
            fileWriter.append(String.valueOf(unit.getStock()));
            fileWriter.append("\n");
        }

        fileWriter.flush();
        fileWriter.close();
    }

    public static void writeMonthlyReport() throws IOException{
        if(LIST_OF_ALL_SOLD_A_U.isEmpty()){
            LIST_OF_ALL_SOLD_A_U.add(AMOUNT_OF_SOLD_Вода_минеральная_Хорошо_vol_0p3);
            LIST_OF_ALL_SOLD_A_U.add(AMOUNT_OF_SOLD_Вода_минеральная_Хорошо_vol_1p5);
            LIST_OF_ALL_SOLD_A_U.add(AMOUNT_OF_SOLD_Пиво_Одесское_Новое);
            LIST_OF_ALL_SOLD_A_U.add(AMOUNT_OF_SOLD_Красная_испанка);
            LIST_OF_ALL_SOLD_A_U.add(AMOUNT_OF_SOLD_Сок_Богач_Грейпфрутовый);
            LIST_OF_ALL_SOLD_A_U.add(AMOUNT_OF_SOLD_Вода_Енерджи_бум_Плюс);
            LIST_OF_ALL_SOLD_A_U.add(AMOUNT_OF_SOLD_Мартини_Биссе);
            LIST_OF_ALL_SOLD_A_U.add(AMOUNT_OF_SOLD_Два_моря);
        }

        if(LIST_OF_ALL_RESTOCKED_A_U.isEmpty()){
            LIST_OF_ALL_RESTOCKED_A_U.add(AMOUNT_OF_RESTOCKED_Вода_минеральная_Хорошо_vol_0p3);
            LIST_OF_ALL_RESTOCKED_A_U.add(AMOUNT_OF_RESTOCKED_Вода_минеральная_Хорошо_vol_1p5);
            LIST_OF_ALL_RESTOCKED_A_U.add(AMOUNT_OF_RESTOCKED_Пиво_Одесское_Новое);
            LIST_OF_ALL_RESTOCKED_A_U.add(AMOUNT_OF_RESTOCKED_Красная_испанка);
            LIST_OF_ALL_RESTOCKED_A_U.add(AMOUNT_OF_RESTOCKED_Сок_Богач_Грейпфрутовый);
            LIST_OF_ALL_RESTOCKED_A_U.add(AMOUNT_OF_RESTOCKED_Вода_Енерджи_бум_Плюс);
            LIST_OF_ALL_RESTOCKED_A_U.add(AMOUNT_OF_RESTOCKED_Мартини_Биссе);
            LIST_OF_ALL_RESTOCKED_A_U.add(AMOUNT_OF_RESTOCKED_Два_моря);
        }

        MONTHLY_REPORT_LIST.add("Clean profit: " + Float.toString(CLEAN_PROFIT_FOR_30_DAYS));
        MONTHLY_REPORT_LIST.add("Money spent on restock: " + Float.toString(MONEY_SPEND_ON_RESTOCK));
        MONTHLY_REPORT_LIST.add("\n");
        int counter = 0;
        for (Integer unit : LIST_OF_ALL_SOLD_A_U){
            MONTHLY_REPORT_LIST.add("Amount of sold " + LIST_OF_AVAILABLE_DRINKS.get(counter).getName() + " is: " + Integer.toString(unit));
            counter++;
        }
        counter = 0;
        MONTHLY_REPORT_LIST.add("\n");
        for (Integer unit : LIST_OF_ALL_RESTOCKED_A_U){
            MONTHLY_REPORT_LIST.add("Amount of Restocked " + LIST_OF_AVAILABLE_DRINKS.get(counter).getName() + " is: " + Integer.toString(unit));
            counter++;
        }

        Path file = Paths.get("monthly_report.txt");
        Files.write(file, MONTHLY_REPORT_LIST, Charset.forName("UTF-8"));
    }
}
