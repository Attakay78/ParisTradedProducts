public interface ParisTradedProducts {

    void addNewProduct(Product product) throws ProductAlreadyRegisteredException;

    void trade(Product product, int quantity) throws ProductNotRegisteredException;

    int totalTradeQuantityForDay();

    double totalValueOfDaysTradedProducts();

}
