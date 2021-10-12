//creating a Stock class which extends Product abstract class

public class Stock extends Product{
    private String ticker;
    private String exchange;
    private ProductPricingService productPricingService;

    public Stock(String ticker, String exchange, String id, ProductPricingService productPricingService) {
        super(id, exchange);
        this.ticker = ticker;
        this.exchange = exchange;
        this.productPricingService = productPricingService;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    @Override
    public double getPrice() {
        return this.productPricingService.price(this.exchange, this.ticker);
    }

    @Override
    public String toString() {
        return "Stock{" +
                "ticker='" + ticker + '\'' +
                ", exchange='" + exchange + '\'' +
                '}';
    }
}
