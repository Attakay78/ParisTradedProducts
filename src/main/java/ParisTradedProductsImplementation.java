import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Creating a class which implements an interface
public class ParisTradedProductsImplementation implements ParisTradedProducts {
    private final List<Product> productList = new ArrayList<>();
    private final Map<Product, Integer> mapProductToQuantity = new HashMap<>();

    //method to add new product to product list, method throws an exception if product already exist
    @Override
    public void addNewProduct(Product product) throws ProductAlreadyRegisteredException {
        if(productList.contains(product)){
            throw new ProductAlreadyRegisteredException("Product Already Exist");
        }else{
            productList.add(product);
        }
    }

    //method to trade a product, method throws an exception if product is not registered
    @Override
    public void trade(Product product, int quantity) throws ProductNotRegisteredException {
        if (productList.contains(product)) {
            mapProductToQuantity.put(product, quantity);
        }else{
            throw new ProductNotRegisteredException("Product is not registered to be traded");
        }
    }

    //method to return the total trade quantity for the day
    @Override
    public int totalTradeQuantityForDay() {
        int sum = 0; //sets initial value for sum = 0
        for(Product product : mapProductToQuantity.keySet()){
            sum += mapProductToQuantity.get(product); //adds product quantity to existing sum value
        }
        return sum; //returns total sum value
    }

    //method to return the total value of days traded products
    @Override
    public double totalValueOfDaysTradedProducts() {
        int sum = 0;
        for(Product product : mapProductToQuantity.keySet()){
            sum += mapProductToQuantity.get(product) *product.getPrice(); //adds product quantity*price to existing sum value
        }
        return sum;
    }
}
