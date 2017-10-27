package store;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args)throws IOException{

        //Reading from the file
        CurrentStock.readFromFile(LogInfo.LIST_OF_AVAILABLE_DRINKS);

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

    }
}
