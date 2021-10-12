//creating a Future class which extends Product abstract class

public class Future extends Product{
    private final String exchange;
    private String contractCode;
    private int month;
    private int year;
    private final ProductPricingService productPricingService;

    //Class constructor
    public Future(String exchange, String contractCode, int month, int year, String id, ProductPricingService productPricingService) {
        super(id, exchange); //inherits id and exchange from parent Product class
        this.exchange = exchange;
        this.contractCode = contractCode;
        this.month = month;
        this.year = year;
        this.productPricingService = productPricingService;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

//    getPrice method overridden from Interface ProductPricingService
    @Override
    public double getPrice() {
        return this.productPricingService.price(this.exchange,this.contractCode,this.month,this.year);
    }

    @Override
    public String toString() {
        return "Future{" +
                "exchange='" + exchange + '\'' +
                ", contractCode='" + contractCode + '\'' +
                ", month=" + month +
                ", year=" + year +
                '}';
    }
}
