public class BMIRecord {
    private double bmi;
    private String category;

    public BMIRecord(double bmi, String category) {
        this.bmi = bmi;
        this.category = category;
    }

    public double getBmi() {
        return bmi;
    }

    public String getCategory() {
        return category;
    }
}
