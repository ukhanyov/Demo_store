package store;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CurrentStock {

    private static final String COMMA_DELIMITER = ",";

    private static final int AU_NAME = 0;
    private static final int AU_PURCHASE_PRICE = 1;
    private static final int AU_CLASSIFICATION = 2;
    private static final int AU_VOLUME = 3;
    private static final int AU_BEVERAGE_STRENGTH = 4;
    private static final int AU_COMPOSITION = 5;
    private static final int AU_STOCK = 6;

    //static List<AcquisitionUnit> listOfAUs = new ArrayList<>();

    public static void readFromFile(List<AcquisitionUnit> listOfAUs){

        BufferedReader fileReader = null;

        try{

            String line = "";
            fileReader = new BufferedReader(new FileReader("stock.csv"));
            fileReader.readLine();

            while ((line = fileReader.readLine()) != null) {
                String[] tokens = line.split(COMMA_DELIMITER);
                if (tokens.length > 0) {
                    AcquisitionUnit AU = new AcquisitionUnit(
                                                             tokens[AU_NAME].trim(),
                                                             Float.parseFloat(tokens[AU_PURCHASE_PRICE]),
                                                             tokens[AU_CLASSIFICATION].trim(),
                                                             Float.parseFloat(tokens[AU_VOLUME]),
                                                             tokens[AU_BEVERAGE_STRENGTH].trim(),
                                                             tokens[AU_COMPOSITION].trim(),
                                                             Integer.parseInt(tokens[AU_STOCK]));
                    listOfAUs.add(AU);
                }
            }

        }catch (Exception e){
            System.out.println("Error in the CurrentStock");
            e.printStackTrace();
        }finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                System.out.println("Error while closing fileReader !!! in the CurrentStock !!!");
                e.printStackTrace();
            }
        }
    }
}
