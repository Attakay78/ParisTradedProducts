//creating an Option class which extends Product abstract class

public class Option extends Product{
    private final String exchange;
    private String ticker;
    private int validForDays;
    private final ProductPricingService productPricingService;

    public Option(String exchange, String ticker, int validForDays, String id, ProductPricingService productPricingService) {
        super(id, exchange);
        this.exchange = exchange;
        this.ticker = ticker;
        this.validForDays = validForDays;
        this.productPricingService = productPricingService;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public int getValidForDays() {
        return validForDays;
    }

    public void setValidForDays(int validForDays) {
        this.validForDays = validForDays;
    }

    @Override
    public double getPrice() {
        return this.productPricingService.price(this.exchange, this.ticker, this.validForDays);
    }

    @Override
    public String toString() {
        return "Option{" +
                "exchange='" + exchange + '\'' +
                ", ticker='" + ticker + '\'' +
                ", validForDays=" + validForDays +
                '}';
    }

}
