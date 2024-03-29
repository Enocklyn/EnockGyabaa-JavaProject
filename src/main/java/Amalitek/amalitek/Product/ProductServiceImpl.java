package Amalitek.amalitek.Product;

import Amalitek.amalitek.Order.Order;
import Amalitek.amalitek.ProductLine.ProductLine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
private final ProductRepository Pr;
    @Override
    public Product CreateProduct(Product product) {
    try{
     return   Pr.save(product);
    }catch (Exception ex){
      return null;

    }

    }

    @Override
    public Product EditProduct(Product product) {
       try{
       if (FindProduct(product.getId()).isPresent()){
        return Pr.save(product);
       }else{
           return null;
       }
       }catch (Exception ex){
        return null;
       }
    }

    @Override
    public void DeleteProduct(int productId) {
         try{
              Pr.delete(new Product(productId));
         }catch (Exception ex){
           System.out.println(ex);

         }
    }

    @Override
    public Optional<Product> FindProduct(int ProductId) {
      return Pr.findById(ProductId);
    }

    @Override
    public List<Product> product(Order order) {
        return order.getProductLine().stream().map(ProductLine::getProduct).toList();
    }
}
