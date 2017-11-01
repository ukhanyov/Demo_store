package store;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class LogInfo {
    public static LocalTime OPENING_TIME = LocalTime.of(8,00);
    public static LocalTime CLOSING_TIME = LocalTime.of(21,00);

    public static List<AcquisitionUnit> LIST_OF_AVAILABLE_DRINKS = new ArrayList<>();
    public static List<String> MONTHLY_REPORT_LIST = new ArrayList<>();
    public static List<Integer> LIST_OF_ALL_SOLD_A_U = new ArrayList<>();
    public static List<Integer> LIST_OF_ALL_RESTOCKED_A_U = new ArrayList<>();

    public static int AMOUNT_OF_SOLD_Вода_минеральная_Хорошо_vol_0p3 = 0;
    public static int AMOUNT_OF_SOLD_Вода_минеральная_Хорошо_vol_1p5 = 0;
    public static int AMOUNT_OF_SOLD_Пиво_Одесское_Новое = 0;
    public static int AMOUNT_OF_SOLD_Красная_испанка = 0;
    public static int AMOUNT_OF_SOLD_Сок_Богач_Грейпфрутовый = 0;
    public static int AMOUNT_OF_SOLD_Вода_Енерджи_бум_Плюс = 0;
    public static int AMOUNT_OF_SOLD_Мартини_Биссе = 0;
    public static int AMOUNT_OF_SOLD_Два_моря= 0;

    public static int AMOUNT_OF_RESTOCKED_Вода_минеральная_Хорошо_vol_0p3 = 0;
    public static int AMOUNT_OF_RESTOCKED_Вода_минеральная_Хорошо_vol_1p5 = 0;
    public static int AMOUNT_OF_RESTOCKED_Пиво_Одесское_Новое = 0;
    public static int AMOUNT_OF_RESTOCKED_Красная_испанка = 0;
    public static int AMOUNT_OF_RESTOCKED_Сок_Богач_Грейпфрутовый = 0;
    public static int AMOUNT_OF_RESTOCKED_Вода_Енерджи_бум_Плюс = 0;
    public static int AMOUNT_OF_RESTOCKED_Мартини_Биссе = 0;
    public static int AMOUNT_OF_RESTOCKED_Два_моря= 0;

    public static float CLEAN_PROFIT_FOR_30_DAYS = 0;
    public static float MONEY_SPEND_ON_RESTOCK = 0;

    public static int MAX_NUMBER_OF_DRINKS_PER_CUSTOMER = 10;
    public static int MAX_NUMBER_OF_CUSTOMERS = 10;

    public static String STR_LINE_SEPARATOR_BETWEEN_CUSTOMERS = "------------------------------------------------------------------------------------------------";
    public static String STR_LINE_SEPARATOR = "////////////////////////////////////////////////////////////////////////////////////////////////";
    public static String STR_CUSTOMER_CHOSE_NOTHING = "Customer chose nothing<-------------------------------------------------------------------------------------------------------------";
    public static String STR_END_OF_THE_DAY = "|||||||||||||||||||||||     End of the day     |||||||||||||||||||||||";
    public static String STR_END_OF_THE_WEEKEND_DAY = "|||||||||||||||||||||||     End of the weekend day     |||||||||||||||||||||||";
}
