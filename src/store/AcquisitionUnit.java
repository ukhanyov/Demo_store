package store;

public class AcquisitionUnit {

    private String name;
    private float purchase_price;
    private String classification;
    private float volume;
    private String  beverage_strength;
    private String composition;
    private int stock;

    public AcquisitionUnit(String name, float purchase_price, String classification, float volume, String beverage_strength, String composition, int stock) {
        super();
        this.name = name;
        this.purchase_price = purchase_price;
        this.classification = classification;
        this.volume = volume;
        this.beverage_strength = beverage_strength;
        this.composition = composition;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPurchase_price() {
        return purchase_price;
    }

    public void setPurchase_price(float purchase_price) {
        this.purchase_price = purchase_price;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public float getVolume() {
        return volume;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public String getBeverage_strength() {
        return beverage_strength;
    }

    public void setBeverage_strength(String beverage_strength) {
        this.beverage_strength = beverage_strength;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String toString(){
        return  "AU_name: " + this.name.toString() + " *** " +
                "AU_purchase_price: " + Float.toString(this.purchase_price) + " *** " +
                "AU_classification: " + this.classification + " *** " +
                "AU_volume: " + Float.toString(this.volume) + " *** " +
                "AU_beverage_strength: " + this.beverage_strength.toString() + " *** " +
                "AU_composition: " + this.composition.toString() + " *** " +
                "AU_stock: " + Integer.toString(this.stock);
    }
}
